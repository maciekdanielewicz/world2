package com.company;

import java.awt.*;
import java.util.Random;

public class Sheep extends Animal {
    Sheep() {}

    Sheep(World world, Cords cords) {
        super(world,cords);
        this.initiative = 4;
        this.power = 4;
    }

    Sheep(World world, Cords cords, int age, int initiative, int power) {
        super(world,cords,age,initiative,power);
    }

    void Print() {System.out.print('s');}

    @Override
    public Color Color() {return new Color(0xEAEAEF);}

    char getSign() {return 's';}

    void Split(Organism collider) {
        Sheep temp_h = (Sheep) collider;
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
                        world.toAdd(new Sheep(world, cords));
                        break;
                    }
                    case DOWN -> {
                        Cords cords = this.position;
                        cords.setY(cords.getY()+1);
                        world.toAdd(new Sheep(world, cords));
                        break;
                    }
                    case LEFT -> {
                        Cords cords = this.position;
                        cords.setX(cords.getX()-1);
                        world.toAdd(new Sheep(world, cords));
                        break;
                    }
                    case RIGHT -> {
                        Cords cords = this.position;
                        cords.setX(cords.getX()+1);
                        world.toAdd(new Sheep(world, cords));
                        break;
                    }
                }
            }
        }
    }
}
