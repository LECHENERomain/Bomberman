package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;

public class Bonus extends AbstractMovable{

    /**
     * La stratégie du bonus
     */
    IBonusStrategy bonusStrategy;

    /**
     * Constructeur du bonus
     * @param game L'instance du jeu
     * @param xPosition La position x du bonus
     * @param yPosition La position y du bonus
     * @param sprite Le sprite du bonus
     * @param bonusStrategy La stratégie du bonus
     */
    public Bonus(BombermanGame game, double xPosition, double yPosition, Sprite sprite, IBonusStrategy bonusStrategy) {
        super(game, xPosition, yPosition, sprite);
        this.bonusStrategy = bonusStrategy;
    }

    /**
     * Informe cet objet qu'il est entré en collision avec une autre instance de
     * {@link IMovable}.
     *
     * @param other L'objet avec lequel cet objet est entré en collision.
     */
    @Override
    public void collidedWith(IMovable other) {
        other.hitBonus(this, bonusStrategy);
    }

    /**
     * Fait exploser cet objet.
     */
    @Override
    public void explode() {
        //Nothing happens
    }

    /**
     * Informe cet objet qu'il a touché un ennemi.
     */
    @Override
    public void hitEnemy() {
        //Nothing happens
    }

    @Override
    public void hitBonus(IMovable bonusBombe, IBonusStrategy strategie ) {
        //Nothing happens
    }

}
