package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;


public abstract class MovableDecorator implements IMovable {


    /**
     * Le movable décoré
     */
    protected final IMovable decoratedMovable;

    /**
     * Constructeur de l'objet décoré
     * @param decoratedMovable L'objet décoré
     */
    protected MovableDecorator(IMovable decoratedMovable) {
        this.decoratedMovable = decoratedMovable;
    }

    @Override
    public int getWidth() {
        return decoratedMovable.getWidth();
    }

    @Override
    public int getHeight() {
        return decoratedMovable.getHeight();
    }

    @Override
    public void setX(int xPosition) {
        decoratedMovable.setX(xPosition);
    }

    @Override
    public int getX() {
        return decoratedMovable.getX();
    }

    @Override
    public DoubleProperty getXProperty() {
        return decoratedMovable.getXProperty();
    }

    @Override
    public void setY(int yPosition) {
        decoratedMovable.setY(yPosition);
    }

    @Override
    public int getY() {
        return decoratedMovable.getY();
    }

    @Override
    public DoubleProperty getYProperty() {
        return decoratedMovable.getYProperty();
    }

    @Override
    public void consume() {
        decoratedMovable.consume();
    }

    @Override
    public boolean isConsumed() {
        return decoratedMovable.isConsumed();
    }

    @Override
    public BooleanProperty isConsumedProperty() {
        return decoratedMovable.isConsumedProperty();
    }

    @Override
    public void setHorizontalSpeed(double speed) {
        decoratedMovable.setHorizontalSpeed(speed);
    }

    @Override
    public double getHorizontalSpeed() {
        return decoratedMovable.getHorizontalSpeed();
    }

    @Override
    public void setVerticalSpeed(double speed) {
        decoratedMovable.setVerticalSpeed(speed);
    }

    @Override
    public double getVerticalSpeed() {
        return decoratedMovable.getVerticalSpeed();
    }

    @Override
    public void setSprite(Sprite sprite) {
        decoratedMovable.setSprite(sprite);
    }

    @Override
    public Sprite getSprite() {
        return decoratedMovable.getSprite();
    }

    @Override
    public ObjectProperty<Sprite> getSpriteProperty() {
        return decoratedMovable.getSpriteProperty();
    }

    @Override
    public boolean move(long timeDelta) {
        return decoratedMovable.move(timeDelta);
    }

    @Override
    public boolean isCollidingWith(IMovable other) {
        return decoratedMovable.isCollidingWith(other);
    }

    @Override
    public void collidedWith(IMovable other) {
        decoratedMovable.collidedWith(other);
    }

    @Override
    public void explode() {
        decoratedMovable.explode();
    }

    @Override
    public void hitEnemy() {
        decoratedMovable.hitEnemy();
    }

    @Override
    public IMovable self() {
        return decoratedMovable.self();
    }

    @Override
    public int hashCode() {
        return decoratedMovable.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return decoratedMovable.equals(obj);
    }

    /**
     * Méthode permettant de faire prendre des dégâts à un movable
     * @param damage La quantité de dégâts que le movable prend
     */
    public abstract void takeDamage(int damage);

    /**
     * Méthode permettant de soigner un movable
     * @param healthPoints La quantité de PV rendue
     */
    public abstract void heal(int healthPoints);

    /**
     * Accesseur de la quantité de PV du movable
     * @return Le nombre de PV
     */
    public abstract int getHealthPoints();

}
