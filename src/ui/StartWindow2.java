package ui;

import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sun Nov 29 21:34:18 CST 2020
 */



/**
 * @author unknown
 */
public class StartWindow2 extends JFrame {
    public StartWindow2() {
        initComponents();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panelButton = new JPanel();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setIconImage(null);
        var contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 190));

        //======== panelButton ========
        {
            panelButton.setPreferredSize(new Dimension(160, 130));
            panelButton.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
            border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER
            ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font
            .BOLD,12),java.awt.Color.red),panelButton. getBorder()));panelButton. addPropertyChangeListener(
            new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order"
            .equals(e.getPropertyName()))throw new RuntimeException();}});
            panelButton.setLayout(new GridLayout(2, 1, 0, 20));

            //---- button1 ----
            button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
            button1.setIcon(new ImageIcon(getClass().getResource("/resources/button_start.png")));
            button1.setBorderPainted(false);
            button1.setContentAreaFilled(false);
            button1.setIconTextGap(0);
            button1.setBorder(null);
            button1.setPreferredSize(new Dimension(120, 45));
            panelButton.add(button1);

            //---- button2 ----
            button2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
            button2.setIcon(new ImageIcon(getClass().getResource("/resources/button_select.png")));
            button2.setPreferredSize(new Dimension(120, 45));
            button2.setContentAreaFilled(false);
            button2.setBorder(null);
            button2.setBorderPainted(false);
            button2.setIconTextGap(0);
            panelButton.add(button2);
        }
        contentPane.add(panelButton);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawImage(image, 0, 0, d.width, d.height, this);
//        MainFrame.instance().repaint();
//    }

    public static void main(String[] args) {
        new StartWindow2().setVisible(true);
    }
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawImage(image, 0, 0, d.width, d.height, this);
//        MainFrame.instance().repaint();
//    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panelButton;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
