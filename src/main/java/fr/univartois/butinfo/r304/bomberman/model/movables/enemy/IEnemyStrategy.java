package fr.univartois.butinfo.r304.bomberman.model.movables.enemy;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;

public interface IEnemyStrategy {

    /**
     * Méthode permettant à l'ennemi de se déplacer
     * @param delta Le temps écoulé depuis le dernier déplacement
     * @param game L'instance du jeu
     * @param enemy L'ennemi en question
     * @param b Le booléen indiquant si l'ennemi est contre un mur
     * @return Un booléen indiquant si l'ennemi peut se déplacer
     */
    boolean move(long delta, BombermanGame game, Enemy enemy, boolean b);
}
