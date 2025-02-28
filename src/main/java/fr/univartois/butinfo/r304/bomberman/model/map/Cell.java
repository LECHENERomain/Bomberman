/**
 * Ce logiciel est distribué à des fins éducatives.
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 * (c) 2022-2024 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;
import fr.univartois.butinfo.r304.bomberman.model.bonusstrategy.AddBombStrategy;
import fr.univartois.butinfo.r304.bomberman.model.bonusstrategy.AddHpStrategy;
import fr.univartois.butinfo.r304.bomberman.model.bonusstrategy.InvincibilityStrategy;
import fr.univartois.butinfo.r304.bomberman.model.IMovable;
import fr.univartois.butinfo.r304.bomberman.model.movables.Bonus;
import fr.univartois.butinfo.r304.bomberman.model.explosion.DoubleExplosion;
import fr.univartois.butinfo.r304.bomberman.model.explosion.NormalExplosion;
import fr.univartois.butinfo.r304.bomberman.model.explosion.NukeExplosion;
import fr.univartois.butinfo.r304.bomberman.view.ISpriteStore;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.util.Random;

/**
 * La classe {@link Cell} représente une cellule de la carte du jeu du Bomberman.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class Cell {

    /**
     * La ligne où se trouve cette cellule sur la carte.
     */
    private int row;

    /**
     * La colonne où se trouve cette cellule sur la carte.
     */
    private int column;

    /**
     * La propriété contenant le sprite représentant le contenu de cette cellule sur la
     * carte.
     */
    private final ObjectProperty<Sprite> spriteProperty = new SimpleObjectProperty<>();

    /**
     * La propriété contenant le mur présent sur cette cellule sur la carte.
     */
    private final ObjectProperty<Wall> wallProperty = new SimpleObjectProperty<>();

    private final Random random=new Random();

    /**
     * Crée une nouvelle instance de Cell.
     * La cellule créée est initialement vide.
     *
     * @param row La ligne où se trouve la cellule sur la carte.
     * @param column La colonne où se trouve la cellule sur la carte.
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Crée une nouvelle instance de Cell.
     *
     * @param sprite La représentation du contenu initial de la cellule.
     */
    public Cell(Sprite sprite) {
        this.spriteProperty.set(sprite);
    }

    /**
     * Crée une nouvelle instance de cell.
     *
     * @param wall Le mur initialement présent sur la cellule.
     */
    public Cell(Wall wall) {
        this.wallProperty.set(wall);
        this.spriteProperty.set(wall.getSprite());
    }

    /**
     * Donne la ligne où se trouve cette cellule sur la carte.
     *
     * @return La ligne où se trouve cette cellule sur la carte.
     */
    public int getRow() {
        return row;
    }

    /**
     * Donne la colonne où se trouve cette cellule sur la carte.
     *
     * @return La colonne où se trouve cette cellule sur la carte.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Vérifie si cette cellule est vide.
     * Une cellule est vide si elle ne contient pas de mur.
     *
     * @return Si la cellule est vide.
     */
    public boolean isEmpty() {
        return wallProperty.get() == null;
    }

    /**
     * Donne le sprite représentant le contenu de cette cellule sur la carte.
     *
     * @return Le sprite représentant le contenu de cette cellule sur la carte.
     */
    public Sprite getSprite() {
        return spriteProperty.get();
    }

    /**
     * Donne la propriété contenant le sprite représentant le contenu de cette cellule sur
     * la carte.
     *
     * @return La propriété contenant le sprite.
     */
    public ObjectProperty<Sprite> getSpriteProperty() {
        return spriteProperty;
    }

    /**
     * Donne le mur présent sur cette cellule sur la carte.
     *
     * @return Le mur présent sur cette cellule sur la carte.
     */
    public Wall getWall() {
        return wallProperty.get();
    }

    /**
     * Remplace le contenu de cette cellule par celui d'une autre cellule.
     *
     * @param cell La cellule dont le contenu doit être copié dans cette cellule.
     */
    public void replaceBy(Cell cell) {
        spriteProperty.set(cell.getSprite());
        wallProperty.set(cell.getWall());
    }

    /**
     * Simule l'explosion de la cellule, mettant à jour le sprite si c'est un mur.
     */
    public void cellExplose(ISpriteStore spriteStore, BombermanGame game,double xPosition,double yPosition) {

        if (getWall() != null) {
            wallProperty.get().handleExplosion();
            if(wallProperty.get().isDestroyed()){
                int chance= random.nextInt(0,100);
                spawnBonus(spriteStore, game, xPosition, yPosition, chance);
                spriteProperty.set(spriteStore.getSprite("lawn"));
                wallProperty.set(null);



            }else {
                spriteProperty.set(getWall().getSprite());
            }
        }
    }

    private static void spawnBonus(ISpriteStore spriteStore, BombermanGame game,
            double xPosition, double yPosition, int chance) {

        if(chance <= 50){
            IMovable bonus = new Bonus(game, xPosition, yPosition, spriteStore.getSprite("horsebomb"), new AddBombStrategy(new NormalExplosion()));
            game.addMovable(bonus);
        }
        if(chance >= 51 && chance <= 60){
            IMovable bonus = new Bonus(game, xPosition, yPosition, spriteStore.getSprite("bombdouble"), new AddBombStrategy(new DoubleExplosion()));
            game.addMovable(bonus);
        }
        if(chance == 61){
            IMovable bonus= new Bonus(game, xPosition, yPosition, spriteStore.getSprite("nuclearbomb"), new AddBombStrategy(new NukeExplosion()));
            game.addMovable(bonus);
        }
        if(chance >= 62 && chance <= 80){
            IMovable bonus = new Bonus(game, xPosition, yPosition, spriteStore.getSprite("shield"), new InvincibilityStrategy());
            game.addMovable(bonus);
        }
        if(chance >= 81 && chance <= 100){
            IMovable bonus = new Bonus(game, xPosition, yPosition, spriteStore.getSprite("heart"), new AddHpStrategy());
            game.addMovable(bonus);
        }
    }

}
