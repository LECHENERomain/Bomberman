package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.model.movables.enemy.IEnemyStrategy;

public interface ILevelBuilder {
    /**
     * Accesseur de la map
     * @return La map du niveau
     */
    IMapGenerator getMap();

    /**
     * Accesseur du nombre d'ennemis sur le niveau
     * @return Le nombre d'ennemis sur le niveau
     */
    int getNumberOfEnnemy();

    /**
     * Accesseur de la stratégie des ennemis
     * @return La stratégie des ennemis
     */
    IEnemyStrategy getEnemyStrategy();

    /**
     * Accesseur du niveau suivant
     * @return Le niveau suivant
     */
    ILevelBuilder getNextLevelBuilder();
}
