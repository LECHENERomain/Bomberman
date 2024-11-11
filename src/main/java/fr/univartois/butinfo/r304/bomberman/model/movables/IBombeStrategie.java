package fr.univartois.butinfo.r304.bomberman.model.movables;

import fr.univartois.butinfo.r304.bomberman.model.BombermanGame;

interface IBombeStrategie {
    void explode(BombermanGame game, double xPosition, double yPosition);
}
