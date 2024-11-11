package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public interface IWallState {
    /**
     * Méthode appelée lorsqu'un mur subit une explosion.
     *
     * @param wall Le mur qui subit l'explosion.
     */
    void handleExplosion(Wall wall);

    /**
     * Renvoie le sprite à affiché pour cet état.
     *
     * @return Le sprite correspondant à cet état du mur.
     */
    Sprite getSprite();
}
