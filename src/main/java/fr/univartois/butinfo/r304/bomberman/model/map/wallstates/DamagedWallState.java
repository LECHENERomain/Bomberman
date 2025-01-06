package fr.univartois.butinfo.r304.bomberman.model.map.wallstates;

import fr.univartois.butinfo.r304.bomberman.model.map.IWallState;
import fr.univartois.butinfo.r304.bomberman.model.map.Wall;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class DamagedWallState implements IWallState {

    /**
     * Le sprite du mur
     */
    private final Sprite sprite;

    /**
     * Le spritestore
     */
    SpriteStore spriteStore;

    /**
     * Constructeur du mur dans son état endommagé
     * @param sprite Le sprite du mur
     * @param spriteStore Le spritestore
     */
    public DamagedWallState(Sprite sprite, SpriteStore spriteStore) {
        this.sprite = sprite;
        this.spriteStore = spriteStore;
    }

    @Override
    public void handleExplosion(Wall wall) {
        // Passe à l'état "Destroyed" après la seconde explosion.
        wall.setState(new DestroyedWallState(spriteStore.getSprite("empty-bricks")));
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
