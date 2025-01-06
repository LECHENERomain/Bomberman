package fr.univartois.butinfo.r304.bomberman.model.explosion;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.bombs.IBombeStrategie;
import fr.univartois.butinfo.r304.bomberman.model.movables.Explosion;

public class NormalExplosion implements IBombeStrategie {


    @Override
    public void explode(BombermanGame game, double xPosition, double yPosition) {
        String explosionString = "explosion";
        game.addMovable(new Explosion(game, xPosition, yPosition, game.getSpriteStore().getSprite(explosionString), System.currentTimeMillis()));
        game.addMovable(new Explosion(game, xPosition, yPosition + game.getSpriteStore().getSpriteSize(), game.getSpriteStore().getSprite(explosionString), System.currentTimeMillis())); // en haut
        game.addMovable(new Explosion(game, xPosition, yPosition - game.getSpriteStore().getSpriteSize(), game.getSpriteStore().getSprite(explosionString),System.currentTimeMillis())); // en bas
        game.addMovable(new Explosion(game, xPosition + game.getSpriteStore().getSpriteSize(), yPosition, game.getSpriteStore().getSprite(explosionString), System.currentTimeMillis())); // à droite
        game.addMovable(new Explosion(game, xPosition - game.getSpriteStore().getSpriteSize(), yPosition, game.getSpriteStore().getSprite(explosionString), System.currentTimeMillis())); // à gauche
    }
}
