package dev.duels.entities.creatures;

import java.awt.*;
import java.io.*;


public class Human extends Creature implements Serializable {


    private HumanBodySystem HBS;
    private int HP_Bar;
    private int Quantity_Of_Killed_Enemies;
    private boolean vulnerability = false;
    private int block_modifier = 1;
    private int evade_modifier = 1;
    public Human(String name, int strength, int agility, int fortune){
        super(name);
        HBS = new HumanBodySystem(strength, agility, fortune);

    }

    @Override
    public void render(Graphics g){

    }

    @Override
    public void tick(){
        int tmpHp = HBS.getHP();
        int counter = 0;
        while(tmpHp > 0){
            tmpHp -= 10;
            counter++;
        }
        HP_Bar = counter;
    }


    public int getHP_bar(){
        return HP_Bar;
    }

    public HumanBodySystem getHBS(){
        return HBS;
    }

    public static int CalculateATK(HumanBodySystem enemyHBS, int ATK_Level, int[] ATK_part_of_body, int ATK_Type, int ATK_DAMAGE){
        int DMG;
        int DMG_MODIFIER;

        if(ATK_Type == 1) {
            DMG_MODIFIER = 1;
        } else {
            DMG_MODIFIER = 2;
        }

        int quantity_of_parts_of_body = 2;
        int[][] tmp_arr = enemyHBS.getMiddle_BOXES();

        switch (ATK_Level){
            case (1):{
                quantity_of_parts_of_body = 2;
                tmp_arr = enemyHBS.getTop_BOXES();
                break;
            }
            case (2):{
                quantity_of_parts_of_body = 3;
                tmp_arr = enemyHBS.getMiddle_BOXES();
                break;
            }
            case (3):{
                quantity_of_parts_of_body = 2;
                tmp_arr = enemyHBS.getBottom_BOXES();
                break;
            }
        }
        if(ATK_part_of_body == null) {
            int i = (int) (Math.random() * quantity_of_parts_of_body);
            ATK_part_of_body = tmp_arr[i];
        }

//        for(int i1 = 0; i1 < ATK_part_of_body.length; i1++){
//            System.out.print(" " + ATK_part_of_body[i1]);
//        }
        int Rand = (int) (Math.random() * 100);
        //System.out.println("arr[" + Rand + "]=" + ATK_part_of_body[Rand]);
        DMG = ATK_part_of_body[Rand] * DMG_MODIFIER * ATK_DAMAGE ;

        return DMG;
    }

    public int getQuantity_Of_Killed_Enemies() {
        return Quantity_Of_Killed_Enemies;
    }

    public void setQuantity_Of_Killed_Enemies(int quantity_Of_Killed_Enemies) {
        Quantity_Of_Killed_Enemies = quantity_Of_Killed_Enemies;
    }

    public boolean isVulnerability() {
        return this.vulnerability;
    }

    public void setVulnerability(boolean vulnerability) {
        this.vulnerability = vulnerability;
    }

    public String getName(){
        return super.getName();
    }

    public int getBlock_modifier() {
        return block_modifier;
    }

    public void setBlock_modifier(int block_modifier) {
        this.block_modifier = block_modifier;
    }

    public void setHP_Bar(int HP_Bar) {
        this.HP_Bar = HP_Bar;
    }

    public int getEvade_modifier() {
        return evade_modifier;
    }

    public void setEvade_modifier(int evade_modifier) {
        this.evade_modifier = evade_modifier;
    }
}


