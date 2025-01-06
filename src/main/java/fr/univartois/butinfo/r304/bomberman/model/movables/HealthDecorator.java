package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;

public class HealthDecorator extends MovableDecorator {

    /**
     * La quantité de PV
     */
    private int healthPoints;

    /**
     * Constructeur de l'objet avec des points de vie
     * @param decoratedMovable Le movable décoré
     * @param initialHealthPoints Les points de vie au moment de l'apparition du movable
     */
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

    public void heal(int healPoints) {
        healthPoints += healPoints;
    }

    @Override
    public void explode() {
        healthPoints = 0;
        decoratedMovable.explode();
    }

    @Override
    public void hitBonus(IMovable bonusBombe, IBonusStrategy strategie ) {
        //Nothing happens
    }
}
