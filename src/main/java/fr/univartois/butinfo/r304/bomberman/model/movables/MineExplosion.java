package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.Player;

public class MineExplosion implements IBombeStrategie {
     private boolean estEnclenche=false;

    @Override
    public void explode(BombermanGame game, double xPosition, double yPosition) {
        if (estEnclenche) {
            game.addMovable(new Explosion(game, xPosition + game.getSpriteStore().getSpriteSize(), yPosition, game.getSpriteStore().getSprite("explosion"), 5000));
        }
        else{
            game.addMovable(new Explosion(game, xPosition, yPosition, game.getSpriteStore().getSprite("explosion"), System.currentTimeMillis()));

        }
    }
}
