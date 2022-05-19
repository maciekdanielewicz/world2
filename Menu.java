package com.company;

import com.company.Direction;
import com.company.WolfBerries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Menu extends JPanel implements KeyListener, ActionListener{
    private World world;
    private Screen s;
    public Menu(World world, Screen s) {
        this.world = world;
        this.s = s;
        repaint();
        AddButtons();
    }

    public void paint(Graphics g){
        super.paint(g);
        PrintColors(g);
        PrintLabels(g);

    }
    private void PrintColors(Graphics g){
        g.setColor(new Guarana().Color());
        g.fillRect(100, 100, 30,30);
        g.setColor(new PineBorscht().Color());
        g.fillRect(100, 150, 30,30);
        g.setColor(new Dandelion().Color());
        g.fillRect(100, 200, 30,30);
        g.setColor(new WolfBerries().Color());
        g.fillRect(100, 250, 30,30);
        g.setColor(new Antylope().Color());
        g.fillRect(100, 300, 30,30);
        g.setColor(new Fox().Color());
        g.fillRect(100, 400, 30,30);
        g.setColor(new Sheep().Color());
        g.fillRect(100, 450, 30,30);
        g.setColor(new Turtle().Color());
        g.fillRect(100, 500, 30,30);
        g.setColor(new Wolf().Color());
        g.fillRect(100, 550, 30,30);
        g.setColor(new Human().Color());
        g.fillRect(100,600, 30,30);

    }
    private void PrintLabels(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN,25));
        g.drawString("com.company.Guarana", 150, 125);
        g.drawString("com.company.PineBorscht", 150, 175);
        g.drawString("com.company.Dandelion",150,225);
        g.drawString("com.company.Wolf Berries",150,275);
        g.drawString("com.company.Antylope",150,325);
        g.drawString("com.company.Fox",150,425);
        g.drawString("com.company.Sheep",150,475);
        g.drawString("com.company.Turtle",150,525);
        g.drawString("com.company.Wolf",150,575);
        g.drawString("com.company.Human",150,625);

    }
    private void AddButtons() {

        //Button's initializations
        JButton nextT = new JButton("Next turn");
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
        JButton logs = new JButton(("Logs"));

        //Action listeners
        nextT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.NextTurn(null);
                System.out.println("Next turn button clicked!");
                s.requestFocus();
            }
        });
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                world.Save();
                world.frame.setVisible(false);
                System.out.println("Save button");
                s.requestFocus();
            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.Load();
                System.out.println("Load button");
                s.requestFocus();
            }
        });
        logs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JFrame textArea = new JFrame();
                JPanel panel = new JPanel();
                JTextArea area = new JTextArea(world.getLog(), 1000, 50);
                JScrollPane scroll = new JScrollPane(area);
                scroll.setVisible(true);
                area.setEditable(false);
                area.setLineWrap(true);
                area.setWrapStyleWord(true);
                textArea.getContentPane().add(scroll, BorderLayout.EAST);
                textArea.add(panel);
                textArea.setSize(1000, 500);
                textArea.setVisible(true);



                s.requestFocus();
            }
        });
        //button's sizes
        nextT.setPreferredSize(new Dimension(100, 50));
        save.setPreferredSize(new Dimension(100, 50));
        load.setPreferredSize(new Dimension(100, 50));
        logs.setPreferredSize(new Dimension(100,50));
        //Adding buttons to menu
        add(nextT);
        add(save);
        add(load);
        add(logs);

        nextT.setVisible(true);
        save.setVisible(true);
        load.setVisible(true);
        logs.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        repaint();
    }
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                world.NextTurn(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                world.NextTurn(Direction.DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                world.NextTurn(Direction.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                world.NextTurn(Direction.LEFT);
                break;
        }
    }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
}
