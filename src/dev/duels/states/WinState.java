package dev.duels.states;

import dev.duels.Game;
import dev.duels.Handler;
import dev.duels.UI.ClickListener;
import dev.duels.UI.UIImageButton;
import dev.duels.UI.UIManager;
import dev.duels.entities.creatures.Human;
import dev.duels.entities.creatures.HumanDataSaver;
import dev.duels.gfx.Assets;
import dev.duels.gfx.ImageLoader;

import java.awt.*;


public class WinState extends State{

    private UIManager uiManager;

    private int SkillPtsLeft = 1, StrengthPts = 0, AgilityPts = 0, FortunePts = 0;
    private UIImageButton DoneButton;
    //RESTORING MY CHARACTER DATA
    private Human MyCharacter = HumanDataSaver.RestoreHumanData("MyCharacter.ser");;

    public WinState(final Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        //STRENGTH PLUS BUTTON
        uiManager.addObject(new UIImageButton(240, 207, 45, 49, Assets.plus_button, new ClickListener() {
            @Override
            public void onClick() {

                if(SkillPtsLeft > 0){
                    SkillPtsLeft --;
                    StrengthPts ++;
                }
            }
        }));

        //AGILITY PLUS BUTTON
        uiManager.addObject(new UIImageButton(240, 300, 45, 49, Assets.plus_button, new ClickListener() {
            @Override
            public void onClick() {
                if(SkillPtsLeft > 0){
                    SkillPtsLeft --;
                    AgilityPts ++;
                }
            }
        }));

        //FORTUNE PLUS BUTTON
        uiManager.addObject(new UIImageButton(240, 393, 45, 49, Assets.plus_button, new ClickListener() {
            @Override
            public void onClick() {
                if(SkillPtsLeft > 0){
                    SkillPtsLeft --;
                    FortunePts ++;
                }
            }
        }));

        //STRENGTH MINUS BUTTON
        uiManager.addObject(new UIImageButton(305, 207, 45, 49, Assets.minus_button, new ClickListener() {
            @Override
            public void onClick() {
                if(StrengthPts > 0){
                    SkillPtsLeft ++;
                    StrengthPts --;
                }
            }
        }));

        //AGILITY MINUS BUTTON
        uiManager.addObject(new UIImageButton(305, 300, 45, 49, Assets.minus_button, new ClickListener() {
            @Override
            public void onClick() {
                if(AgilityPts > 0){
                    SkillPtsLeft ++;
                    AgilityPts --;
                }
            }
        }));

        //FORTUNE MINUS BUTTON
        uiManager.addObject(new UIImageButton(305, 393, 45, 49, Assets.minus_button, new ClickListener() {
            @Override
            public void onClick() {
                if(FortunePts > 0){
                    SkillPtsLeft ++;
                    FortunePts --;
                }
            }
        }));

        //DONE BUTTON
        DoneButton = new UIImageButton(285, 530, 192, 64, Assets.done_button, new ClickListener() {
            @Override
            public void onClick() {
                System.out.println(MyCharacter);
                MyCharacter.getHBS().setAgility(MyCharacter.getHBS().getAgility() + AgilityPts);
                MyCharacter.getHBS().setStrength(MyCharacter.getHBS().getStrength() + StrengthPts);
                MyCharacter.getHBS().setFortune(MyCharacter.getHBS().getFortune() + FortunePts);
                System.out.println("Ag" + MyCharacter.getHBS().getAgility());
                System.out.println("Str" + MyCharacter.getHBS().getStrength());
                System.out.println("For" + MyCharacter.getHBS().getFortune());
                //SERIALIZATION MY CHARACTER DATA
                HumanDataSaver.SerializeHumanData(MyCharacter,"MyCharacter.ser");


                handler.getMouseManager().setUiManager(null);//WHEN YOU CHANGE GAME STATE
                Game.myTurn = new MyTurn(handler);
                State.setState(Game.myTurn);
            }
        });
        uiManager.addObject(DoneButton);


    }

    @Override
    public void tick() {

        if(SkillPtsLeft > 0){
            DoneButton.setEnable(false);
            Assets.done_button[0] = ImageLoader.loadImage("/textures/DoneButtonPressedUnable.png");
        } else {
            DoneButton.setEnable(true);
            Assets.done_button[0] = ImageLoader.loadImage("/textures/DoneButton.png");
        }

        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.MenuBackGround,0,0, null);

        g.drawImage(Assets.SkillPtsLeftPanel, 35, 100, null);
        g.drawImage(Assets.StrengthPanel, 35, 207, null);
        g.drawImage(Assets.AgilityPanel, 35, 300, null);
        g.drawImage(Assets.FortunePanel, 35, 393, null);

        g.drawImage(Assets.NumPanel[SkillPtsLeft], 415, 113, null);
        g.drawImage(Assets.NumPanel[StrengthPts], 365, 212, null);
        g.drawImage(Assets.NumPanel[AgilityPts], 365, 305, null);
        g.drawImage(Assets.NumPanel[FortunePts], 365, 398, null);
        uiManager.render(g);
    }
}
