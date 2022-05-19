package com.company;

import com.company.Animal;
import com.company.Cords;
import com.company.Direction;
import com.company.Ground;

import java.awt.*;
import java.util.List;
import java.util.Random;
public class Human extends Animal {

    private final int id;

    static final int UP = 0;

    static final int DOWN = 1;

    static final int LEFT = 2;

    static final int RIGHT = 3;

    private int turnsLeft = 0;

    Human() {
        id = 0;
    }

    @Override
    void Action() {

    }


    Human(World _world, Cords _cords, int index) {
        super(_world,_cords);
        this.power = 5;
        this.initiative = 4;
        this.id = 0;
    }

    Human(World _world, Cords _cords, int _age, int _initiative, int _power, int _id, int _turnsLeft) {
        super(_world,_cords,_age,_initiative,_power);
        this.id = _id;
        this.turnsLeft = _turnsLeft;
    }

    @Override
    public Color Color() {return new Color(212, 76, 20);}

    public char GetSign() {return 'h';}

    public int getIndex() {return 0; }

    public int TurnsLeft() {return turnsLeft;}

    public void UseAbility() {
        System.out.println("H tries to use his special ability\n");
        if(turnsLeft == 0) {
            turnsLeft = 5;
            System.out.println("Ability active for next 5 rounds\n");
        }
        else {
            System.out.println("It's not effective :(\n");
        }
    }
    
    

    private Organism Next(Organism _org, Direction direction, int distance) {
        switch(direction) {
            case UP -> {
                return world.world[_org.position.getY() - 1][_org.position.getX()];
            }
            case DOWN -> {
                return world.world[_org.position.getY() + 1][_org.position.getX()];
            }
            case RIGHT -> {
                return world.world[_org.position.getY()][_org.position.getX() + 1];
            }
            case LEFT -> {
                return world.world[_org.position.getY()][_org.position.getX() - 1];
            }
        }
        return null;
    }

    public Organism Next(Direction direction, int distance) {
        switch(direction) {
            case UP -> {
                return world.world[position.getY() - distance][position.getX()];
            }
            case DOWN -> {
                return world.world[position.getY() + distance][position.getX()];
            }
            case RIGHT -> {
                return world.world[position.getY()][position.getX() + distance];
            }
            case LEFT -> {
                return world.world[position.getY()][position.getX() - distance];
            }
        }
        return null;
    }

    public void Move(Direction direction) {
        switch(direction) {
            case UP -> {
                if(position.getY()==0) break;
                world.world[position.getY() - 1][position.getX()] = world.world[position.getY()][position.getX()];
                world.world[position.getY()][position.getX()] = new Ground();
                this.position.setY(position.getY()-1);
                break;
            }
            case DOWN -> {
                if(position.getY()==world.getHeight()-1) break;
                world.world[position.getY() + 1][position.getX()] = world.world[position.getY()][position.getX()];
                world.world[position.getY()][position.getX()] = new Ground();
                this.position.setY(position.getY()+1);
                break;
            }
            case RIGHT -> {
                if(position.getX() == world.getWidth() -1) break;
                world.world[position.getY()][position.getX() + 1] = world.world[position.getY()][position.getX()];
                world.world[position.getY()][position.getX()] = new Ground();
                this.position.setX(position.getX()+1);
                break;
            }
            case LEFT -> {
                world.world[position.getY()][position.getX() - 1] = world.world[position.getY()][position.getX()];
                world.world[position.getY()][position.getX()] = new Ground();
                this.position.setX(position.getX()-1);
                break;
            }
        }
    }

    private void Move(Organism _org, Direction direction) {
        switch(direction) {
            case UP -> {
                if(_org.position.getY()==0) break;
                world.world[_org.position.getY() - 1][_org.position.getX()] = world.world[_org.position.getY()][_org.position.getX()];
                world.world[_org.position.getY()][_org.position.getX()] = new Ground();
                _org.position.setY(_org.position.getY()-1);
                break;
            }
            case DOWN -> {
                if(_org.position.getY()==world.getHeight()-1) break;
                world.world[_org.position.getY() + 1][_org.position.getX()] = world.world[_org.position.getY()][_org.position.getX()];
                world.world[_org.position.getY()][_org.position.getX()] = new Ground();
                _org.position.setY(_org.position.getY()+1);
                break;
            }
            case RIGHT -> {
                if(_org.position.getX() == world.getWidth() -1) break;
                world.world[_org.position.getY()][_org.position.getX() + 1] = world.world[_org.position.getY()][_org.position.getX()];
                world.world[_org.position.getY()][_org.position.getX()] = new Ground();
                _org.position.setX(_org.position.getX()+1);
                break;
            }
            case LEFT -> {
                world.world[_org.position.getY()][_org.position.getX() - 1] = world.world[_org.position.getY()][_org.position.getX()];
                world.world[_org.position.getY()][_org.position.getX()] = new Ground();
                _org.position.setX(_org.position.getX()-1);
                break;
            }
        }
    }

    void Action(Direction dir) {
        String temp = "";
        temp +=this.getSign();
        if(turnsLeft != 0) temp += " has his special ability active for the next ";
        temp += this.turnsLeft;
        temp += " turns and wants to move ";
        switch (dir) {
            case UP -> {
                temp+="up";
                break;
            }
            case DOWN -> {
                temp +="down";
                break;
            }
            case LEFT -> {
                temp +="left";
                break;
            }
            case RIGHT -> {
                temp+="right";
                break;
            }
        }
        if(dir.equals(UP) && position.getY()>0 || dir.equals(DOWN) && position.getY()<world.getHeight()-1 ||
        dir.equals(LEFT) && position.getX()>0 || dir.equals(RIGHT) && position.getX() < world.getWidth()-1) {
            world.Log(temp);
            if (Next(dir,1) instanceof Ground) {
                Move(dir);
            }
            else {
                if (id == 0 && turnsLeft >0) {
                    if(Next(dir,1).getPower() > this.getPower()) {
                        this.Immortality();
                    }
                }
                Next(dir,1).Collision(this);
                if(Next(dir,1) instanceof Ground) {
                    Move(dir);
                }
            }
            if(turnsLeft != 0) turnsLeft--;
        }
    }

    void Immortality() {
        world.Log("H is immortal!");
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
                    Move(Direction.UP);
                    break;
                }
                case DOWN -> {
                    Move(Direction.DOWN);
                    break;
                }
                case LEFT -> {
                    Move(Direction.LEFT);
                    break;
                }
                case RIGHT -> {
                    Move(Direction.RIGHT);
                    break;
                }
            }
        }
    }

    void Print() {
        System.out.println("h");
    }

    void Collision(Organism collider) {
        if(turnsLeft>0) {
            String temp = "";
            temp += this.GetSign();
            temp += " works for next ";
            temp += turnsLeft;
            temp += " turns";
            this.Immortality();
        }
        else {
            if(collider.getPower() > this.getPower()) {
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
    }
}
