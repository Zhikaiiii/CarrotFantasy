package ui;

import javax.swing.*;
import java.awt.*;

public class RotateJLabel extends JLabel{
    private double angle = 0;
    public void paintComponent(Graphics g) {
        Graphics2D gx = (Graphics2D) g;
        gx.rotate(angle, getWidth()/2, getHeight()/2);
        super.paintComponent(g);
    }
    public void setAngle(double angle){
        this.angle = Math.PI/2 + angle;
    }
}
