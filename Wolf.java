package com.company;

import java.util.Random;
import java.awt.*;

public class Wolf extends Animal {
    Wolf() {}

    Wolf (World world, Cords cords) {
        super(world,cords);
        this.initiative = 5;
        this.power = 9;
    }

    Wolf(World world, Cords cords, int age, int initiative, int power) {
        super(world,cords,age,initiative,power);
    }

    void Print() { System.out.print('w');}

    @Override
    public Color Color() {return new Color(2);}

    char getSign() { return 'w';}

    void Split(Organism collider) {
        Wolf temp_w = (Wolf) collider;
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
                        world.toAdd(new Wolf(world, cords));
                        break;
                    }
                    case DOWN -> {
                        Cords cords = this.position;
                        cords.setY(cords.getY()+1);
                        world.toAdd(new Wolf(world, cords));
                        break;
                    }
                    case LEFT -> {
                        Cords cords = this.position;
                        cords.setX(cords.getX()-1);
                        world.toAdd(new Wolf(world, cords));
                        break;
                    }
                    case RIGHT -> {
                        Cords cords = this.position;
                        cords.setX(cords.getX()+1);
                        world.toAdd(new Wolf(world, cords));
                        break;
                    }
                }
            }
        }
    }
}
