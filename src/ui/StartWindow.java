/*
 * Created by JFormDesigner on Sun Dec 06 20:22:28 CST 2020
 */

package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import control.Controller;

/**
 * @author unknown
 */
public class StartWindow extends JPanel {
    public StartWindow() {
        backgroundImg = (new ImageIcon(getClass().getResource("/resources/background.png"))).getImage();
        initComponents();
        setVisible(true);
    }

    private void buttonStartMouseClicked(MouseEvent e) {
        // TODO add your code here
        Controller.start();
    }

    private void buttonSelectActionPerformed(ActionEvent e) {
        // TODO add your code here
        LevelSelectWindow s = new LevelSelectWindow();
        s.setVisible(true);
        Controller.f.dispose();
        Controller.f = new JFrame();
        Controller.f.setSize(540, 380);
        Controller.f.add(s);
        Controller.f.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panelButton = new JPanel();
        buttonSelect = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
        . swing. border. EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing
        . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
        Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
        ) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
        public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName (
        ) )) throw new RuntimeException( ); }} );
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 200));

        //======== panelButton ========
        {
            panelButton.setOpaque(false);
            panelButton.setLayout(new GridLayout(2, 1, 0, 20));

            //---- buttonSelect ----
            buttonSelect.setPreferredSize(new Dimension(152, 50));
            buttonSelect.setIconTextGap(0);
            buttonSelect.setContentAreaFilled(false);
            buttonSelect.setBorderPainted(false);
            buttonSelect.setBorder(null);
            buttonSelect.setIcon(new ImageIcon(getClass().getResource("/resources/button_select.png")));
            buttonSelect.addActionListener(e -> buttonSelectActionPerformed(e));
            panelButton.add(buttonSelect);
        }
        add(panelButton);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
        g.drawImage(backgroundImg, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public static void main(String[] args) {
        StartWindow p = new StartWindow();
        Controller.f = new JFrame();
        Controller.f.setSize(700, 500);
        Controller.f.add(p);
        Controller.f.setVisible(true);
    }
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - unknown
        private JPanel panelButton;
        private JButton buttonSelect;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Image backgroundImg;
}
