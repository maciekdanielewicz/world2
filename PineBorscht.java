package com.company;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class PineBorscht extends Plant {
    PineBorscht() {}

    PineBorscht(World world, Cords cords) {
        super(world,cords);
        this.initiative = 0;
        this.power = 10;
    }

    PineBorscht(World world, Cords cords, int age, int initiative, int power) {
        super(world,cords,age,initiative,power);
    }

    void Print() {System.out.print('$');}

    @Override
    public Color Color() {return new Color(0xFC0505);}

    char getSign() {return '$';}

    void Action() {
        Random random = new Random();
        int temp = random.nextInt(20);
        if(temp==0) Divide();
        Burn();
    }

    public void Burn_func(int y, int x, String temp2) {
        if(world.world[position.getY() + y][position.getX() + x] instanceof Animal) {
            temp2="";
            temp2+=world.world[position.getY() + y][position.getX() + x].getSign();
            temp2 += " burned by $";
            world.Log(temp2);
            world.world[position.getY() + y][position.getX() + x] = new Ground();
        }
    }

    public void Burn() {
        String temp2 = "";
        if(position.getX() == 0) {
            if(position.getY() == 0) {
                Burn_func(1,0,temp2);
                Burn_func(1,1,temp2);
                Burn_func(0,1,temp2);
            }
            else if(position.getY() == world.getHeight()-1) {
                Burn_func(-1,0,temp2);
                Burn_func(-1,1,temp2);
                Burn_func(0,1,temp2);
            }
            else {
                Burn_func(-1,0,temp2);
                Burn_func(-1,1,temp2);
                Burn_func(0,1,temp2);
                Burn_func(1,0,temp2);
                Burn_func(1,1,temp2);
            }
        }
        else if(position.getX()==world.getWidth()-1) {
            if(position.getY() == 0) {
                Burn_func(1,0,temp2);
                Burn_func(1,-1,temp2);
                Burn_func(0,-1,temp2);
            }
            else if(position.getY() == world.getHeight()-1) {
                Burn_func(-1,0,temp2);
                Burn_func(-1,-1,temp2);
                Burn_func(0,-1,temp2);
            }
            else {
                Burn_func(-1,0,temp2);
                Burn_func(-1,-1,temp2);
                Burn_func(0,-1,temp2);
                Burn_func(1,0,temp2);
                Burn_func(1,-1,temp2);
            }
        }
        else if(position.getY()==0) {
            Burn_func(1,0,temp2);
            Burn_func(1,1,temp2);
            Burn_func(1,-1,temp2);
            Burn_func(0,1,temp2);
            Burn_func(0,-1,temp2);
        }
        else if(position.getY() == world.getHeight()-1) {
            Burn_func(-1,0,temp2);
            Burn_func(-1,1,temp2);
            Burn_func(-1,-1,temp2);
            Burn_func(0,1,temp2);
            Burn_func(0,-1,temp2);
        }
        else {
            Burn_func(-1,0,temp2);
            Burn_func(-1,1,temp2);
            Burn_func(-1,-1,temp2);
            Burn_func(0,1,temp2);
            Burn_func(0,-1,temp2);
            Burn_func(1,0,temp2);
            Burn_func(1,1,temp2);
            Burn_func(1,-1,temp2);
        }
    }
    public void Divide() {
        List<Integer> chances = null;
        if (position.getX() == 0) {
            if (position.getY() == 0) {
                if (world.world[position.getY() + 1][position.getX()].getSign() == '#') chances.add(DOWN);
                if (world.world[position.getY()][position.getX() + 1].getSign() == '#') chances.add(RIGHT);
            } else if (position.getY() == world.getHeight() - 1) {
                if (world.world[position.getY() - 1][position.getX()].getSign() == '#') chances.add(UP);
                if (world.world[position.getY()][position.getX() + 1].getSign() == '#') chances.add(RIGHT);
            } else {
                if (world.world[position.getY() - 1][position.getX()].getSign() == '#') chances.add(UP);
                if (world.world[position.getY() + 1][position.getX()].getSign() == '#') chances.add(DOWN);
                if (world.world[position.getY()][position.getX() + 1].getSign() == '#') chances.add(RIGHT);
            }
        } else if (position.getX() == world.getWidth() - 1) {
            if (position.getY() == 0) {
                if (world.world[position.getY() + 1][position.getX()].getSign() == '#') chances.add(DOWN);
                if (world.world[position.getY()][position.getX() - 1].getSign() == '#') chances.add(LEFT);
            } else if (position.getY() == world.getHeight() - 1) {
                if (world.world[position.getY() - 1][position.getX()].getSign() == '#') chances.add(UP);
                if (world.world[position.getY()][position.getX() - 1].getSign() == '#') chances.add(LEFT);
            } else {
                if (world.world[position.getY() - 1][position.getX()].getSign() == '#') chances.add(UP);
                if (world.world[position.getY() + 1][position.getX()].getSign() == '#') chances.add(DOWN);
                if (world.world[position.getY()][position.getX() - 1].getSign() == '#') chances.add(LEFT);
            }
        } else if (position.getY() == 0) {
            if (world.world[position.getY() + 1][position.getX()].getSign() == '#') chances.add(DOWN);
            if (world.world[position.getY()][position.getX() - 1].getSign() == '#') chances.add(LEFT);
            if (world.world[position.getY()][position.getX() + 1].getSign() == '#') chances.add(RIGHT);
        } else if (position.getY() == world.getHeight() - 1) {
            if (world.world[position.getY() - 1][position.getX()].getSign() == '#') chances.add(UP);
            if (world.world[position.getY()][position.getX() - 1].getSign() == '#') chances.add(LEFT);
            if (world.world[position.getY()][position.getX() + 1].getSign() == '#') chances.add(RIGHT);
        } else {
            if (world.world[position.getY() - 1][position.getX()].getSign() == '#') chances.add(UP);
            if (world.world[position.getY() + 1][position.getX()].getSign() == '#') chances.add(DOWN);
            if (world.world[position.getY()][position.getX() - 1].getSign() == '#') chances.add(LEFT);
            if (world.world[position.getY()][position.getX() + 1].getSign() == '#') chances.add(RIGHT);
        }
        if (chances.size() == 0) return;
        else {
            Random rand_temp = new Random();
            int temp = rand_temp.nextInt(chances.size());
            for (int i = 0; i < temp; i++) {
                chances.remove(0);
            }
            switch (chances.get(0)) {
                case UP -> {
                    Cords cords = position;
                    cords.setY(cords.getY() - 1);
                    world.toAdd(new PineBorscht(world, cords));
                    break;
                }
                case DOWN -> {
                    Cords cords = position;
                    cords.setY(cords.getY() + 1);
                    world.toAdd(new PineBorscht(world, cords));
                    break;
                }
                case LEFT -> {
                    Cords cords = position;
                    cords.setX(cords.getX() - 1);
                    world.toAdd(new PineBorscht(world, cords));
                    break;
                }
                case RIGHT -> {
                    Cords cords = position;
                    cords.setX(cords.getX() + 1);
                    world.toAdd(new PineBorscht(world, cords));
                    break;
                }
            }
        }
    }

    void Collision(Organism collider) {
        if(collider.getSign() != '&') {
            String temp = "";
            temp += collider.getSign();
            temp += " eats ";
            temp += this.getSign();
            temp += " and dies :(";
            world.Log(temp);
            world.world[this.position.getY()][this.position.getX()] = new Ground();
            world.world[collider.position.getY()][collider.position.getX()] = new Ground();
        }
        else {
            world.world[this.position.getY()][this.position.getX()] = new Ground();
        }
    }
}
