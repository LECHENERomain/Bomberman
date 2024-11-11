package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class DamagedWallState implements IWallState {
    private final Sprite sprite;
    SpriteStore spriteStore;

    public DamagedWallState(Sprite sprite, SpriteStore spriteStore) {
        this.sprite = sprite;
        this.spriteStore = spriteStore;
    }

    @Override
    public void handleExplosion(Wall wall) {
        // Passe à l'état "Destroyed" après la seconde explosion.
        wall.setState(new DestroyedWallState(spriteStore.getSprite("empty-bricks")));
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}