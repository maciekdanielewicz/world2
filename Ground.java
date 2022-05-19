package com.company;

import com.company.Cords;

import java.awt.*;

public class Ground extends Organism{
    Ground() {}

    Ground(World world, Cords cords) {
        super(world,cords);
        this.power = 0;
        this.initiative = 0;
    }

    Ground(World world, Cords cords, int age, int initiative, int power) {
        super(world,cords,age,initiative,power);
    }

    public char getSign() {return '.';}

    public void Print() {System.out.print('.');}

    public void Action() {}

    public Color Color() {
        return Color.GREEN;
    }


}