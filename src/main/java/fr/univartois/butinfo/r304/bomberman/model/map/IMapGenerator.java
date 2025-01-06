package fr.univartois.butinfo.r304.bomberman.model.map;

public interface IMapGenerator {

    /**
     * Méthode permettant de générer la carte du niveau
     * @param height La hauteur de la carte
     * @param width La largeur de la carte
     * @return La carte générée
     */
    GameMap generateMap(int height, int width);
}
