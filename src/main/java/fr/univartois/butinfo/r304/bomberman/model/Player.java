package fr.univartois.butinfo.r304.bomberman.model;

import fr.univartois.butinfo.r304.bomberman.model.movables.AbstractMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.Bombe;
import fr.univartois.butinfo.r304.bomberman.model.movables.HealthDecorator;
import fr.univartois.butinfo.r304.bomberman.model.movables.MovableDecorator;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Player extends AbstractMovable {

    private final IntegerProperty lives;
    private final IntegerProperty score;
    final int MAXLIVES = 3;
    private final ObservableList<Bombe> bombes;
    private int maxbombes = 3;
    private final MovableDecorator healthDecorator;

    private iPlayerState state;

    protected Player(BombermanGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        lives = new SimpleIntegerProperty(MAXLIVES);
        score = new SimpleIntegerProperty(0);
        bombes = FXCollections.observableArrayList();
        state = new NormalState();
        healthDecorator = new HealthDecorator(this,MAXLIVES);
    }

    public int getMaxbombes() {
        return maxbombes;
    }

    public void setMaxbombes(int maxbombes) {
        this.maxbombes = maxbombes;
    }

    public ObservableList<Bombe> getBombes() {
        return bombes;
    }

    public void addBomb(Bombe bombe) {
        if (bombes.size() >= maxbombes) {
            return;
        }
        bombes.add(bombe);
    }

    public IntegerProperty livesProperty() {
        return lives;
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public int getLives() {
        return lives.get();
    }

    public int getScore() {
        return score.get();
    }

    public void decreaseLives() {
        if (healthDecorator.getHealthPoints() == 1) {
            game.playerIsDead();
        }
        healthDecorator.takeDamage(1);
        lives.set(healthDecorator.getHealthPoints());
    }

    public void increaseScore() {
        score.set(score.get() + 1);
    }

    /*
     * Handle the hit depending on the current state
     */
    public void hit() {
        state.hit(this);
    }

    /*
     * Set the current state of the player
     */
    public void setState(iPlayerState state) {
        this.state = state;
    }

    @Override
    public void collidedWith(IMovable other) {

    }

    @Override
    public void explode() {
        hit();
    }

    @Override
    public void hitEnemy() {
        hit();
    }
}

