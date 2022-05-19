package com.company;

import com.company.WolfBerries;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListOfOrganisms extends JFrame {
    private JList<String> orgList;
    private World world;
    private Cords pos;
    private JFrame frame;
    public ListOfOrganisms(World _world, Cords _pos){
        world = _world;
        pos = _pos;
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("com.company.Antylope");
        model.addElement("com.company.Fox");
        model.addElement("com.company.Sheep");
        model.addElement("com.company.Turtle");
        model.addElement("com.company.Wolf");

        model.addElement("com.company.Guarana");
        model.addElement("com.company.PineBorscht");
        model.addElement("com.company.Dandelion");
        model.addElement("com.company.Wolf berries");

        orgList = new JList<>(model);
        orgList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orgList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                switch(orgList.getSelectedValue()) {
                    case "com.company.Antylope":
                        world.AddOrganism(new Antylope(world, pos));
                        break;
                    case "com.company.Fox":
                        world.AddOrganism(new Fox(world, pos));
                        break;
                    case "com.company.Sheep":
                        world.AddOrganism(new Sheep(world, pos));
                        break;
                    case "com.company.Turtle":
                        world.AddOrganism(new Turtle(world, pos));
                        break;
                    case "com.company.Wolf":
                        world.AddOrganism(new Wolf(world, pos));
                        break;
                    case "com.company.Guarana":
                        world.AddOrganism(new Guarana(world, pos));
                        break;
                    case "com.company.PineBorscht":
                        world.AddOrganism(new PineBorscht(world, pos));
                        break;
                    case "com.company.Dandelion":
                        world.AddOrganism(new Dandelion(world, pos));
                        break;
                    case "com.company.Wolf berries":
                        world.AddOrganism(new WolfBerries(world, pos));
                        break;
                }
                world.frame.repaint();
                SwingUtilities.getWindowAncestor(orgList).setVisible(false);
                // world.PrintWorld();
            }

        });
        add(orgList);
        setTitle("List of organisms");
        orgList.setSize(400,400);
        setSize(400,400);
        setVisible(true);

    }
}