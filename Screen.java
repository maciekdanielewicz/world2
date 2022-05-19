package com.company;

import com.company.Cords;
import com.company.Direction;
import com.company.ListOfOrganisms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Screen extends JPanel implements ActionListener, KeyListener, MouseListener{
    private Color color;
    private Organism[][] board;
    private int height, width;
    private World world;
    private JFrame frame;

    public Screen(JFrame frame, World world,Organism[][] board,int height, int width ){
        this.board=board;
        this.height=height;
        this.width=width;
        this.world = world;
        this.frame = frame;
        repaint();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        addMouseListener(this);

    }

    public void paint(Graphics g){
        super.paint(g);
        for(int i = 1; i <= height; i++){
            for(int j = 1; j <= width; j++){
                g.setColor(board[i-1][j-1].Color());
                g.fillRect(j*30, i * 30, 30,30);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void keyPressed(KeyEvent e) {
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

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void mousePressed(MouseEvent e) {return;}

    public void mouseExited(MouseEvent e) {return;}

    public void mouseReleased(MouseEvent e) {return;}

    public void mouseClicked(MouseEvent e) {
        ListOfOrganisms listWindow = new ListOfOrganisms(world, new Cords(e.getX()/30-1, e.getY()/30-1));
    }

    public void mouseEntered(MouseEvent e) {return;}
}
