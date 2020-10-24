package dev.duels.states;

import dev.duels.Game;
import dev.duels.Handler;
import dev.duels.UI.ClickListener;
import dev.duels.UI.UIImageButton;
import dev.duels.UI.UIManager;
import dev.duels.entities.creatures.Human;
import dev.duels.entities.creatures.HumanDataSaver;
import dev.duels.gfx.Assets;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class MainMenuState extends State{

    private UIManager uiManager;
    private UIImageButton continue_Button;

    public MainMenuState(final Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        //RESTORING MY CHARACTER DATA
        Handler.setMyCharacter(HumanDataSaver.RestoreHumanData("MyCharacter.ser"));
        //RESTORING ENEMY CHARACTER DATA
        Handler.setEnemyCharacter(HumanDataSaver.RestoreHumanData("EnemyCharacter.ser"));


        //START BUTTON
        uiManager.addObject(new UIImageButton(192, 100, 128, 64, Assets.start_button, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);//WHEN YOU CHANGE GAME STATE
                Game.heroCreatorState = new HeroCreator(handler);
                State.setState(Game.heroCreatorState);
            }
        }));

        //CONTINUE BUTTON
        continue_Button = new UIImageButton(192, 200, 128, 64, Assets.continue_button, new ClickListener() {
            @Override
            public void onClick() {
                //MARK ENTERING MY TURN STATE FROM THE CONTINUE BUTTON
                MyTurn.LOAD_DATA = true;

                handler.getMouseManager().setUiManager(null);//WHEN YOU CHANGE GAME STATE
                Game.myTurn = new MyTurn(handler);
                State.setState(Game.myTurn);
            }
        });
        uiManager.addObject(continue_Button);

        //OPTIONS BUTTON
        uiManager.addObject(new UIImageButton(192, 300, 128, 64, Assets.options_button, new ClickListener() {
            @Override
            public void onClick() {

            }
        }));

        //EXIT BUTTON
        uiManager.addObject(new UIImageButton(192, 400, 128, 64, Assets.exit_button, new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().stop();
            }
        }));

    }

    @Override
    public void tick() {
        if(Handler.getMyCharacter() == null || Handler.getEnemyCharacter() == null){
            continue_Button.setEnable(false);
        } else {
            continue_Button.setEnable(true);
        }
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.MenuBackGround,0,0, null);
        uiManager.render(g);
    }
}
