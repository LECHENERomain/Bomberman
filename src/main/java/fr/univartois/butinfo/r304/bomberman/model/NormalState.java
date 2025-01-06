package fr.univartois.butinfo.r304.bomberman.model;

public class NormalState implements IPlayerState {

    @Override
    public void hit(Player player) {
        player.decreaseLives();
        player.setState(new InvisibleState(player)); // Transition to invisible state after being hit
    }
}
