package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public class UnbreakableWallState implements IWallState {
    Sprite sprite;

    public UnbreakableWallState(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void handleExplosion(Wall wall) {
        // Ne rien faire
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }


}
