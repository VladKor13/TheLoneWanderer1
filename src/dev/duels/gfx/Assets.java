package dev.duels.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage[] start_button, continue_button, options_button, exit_button,
            plus_button, minus_button, done_button, back_button,
            lightAttack_button, hardAttach_button, block_button, evade_button,
            cross_button, fight_button;
    public static BufferedImage MenuBackGround, RedNightBackGround, HeroCreationBackGround, YouDiedBackGround,
        RainBackGround, RockBackGround;

    public static BufferedImage StrengthPanel, AgilityPanel, FortunePanel, SkillPtsLeftPanel;

    public static BufferedImage[] NumPanel;

    public static BufferedImage Enemy;

    public static BufferedImage[] greenHP_Bar, redHP_Bar;

    public static void init(){

        //BUTTONS
        start_button = new BufferedImage[3];
        start_button[0] = ImageLoader.loadImage("/textures/NewGameButton.png");
        start_button[1] = ImageLoader.loadImage("/textures/NewGameButtonHovering.png");
        start_button[2] = ImageLoader.loadImage("/textures/NewGameButtonPressed.png");

        continue_button = new BufferedImage[3];
        continue_button[0] = ImageLoader.loadImage("/textures/ContinueButton.png");
        continue_button[1] = ImageLoader.loadImage("/textures/ContinueButtonHovering.png");
        continue_button[2] = ImageLoader.loadImage("/textures/ContinueButtonPressed.png");

        options_button = new BufferedImage[3];
        options_button[0] = ImageLoader.loadImage("/textures/OptionsButton.png");
        options_button[1] = ImageLoader.loadImage("/textures/OptionsButtonHovering.png");
        options_button[2] = ImageLoader.loadImage("/textures/OptionsButtonPressed.png");

        exit_button = new BufferedImage[3];
        exit_button[0] = ImageLoader.loadImage("/textures/ExitButton.png");
        exit_button[1] = ImageLoader.loadImage("/textures/ExitButtonHovering.png");
        exit_button[2] = ImageLoader.loadImage("/textures/ExitButtonPressed.png");

        plus_button = new BufferedImage[3];
        plus_button[0] = ImageLoader.loadImage("/textures/PlusButton.png");
        plus_button[1] = ImageLoader.loadImage("/textures/PlusButtonHovering.png");
        plus_button[2] = ImageLoader.loadImage("/textures/PlusButtonPressed.png");

        minus_button = new BufferedImage[3];
        minus_button[0] = ImageLoader.loadImage("/textures/MinusButton.png");
        minus_button[1] = ImageLoader.loadImage("/textures/MinusButtonHovering.png");
        minus_button[2] = ImageLoader.loadImage("/textures/MinusButtonPressed.png");

        done_button = new BufferedImage[3];
        done_button[0] = ImageLoader.loadImage("/textures/DoneButton.png");
        done_button[1] = ImageLoader.loadImage("/textures/DoneButtonHovering.png");
        done_button[2] = ImageLoader.loadImage("/textures/DoneButtonPressed.png");

        back_button = new BufferedImage[3];
        back_button[0] = ImageLoader.loadImage("/textures/BackButton.png");
        back_button[1] = ImageLoader.loadImage("/textures/BackButtonHovering.png");
        back_button[2] = ImageLoader.loadImage("/textures/BackButtonPressed.png");

        block_button = new BufferedImage[3];
        block_button[0] = ImageLoader.loadImage("/textures/BlockButton.png");
        block_button[1] = ImageLoader.loadImage("/textures/BlockButtonHovering.png");
        block_button[2] = ImageLoader.loadImage("/textures/BlockButtonPressed.png");

        hardAttach_button = new BufferedImage[3];
        hardAttach_button[0] = ImageLoader.loadImage("/textures/HardAttackButton.png");
        hardAttach_button[1] = ImageLoader.loadImage("/textures/HardAttackButtonHovering.png");
        hardAttach_button[2] = ImageLoader.loadImage("/textures/HardAttackButtonPressed.png");

        lightAttack_button = new BufferedImage[3];
        lightAttack_button[0] = ImageLoader.loadImage("/textures/LightAttackButton.png");
        lightAttack_button[1] = ImageLoader.loadImage("/textures/LightAttackButtonHovering.png");
        lightAttack_button[2] = ImageLoader.loadImage("/textures/LightAttackButtonPressed.png");

        evade_button = new BufferedImage[3];
        evade_button[0] = ImageLoader.loadImage("/textures/EvadeButton.png");
        evade_button[1] = ImageLoader.loadImage("/textures/EvadeButtonHovering.png");
        evade_button[2] = ImageLoader.loadImage("/textures/EvadeButtonPressed.png");

        cross_button = new BufferedImage[3];
        cross_button[0] = ImageLoader.loadImage("/textures/CrossButton.png");
        cross_button[1] = ImageLoader.loadImage("/textures/CrossButtonHovering.png");
        cross_button[2] = ImageLoader.loadImage("/textures/CrossButtonPressed.png");

        fight_button = new BufferedImage[3];
        fight_button[0] = ImageLoader.loadImage("/textures/FightButton.png");
        fight_button[1] = ImageLoader.loadImage("/textures/FightButtonHovering.png");
        fight_button[2] = ImageLoader.loadImage("/textures/FightButtonPressed.png");

        NumPanel = new BufferedImage[11];
        NumPanel[0] = ImageLoader.loadImage("/textures/Num0Panel.png");
        NumPanel[1] = ImageLoader.loadImage("/textures/Num1Panel.png");
        NumPanel[2] = ImageLoader.loadImage("/textures/Num2Panel.png");
        NumPanel[3] = ImageLoader.loadImage("/textures/Num3Panel.png");
        NumPanel[4] = ImageLoader.loadImage("/textures/Num4Panel.png");
        NumPanel[5] = ImageLoader.loadImage("/textures/Num5Panel.png");
        NumPanel[6] = ImageLoader.loadImage("/textures/Num6Panel.png");
        NumPanel[7] = ImageLoader.loadImage("/textures/Num7Panel.png");
        NumPanel[8] = ImageLoader.loadImage("/textures/Num8Panel.png");
        NumPanel[9] = ImageLoader.loadImage("/textures/Num9Panel.png");
        NumPanel[10] = ImageLoader.loadImage("/textures/Num10Panel.png");



        //BACKGROUNDS
        MenuBackGround = ImageLoader.loadImage("/textures/MenuBackGround.png");
        RedNightBackGround = ImageLoader.loadImage("/textures/RedNightBackGround.png");
        HeroCreationBackGround = ImageLoader.loadImage("/textures/HeroCreationBackGround.jpg");
        YouDiedBackGround = ImageLoader.loadImage("/textures/YouDiedBackGround.png");
        RainBackGround = ImageLoader.loadImage("/textures/RainBackGround.png");
        RockBackGround = ImageLoader.loadImage("/textures/RockBackGround.png");

        //PANELS
        StrengthPanel = ImageLoader.loadImage("/textures/StrengthPanel.png");
        AgilityPanel = ImageLoader.loadImage("/textures/AgilityPanel.png");
        FortunePanel = ImageLoader.loadImage("/textures/FortunePanel.png");
        SkillPtsLeftPanel = ImageLoader.loadImage("/textures/SkillPtsLeftPanel.png");

        //HP BARS

        greenHP_Bar = new BufferedImage[3];
        greenHP_Bar[0] = ImageLoader.loadImage("/textures/GreenBarHorizontalStart.png");
        greenHP_Bar[1] = ImageLoader.loadImage("/textures/GreenBarHorizontalBody.png");
        greenHP_Bar[2] = ImageLoader.loadImage("/textures/GreenBarHorizontalEnd.png");

        redHP_Bar = new BufferedImage[3];
        redHP_Bar[0] = ImageLoader.loadImage("/textures/RedBarHorizontalStart.png");
        redHP_Bar[1] = ImageLoader.loadImage("/textures/RedBarHorizontalBody.png");
        redHP_Bar[2] = ImageLoader.loadImage("/textures/RedBarHorizontalEnd.png");

        //OTHER STUFF
        Enemy = ImageLoader.loadImage("/textures/Enemy.png");


    }

}
