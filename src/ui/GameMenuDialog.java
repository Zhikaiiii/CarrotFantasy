/*
 * Created by JFormDesigner on Fri Dec 11 17:42:56 CST 2020
 */

package ui;

import control.Controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class GameMenuDialog extends JDialog {
    public GameMenuDialog(JFrame frame) {
        super(frame);
        initComponents();
        this.getRootPane ().setOpaque (false);
        this.getContentPane ().setBackground (new Color (0, 0, 0, 0));
        this.setBackground (new Color (0, 0, 0, 0));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panelMenu = new DialogPanel();

        //======== this ========
        setUndecorated(true);
        var contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, -30, 5));
        contentPane.add(panelMenu);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private DialogPanel panelMenu;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private class DialogPanel extends JPanel {
        private DialogPanel() {
            initComponents();
            backgroundGameMenu = (new ImageIcon(getClass().getResource("/resources/menuDialogBackground.png"))).getImage();

            buttonResumeImage = (new ImageIcon(getClass().getResource("/resources/buttonResume.png"))).getImage();
            buttonRestartImage = (new ImageIcon(getClass().getResource("/resources/buttonRestart.png"))).getImage();
            buttonBackImage = (new ImageIcon(getClass().getResource("/resources/buttonBack.png"))).getImage();

        }

        private void buttonResumeActionPerformed(ActionEvent e) {
            // TODO add your code here
            Controller.pause();
            JDialog d = (JDialog)this.getTopLevelAncestor();
            d.dispose();
        }

        private void buttonRestartActionPerformed(ActionEvent e) {
            // TODO add your code here
            Controller.w = new MainWindow();
            Controller.w.setVisible(true);
            Controller.f.dispose();
            Controller.f = new JFrame();
            Controller.f.setSize(900, 700);
            Controller.f.add(Controller.w);
            Controller.f.setVisible(true);
            Controller.initialize();
            Controller.run();
        }

        private void buttonBackActionPerformed(ActionEvent e) {
            // TODO add your code here
            Controller.f.dispose();
            Controller.f = new JFrame();
            StartWindow p = new StartWindow();
            Controller.f.setSize(700, 500);
            Controller.f.add(p);
            Controller.f.setVisible(true);
        }

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - unknown
            buttonResume = new JButton();
            buttonRestart = new JButton();
            buttonBack = new JButton();

            //======== this ========
            setPreferredSize(new Dimension(442, 280));
            setOpaque(false);
            setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
            . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder
            . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .
            awt . Font. BOLD ,12 ) ,java . awt. Color .red ) , getBorder () ) )
            ;  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
            ;
            setLayout(new FlowLayout(FlowLayout.LEFT, 80, 1));

            //---- buttonResume ----
            buttonResume.setPreferredSize(new Dimension(250, 84));
            buttonResume.setContentAreaFilled(false);
            buttonResume.setBorder(null);
            buttonResume.setIconTextGap(0);
            buttonResume.addActionListener(e -> buttonResumeActionPerformed(e));
            add(buttonResume);

            //---- buttonRestart ----
            buttonRestart.setPreferredSize(new Dimension(250, 84));
            buttonRestart.setContentAreaFilled(false);
            buttonRestart.setBorder(null);
            buttonRestart.setIconTextGap(0);
            buttonRestart.addActionListener(e -> buttonRestartActionPerformed(e));
            add(buttonRestart);

            //---- buttonBack ----
            buttonBack.setPreferredSize(new Dimension(250, 84));
            buttonBack.setContentAreaFilled(false);
            buttonBack.setBorder(null);
            buttonBack.setIconTextGap(0);
            buttonBack.addActionListener(e -> buttonBackActionPerformed(e));
            add(buttonBack);
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }

        // 重绘背景图
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundGameMenu, 0, 0, this.getWidth(), this.getHeight(), this);
            g.drawImage(buttonResumeImage, buttonResume.getX(), buttonResume.getY(), buttonResume.getWidth(), buttonResume.getHeight(), this);
            g.drawImage(buttonRestartImage, buttonRestart.getX(), buttonRestart.getY(), buttonRestart.getWidth(), buttonRestart.getHeight(), this);
            g.drawImage(buttonBackImage, buttonBack.getX(), buttonBack.getY(), buttonBack.getWidth(), buttonBack.getHeight(), this);
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - unknown
        private JButton buttonResume;
        private JButton buttonRestart;
        private JButton buttonBack;
        // JFormDesigner - End of variables declaration  //GEN-END:variables
        protected Image backgroundGameMenu;
        protected Image buttonResumeImage;
        protected Image buttonRestartImage;
        protected Image buttonBackImage;
    }
}
