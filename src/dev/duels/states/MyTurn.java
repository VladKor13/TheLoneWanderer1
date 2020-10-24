package dev.duels.states;

import dev.duels.Game;
import dev.duels.Handler;
import dev.duels.UI.UIImageButton;
import dev.duels.UI.UIImageToggleButton;
import dev.duels.UI.UIManager;
import dev.duels.display.Display;
import dev.duels.entities.creatures.Human;
import dev.duels.entities.creatures.HumanDataSaver;
import dev.duels.gfx.Assets;
import dev.duels.gfx.ImageLoader;

import java.awt.*;

public class MyTurn extends State {


    private UIManager uiManager;
    private UIImageToggleButton[] MainActionSButtonsGroup = new UIImageToggleButton[4];
    private UIImageToggleButton[] PartsOfBodyButtonsGroup = new UIImageToggleButton[7];
    private int ToggledButtonMainGroup, PartsOfBodyButtonGroup;
    private UIImageButton FightButton;
    private boolean  Cross_Pressed = false;


    //MARK THAT ENEMY HERO IS ALREADY CREATED
    static boolean LOAD_DATA = false;

    private Human MyCharacter,
            EnemyCharacter;

    MyTurn(final Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        //GENERATING ENEMY HERO
        if(!LOAD_DATA) {
            int[] tmpStats = generateStats();
            EnemyCharacter = new Human("Enemy", tmpStats[0], tmpStats[1], tmpStats[2]);
        } else {
            //RESTORING ENEMY CHARACTER DATA
            EnemyCharacter = HumanDataSaver.RestoreHumanData("EnemyCharacter.ser");
            LOAD_DATA = false;
        }

        //SERIALIZATION ENEMY CHARACTER DATA
        HumanDataSaver.SerializeHumanData(EnemyCharacter,"EnemyCharacter.ser");
        //RESTORING MY CHARACTER DATA
        MyCharacter = HumanDataSaver.RestoreHumanData("MyCharacter.ser");

        //LIGHT ATTACK BUTTON
        UIImageToggleButton LightAttackButton = new UIImageToggleButton(23, 520, 100, 100,
                Assets.lightAttack_button, () -> {
            for (UIImageToggleButton uiImageToggleButton : PartsOfBodyButtonsGroup) {
                uiImageToggleButton.setPressed(false);
            }
            ToggledButtonMainGroup = 0;
            BattleLog.ATK_Pressed = true;
            BattleLog.Block_Pressed = false;
            BattleLog.Evade_Pressed = false;
            Cross_Pressed = false;

            BattleLog.ATK_type = 1;
        });
        uiManager.addObject(LightAttackButton);

        //HARD ATTACK BUTTON
        UIImageToggleButton HardAttackButton = new UIImageToggleButton(145, 520, 100, 100,
                Assets.hardAttach_button, () -> {
            for (UIImageToggleButton uiImageToggleButton : PartsOfBodyButtonsGroup) {
                uiImageToggleButton.setPressed(false);
            }
            ToggledButtonMainGroup = 1;
            BattleLog.ATK_Pressed = true;
            BattleLog.Block_Pressed = false;
            BattleLog.Evade_Pressed = false;
            Cross_Pressed = false;

            BattleLog.ATK_type = 2;
        });
        uiManager.addObject(HardAttackButton);

        //BLOCK BUTTON
        UIImageToggleButton BlockButton = new UIImageToggleButton(267, 520, 100, 100,
                Assets.block_button, () -> {
                    ToggledButtonMainGroup = 2;
                    BattleLog.ATK_Pressed = false;
                    BattleLog.Block_Pressed = true;
                    BattleLog.Evade_Pressed = false;
                    Cross_Pressed = false;

                    //DISABLING CROSSES
                    for (UIImageToggleButton uiImageToggleButton : PartsOfBodyButtonsGroup) {
                        uiImageToggleButton.setPressed(false);
                    }
                });
        uiManager.addObject(BlockButton);

        //EVADE BUTTON
        UIImageToggleButton EvadeButton = new UIImageToggleButton(389, 520, 100, 100,
                Assets.evade_button, () -> {
                    ToggledButtonMainGroup = 3;
                    BattleLog.ATK_Pressed = false;
                    BattleLog.Block_Pressed = false;
                    BattleLog.Evade_Pressed = true;
                    Cross_Pressed = false;

                    //DISABLING CROSSES
                    for (UIImageToggleButton uiImageToggleButton : PartsOfBodyButtonsGroup) {
                        uiImageToggleButton.setPressed(false);
                    }
                });
        uiManager.addObject(EvadeButton);

        MainActionSButtonsGroup[0] = LightAttackButton;
        MainActionSButtonsGroup[1] = HardAttackButton;
        MainActionSButtonsGroup[2] = BlockButton;
        MainActionSButtonsGroup[3] = EvadeButton;


        //HEAD CROSS BUTTON
        UIImageToggleButton HeadCrossButton = new UIImageToggleButton(247, 257, 24, 24,
                Assets.cross_button, () -> {
                    PartsOfBodyButtonGroup = 0;
                    Cross_Pressed = true;

                    BattleLog.ATK_Level = 1;
                    BattleLog.ATK_part_of_body = 0;
                });
        uiManager.addObject(HeadCrossButton);

        //THROAT CROSS BUTTON
        UIImageToggleButton ThroatCrossButton = new UIImageToggleButton(247, 290, 24, 24,
                Assets.cross_button, () -> {
                    PartsOfBodyButtonGroup = 1;
                    Cross_Pressed = true;

                    BattleLog.ATK_Level = 1;
                    BattleLog.ATK_part_of_body = 1;
                });
        uiManager.addObject(ThroatCrossButton);

        //CHEST CROSS BUTTON
        UIImageToggleButton ChestCrossButton = new UIImageToggleButton(247, 320, 24, 24,
                Assets.cross_button, () -> {
                    PartsOfBodyButtonGroup = 2;
                    Cross_Pressed = true;

                    BattleLog.ATK_Level = 2;
                    BattleLog.ATK_part_of_body = 1;
                });
        uiManager.addObject(ChestCrossButton);

        //LEFT ARM CROSS BUTTON
        UIImageToggleButton Left_ArmCrossButton = new UIImageToggleButton(285, 320, 24, 24,
                Assets.cross_button, () -> {
                    PartsOfBodyButtonGroup = 3;
                    Cross_Pressed = true;

                    BattleLog.ATK_Level = 2;
                    BattleLog.ATK_part_of_body = 0;
                });
        uiManager.addObject(Left_ArmCrossButton);

        //RIGHT ARM CROSS BUTTON
        UIImageToggleButton Right_ArmCrossButton = new UIImageToggleButton(212, 320, 24, 24,
                Assets.cross_button, () -> {
                    PartsOfBodyButtonGroup = 4;
                    Cross_Pressed = true;

                    BattleLog.ATK_Level = 2;
                    BattleLog.ATK_part_of_body = 2;
                });
        uiManager.addObject(Right_ArmCrossButton);

        //LEFT LEG CROSS BUTTON
        UIImageToggleButton Left_LegCrossButton = new UIImageToggleButton(273, 440, 24, 24,
                Assets.cross_button, () -> {
                    PartsOfBodyButtonGroup = 5;
                    Cross_Pressed = true;

                    BattleLog.ATK_Level = 3;
                    BattleLog.ATK_part_of_body = 0;
                });
        uiManager.addObject(Left_LegCrossButton);

        //RIGHT LEG CROSS BUTTON
        UIImageToggleButton Right_LegCrossButton = new UIImageToggleButton(229, 440, 24, 24,
                Assets.cross_button, () -> {
                    PartsOfBodyButtonGroup = 6;
                    Cross_Pressed = true;

                    BattleLog.ATK_Level = 3;
                    BattleLog.ATK_part_of_body = 1;
                });
        uiManager.addObject(Right_LegCrossButton);

        PartsOfBodyButtonsGroup[0] = HeadCrossButton;
        PartsOfBodyButtonsGroup[1] = ThroatCrossButton;
        PartsOfBodyButtonsGroup[2] = ChestCrossButton;
        PartsOfBodyButtonsGroup[3] = Left_ArmCrossButton;
        PartsOfBodyButtonsGroup[4] = Right_ArmCrossButton;
        PartsOfBodyButtonsGroup[5] = Left_LegCrossButton;
        PartsOfBodyButtonsGroup[6] = Right_LegCrossButton;

        //BACK BUTTON
        uiManager.addObject(new UIImageButton(310, 10, 192, 64, Assets.back_button, () -> {

            //SERIALIZATION MY CHARACTER DATA
            HumanDataSaver.SerializeHumanData(MyCharacter,"MyCharacter.ser");
            //SERIALIZATION ENEMY CHARACTER DATA
            HumanDataSaver.SerializeHumanData(EnemyCharacter,"EnemyCharacter.ser");

            handler.getMouseManager().setUiManager(null);//WHEN YOU CHANGE GAME STATE
            Game.mainMenuState = new MainMenuState(handler);
            State.setState(Game.mainMenuState);
        }));

        //FIGHT BUTTON
        FightButton = new UIImageButton(161, 180, 192, 64, Assets.fight_button, () -> {

            //SERIALIZATION MY CHARACTER DATA
            HumanDataSaver.SerializeHumanData(MyCharacter, "MyCharacter.ser");
            //SERIALIZATION ENEMY CHARACTER DATA
            HumanDataSaver.SerializeHumanData(EnemyCharacter, "EnemyCharacter.ser");

            Cross_Pressed = false;

            handler.getMouseManager().setUiManager(null);//WHEN YOU CHANGE GAME STATE
            Game.battleLog = new BattleLog(handler);
            State.setState(Game.battleLog);
        });
        uiManager.addObject(FightButton);


    }

    @Override
    public void tick() {
        //CHANGING PRESSED BUTTONS
        for (int i = 0; i < MainActionSButtonsGroup.length; i++) {
            if (i == ToggledButtonMainGroup) continue;
            MainActionSButtonsGroup[i].setPressed(false);
        }

        //CHANGING PRESSED BUTTONS
        for (int i = 0; i < PartsOfBodyButtonsGroup.length; i++) {
            if (i == PartsOfBodyButtonGroup) continue;
            PartsOfBodyButtonsGroup[i].setPressed(false);
        }

        // ENABLE OR DISABLE CROSS BUTTONS
        if (!BattleLog.ATK_Pressed) {
            for (UIImageToggleButton uiImageToggleButton : PartsOfBodyButtonsGroup) {
                uiImageToggleButton.setEnable(false);
            }
        } else {
            for (UIImageToggleButton uiImageToggleButton : PartsOfBodyButtonsGroup) {
                uiImageToggleButton.setEnable(true);
            }
        }

        //CHANGING DISABLED BUTTON TEXTURE
        if ((BattleLog.ATK_Pressed && Cross_Pressed) || BattleLog.Block_Pressed || BattleLog.Evade_Pressed) {
            Assets.fight_button[0] = ImageLoader.loadImage("/textures/FightButton.png");
            FightButton.setEnable(true);
        } else {
            Assets.fight_button[0] = ImageLoader.loadImage("/textures/FightButtonPressedUnable.png");
            FightButton.setEnable(false);
        }

        EnemyCharacter.tick();
        MyCharacter.tick();
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.RedNightBackGround, 0, 0, null);
        g.drawImage(Assets.Enemy, 100, 250, null);


        //DRAWING MY CHARACTER HP BAR
        int myCharacterHP_BarX = ((310 - (MyCharacter.getHP_bar() - 1) * 18) / 2);
        int myCharacterHB_BarY = 30;

        if(MyCharacter.getHP_bar() >= 15) MyCharacter.setHP_Bar(15);

        for (int i = 0; i < MyCharacter.getHP_bar(); i++) {
            if (MyCharacter.getHP_bar() < 2) {
                g.drawImage(Assets.greenHP_Bar[0], myCharacterHP_BarX, myCharacterHB_BarY, null);
                myCharacterHP_BarX += 9;
                g.drawImage(Assets.greenHP_Bar[2], myCharacterHP_BarX, myCharacterHB_BarY, null);
                break;
            } else {
                if (i == 0) {
                    g.drawImage(Assets.greenHP_Bar[0], myCharacterHP_BarX, myCharacterHB_BarY, null);
                    myCharacterHP_BarX += 9;
                } else if (i == MyCharacter.getHP_bar() - 1) {
                    g.drawImage(Assets.greenHP_Bar[2], myCharacterHP_BarX, myCharacterHB_BarY, null);
                    break;
                }

                g.drawImage(Assets.greenHP_Bar[1], myCharacterHP_BarX, myCharacterHB_BarY, null);
                myCharacterHP_BarX += 18;
            }
        }


        //DRAWING ENEMY CHARACTER HP BAR
        int enemyCharacterHP_BarX = ((512 - (EnemyCharacter.getHP_bar() - 1) * 18) / 2) - 5;
        int enemyCharacterHP_BarY = 495;
        for (int i = 0; i < EnemyCharacter.getHP_bar(); i++) {
            if (EnemyCharacter.getHP_bar() < 2) {
                g.drawImage(Assets.redHP_Bar[0], enemyCharacterHP_BarX, enemyCharacterHP_BarY, null);
                enemyCharacterHP_BarX += 9;
                g.drawImage(Assets.redHP_Bar[2], enemyCharacterHP_BarX, enemyCharacterHP_BarY, null);
                break;
            } else {
                if (i == 0) {
                    g.drawImage(Assets.redHP_Bar[0], enemyCharacterHP_BarX, enemyCharacterHP_BarY, null);
                    enemyCharacterHP_BarX += 9;
                } else if (i == EnemyCharacter.getHP_bar() - 1) {
                    g.drawImage(Assets.redHP_Bar[2], enemyCharacterHP_BarX, enemyCharacterHP_BarY, null);
                    break;
                }
                g.drawImage(Assets.redHP_Bar[1], enemyCharacterHP_BarX, enemyCharacterHP_BarY, null);
                enemyCharacterHP_BarX += 18;
            }
        }

        //DRAWING ENEMY HP
        int EnemyHP = EnemyCharacter.getHBS().getHP();

        g.setFont(Display.myFont0);
        g.setColor(Display.Black_Col);
        g.drawString(Integer.toString(EnemyHP),248,510);

        //DRAWING MY HP
        int MyHP = MyCharacter.getHBS().getHP();
        g.setFont(Display.myFont0);
        g.setColor(Display.Black_Col);
        FontMetrics fm = g.getFontMetrics();
        g.drawString(Integer.toString(MyHP),
                (330 - fm.stringWidth(Integer.toString(MyHP))) / 2,
                45);

        EnemyCharacter.render(g);
        MyCharacter.render(g);
        uiManager.render(g);
    }

    private int[] generateStats() {
        int[] Stats = new int[3];
        while (Stats[0] + Stats[1] + Stats[2] != 10) {
            Stats[0] = (int) (Math.random() * 9 + 1);
            Stats[1] = (int) (Math.random() * 9 + 1);
            Stats[2] = (int) (Math.random() * 9 + 1);
        }
        return Stats;
    }


}