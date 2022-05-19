package com.company;

import java.awt.*;
import java.util.Random;

public class Antylope extends Animal {
    protected void ChooseNewDirection(Direction dir) {
        int temp;
        Random random = new Random();
        if(position.getX()==0 || position.getX() ==1) {
            if(position.getY()==0) {
                temp = random.nextInt(2);
                dir = temp == 0 ? Direction.DOWN:Direction.RIGHT;
            }
            else if(position.getY()==world.getHeight()-1) {
                temp = random.nextInt(2);
                dir = temp == 0 ? Direction.UP : Direction.RIGHT;
            }
            else {
                temp = random.nextInt(3);
                switch(temp) {
                    case 0 -> {
                        dir = Direction.UP;
                        break;
                    }
                    case 1 -> {
                        dir = Direction.DOWN;
                        break;
                    }
                    case 2 -> {
                        dir = Direction.RIGHT;
                        break;
                    }
                }
            }
        }
        else if(position.getX() == world.getWidth()-1 || position.getX() == world.getWidth() -2) {
            if(position.getY()==0) {
                temp = random.nextInt(2);
                dir = temp == 0 ? Direction.DOWN:Direction.LEFT;
            }
            else if(position.getY()==world.getHeight()-1) {
                temp = random.nextInt(2);
                dir = temp == 0 ? Direction.UP : Direction.LEFT;
            }
            else {
                temp = random.nextInt(3);
                switch(temp) {
                    case 0 -> {
                        dir = Direction.UP;
                        break;
                    }
                    case 1 -> {
                        dir = Direction.DOWN;
                        break;
                    }
                    case 2 -> {
                        dir = Direction.LEFT;
                        break;
                    }
                }
            }
        }
        else if(position.getY()==0 || position.getY() == 1) {
            temp = random.nextInt(3);
            switch(temp) {
                case 0 -> {
                    dir = Direction.RIGHT;
                    break;
                }
                case 1 -> {
                    dir = Direction.DOWN;
                    break;
                }
                case 2 -> {
                    dir = Direction.LEFT;
                    break;
                }
            }
        }
        else if(position.getY()==world.getHeight()-1 || position.getY()==world.getHeight()-2) {
            temp = random.nextInt(3);
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
            }
        }
        else {
            temp = random.nextInt(4);
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
        }
    }
    protected void Split(Organism org) {
        Antylope temp_a = (Antylope) org;
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
                        world.toAdd(new Antylope(world, cords));
                        break;
                    }
                    case DOWN -> {
                        Cords cords = this.position;
                        cords.setY(cords.getY()+1);
                        world.toAdd(new Antylope(world, cords));
                        break;
                    }
                    case LEFT -> {
                        Cords cords = this.position;
                        cords.setX(cords.getX()-1);
                        world.toAdd(new Antylope(world, cords));
                        break;
                    }
                    case RIGHT -> {
                        Cords cords = this.position;
                        cords.setX(cords.getX()+1);
                        world.toAdd(new Antylope(world, cords));
                        break;
                    }
                }
            }
        }

    }

    Antylope() {

    }

    @Override
            public Color Color() {return new Color(0x592E03);}

    Antylope(World world, Cords cords) {
        super(world,cords);
        this.initiative = 4;
        this.power = 4;
    }

    Antylope(World world, Cords cords, int age, int ini, int pow) {
        super(world,cords,age,ini,pow);
    }

    char GetSign() {
        return 'a';
    }

    void Print() {
        System.out.print("a");
    }

    void Action() {
        Direction dir = Direction.UP;
        ChooseNewDirection(dir);
        if(dir == Direction.UP && position.getY() > 1 || dir == Direction.DOWN && position.getY() < world.getHeight()-2 ||
                dir == Direction.LEFT && position.getX() > 1 || dir == Direction.RIGHT && position.getX() < world.getWidth() - 2) {
            if(Next(dir,1).getSign() == '#') {
                if(Next(dir,2).getSign() == '#') {
                    Move(dir);
                    Move(dir);
                }
                else {
                    Move(dir);
                    Next(dir,1).Collision(this);
                    if(Next(dir,1).getSign() == '#') Move(dir);
                }
            }
            else {
                Next(dir,1).Collision(this);
                if(Next(dir,1).getSign() == '#') Move(dir);
            }
        }
    }

    void Collision(Organism collider) {
        Random random = new Random();
        int temp = random.nextInt(2);

        if(collider instanceof Antylope) {
            Split(collider);
        }

        else if(temp == 0) {
            world.Log("A runs away!");
            if(position.getX() > 0 && world.world[position.getY()][position.getX()-1].getSign() == '#') Move(Direction.LEFT);
            else if(position.getX() < world.getWidth() -1 && world.world[position.getY()][position.getX()+1].getSign() == '#') Move(Direction.RIGHT);
            else if(position.getY() > 0 && world.world[position.getY()-1][position.getX()].getSign() == '#') Move(Direction.UP);
            else if(position.getY() < world.getHeight() -1 && world.world[position.getY()+1][position.getX()].getSign() == '#') Move(Direction.DOWN);
        }

        else if(collider.getPower() > this.getPower()) {
            String tmp = "";
            tmp += collider.getSign();
            tmp += " overpowers ";
            tmp += this.getSign();
            world.Log(tmp);
            world.world[this.position.getY()][this.position.getX()] = new Ground();
        }
        else {
            String tmp = "";
            tmp += collider.getSign();
            tmp += " attacks stronger ";
            tmp += this.getSign();
            world.Log(tmp);
            world.world[collider.position.getY()][collider.position.getX()] = new Ground();
        }
    }
}
