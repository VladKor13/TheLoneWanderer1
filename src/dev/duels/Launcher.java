package dev.duels;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("The Lone Wanderer", 512, 640);
        game.start();
    }

}
