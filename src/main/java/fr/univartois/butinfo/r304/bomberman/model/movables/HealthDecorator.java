package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;

public class HealthDecorator extends MovableDecorator {

    private int healthPoints;

    public HealthDecorator(IMovable decoratedMovable, int initialHealthPoints) {
        super(decoratedMovable);
        this.healthPoints = initialHealthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void takeDamage(int damage) {
        healthPoints -= damage;
    }

    @Override
    public void explode() {
        healthPoints = 0;
        decoratedMovable.explode();
    }
}
