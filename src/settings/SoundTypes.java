package settings;

/**
 * Sound settings used in the sound pipeline, you can add a few if you feel like it
 * Limitation : you can only play .wav audio files
 */
public class SoundTypes {
    public static final int OH_SOUND = 0;
    public static final int USSR_ANTHEM = 1;
    public static final int POKE_SOUND = 2;
    public static final int REMOVE_SOUND = 3;
    public static final int UKRAINIAN_ANTHEM = 4;

    /**
     * Returns the path to the required sound file
     * @param soundType the type of sound you want the file of (see SoundTypes.java for accepted values)
     * @return the path to the file you crave
     */
    public static String getSoundFileFromSoundType(int soundType) {
        switch (soundType) {
            case OH_SOUND:
                return Path.SOUND_DIR + "oh.wav";
            case USSR_ANTHEM:
                return Path.SOUND_DIR + "ussr.wav";
            case POKE_SOUND:
                return Path.SOUND_DIR + "poke.wav";
            case REMOVE_SOUND:
                return Path.SOUND_DIR + "remove.wav";
            case UKRAINIAN_ANTHEM:
                return Path.SOUND_DIR + "ukraine.wav";
            default:
                return Path.SOUND_DIR + "error.wav";
        }
    }
}
