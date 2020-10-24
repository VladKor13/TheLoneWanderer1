package dev.duels.entities.creatures;

import java.io.Serializable;

public class HumanBodySystem implements Serializable {

    private int[][] top_BOXES = new int[2][100];
    private int[][] middle_BOXES = new int[3][100];
    private int[][] bottom_BOXES = new int[2][100];

    private int strength, agility, fortune, ATK,  HP, QuantityOfRolls;


    HumanBodySystem(int strength, int agility, int fortune){
        this.strength = strength;
        this.agility = agility;
        this.fortune = fortune;

        ATK = 10 + strength * 2;
        HP = 50 + (strength / 2) + (agility * 10);
        QuantityOfRolls = 2 + fortune / 3;

        setDefaultBoxes();

    }

    private void setDefaultBoxes(){
        //  HEAD
        for(int i = 35; i < 65; i++) top_BOXES[0][i] = 1;
        for(int i = 48; i < 52; i++) top_BOXES[0][i] = 2;

        //  NECK
        for(int i = 40; i < 60; i++) top_BOXES[1][i] = 1;
        for(int i = 48; i < 52; i++) top_BOXES[1][i] = 2;

        //  LEFT_ARM
        for(int i = 5; i < 25; i++) middle_BOXES[0][i] = 1;
        for(int i = 13; i < 17; i++) middle_BOXES[0][i] = 2;

        //  RIGHT_ARM
        for(int i = 75; i < 95; i++) middle_BOXES[2][i] = 1;
        for(int i = 83; i < 87; i++) middle_BOXES[2][i] = 2;

        // CHEST
        for(int i = 30; i < 70; i++) middle_BOXES[1][i] = 1;
        for(int i = 47; i < 53; i++) middle_BOXES[1][i] = 2;

        // LEFT_LEG
        for(int i = 10; i < 35; i++) bottom_BOXES[0][i] = 1;
        for(int i = 20; i < 25; i++) bottom_BOXES[0][i] = 2;

        // RIGHT_LEG
        for(int i = 65; i < 90; i++) bottom_BOXES[1][i] = 1;
        for(int i = 75; i < 80; i++) bottom_BOXES[1][i] = 2;

    }


    //SETTERS AND GETTERS


    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
        ATK = 10 + strength * 2;

    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
        HP = 50 + (strength / 2) + (agility * 10);
    }

    public int getFortune() {
        return fortune;
    }

    public void setFortune(int fortune) {
        this.fortune = fortune;
        QuantityOfRolls = 2 + fortune / 3;
    }

    public int getATK() {
        return ATK;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP){
        this.HP = HP;
    }


    public int getQuantityOfRolls(){
        return  QuantityOfRolls;
    }

    public int[][] getTop_BOXES() {
        return top_BOXES;
    }

    public int[][] getMiddle_BOXES() {
        return middle_BOXES;
    }

    public int[][] getBottom_BOXES() {
        return bottom_BOXES;
    }

}
