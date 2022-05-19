package com.company;

import java.awt.desktop.ScreenSleepEvent;
import java.util.LinkedList;
import java.util.List;
import java.lang.String;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class World {
    private String text;
    private int width, height;
    private int sinceLastSuperAbility = 0;
    private List<Organism> organisms = new LinkedList<>();
    private String log;
    private String logAdmin;
    private List<Organism> toAdd = new LinkedList<>();
    private List<Organism> toRem = new LinkedList<>();
    private boolean human_alive;
    public JFrame frame;
    public Organism[][] world;
    World (int _width, int _height) {
        this.width = _width;
        this.height = _height;
        world = new Organism[_height][_width];
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                world[i][j] = new Ground();
            }
        }
        this.log = "";
    }

    public void AddOrganism(Organism _org) {
        world[_org.position.getY()][_org.position.getX()] = _org;
        if(organisms.isEmpty()) organisms.add(_org);

        else {
            boolean added = false;
            for(Organism temp: organisms) {
                if(_org.getInitiative() > temp.getInitiative()) {
                    organisms.add(organisms.indexOf(temp), _org);
                    added = true;
                    break;
                }
            }
            if(!added) {
                organisms.add(_org);
            }
        }
    }

    public void toAdd(Organism org) {
        world[org.position.getY()][org.position.getX()] = org;
        toAdd.add(org);
    }

    public void NextTurn(Direction dir) {
        for(Organism it : organisms){//performing actions
            if(it.age>0){
                if(world[it.position.getY()][it.position.getX()].getSign() == it.getSign()){
                    if(it instanceof Human) ((Human) it).Action(dir);
                    else it.Action();
                }
            }
        }
        for(Organism it : toAdd){
            if(!( it instanceof Ground)){
                AddOrganism(it);
            }
        }
        toAdd.clear();
        for(Organism it : organisms){
            if(it instanceof PineBorscht){
                ((PineBorscht) it).Burn();
            }
        }
        for(Organism it : organisms){//removing dead organisms
            if(world[it.position.getY()][it.position.getX()] instanceof Ground){
                toRem.add(it);
            }
        }
        for(Organism it : toRem){
            organisms.remove(it);
        }
        toRem.clear();

        for(Organism it : organisms) it.age++;

        if(sinceLastSuperAbility>0) sinceLastSuperAbility--;
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isHuman_alive() {
        return human_alive;
    }

    public void printWorld() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1,1,0,0));
        frame.setSize(1900,1000);
        Screen s = new Screen(frame, this, world, height, width);
        frame.add(s);
        s.setVisible(true);

        Menu m = new Menu(this,s);

        m.setVisible(true);
        frame.add(m);

        frame.setVisible(true);
    }

    public void printLog() {
        System.out.println(log);
        log = "";
    }

    public void Log(String _log) {
        this.log += _log;
        this.log += '\n';
    }

    public void LogA(String _log) {
        this.logAdmin += _log;
        this.logAdmin += '\n';
    }

    public void startHumanAbility() {
        for(Organism temp : organisms) {
            if(temp instanceof Human) {
                Human temp_h = (Human) temp;
                temp_h.UseAbility();
            }
        }
    }

    public String getLog() {
        return log;
    }

    public int AmountOfOrganisms() {
        return organisms.size();
    }

    public void Save() {

        JFrame input = new JFrame();
        JTextField textField  = new JTextField(30);
        input.add(textField);
        input.setSize(100,100);
        input.setVisible(true);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text = textField.getText();

                String save = "";
                save+=(width + " " + height + " "+ organisms.size() + " " + sinceLastSuperAbility + "\n");
                for(Organism it : organisms){
                    save+=(it.getSign() + " ");
                    save+=(it.position.getX() + " ");
                    save+=(it.position.getY() + " ");
                    save+=(it.age + " ");
                    save+=(it.power + " ");
                    save+=(it.initiative + " ");
                    if(it instanceof Human){
                        save+=(((Human) it).getIndex() + " ");
                        save+=(((Human) it).TurnsLeft() + " ");
                    }
                    save+="\n";
                }
                try {
                    SaveToFile(text, save);
                }catch(IOException ie){
                    ie.printStackTrace();
                }


                input.setVisible(false);
                frame.setVisible(true);
            }
        });



    }
    private void SaveToFile(String fileName, String content) throws IOException{
        try{
            String[] elements = content.split("\n");
            String path = ".\\src\\saves\\" + text;
            File file = new File(path);
            System.out.println("Write to " + text);
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for(String it : elements){
                writer.write(it);
                writer.newLine();
            }
            writer.close();

        }catch(IOException ie){
            ie.printStackTrace();
        }

    }
    public void Load(){
        frame.setVisible(false);
        JFrame input = new JFrame();
        JTextField textField  = new JTextField(30);
        input.add(textField);
        input.setSize(100,100);
        input.setVisible(true);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text = textField.getText();
                System.out.println(text);
                try {
                    LoadWorldFromFile(text);
                }catch(IOException ie){
                    ie.printStackTrace();
                }
                input.setVisible(false);

                printWorld();
            }
        });

    }
    private void LoadWorldFromFile(String FileName) throws IOException{
        String path = ".\\src\\saves\\"+FileName;
        File file = new File(path);
        if(file.exists()){
            log="";
            world=null;
            organisms.clear();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            String[] elements = line.split(" ");

            width = Integer.parseInt(elements[0]);
            height = Integer.parseInt(elements[1]);
            int tempSize = Integer.parseInt(elements[2]);
            sinceLastSuperAbility = Integer.parseInt(elements[3]);


            world = new Organism[height][width];
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    world[i][j] = new Ground();
                }
            }
            for(int i = 0; i < tempSize; i++){
                line = reader.readLine();

                elements = line.split(" ");
                switch(elements[0]){
                    case "h":
                        AddOrganism(new Human(this,//world
                                new Cords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5]),//initiative
                                Integer.parseInt(elements[6]),//id
                                Integer.parseInt(elements[7])));//turnsLeft
                        human_alive = true;
                        break;
                    case "a":
                        AddOrganism(new Antylope(this,//world
                                new Cords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "w":
                        AddOrganism(new Wolf(this,//world
                                new Cords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "f":
                        AddOrganism(new Fox(this,//world
                                new Cords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "s":
                        AddOrganism(new Sheep(this,//world
                                new Cords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "t":
                        AddOrganism(new Turtle(this,//world
                                new Cords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "$":
                        AddOrganism(new PineBorscht(this,//world
                                new Cords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "#":
                        AddOrganism(new Guarana(this,//world
                                new Cords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "*":
                        AddOrganism(new WolfBerries(this,//world
                                new Cords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "@":
                        AddOrganism(new Dandelion(this,//world
                                new Cords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                }
            }

        }

    }
}
