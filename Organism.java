package com.company;

import java.awt.*;
import java.util.Random;
import java.lang.Object;

abstract class Organism {
    int age = 0;
    Cords position;
    int initiative;
    int power;
    World world;
    abstract void Action();
    void Collision(Organism collider) {
        if(collider.getPower() > this.getPower()) {
            String temp = "";
            temp += this.getSign();
            temp += " attacks stronger than ";
            temp += collider.getSign();
            world.Log(temp);
            world.world[this.position.getY()][this.position.getX()] = new Ground();
        }
    }
    abstract void Print();


    Organism() {};

    Organism(World world, Cords cords) {
        this.world = world;
        this.position = cords;
    }

    Organism(World world, Cords cords, int age, int initiative, int power) {
        this.world = world;
        this.position = cords;
        this.age = age;
        this.initiative = initiative;
        this.power = power;
    }

    Cords GetPosition() {return position;}

    int getInitiative() {return initiative;}

    int getAge() {return age;}

    Organism Next(Direction dir, int distance) {
        switch (dir) {
            case UP -> {
                if(position.getY()-distance>=0) {
                    return world.world[position.getY() - distance][position.getX()];
                }
            }
            case DOWN -> {
                if(position.getY()+distance < world.getHeight()-1) {
                    return world.world[position.getY() + distance][position.getX()];
                }
            }
            case LEFT -> {
                if(position.getX() - distance > 0) {
                    return world.world[position.getY()][position.getX() - distance];
                }
            }
            case RIGHT -> {
                if(position.getX() + distance < world.getWidth()-1) {
                    return world.world[position.getY()][position.getX() + distance];
                }
            }
        }
        return null;
    }

    abstract public Color Color();

    char getSign() {return ' ';}

    int getPower() {return power;}


}
