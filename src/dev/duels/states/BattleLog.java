package dev.duels.states;

import dev.duels.Game;
import dev.duels.Handler;
import dev.duels.UI.UIImageButton;
import dev.duels.UI.UIManager;
import dev.duels.display.Display;
import dev.duels.entities.creatures.Human;
import dev.duels.entities.creatures.HumanDataSaver;
import dev.duels.gfx.Assets;

import java.awt.*;


public class BattleLog extends State{

    private UIManager uiManager;

    static int ATK_Level = 4 , ATK_part_of_body = 4 , ATK_type = 0;
    static boolean ATK_Pressed = false, Block_Pressed = false, Evade_Pressed = false;
    private int[] ATK_PART_OF_BODY, enATK_PART_OF_BODY;
    private Human myCharacter, enemyCharacter;
    private String Action = " ", Status = " ", Effect = " ", enAction = " ", enStatus = " ", enEffect = " ",
            Description = " ", enDescription = " ", Result = " ", enResult = " ";


    BattleLog(final Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        myCharacter = HumanDataSaver.RestoreHumanData("MyCharacter.ser");
        enemyCharacter = HumanDataSaver.RestoreHumanData("EnemyCharacter.ser");

        //BATTLE CALCULATIONS
        int EnemyAction = (int) (Math.random() * 3); //[0,2]
        //MY ACTION
        if(ATK_Pressed){
            switch (EnemyAction){
                case 0:{
                    EnemyCharacterATKCalculator();
                    break;
                }
                case 1:{
                    EnemyCharacterBlockCalculator();
                    break;
                }
                case 2:{
                    EnemyCharacterEvadeCalculator();
                }
            }
            MyCharacterATKCalculator();

        } else if(Block_Pressed){
            MyCharacterBlockCalculator();
            //ENEMY ACTION
            switch (EnemyAction){
                case 0:{
                    EnemyCharacterATKCalculator();
                    break;
                }
                case 1:{
                    EnemyCharacterBlockCalculator();
                    break;
                }
                case 2:{
                    EnemyCharacterEvadeCalculator();
                }
            }

        } else if(Evade_Pressed){
            MyCharacterEvadeCalculator();
            //ENEMY ACTION
            switch (EnemyAction){
                case 0:{
                    EnemyCharacterATKCalculator();
                    break;
                }
                case 1:{
                    EnemyCharacterBlockCalculator();
                    break;
                }
                case 2:{
                    EnemyCharacterEvadeCalculator();
                }
            }
        }

        //DONE BUTTON
        UIImageButton doneButton = new UIImageButton(160, 546, 192, 64, Assets.done_button, () -> {

            ATK_Pressed = false;
            Block_Pressed = false;
            Evade_Pressed = false;

            //CHANGING THE STATE WHEN MY HP <= 0
            if(myCharacter.getHBS().getHP() <= 0) {

                Handler.setMyCharacter(null);
                Handler.setEnemyCharacter(null);

                //SERIALIZATION MY CHARACTER DATA
                HumanDataSaver.SerializeHumanData(null, "MyCharacter.ser");
                //SERIALIZATION ENEMY CHARACTER DATA
                HumanDataSaver.SerializeHumanData(null, "EnemyCharacter.ser");

                handler.getMouseManager().setUiManager(null);//WHEN YOU CHANGE GAME STATE
                Game.loseState = new LoseState(handler);
                State.setState(Game.loseState);
            } else
                //CHANGING THE STATE WHEN ENEMY HP <= 0
                if(enemyCharacter.getHBS().getHP() <= 0) {

                    myCharacter.setQuantity_Of_Killed_Enemies(myCharacter.getQuantity_Of_Killed_Enemies() + 1);

                    Handler.setEnemyCharacter(null);
                    //SERIALIZATION MY CHARACTER DATA
                    HumanDataSaver.SerializeHumanData(myCharacter, "MyCharacter.ser");
                    //SERIALIZATION ENEMY CHARACTER DATA
                    HumanDataSaver.SerializeHumanData(null, "EnemyCharacter.ser");

                    handler.getMouseManager().setUiManager(null);//WHEN YOU CHANGE GAME STATE
                    Game.winState = new WinState(handler);
                    State.setState(Game.winState);
                }else {
                    MyTurn.LOAD_DATA = true;

                    //SERIALIZATION MY CHARACTER DATA
                    HumanDataSaver.SerializeHumanData(myCharacter, "MyCharacter.ser");
                    //SERIALIZATION ENEMY CHARACTER DATA
                    HumanDataSaver.SerializeHumanData(enemyCharacter, "EnemyCharacter.ser");

                    handler.getMouseManager().setUiManager(null);//WHEN YOU CHANGE GAME STATE
                    Game.myTurn = new MyTurn(handler);
                    State.setState(Game.myTurn);
                }
        });
        uiManager.addObject(doneButton);
    }

    @Override
    public void tick() {
        enemyCharacter.tick();
        myCharacter.tick();
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.RainBackGround,0,0, null);

        FontMetrics fm;
        int MyCharStatsX = 20, EnCharStatsX = 266;
        int MyCharStatsY = 30, EnCharStatsY = 30;

        //DRAWING BATTLE LOG DATA
        g.setFont(Display.myFont2);
        fm = g.getFontMetrics();
        //MY CHAR NAME
        g.setColor(Display.Blue_Col);
        g.drawString(myCharacter.getName(),
                20 + (226 - fm.stringWidth(myCharacter.getName()))/2,
                MyCharStatsY);

        MyCharStatsY += 40;
        g.setFont(Display.myFont1);
        g.drawString("Action:",MyCharStatsX, MyCharStatsY);

        MyCharStatsY += 40;
        g.drawString(Action,MyCharStatsX, MyCharStatsY);

        MyCharStatsY += 40;
        g.drawString("Status:",MyCharStatsX, MyCharStatsY);

        MyCharStatsY += 40;
        g.drawString(Status,MyCharStatsX, MyCharStatsY);

        MyCharStatsY += 40;
        g.drawString("Description:",MyCharStatsX, MyCharStatsY);

        MyCharStatsY += 40;
        g.drawString(Description,MyCharStatsX, MyCharStatsY);

        MyCharStatsY += 40;
        g.drawString("Result:",MyCharStatsX, MyCharStatsY);

        MyCharStatsY += 40;
        g.drawString(Result,MyCharStatsX, MyCharStatsY);

        MyCharStatsY += 40;
        g.setColor(Display.Blue_Col);
        g.drawString("Effect:",MyCharStatsX, MyCharStatsY);

        MyCharStatsY += 40;
        g.setColor(Display.Blue_Col);
        g.drawString(Effect,MyCharStatsX, MyCharStatsY);


        //ENEMY CHAR DATA
        g.setFont(Display.myFont2);
        g.setColor(Display.Red_Col);
        g.drawString(enemyCharacter.getName(),
                226 + (226 - fm.stringWidth(enemyCharacter.getName()))/2,
                EnCharStatsY);

        EnCharStatsY += 40;
        g.setFont(Display.myFont1);
        g.drawString("Action:",EnCharStatsX, EnCharStatsY);

        EnCharStatsY += 40;
        g.drawString(enAction,EnCharStatsX, EnCharStatsY);

        EnCharStatsY += 40;
        g.drawString("Status:",EnCharStatsX, EnCharStatsY);

        EnCharStatsY += 40;
        g.drawString(enStatus,EnCharStatsX, EnCharStatsY);

        EnCharStatsY += 40;
        g.drawString("Description:",EnCharStatsX, EnCharStatsY);

        EnCharStatsY += 40;
        g.drawString(enDescription,EnCharStatsX, EnCharStatsY);

        EnCharStatsY += 40;
        g.drawString("Result:",EnCharStatsX, EnCharStatsY);

        EnCharStatsY += 40;
        g.drawString(enResult,EnCharStatsX, EnCharStatsY);

        EnCharStatsY += 40;
        g.setColor(Display.Red_Col);
        g.drawString("Effect:",EnCharStatsX, EnCharStatsY);

        EnCharStatsY += 40;
        g.setColor(Display.Red_Col);
        g.drawString(enEffect,EnCharStatsX, EnCharStatsY);

        enemyCharacter.render(g);
        myCharacter.render(g);
        uiManager.render(g);
    }

    private void EnemyCharacterATKCalculator(){
        int[] DMG;
        int finalDMG;
        int maxDMG = 0;

        //RANDOMIZING ENEMY ATK PARAMETERS
        //ATK LVL
        int enATK_Level = (int) (Math.random() * 3 ) + 1; // [1,3]
        //ATK PART OF BODY
        int enATK_part_of_body;
        if(enATK_Level == 2) {
            enATK_part_of_body = (int) (Math.random() * 3); // [0,2]
        } else {
            enATK_part_of_body = (int) (Math.random() * 2); // [0,1]
        }
        //ATK TYPE
        int enATK_type = (int) (Math.random() * 2) + 1; // [1,2]

        //CHANGING VULNERABILITY WHEN ATK == 2
        if(enATK_type == 2) {
            enemyCharacter.setVulnerability(true);
            enAction = "Strong ATK";
            enEffect = "Vulnerability";
        } else {
            enAction = "Light ATK";
        }

        switch (enATK_Level) {
            case 1: {
                enATK_PART_OF_BODY = myCharacter.getHBS().getTop_BOXES()[enATK_part_of_body];
                break;
            }
            case 2: {
                enATK_PART_OF_BODY = myCharacter.getHBS().getMiddle_BOXES()[enATK_part_of_body];
                break;
            }
            case 3: {
                enATK_PART_OF_BODY = myCharacter.getHBS().getBottom_BOXES()[enATK_part_of_body];
                break;
            }
        }

        DMG = new int[enemyCharacter.getHBS().getQuantityOfRolls()];
        for(int i = 0; i < enemyCharacter.getHBS().getQuantityOfRolls(); i++) {
            DMG[i] = Human.CalculateATK(myCharacter.getHBS(), enATK_Level, enATK_PART_OF_BODY,
                    enATK_type, enemyCharacter.getHBS().getATK());
            if(DMG[i] > maxDMG) maxDMG = DMG[i];
        }

        //CHECKING FOR MY VULNERABILITY
        if(ATK_type == 2) myCharacter.setVulnerability(true);
        if(myCharacter.isVulnerability()){
            finalDMG = maxDMG;
        } else {
            // [0,QuantityOfRolls)
            finalDMG =(DMG[(int) (Math.random() * (enemyCharacter.getHBS().getQuantityOfRolls() - 1))] /
            myCharacter.getBlock_modifier()) * myCharacter.getEvade_modifier();
        }

        StringBuilder tmp1 = new StringBuilder();
        tmp1.append("ATK for ");
        if(myCharacter.getBlock_modifier() == 2){
            Description = "Blocked " + finalDMG + " DMG";
            tmp1.append(finalDMG * 2);
        } else {
            tmp1.append(finalDMG);
        }
        tmp1.append(" DMG");
        enDescription = tmp1.toString();

        if(finalDMG > 0){
            enStatus = "Success";
        } else {
            enStatus = "Failure";
        }
        Result = "Took " + finalDMG + " DMG";

        //MAKING CALCULATIONS
        myCharacter.getHBS().setHP(myCharacter.getHBS().getHP() - finalDMG);
        //DISABLING VULNERABILITY AFTER CALCULATIONS
        myCharacter.setVulnerability(false);
        //DISABLING BLOCK MODIFIER AFTER CALCULATIONS
        myCharacter.setBlock_modifier(1);
    }

    private void MyCharacterATKCalculator(){
        int[] DMG;
        int finalDMG;
        int maxDMG = 0;

        //CALCULATING MY CHARACTER DMG
        switch (ATK_Level) {
            case 1: {
                ATK_PART_OF_BODY = enemyCharacter.getHBS().getTop_BOXES()[ATK_part_of_body];
                break;
            }
            case 2: {
                ATK_PART_OF_BODY = enemyCharacter.getHBS().getMiddle_BOXES()[ATK_part_of_body];
                break;
            }
            case 3: {
                ATK_PART_OF_BODY = enemyCharacter.getHBS().getBottom_BOXES()[ATK_part_of_body];
                break;
            }
        }

        if(ATK_type == 2){
            Action = "Strong ATK";
            Effect = "Vulnerability";
        } else {
            Action = "Light ATK";
        }

        DMG = new int[myCharacter.getHBS().getQuantityOfRolls()];
        for(int i = 0; i < myCharacter.getHBS().getQuantityOfRolls(); i++) {
            DMG[i] = Human.CalculateATK(enemyCharacter.getHBS(), ATK_Level, ATK_PART_OF_BODY, ATK_type,
                    myCharacter.getHBS().getATK());
            if(DMG[i] > maxDMG) maxDMG = DMG[i];
        }

        //CHECKING FOR ENEMY VULNERABILITY
        if(enemyCharacter.isVulnerability()){
            finalDMG = maxDMG;
        } else {
            finalDMG = (DMG[(int) (Math.random() * (myCharacter.getHBS().getQuantityOfRolls() - 1))] /
            enemyCharacter.getBlock_modifier() ) * enemyCharacter.getEvade_modifier();
        }

        StringBuilder tmp1 = new StringBuilder();
        tmp1.append("ATK for ");
        if(enemyCharacter.getBlock_modifier() == 2){
            enDescription = "Blocked " + finalDMG + " DMG";
            tmp1.append(finalDMG * 2);
        } else {
            tmp1.append(finalDMG);
        }
        tmp1.append(" DMG");
        Description = tmp1.toString();

        if(finalDMG > 0){
            Status = "Success";
        } else {
            Status = "Failure";
        }

        enResult = "Took " + finalDMG + " DMG";

        //MAKING CALCULATIONS
        enemyCharacter.getHBS().setHP(enemyCharacter.getHBS().getHP() - finalDMG);
        //DISABLING VULNERABILITY AFTER CALCULATIONS
        enemyCharacter.setVulnerability(false);
        //DISABLING BLOCK MODIFIER AFTER CALCULATIONS
        enemyCharacter.setBlock_modifier(1);
        //DISABLING EVADE MODIFIER AFTER CALCULATIONS
        enemyCharacter.setEvade_modifier(1);
    }

    private void MyCharacterBlockCalculator(){
        Action = "Block";
        Status = "Success";
        enemyCharacter.setVulnerability(true);
        myCharacter.setBlock_modifier(2);
    }

    private void EnemyCharacterBlockCalculator(){
        enAction = "Block";
        enStatus = "Success";
        myCharacter.setVulnerability(true);
        enemyCharacter.setBlock_modifier(2);
    }

    private void MyCharacterEvadeCalculator(){
        Action = "Evade";
        int EvadeRolls =
                (myCharacter.getHBS().getAgility() * myCharacter.getHBS().getFortune()) / 10;
        if(EvadeRolls > 0) {
            int[] EvadeTryings = new int[EvadeRolls];
            for (int i = 0; i < EvadeRolls; i++) {
                EvadeTryings[i] = (int) (Math.random() * 2); // [0,1]
            }
            myCharacter.setEvade_modifier(EvadeTryings[(int) (Math.random() * EvadeRolls)]);
            if(myCharacter.getEvade_modifier() == 0){
                enemyCharacter.setVulnerability(true);
                Status = "Success";
                Description = "Evaded all DMG";
            } else {
                Status = "Failure";
                Description = "Didn't evade ";
            }
        } else {
            Status = "Failure";
            Description = "Didn't evade ";
        }
    }

    private void EnemyCharacterEvadeCalculator(){
        enAction = "Evade";
        int EvadeRolls =
                (enemyCharacter.getHBS().getAgility() * enemyCharacter.getHBS().getFortune()) / 10;
        if(EvadeRolls > 0) {
            int[] EvadeTryings = new int[EvadeRolls];
            for (int i = 0; i < EvadeRolls; i++) {
                EvadeTryings[i] = (int) (Math.random() * 2); // [0,1]
            }
            enemyCharacter.setEvade_modifier(EvadeTryings[(int) (Math.random() * EvadeRolls)]);
            if(enemyCharacter.getEvade_modifier() == 0){
                myCharacter.setVulnerability(true);
                enStatus = "Success";
                enDescription = "Evaded all DMG";
            } else {
                enStatus = "Failure";
                enDescription = "Didn't evade";
            }
        } else {
            enStatus = "Failure";
            enDescription = "Didn't evade";
        }
    }



}

