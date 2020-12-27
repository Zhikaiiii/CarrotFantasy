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
            backgroundGameMenu = (new ImageIcon(getClass().getResource("/resources/Background/menuDialogBackground.png"))).getImage();
        }

        private void buttonResumeActionPerformed(ActionEvent e) {
            // TODO add your code here
            Controller.pause();
            Controller.w.buttonPause.setEnabled(true);
            Controller.w.buttonMenu.setEnabled(true);
            JDialog d = (JDialog)this.getTopLevelAncestor();
            d.dispose();
        }

        private void buttonRestartActionPerformed(ActionEvent e) {
            // TODO add your code here
            // 让上一次游戏的线程退出
            Controller.gameEndFlag = true;
            Controller.gamePauseFlag = false;
            // 重新初始化窗口
            Controller.start();
        }

        private void buttonBackActionPerformed(ActionEvent e) {
            // TODO add your code here
            Controller.gameEndFlag = true;
            Controller.gamePauseFlag = false;
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

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - unknown
            buttonResume = new JButton();
            buttonRestart = new JButton();
            buttonBack = new JButton();

            //======== this ========
            setPreferredSize(new Dimension(442, 280));
            setOpaque(false);
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
