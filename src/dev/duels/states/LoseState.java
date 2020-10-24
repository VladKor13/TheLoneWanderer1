package dev.duels.states;

import dev.duels.Game;
import dev.duels.Handler;
import dev.duels.UI.ClickListener;
import dev.duels.UI.UIImageButton;
import dev.duels.UI.UIManager;
import dev.duels.gfx.Assets;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class LoseState extends State{

    private UIManager uiManager;

    public LoseState(final Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);


        //BACK BUTTON
        uiManager.addObject(new UIImageButton(160, 556, 192, 64, Assets.back_button, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);//WHEN YOU CHANGE GAME STATE
                Game.mainMenuState = new MainMenuState(handler);
                State.setState(Game.mainMenuState);
            }
        }));


    }

    @Override
    public void tick() {

        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.YouDiedBackGround,0,0, null);

        uiManager.render(g);
    }
}
