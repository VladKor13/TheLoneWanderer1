package dev.duels;


import dev.duels.display.Display;
import dev.duels.entities.creatures.Human;
import dev.duels.gfx.Assets;
import dev.duels.input.MouseManager;
import dev.duels.states.*;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;



//MAIN CLASS OF OUR GAME
public class Game  implements Runnable{

    private Display display;
    private int width, height;
    private String title;

    private boolean running = false;
    private Thread thread;


    //STATES
    public static State mainMenuState;
    public static State heroCreatorState;
    public static State myTurn;
    public static State winState;
    public static State loseState;
    public static State battleLog;

    //INPUT
    private MouseManager mouseManager;


    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;

        mouseManager = new MouseManager();
    }

    //WRITE START STUFF HERE !!!!!!!!!!!
    private void init(){

        display = new Display(title, width, height);

        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Assets.init();

        //Handler
        Handler handler = new Handler(this);

        mainMenuState = new MainMenuState(handler);
        State.setState(mainMenuState);

    }

    private void tick(){
        if( State.getState() != null){
            State.getState().tick();
        }
    }

    private void render(){
        BufferStrategy bs = display.getCanvas().getBufferStrategy();
        if( bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        // CLEAR SCREEN HERE !!!!!!!!!!!!
        g.clearRect(0, 0, width, height);

        // DRAW HERE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if( State.getState() != null){
            State.getState().render(g);
        }
        //END DRAWING HERE !!!!!!!!!!!!!!!!!!!!!!!
        bs.show();
        g.dispose();
    }

    public void run(){
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while (running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;

            lastTime = now;

            if(delta >= 1) {
                tick();
                render();

                delta--;
            }
        }

        stop();
    }

    synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running) return;
        running = false;
        display.getFrame().dispatchEvent(new WindowEvent(display.getFrame(), WindowEvent.WINDOW_CLOSING));
        if(thread.isInterrupted())
            thread.interrupt();


    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    MouseManager getMouseManager(){
        return mouseManager;
    }

}
