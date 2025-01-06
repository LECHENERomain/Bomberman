package fr.univartois.butinfo.r304.bomberman.model.bombs;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;

public interface IBombeStrategie {

    /**
     * Métohde permettant de faire exploser la bombe
     * @param game L'instance du jeu
     * @param xPosition La position x de la bombe
     * @param yPosition La position y de la bombe
     */
    void explode(BombermanGame game, double xPosition, double yPosition);
}
