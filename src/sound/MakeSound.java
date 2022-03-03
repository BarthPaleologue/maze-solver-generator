package sound;
import settings.SoundTypes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class MakeSound implements Runnable {

    private File soundFile;
    private AudioInputStream audioStream;
    private SourceDataLine sourceLine;

    private final String soundFileName;

    private static ArrayList<Thread> soundThreads = new ArrayList<>();
    public static boolean isSilent = false;

    /**
     * Creates a new sound player in the current thread that will only play the specified soundType
     * @param soundType the type of sound you would like to hear (see settings.SoundTypes.java for details on available sounds)
     */
    public MakeSound(int soundType) {
        soundFileName = SoundTypes.getSoundFileFromSoundType(soundType);
    }

    /**
     * Play the sound that has been set at the creation of the object in the current thread (not using the play() method will result in playing the sound in the main thread, blocking the execution of the application)
     */
    public void playSound() {
        try {
            soundFile = new File(soundFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e){
            e.printStackTrace();
        }

        AudioFormat audioFormat = audioStream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sourceLine.start();

        int nBytesRead = 0;
        final int BUFFER_SIZE = 128000;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = audioStream.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                sourceLine.write(abData, 0, nBytesRead);
            }
        }

        sourceLine.drain();
        sourceLine.close();
    }

    @Override
    public void run() {
        playSound();
    }

    /**
     * If the app is not silent, creates a new thread playing the required sound type
     * @param soundType the type of sound you want to here (see settings.SoundTypes.java for details on available sounds)
     */
    public static void play(int soundType) {
        if(isSilent) return;
        Thread soundThread = new Thread(new MakeSound(soundType));
        soundThreads.add(soundThread);
        soundThread.start();
    }

    /**
     * Mutes all current sounds by killing their threads
     */
    public static void killAllPlaying() {
        for(Thread soundThread: soundThreads) {
            soundThread.stop();
        }
        soundThreads = new ArrayList<>();
    }

    /**
     * Toggles the application between silent and not silent mode, making it silent will kill all current playing sounds
     */
    public static void toggleSilence() {
        isSilent = !isSilent;
        if(isSilent) killAllPlaying();
    }

    /**
     * Kills all current playing sounds and unmutes the application
     */
    public static void reset() {
        killAllPlaying();
        isSilent = false;
    }
}