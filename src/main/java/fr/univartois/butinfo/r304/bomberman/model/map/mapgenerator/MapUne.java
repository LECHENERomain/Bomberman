package fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator;

import fr.univartois.butinfo.r304.bomberman.model.map.Cell;
import fr.univartois.butinfo.r304.bomberman.model.map.GameMap;
import fr.univartois.butinfo.r304.bomberman.model.map.IMapGenerator;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class MapUne implements IMapGenerator {

    /**
     * Le spritestore
     */
    private final SpriteStore spriteStore;

    /**
     * Le constructeur de la première carte
     * @param spriteStore Le spritestore
     */
    public MapUne(SpriteStore spriteStore) {
        this.spriteStore = spriteStore;
    }

    @Override
    public GameMap generateMap(int height, int width) {
        // Créer une instance de GameMap avec les dimensions fournies.
        GameMap gameMap = new GameMap(height, width);

        // Remplir la carte avec des murs et de la pelouse.
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Placer des murs sur les bords
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    // Créer un sprite pour le mur
                    Sprite wallSprite = spriteStore.getSprite("bricks"); // Assurez-vous d'avoir ce sprite
                    Wall wall = new Wall(wallSprite, spriteStore, true);
                    gameMap.setAt(i, j, new Cell(wall));
                } else {
                    // Remplir l'intérieur avec de la pelouse
                    Sprite lawnSprite = spriteStore.getSprite("lawn"); // Assurez-vous d'avoir ce sprite
                    gameMap.setAt(i, j, new Cell(lawnSprite));
                }
            }
        }
        // Retourner la carte générée.
        return gameMap;
    }
}
