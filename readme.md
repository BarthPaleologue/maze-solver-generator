# Solveur de Labyrinthe Graphique
par Barthélemy Paléologue

![Overview](./data/imgs/overview.png)

## Installation

Pour installer le solveur sur votre machine, entrez cette commande dans votre terminal favori :

```bash
git clone git@gitlab.enst.fr:2021INF103/groupe2/paleologue-barthelemy.git
```

Vous pouvez ensuite ouvrir le dossier avec Eclipse ou IntelliJ IDEA.

Le point d'entrée du programme est ```./src/Main```

## Utilisation

Au lancement du programme, il vous sera demandé d'entrer les dimensions d'un labyrinthe vide qui vous sera présenté. Le programme a été conçu pour supporter des tailles allant jusqu'à 60x60.

L'interface est divisée en deux parties : la barre de menu et la grille du labyrinthe.

### Barre de menu

La barre de menu vous offre différentes options. Tout d'abord le sous menu ```Maze File``` qui permet de créer un nouveau labyrinthe vide, d'ouvrir un fichier labyrinthe compatible ou de sauvegarder le labyrinthe à l'écran dans un fichier sur l'ordinateur.

Reste les boutons ```Set Departure``` et ```Set Arrival``` qui permettent de définir le point de départ et le point d'arrivée du labyrinthe. Pour ce faire clickez sur un des boutons, puis faites clic gauche sur une des cases du labyrinthe pour changer son statut.

### Labyrinthe

Comme vu précédemment, la grille détecte les clicks de la souris. Un clic gauche sur une case (sans avoir clické au préalable sur un des boutons du menu) transforme la case en case de mur.

Un clic droit tranforme la case en case vide.

Le calcul du chemin le plus court est automatique du moment qu'il existe un point de départ et un point d'arrivée. Celui-ci s'illumine en vert.

Si il n'y a pas de chemin vert affiché, c'est qu'il manque un point de départ ou un point d'arrivé ou alors qu'il n'y a pas de chemin possible entre le point de départ et le point d'arrivée.

## Charger un fichier

Le projet vient livré avec de nombreux fichiers de labyrinthe se trouvant dans ```./data``` que vous pouvez facilement ouvrir à l'aide du menu ```Maze File```.

Vous pouvez également charger les labyrinthes que vous avez créé avec l'interface graphique en utilisant la même procédure.

Il est cependant possible de créer vos propres fichiers de labyrinthes sans passer par le mode édition de l'interface graphique, pour cela il faut respécter la norme suivante.

Le solveur peut lire des fichiers de la forme :

```
EDEEEEEEEE
EWWWWWWEWW
EWEEEEEEEE
EWWWWEWWWW
EWEEEEWEEE
EWEEWWWEEE
EWEEWEWEAE
EWEWWEWEEE
EWEEWEEEEE
EEWEEEEEEE
```
C'est-à-dire qu'il ne contient que les labels du labyrinthe avec la correspondance suivante :

E : Case vide (Empty)

D : Case de Départ

A : Case d'Arrivée

W : Case de mur (Wall)

Notez que cette nomenclature est modifiable à volonté dans le fichier ```./src/maze/Labels.java```