package fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator;

import fr.univartois.butinfo.r304.bomberman.model.map.*;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstates.DamagedWallState;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstates.DestroyedWallState;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

import java.util.Random;

public class MapDeux implements IMapGenerator {

    /**
     * Le spritestore
     */
    private final SpriteStore spriteStore;

    /**
     * Un générateur aléatoire
     */
    Random random = new Random();

    /**
     * Le constructeur de la seconde carte
     * @param spriteStore Le spritestore
     */
    public MapDeux(SpriteStore spriteStore) {
        this.spriteStore = spriteStore;
    }

    @Override
    public GameMap generateMap(int height, int width) {
        GameMap gameMap = new GameMap(height, width);

        // Précharger les sprites pour éviter de les récupérer plusieurs fois
        final Sprite wallSprite = spriteStore.getSprite("wall");
        final Sprite emptyBricksSprite = spriteStore.getSprite("empty-bricks");
        final Sprite crackedBricksSprite = spriteStore.getSprite("cracked-bricks");
        final Sprite bricksSprite = spriteStore.getSprite("bricks");
        final Sprite lawnSprite = spriteStore.getSprite("lawn");


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    // Placer des murs indestructible sur les bords
                    gameMap.setAt(i, j, new Cell(new Wall(wallSprite, spriteStore, true)));
                } else {
                    // Générer un nombre aléatoire pour déterminer le contenu de la cellule
                    int choice = random.nextInt(100);
                    if (choice < 20) {
                        // 20% de chance : brique normale
                        Wall wall = new Wall(bricksSprite, spriteStore, false);
                        gameMap.setAt(i, j, new Cell(wall));
                    }
                    else if (choice <27) {
                        // 7% de chance : brique partiellement cassée
                        Wall wall = new Wall(bricksSprite, spriteStore, false);
                        wall.setState(new DamagedWallState(crackedBricksSprite, SpriteStore.getInstance()));
                        gameMap.setAt(i, j, new Cell(wall));
                    }
                    else if (choice <30) {
                        // 3% de chance : brique quasiment cassée
                        Wall wall = new Wall(bricksSprite, spriteStore, false);
                        wall.setState(new DestroyedWallState(emptyBricksSprite));
                        gameMap.setAt(i, j, new Cell(wall));
                    }
                    else {
                        // 70% de chance : pelouse
                        gameMap.setAt(i, j, new Cell(lawnSprite));
                    }
                }
            }
        }
        return gameMap;
    }

}
