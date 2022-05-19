package com.company;

import java.awt.*;
import java.util.Random;

public class Turtle extends Animal {
    Turtle() {}

    Turtle(World world, Cords cords) {
        super(world,cords);
        this.initiative = 1;
        this.power = 2;
    }

    Turtle(World world, Cords cords, int age, int initiative, int power) {
        super(world,cords,age,initiative,power);
    }

    void Print() {System.out.print('t');}

    @Override
    public Color Color() {return new Color(0x448000);}

    char getSign() {return 't';}

    void Split(Organism collider) {
        Turtle temp_t = (Turtle) collider;
        Random random = new Random();
        Direction dir = Direction.UP;
        int temp = random.nextInt(4);
        switch(temp) {
            case 0 -> {
                dir = Direction.UP;
                break;
            }
            case 1 -> {
                dir = Direction.RIGHT;
                break;
            }
            case 2 -> {
                dir = Direction.LEFT;
                break;
            }
            case 3 -> {
                dir = Direction.DOWN;
                break;
            }
        }
        if(dir == Direction.UP && position.getY() > 0 || dir == Direction.DOWN && position.getY() < world.getHeight()-1 ||
                dir == Direction.LEFT && position.getX() > 0 || dir == Direction.RIGHT && position.getX() < world.getWidth() - 1) {
            if(Next(dir,1) instanceof Ground) {
                switch(dir) {
                    case UP -> {
                        Cords cords = this.position;
                        cords.setY(cords.getY()-1);
                        world.toAdd(new Turtle(world, cords));
                        break;
                    }
                    case DOWN -> {
                        Cords cords = this.position;
                        cords.setY(cords.getY()+1);
                        world.toAdd(new Turtle(world, cords));
                        break;
                    }
                    case LEFT -> {
                        Cords cords = this.position;
                        cords.setX(cords.getX()-1);
                        world.toAdd(new Turtle(world, cords));
                        break;
                    }
                    case RIGHT -> {
                        Cords cords = this.position;
                        cords.setX(cords.getX()+1);
                        world.toAdd(new Turtle(world, cords));
                        break;
                    }
                }
            }
        }
    }

    void Action() {
        Random random = new Random();
        int temp2 = random.nextInt(4);
        if(temp2 <=2) {
            String temp = "";
            temp += "T decides not to move";
            world.Log(temp);
            return;
        }
        else {
            int temp;
            Direction dir = null;
            ChooseNewDirection(dir);
            if(Next(dir,1) != null) {
                if(Next(dir,1) instanceof Ground) {
                    Move(dir);
                }
                else {
                    Next(dir, 1).Collision(this);
                    if(Next(dir,1) instanceof Ground) Move(dir);
                }
            }
        }
    }

    void Collision(Organism collider) {
        if(collider instanceof Turtle) {
            Split(collider);
        }
        else if(collider.getPower() < 5) {
            String temp = "";
            temp += this.getSign();
            temp += " stopped by ";
            temp += collider.getSign();
            world.Log(temp);
            return;
        }
        else if (collider.getPower() > this.getPower()) {
            String temp = "";
            temp += collider.getSign();
            temp += " overpowers ";
            temp += this.getSign();
            world.Log(temp);
            world.world[this.position.getY()][this.position.getX()] = new Ground();
        }
        else {
            String temp = "";
            temp += collider.getSign();
            temp += " attacks stronger ";
            temp += this.getSign();
            world.Log(temp);
            world.world[collider.position.getY()][collider.position.getX()] = new Ground();
        }
    }

}
