/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2022-2024 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.bomberman.model;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import fr.univartois.butinfo.r304.bomberman.model.map.*;
import fr.univartois.butinfo.r304.bomberman.model.movables.*;
import fr.univartois.butinfo.r304.bomberman.view.ISpriteStore;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;
import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;

/**
 * La classe {@link BombermanGame} gère une partie du jeu Bomberman.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class BombermanGame {

    Random rand = new Random();

    /**
     * Le générateur de nombres aléatoires utilisé dans le jeu.
     */
    public static final Random RANDOM = new Random();

    /**
     * La vitesse de déplacement du joueur (en pixels/s).
     */
    public static final int DEFAULT_SPEED = 75;

    /**
     * Le nombre de bombes initialement disponibles pour le joueur.
     */
    public static final int DEFAULT_BOMBS = 5;

    /**
     * La largeur de la carte du jeu (en pixels).
     */
    private final int width;

    /**
     * La hauteur de la carte du jeu (en pixels).
     */
    private final int height;

    /**
     * L'instance de {@link ISpriteStore} permettant de créer les {@link Sprite} du jeu.
     */
    private final ISpriteStore spriteStore;

    /**
     * La carte du jeu.
     */
    private GameMap gameMap;

    /**
     * Le personnage du joueur.
     */
    private Player player;

    /**
     * Le nombre d'ennemis initialement dans le jeu.
     */
    private int nbEnemies;

    /**
     * Le nombre d'ennemis restant dans le jeu.
     */
    private int remainingEnemies;

    /**
     * La liste des objets pouvant se déplacer dans le jeu.
     */
    private final List<IMovable> movableObjects = new CopyOnWriteArrayList<>();

    /**
     * L'animation du jeu, qui s'assure que les différents objets se déplacent.
     */
    private final AnimationTimer animation = new BombermanAnimation(movableObjects);

    /**
     * Le contrôleur du jeu.
     */
    private IBombermanController controller;

    /**
     * Crée une nouvelle instance de BombermanGame.
     *
     * @param gameWidth La largeur de la carte du jeu.
     * @param gameHeight La hauteur de la carte du jeu.
     * @param spriteStore L'instance de {@link ISpriteStore} permettant de créer les
     *        {@link Sprite} du jeu.
     * @param nbEnemies Le nombre d'ennemis dans le jeu.
     */
    private IMapGenerator mapGenerator;

    public void setMapGenerator(IMapGenerator mapGenerator) {
        this.mapGenerator = mapGenerator;
    }

    public BombermanGame(int gameWidth, int gameHeight, ISpriteStore spriteStore, int nbEnemies) {
        this.width = gameWidth;
        this.height = gameHeight;
        this.spriteStore = spriteStore;
        this.nbEnemies = nbEnemies;
    }

    /**
     * Modifie le contrôleur avec lequel interagir pour mettre à jour l'affichage.
     *
     * @param controller Le contrôleur avec lequel interagir.
     */
    public void setController(IBombermanController controller) {
        this.controller = controller;
    }

    /**
     * Donne l'instance de {@link ISpriteStore} permettant de créer les {@link Sprite} du jeu..
     *
     * @return L'instance de {@link ISpriteStore} permettant de créer les {@link Sprite} du jeu..
     */
    public ISpriteStore getSpriteStore() {
        return spriteStore;
    }

    /**
     * Donne la largeur de la carte du jeu (en pixels).
     *
     * @return La largeur de la carte du jeu.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Donne la hauteur de la carte du jeu (en pixels).
     *
     * @return La hauteur de la carte du jeu.
     */
    public int getHeight() {
        return height;
    }

    public Player getPlayer(){ return player;}

    /**
     * Prépare une partie de Bomberman avant qu'elle ne démarre.
     */
    public void prepare() {
        gameMap = createMap();
        controller.prepare(gameMap);
    }

    /**
     * Crée la carte du jeu, en respectant les dimensions de la fenêtre.
     *
     * @return La carte du jeu ayant été créée.
     */
    public GameMap createMap() {
        // Récupérer la taille des sprites pour les cellules
        int cellHeight = spriteStore.getSprite("wall").getHeight();
        int cellWidth = spriteStore.getSprite("wall").getWidth();

        // Convertir la hauteur et la largeur en nombre de cellules
        int numCellsHeight = height / cellHeight;
        int numCellsWidth = width / cellWidth;

        // Créer une instance du générateur de carte
        MapGenerator mapGenerator = new MapGenerator((SpriteStore) spriteStore, new MapDeux((SpriteStore)spriteStore));

        // Générer la carte
        return mapGenerator.generateMap(numCellsHeight, numCellsWidth);
    }

    /**
     * Démarre la partie de Bomberman.
     */
    public void start() {
        createMovables();
        initStatistics();
        animation.start();
    }

    /**
     * Crée les différents objets présents au début de la partie et pouvant se déplacer.
     */
    private void createMovables() {
        // On commence par enlever tous les éléments mobiles encore présents.
        clearAllMovables();

        player = new Player(this, 0, 0, spriteStore.getSprite("guy"));
        spawnMovable(player);

        // On ajoute les bombes initiales du joueur.
        for (int i = 0; i < DEFAULT_BOMBS; i++) {
            player.addBomb(new Bombe(this,player.getX(), player.getY(), spriteStore.getSprite("bomb")));
        }

        // On crée ensuite les ennemis sur la carte.
        for (int i = 0; i < nbEnemies; i++) {
            int choix = rand.nextInt(0, 2);
            IEnemyStrategy strategy;
            if (choix == 0) {
                strategy = new StraightLineStrategy();
            }
            else if(choix == 1) {
                strategy = new RandomMovesStrategy();
            }
            else{
                strategy = new MirrorPlayerMovementsStrategy();
            }
            IMovable enemy = new Enemy(this, i, i, spriteStore.getSprite("goblin"), strategy);
            enemy.setHorizontalSpeed(DEFAULT_SPEED);
            enemy.setVerticalSpeed(DEFAULT_SPEED);
            spawnMovable(enemy);
        }
    }

    /**
     * Initialise les statistiques de cette partie.
     */
    private void initStatistics() {
        // Bind the player's lives, score, and bombs to the controller's UI elements
        controller.bindLife(player.livesProperty());
        controller.bindScore(player.scoreProperty());

        // Create a property to observe the number of bombs
        IntegerProperty bombCount = new SimpleIntegerProperty(player.getBombes().size());

        // Add a ListChangeListener to update bombCount when the number of bombs changes
        player.getBombes().addListener((ListChangeListener<Bombe>) change -> {
            bombCount.set(player.getBombes().size());
        });

        // Bind the bomb count property to the controller
        controller.bindBombs(bombCount);

        // Set the number of remaining enemies
        remainingEnemies = nbEnemies;
    }



    /**
     * Fait apparaître un objet pouvant se déplacer sur la carte du jeu.
     *
     * @param movable L'objet à faire apparaître.
     */
    private void spawnMovable(IMovable movable) {
        List<Cell> spawnableCells = gameMap.getEmptyCells();
        if (!spawnableCells.isEmpty()) {
            Cell cell = spawnableCells.get(RANDOM.nextInt(spawnableCells.size()));
            movable.setX(cell.getColumn() * spriteStore.getSpriteSize());
            movable.setY(cell.getRow() * spriteStore.getSpriteSize());
            addMovable(movable);
        }
    }

    /**
     * Déplace le personnage du joueur vers le haut.
     */
    public void moveUp() {
        stopMoving();
        player.setVerticalSpeed(-DEFAULT_SPEED);
    }

    /**
     * Déplace le personnage du joueur vers la droite.
     */
    public void moveRight() {
        stopMoving();
        player.setHorizontalSpeed(DEFAULT_SPEED);
    }

    /**
     * Déplace le personnage du joueur vers le bas.
     */
    public void moveDown() {
        stopMoving();
        player.setVerticalSpeed(DEFAULT_SPEED);
    }

    /**
     * Déplace le personnage du joueur vers la gauche.
     */
    public void moveLeft() {
        stopMoving();
        player.setHorizontalSpeed(-DEFAULT_SPEED);
    }

    /**
     * Arrête le déplacement du joueur.
     */
    public void stopMoving() {
        player.setVerticalSpeed(0);
        player.setHorizontalSpeed(0);
    }

    /**
     * Dépose une bombe sur la tuile où se trouve le joueur, et programme l'explosion de
     * cette bombe.
     */
    /**
     * Dépose une bombe sur la tuile où se trouve le joueur et l'ajoute aux objets mobiles du jeu.
     */
    public void dropBomb() {
        if (!player.getBombes().isEmpty()) {
            Bombe bomb = player.getBombes().remove(0);
            dropBomb(bomb);
        }


    }


    /**
     * Dépose une bombe sur la tuile où se trouve le joueur, et programme l'explosion de
     * cette bombe.
     *
     * @param bomb La bombe à déposer.
     */
    /**
     * Dépose une bombe sur la tuile où se trouve le joueur et l'ajoute aux objets mobiles du jeu.
     *
     * @param movable L'objet mobile à déposer, qui doit être une instance de Bomb.
     */
    public void dropBomb(IMovable movable) {
        // Vérifier que l'objet est bien une instance de Bomb
        if (movable instanceof Bombe) {
            Bombe bombe = (Bombe) movable; // Convertir en type Bomb

            // Obtenir la cellule actuelle du joueur
            Cell playerCell = getCellOf(player);

            // Placer la bombe aux coordonnées de la cellule du joueur
            bombe.setX(playerCell.getColumn() * spriteStore.getSpriteSize());
            bombe.setY(playerCell.getRow() * spriteStore.getSpriteSize());

            bombe.setTimeDroppedBombe(System.currentTimeMillis());

            // Ajouter la bombe à la liste des objets mobiles du jeu
            addMovable(bombe);
        } else {
            System.out.println("isn't a bomb");
        }
    }


    /**
     * Récupére la cellule correspondant à la position d'un objet mobile.
     * Il s'agit de la cellule sur laquelle l'objet en question occupe le plus de place.
     *
     * @param movable L'objet mobile dont la cellule doit être récupérée.
     *
     * @return La cellule occupée par l'objet mobile.
     */
    private Cell getCellOf(IMovable movable) {
        // On commence par récupérer la position du centre de l'objet.
        int midX = movable.getX() + (movable.getWidth() / 2);
        int midY = movable.getY() + (movable.getHeight() / 2);
        return getCellAt(midX, midY);
    }

    /**
     * Donne la cellule à la position donnée sur la carte.
     *
     * @param x La position en x de la cellule.
     * @param y La position en y de la cellule.
     *
     * @return La cellule à la position donnée.
     */
    public Cell getCellAt(int x, int y) {
        // On traduit cette position en position dans la carte.
        int row = y / spriteStore.getSpriteSize();
        int column = x / spriteStore.getSpriteSize();

        // On récupère enfin la cellule à cette position dans la carte.
        return gameMap.getAt(row, column);
    }

    /**
     * Ajoute un objet pouvant se déplacer dans le jeu.
     *
     * @param object L'objet à ajouter.
     */
    public void addMovable(IMovable object) {
        movableObjects.add(object);
        controller.addMovable(object);
    }

    /**
     * Supprime un objet pouvant se déplacer dans le jeu.
     *
     * @param object L'objet à supprimer.
     */
    public void removeMovable(IMovable object) {
        movableObjects.remove(object);
        object.consume();
    }

    /**
     * Supprime tous les objets pouvant se déplacer dans le jeu.
     */
    private void clearAllMovables() {
        for (IMovable movable : movableObjects) {
            movable.consume();
        }
        movableObjects.clear();
    }

    /**
     * Met à jour le score du joueur lorsqu'un ennemi est tué.
     * Si c'était le dernier, le joueur gagne la partie.
     *
     * @param enemy L'ennemi qui a été tué.
     */
    public void enemyIsDead(IMovable enemy) {
        player.increaseScore();
        remainingEnemies--;
        removeMovable(enemy);

        if (remainingEnemies == 0) {
            // Tous les aliens ont été tués : la partie est terminée.
            gameOver("YOU WIN!");
        }
    }

    /**
     * Termine la partie lorsque le joueur est tué.
     */
    public void playerIsDead() {
        gameOver("YOU HAVE BEEN KILLED!");
    }

    /**
     * Termine la partie en cours.
     *
     * @param message Le message indiquant le résultat de la partie.
     */
    private void gameOver(String message) {
        animation.stop();
        controller.gameOver(message);
    }

    public GameMap getGameMap() {
        return gameMap;
    }

}
