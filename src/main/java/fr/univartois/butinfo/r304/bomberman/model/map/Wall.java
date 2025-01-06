/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2022-2024 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.bomberman.model.map;

import fr.univartois.butinfo.r304.bomberman.model.map.wallstates.IntactWallState;
import fr.univartois.butinfo.r304.bomberman.model.map.wallstates.UnbreakableWallState;
import fr.univartois.butinfo.r304.bomberman.view.Sprite;
import fr.univartois.butinfo.r304.bomberman.view.SpriteStore;

/**
 * La classe {@link Wall} représente un mur de briques sur la carte du jeu.
 * Il s'agit d'un élément qui ne peut pas être traversé, mais qui peut être détruit par
 * une explosion.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class Wall {
    private IWallState state;
    private boolean isDestroyed = false;

    public Wall(Sprite sprite, SpriteStore spriteStore, boolean unbreakable) {
        if (unbreakable) {
            this.state = new UnbreakableWallState(sprite);
        } else {
            this.state = new IntactWallState(sprite, spriteStore);
        }
    }

    /**
     * Définit un nouvel état pour le mur.
     *
     * @param state Le nouvel état du mur.
     */
    public void setState(IWallState state) {
        this.state = state;
    }

    /**
     * Gère l'explosion touchant ce mur, dégradant son état.
     */
    public void handleExplosion() {
        state.handleExplosion(this);
    }

    /**
     * Donne le sprite représentant ce mur en fonction de son état actuel.
     *
     * @return Le sprite représentant ce mur.
     */
    public Sprite getSprite() {
        return state.getSprite();
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

    public void setDestroyed(boolean isDestroyed){
        this.isDestroyed = isDestroyed;
    }
}
