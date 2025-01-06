package fr.univartois.butinfo.r304.bomberman.model.map.wallstates;

import fr.univartois.butinfo.r304.bomberman.model.map.IWallState;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public class DestroyedWallState implements IWallState {

    /**
     * Sprite du mur
     */
    private final Sprite sprite;

    /**
     * Constructeur du mur dans son état détruit
     * @param sprite Le sprite du mur
     */
    public DestroyedWallState(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void handleExplosion(Wall wall) {
        wall.setDestroyed(true);
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
