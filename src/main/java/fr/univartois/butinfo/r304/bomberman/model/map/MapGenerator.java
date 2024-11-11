package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

/**
 * La classe MapGenerator permet de générer une carte de jeu pour Bomberman.
 */
public class MapGenerator implements IMapGenerator {

    private SpriteStore spriteStore;
    private IMapGenerator mapGenerator;
    /**
     * Constructeur du générateur de carte.
     *
     * @param spriteStore Le store de sprites à utiliser pour la carte.
     */
    public MapGenerator(SpriteStore spriteStore, IMapGenerator mapGenerator) {
        this.spriteStore = spriteStore;
        this.mapGenerator = mapGenerator;
    }

    /**
     * Génère une carte de jeu de la taille donnée.
     *
     * @param height La hauteur (en nombre de cellules) de la carte.
     * @param width La largeur (en nombre de cellules) de la carte.
     *
     * @return La carte générée.
     */
    @Override
    public GameMap generateMap(int height, int width) {
        return mapGenerator.generateMap(height, width);
    }

}
