package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

import java.util.Random;

public class MapDeux implements IMapGenerator {

    private SpriteStore spriteStore;

    public MapDeux(SpriteStore spriteStore) {
        this.spriteStore = spriteStore;
    }

    @Override
    public GameMap generateMap(int height, int width) {
        GameMap gameMap = new GameMap(height, width);
        Random random = new Random();

        // Précharger les sprites pour éviter de les récupérer plusieurs fois
        Sprite wallSprite = spriteStore.getSprite("wall");
        Sprite emptyBricksSprite = spriteStore.getSprite("empty-bricks");
        Sprite crackedBricksSprite = spriteStore.getSprite("cracked-bricks");
        Sprite bricksSprite = spriteStore.getSprite("bricks");
        Sprite lawnSprite = spriteStore.getSprite("lawn");


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    // Placer des murs indestructible sur les bords
                    gameMap.setAt(i, j, new Cell(new Wall(wallSprite, spriteStore, true)));
                } else {
                    // Générer un nombre aléatoire pour déterminer le contenu de la cellule
                    int choice = random.nextInt(100);
                    if (choice < 30) {
                        // 30% de chance : brique
                        gameMap.setAt(i, j, new Cell(new Wall(bricksSprite, spriteStore, false)));
                    } else {
                        // 70% de chance : pelouse
                        gameMap.setAt(i, j, new Cell(lawnSprite));
                    }
                }
            }
        }


        return gameMap;
    }

}
