package com.company;

import java.awt.*;
import java.util.List;
import java.util.Random;
class Plant extends Organism{
    Plant() {}

    static final int UP = 0;

    static final int DOWN = 1;

    static final int LEFT = 2;

    static final int RIGHT = 3;

    public Plant(World world, Cords position, int age, int initiative, int power) {
        this.world = world;
        this.position = position;
        this.age = age;
        this.initiative = initiative;
        this.power = power;
    }

    public Plant(World world, Cords position) {
        this.world = world;
        this.position = position;
    }

    void Print() {System.out.print('|');}

    @Override
    public Color Color() {return new Color(0x07E507);}

    char getSign() {return '|';}

    void Action() {
        Random random = new Random();
        int temp = random.nextInt(10);
        if(temp == 0) Divide();
    }

    void Divide() {
        List<Integer> chances = null;
        if(position.getX() == 0) {
            if(position.getY() == 0) {
                if (world.world[position.getY() + 1][position.getX()].getSign() == '#') chances.add(DOWN);
                if (world.world[position.getY()][position.getX()+1].getSign() == '#') chances.add(RIGHT);
            }
            else if(position.getY() == world.getHeight()-1) {
                if (world.world[position.getY() - 1][position.getX()].getSign() == '#') chances.add(UP);
                if (world.world[position.getY()][position.getX()+1].getSign() == '#') chances.add(RIGHT);
            }
            else {
                if (world.world[position.getY() - 1][position.getX()].getSign() == '#') chances.add(UP);
                if (world.world[position.getY() + 1][position.getX()].getSign() == '#') chances.add(DOWN);
                if (world.world[position.getY()][position.getX()+1].getSign() == '#') chances.add(RIGHT);
            }
        }
        else if(position.getX()==world.getWidth()-1) {
            if(position.getY() == 0) {
                if (world.world[position.getY() + 1][position.getX()].getSign() == '#') chances.add(DOWN);
                if (world.world[position.getY()][position.getX()-1].getSign() == '#') chances.add(LEFT);
            }
            else if(position.getY() == world.getHeight()-1) {
                if (world.world[position.getY() - 1][position.getX()].getSign() == '#') chances.add(UP);
                if (world.world[position.getY()][position.getX()-1].getSign() == '#') chances.add(LEFT);
            }
            else {
                if (world.world[position.getY() - 1][position.getX()].getSign() == '#') chances.add(UP);
                if (world.world[position.getY() + 1][position.getX()].getSign() == '#') chances.add(DOWN);
                if (world.world[position.getY()][position.getX()-1].getSign() == '#') chances.add(LEFT);
            }
        }
        else if(position.getY()==0) {
            if (world.world[position.getY() + 1][position.getX()].getSign() == '#') chances.add(DOWN);
            if (world.world[position.getY()][position.getX()-1].getSign() == '#') chances.add(LEFT);
            if (world.world[position.getY()][position.getX()+1].getSign() == '#') chances.add(RIGHT);
        }
        else if(position.getY() == world.getHeight()-1) {
            if (world.world[position.getY() - 1][position.getX()].getSign() == '#') chances.add(UP);
            if (world.world[position.getY()][position.getX()-1].getSign() == '#') chances.add(LEFT);
            if (world.world[position.getY()][position.getX()+1].getSign() == '#') chances.add(RIGHT);
        }
        else {
            if (world.world[position.getY() - 1][position.getX()].getSign() == '#') chances.add(UP);
            if (world.world[position.getY() + 1][position.getX()].getSign() == '#') chances.add(DOWN);
            if (world.world[position.getY()][position.getX()-1].getSign() == '#') chances.add(LEFT);
            if (world.world[position.getY()][position.getX()+1].getSign() == '#') chances.add(RIGHT);
        }
        if(chances.size()==0) return;
        else {
            Random rand_temp = new Random();
            int temp = rand_temp.nextInt(chances.size());
            for (int i = 0; i < temp; i++) {
                chances.remove(0);
            }
            switch(chances.get(0)) {
                case UP -> {
                    Cords cords = position;
                    cords.setY(cords.getY()-1);
                    world.toAdd(new Plant(world, cords));
                    break;
                }
                case DOWN -> {
                    Cords cords = position;
                    cords.setY(cords.getY()+1);
                    world.toAdd(new Plant(world, cords));
                    break;
                }
                case LEFT -> {
                    Cords cords = position;
                    cords.setX(cords.getX()-1);
                    world.toAdd(new Plant(world, cords));
                    break;
                }
                case RIGHT -> {
                    Cords cords = position;
                    cords.setX(cords.getX()+1);
                    world.AddOrganism(new Plant(world, cords));
                    break;
                }
            }
        }
    }

    Organism Next(Direction dir, int distance) {
        switch(dir) {
            case UP -> {
                return world.world[position.getY()-distance][position.getX()];
            }
            case DOWN -> {
                return world.world[position.getY()+distance][position.getX()];
            }
            case LEFT -> {
                return world.world[position.getY()][position.getX()-distance];
            }
            case RIGHT -> {
                return world.world[position.getY()][position.getX()+distance];
            }
        }
        return null;
    }
    void Collision(Organism collider) {
        world.world[this.position.getY()][this.position.getX()] = new Ground();
        String temp = "";
        temp += collider.getSign();
        temp+=" eats ";
        temp += this.getSign();
        world.Log(temp);
    }
}
