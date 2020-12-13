/*
 * Created by JFormDesigner on Fri Dec 11 11:23:45 CST 2020
 */

package ui;

import java.awt.event.*;
import control.Controller;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class GameEndDialog extends JDialog {
    public GameEndDialog(JFrame frame) {
        super(frame);
        initComponents();
        this.getRootPane ().setOpaque (false);
        this.getContentPane ().setBackground (new Color (0, 0, 0, 0));
        this.setBackground (new Color (0, 0, 0, 0));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panelDialog = new DialogPanel();

        //======== this ========
        setUndecorated(true);
        setMinimumSize(new Dimension(400, 300));
        var contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        contentPane.add(panelDialog);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public void setStatus(boolean status){
        panelDialog.status = status;
//        this.getRootPane().getGraphics().drawImage(currImage, 0, 0, this.getWidth(), this.getHeight(), this);
        this.setVisible(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private DialogPanel panelDialog;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private class DialogPanel extends JPanel {
        private DialogPanel() {
            initComponents();
            status = false;
            backgroundGameWin = (new ImageIcon(getClass().getResource("/resources/winBackground.png"))).getImage();
            backgroundGameLost = (new ImageIcon(getClass().getResource("/resources/loseBackground.png"))).getImage();
            buttonRestartImage = (new ImageIcon(getClass().getResource("/resources/buttonRestart.png"))).getImage();
            buttonBackImage = (new ImageIcon(getClass().getResource("/resources/buttonBack.png"))).getImage();
        }

        private void buttonExitActionPerformed(ActionEvent e) {
            // TODO add your code here
            Controller.f.dispose();
            Controller.f = new JFrame();
            StartWindow p = new StartWindow();
            Controller.f.setSize(700, 500);
            Controller.f.add(p);
            Controller.f.setVisible(true);
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

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - unknown
            label1 = new JLabel();
            buttonExit = new JButton();
            buttonRestart = new JButton();

            //======== this ========
            setMinimumSize(new Dimension(400, 270));
            setPreferredSize(new Dimension(400, 270));
            setOpaque(false);
            setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .
            EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax . swing
            . border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,
            java . awt. Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( )
            { @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )
            throw new RuntimeException( ) ;} } );
            setLayout(new FlowLayout(FlowLayout.CENTER, 15, 70));

            //---- label1 ----
            label1.setPreferredSize(new Dimension(300, 30));
            add(label1);

            //---- buttonExit ----
            buttonExit.setPreferredSize(new Dimension(120, 40));
            buttonExit.setContentAreaFilled(false);
            buttonExit.setBorder(null);
            buttonExit.setIconTextGap(0);
            buttonExit.addActionListener(e -> buttonExitActionPerformed(e));
            add(buttonExit);

            //---- buttonRestart ----
            buttonRestart.setPreferredSize(new Dimension(120, 40));
            buttonRestart.setBorder(null);
            buttonRestart.setContentAreaFilled(false);
            buttonRestart.setIconTextGap(0);
            buttonRestart.addActionListener(e -> buttonRestartActionPerformed(e));
            add(buttonRestart);
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }
        // 重绘背景图
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(status){
                g.drawImage(backgroundGameWin, 0, 0, this.getWidth(), this.getHeight(), this);
            }
            else{
                g.drawImage(backgroundGameLost, 0, 0, this.getWidth(), this.getHeight(), this);
            }
            g.drawImage(buttonRestartImage, buttonRestart.getX(), buttonRestart.getY(), buttonRestart.getWidth(), buttonRestart.getHeight(), this);
            g.drawImage(buttonBackImage, buttonExit.getX(), buttonExit.getY(), buttonExit.getWidth(), buttonExit.getHeight(), this);
        }
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - unknown
        private JLabel label1;
        private JButton buttonExit;
        private JButton buttonRestart;
        // JFormDesigner - End of variables declaration  //GEN-END:variables

        protected Image backgroundGameWin;
        protected Image backgroundGameLost;

        protected Image buttonRestartImage;
        protected Image buttonNextImage;
        protected Image buttonBackImage;

        protected boolean status;
    }
}
