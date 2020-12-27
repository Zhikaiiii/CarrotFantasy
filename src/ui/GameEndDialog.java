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
        panelDialog.setBounds(0, 0, 480, 320);
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
        setMinimumSize(new Dimension(480, 360));
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
            backgroundGameWin = (new ImageIcon(getClass().getResource("/resources/Background/winBackground.png"))).getImage();
            backgroundGameLost = (new ImageIcon(getClass().getResource("/resources/Background/loseBackground.png"))).getImage();
            buttonRestartImage = (new ImageIcon(getClass().getResource("/resources/Items/buttonRestart.png"))).getImage();
            buttonBackImage = (new ImageIcon(getClass().getResource("/resources/Items/buttonBack.png"))).getImage();

            String text = "";
            if(Controller.getCurrWave() < 10){
                text = "0 " + Controller.getCurrWave()+ "   " + Controller.getNumWave();
            }
            else{
                text = Controller.getCurrWave()/10 + " " + Controller.getCurrWave()%10 + "   " + Controller.getNumWave();
            }
            labelInfo.setText(text);
        }

        private void buttonExitActionPerformed(ActionEvent e) {
            // TODO add your code here
            Controller.f.dispose();
            Controller.f = new JFrame();
            StartWindow p = new StartWindow();
            Controller.f.setSize(700, 500);
            Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) screensize.getWidth() / 2 - Controller.f.getWidth() / 2;
            int y = (int) screensize.getHeight() / 2 - Controller.f.getHeight() / 2;
            Controller.f.setLocation(x, y);
            Controller.f.add(p);
            Controller.f.setVisible(true);
            Controller.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        private void buttonRestartActionPerformed(ActionEvent e) {
            // TODO add your code here
            Controller.gameEndFlag = true;
            Controller.gamePauseFlag = false;
            Controller.start();
        }

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - unknown
            labelInfo = new JLabel();
            buttonRestart = new JButton();
            buttonExit = new JButton();

            //======== this ========
            setMinimumSize(new Dimension(480, 320));
            setPreferredSize(new Dimension(480, 320));
            setOpaque(false);
            setLayout(null);

            //---- labelInfo ----
            labelInfo.setPreferredSize(new Dimension(300, 30));
            labelInfo.setHorizontalAlignment(SwingConstants.CENTER);
            labelInfo.setForeground(Color.white);
            labelInfo.setFont(new Font("\u5e7c\u5706", Font.BOLD, 20));
            add(labelInfo);
            labelInfo.setBounds(145, 125, 215, labelInfo.getPreferredSize().height);

            //---- buttonRestart ----
            buttonRestart.setPreferredSize(new Dimension(120, 30));
            buttonRestart.setBorder(null);
            buttonRestart.setContentAreaFilled(false);
            buttonRestart.setIconTextGap(0);
            buttonRestart.addActionListener(e -> buttonRestartActionPerformed(e));
            add(buttonRestart);
            buttonRestart.setBounds(240, 200, 145, 45);

            //---- buttonExit ----
            buttonExit.setPreferredSize(new Dimension(120, 30));
            buttonExit.setContentAreaFilled(false);
            buttonExit.setBorder(null);
            buttonExit.setIconTextGap(0);
            buttonExit.addActionListener(e -> buttonExitActionPerformed(e));
            add(buttonExit);
            buttonExit.setBounds(55, 200, 145, 45);
            // JFormDesigner - End of component initialization  //GEN-END:initComponents
        }
        // 重绘背景图
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(status){
                g.drawImage(backgroundGameWin, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this);
            }
            else{
                g.drawImage(backgroundGameLost, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this);
            }
//            g.drawImage(buttonRestartImage, buttonRestart.getX(), buttonRestart.getY(), buttonRestart.getWidth(), buttonRestart.getHeight(), this);
//            g.drawImage(buttonBackImage, buttonExit.getX(), buttonExit.getY(), buttonExit.getWidth(), buttonExit.getHeight(), this);
        }
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - unknown
        private JLabel labelInfo;
        private JButton buttonRestart;
        private JButton buttonExit;
        // JFormDesigner - End of variables declaration  //GEN-END:variables

        protected Image backgroundGameWin;
        protected Image backgroundGameLost;

        protected Image buttonRestartImage;
        protected Image buttonNextImage;
        protected Image buttonBackImage;

        protected boolean status;
    }
}

