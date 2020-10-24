package dev.duels;

import dev.duels.entities.creatures.Human;
import dev.duels.input.MouseManager;

public class Handler {

    private static Human EnemyCharacter, MyCharacter;

    private Game game;

    public Handler(Game game){
        this.game = game;
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public static Human getEnemyCharacter() {
        return EnemyCharacter;
    }

    public static void setEnemyCharacter(Human enemyCharacter) {
        EnemyCharacter = enemyCharacter;
    }

    public static Human getMyCharacter() {
        return MyCharacter;
    }

    public static void setMyCharacter(Human myCharacter) {
        MyCharacter = myCharacter;
    }
}
