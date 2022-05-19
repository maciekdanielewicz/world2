package com.company;

import com.company.Direction;

import java.util.Random;

public abstract class Animal extends Organism {
    public Animal() {
    }



    public Animal(World world, Cords position, int age, int initiative, int power) {
        this.world = world;
        this.position = position;
        this.age = age;
        this.initiative = initiative;
        this.power = power;
    }

    public Animal(World world, Cords position) {
        this.world = world;
        this.position = position;
    }

    void Action() {
        int temp;
        Direction dir = null;
        ChooseNewDirection(dir);
        if (((dir == Direction.UP) && (position.getY() > 0)) || ((dir == Direction.DOWN) && (position.getY() < (world.getHeight() - 1))) ||
                ((dir == Direction.LEFT) && (position.getX() > 0)) || ((dir == Direction.RIGHT) && (position.getX() < (world.getWidth() - 1)))) {
            if (Next(dir, 1) instanceof Ground) {
                Move(dir);
            } else {
                Next(dir, 1).Collision(this);
                if (Next(dir, 1) instanceof Ground) Move(dir);
            }
        }
    }

    void Split(Organism collider) {
    }

    void Collision(Organism collider) {
        if(collider.getSign() == this.getSign()) {
            Split(collider);
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
            temp += " attacks stonger ";
            temp += this.getSign();
            world.Log(temp);
            world.world[collider.position.getY()][collider.position.getX()] = new Ground();
        }

    }

    void Print() {
        System.out.print('a');
    }

    void ChooseNewDirection(Direction dir) {
        int temp;
        Random random = new Random();
        if(position.getX()==0) {
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
        else if(position.getX() == world.getWidth()-1) {
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
        else if(position.getY()==0) {
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
        else if(position.getY()==world.getHeight()-1) {
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


    void Move(Direction dir) {
        switch (dir) {
            case UP -> {
                if (position.getY() == 0) break;
                world.world[position.getY() - 1][position.getX()] = world.world[position.getY()][position.getX()];
                world.world[position.getY()][position.getX()] = new Ground();
                this.position.setY(position.getY() - 1);
                break;
            }
            case DOWN -> {
                if (position.getY() == world.getHeight() - 1) break;
                world.world[position.getY() + 1][position.getX()] = world.world[position.getY()][position.getX()];
                world.world[position.getY()][position.getX()] = new Ground();
                this.position.setY(position.getY() + 1);
                break;
            }
            case LEFT -> {
                if (position.getX() == 0) break;
                world.world[position.getY()][position.getX() - 1] = world.world[position.getY()][position.getX()];
                world.world[position.getY()][position.getX()] = new Ground();
                this.position.setX(position.getX() - 1);
                break;
            }
            case RIGHT -> {
                if (position.getX() == world.getWidth() - 1) break;
                world.world[position.getY()][position.getX() + 1] = world.world[position.getY()][position.getX()];
                world.world[position.getY()][position.getX()] = new Ground();
                this.position.setX(position.getX() + 1);
                break;
            }
        }
    }

}
