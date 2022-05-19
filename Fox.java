package com.company;

import java.awt.*;
import java.util.Random;

public class Fox extends Animal {
    Fox() {}

    Fox(World world, Cords cords) {
        super(world, cords);
        this.initiative = 7;
        this.power = 3;
    }

    Fox(World world, Cords cords, int age, int initiative, int power) {
        super(world,cords,age,initiative,power);
    }

    void Print() {System.out.print('f');}

    @Override
    public Color Color() {return new Color(0x6B4940);}

    char getSign() {return 'f';}

    void Action() {
        int temp;
        Direction dir = Direction.UP;
        ChooseNewDirection(dir);
        if(Next(dir,1)!=null) {
            if(Next(dir,1) instanceof Ground) {
                Move(dir);
            }
            else {
                if(Next(dir,1).getPower() <= this.getPower()) {
                    Next(dir,1).Collision(this);
                    if(Next(dir,1) instanceof Ground) Move(dir);
                }
            }
        }
    }

    void Split(Organism collider) {
        Fox temp_f = (Fox) collider;
        Random random = new Random();
        Direction dir = null;
        int temp = random.nextInt(4);
        switch (temp) {
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
        if (dir == Direction.UP && position.getY() > 0 || dir == Direction.DOWN && position.getY() < world.getHeight() - 1 ||
                dir == Direction.LEFT && position.getX() > 0 || dir == Direction.RIGHT && position.getX() < world.getWidth() - 1) {
            if (Next(dir, 1) instanceof Ground) {
                switch (dir) {
                    case UP -> {
                        Cords cords = this.position;
                        cords.setY(cords.getY() - 1);
                        world.toAdd(new Fox(world, cords));
                        break;
                    }
                    case DOWN -> {
                        Cords cords = this.position;
                        cords.setY(cords.getY() + 1);
                        world.toAdd(new Fox(world, cords));
                        break;
                    }
                    case LEFT -> {
                        Cords cords = this.position;
                        cords.setX(cords.getX() - 1);
                        world.toAdd(new Fox(world, cords));
                        break;
                    }
                    case RIGHT -> {
                        Cords cords = this.position;
                        cords.setX(cords.getX() + 1);
                        world.toAdd(new Fox(world, cords));
                        break;
                    }
                }
            }
        }
    }
}
