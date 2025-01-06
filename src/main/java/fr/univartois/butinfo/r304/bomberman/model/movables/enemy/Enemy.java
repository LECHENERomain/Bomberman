package fr.univartois.butinfo.r304.bomberman.model.movables.enemy;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.HealthDecorator;
import fr.univartois.butinfo.r304.bomberman.model.movables.IBonusStrategy;
import fr.univartois.butinfo.r304.bomberman.model.movables.MovableDecorator;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Enemy extends AbstractMovable {

    MovableDecorator healthDecorator;
    private final IEnemyStrategy strategy;

    private final BooleanProperty isInvincible = new SimpleBooleanProperty(false);
    private static final int INVINCIBILITY_DURATION_MS = 5000; // Adjust as needed

    /**
     * Creates a new AbstractMovable instance.
     *
     * @param game      The game in which the object is contained.
     * @param xPosition The initial x position of the object.
     * @param yPosition The initial y position of the object.
     * @param sprite    The {@link Sprite} instance representing the object.
     */
    public Enemy(BombermanGame game, double xPosition, double yPosition, Sprite sprite, IEnemyStrategy strategy) {
        super(game, xPosition, yPosition, sprite);
        this.strategy = strategy;
        healthDecorator = new HealthDecorator(this, 2);
    }

    /**
     * Call an ambulance, but not for me.
     * It's not the enemy colliding with an object, but the object colliding with the enemy.
     *
     * @param other The object who collided with the enemy.
     */
    @Override
    public void collidedWith(IMovable other) {
        other.hitEnemy();
    }

    @Override
    public void explode() {
        if (!isInvincible.get()) {
            healthDecorator.takeDamage(1);
            if (healthDecorator.getHealthPoints() <= 0) {
                destroy();
            } else {
                triggerInvincibility();
            }
        }
    }

    /**
     * Méthode permettant aux ennemis de devenir invincibles (pour ne pas mourir instantanément dans une explosion)
     */
    private void triggerInvincibility() {
        isInvincible.set(true);

        // Set a timer for the invincibility duration
        PauseTransition invincibilityTimer = new PauseTransition(Duration.millis(INVINCIBILITY_DURATION_MS));
        invincibilityTimer.setOnFinished(event -> isInvincible.set(false));
        invincibilityTimer.play();
    }

    public void destroy() {
        game.enemyIsDead(this);
        this.game.removeMovable(this);
    }

    @Override
    public void hitEnemy() {
        // Nothing happens when two enemies collide
    }

    @Override
    public void hitBonus(IMovable bonusBombe, IBonusStrategy strategie) {
        //Nothing happens
    }

    @Override
    public boolean move(long delta) {
        boolean b = super.move(delta);
        return strategy.move(delta, this.game, this, b);
    }

}
