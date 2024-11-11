package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class DestroyedWallState implements IWallState {
    private final Sprite sprite;

    public DestroyedWallState(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void handleExplosion(Wall wall) {
        wall.setDestroyed(true);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}