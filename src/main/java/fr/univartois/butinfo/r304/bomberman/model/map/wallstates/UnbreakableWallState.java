package fr.univartois.butinfo.r304.bomberman.model.map.wallstates;

import fr.univartois.butinfo.r304.bomberman.model.map.IWallState;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public class UnbreakableWallState implements IWallState {

    /**
     * Le sprite du mur
     */
    Sprite sprite;

    /**
     * Constructeur du mur dans son état incassable
     * @param sprite Le sprite du mur
     */
    public UnbreakableWallState(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void handleExplosion(Wall wall) {
        // Ne rien faire
    }

    /**
     * Accesseur du sprite du mur
     * @return Le sprite du mur
     */
    @Override
    public Sprite getSprite() {
        return sprite;
    }


}
