package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

public class IntactWallState implements IWallState {
    private final Sprite sprite;
    SpriteStore spriteStore;


    public IntactWallState(Sprite sprite, SpriteStore spriteStore) {
        this.sprite = sprite;
        this.spriteStore = spriteStore;
    }

    @Override
    public void handleExplosion(Wall wall) {
        // Passe à l'état "Damaged" après la première explosion.
        wall.setState(new DamagedWallState(spriteStore.getSprite("cracked-bricks"), spriteStore));
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
