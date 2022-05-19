package com.company;

import java.awt.event.KeyEvent;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        World map = new World(20,20);
        RandNewOrganisms(map);
        map.printWorld();
    }
    private static void RandNewOrganisms(World map) {
        Random gen = new Random();
        int amOfOrg = 100;
        for(int i = 0; i < amOfOrg; i++) {
            int x = gen.nextInt(map.getWidth());
            int y = gen.nextInt(map.getHeight());

            if (map.world[y][x] instanceof Ground){
                int org = gen.nextInt(9);
                switch(org){
                    case 0: map.AddOrganism(new Antylope(map, new Cords(x,y))); break;
                    case 1: map.AddOrganism(new Fox(map, new Cords(x,y)));break;
                    case 2: map.AddOrganism(new Guarana(map, new Cords(x,y)));break;
                    case 3: map.AddOrganism(new Sheep(map, new Cords(x,y)));break;
                    case 4: map.AddOrganism(new PineBorscht(map, new Cords(x,y)));break;
                    case 5: map.AddOrganism(new Dandelion(map, new Cords(x,y)));break;
                    case 6: map.AddOrganism(new Turtle(map, new Cords(x,y)));break;
                    case 7: map.AddOrganism(new Wolf(map, new Cords(x,y)));break;
                    case 8: map.AddOrganism(new WolfBerries(map, new Cords(x,y)));break;
                }
            }
        }
        int x = gen.nextInt(map.getWidth());
        int y = gen.nextInt(map.getHeight());
        while(!(map.world[y][x] instanceof Ground)){
            x = gen.nextInt(map.getWidth());
            y = gen.nextInt(map.getHeight());
        }
        map.AddOrganism(new Human(map, new Cords(x,y), 0));
    }

}
