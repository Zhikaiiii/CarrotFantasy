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
        backgroundImg = (new ImageIcon(getClass().getResource("/resources/Background/background.png"))).getImage();
        buttonSelectImg = (new ImageIcon(getClass().getResource("/resources/Items/buttonSelect.png"))).getImage();
        initComponents();
        buttonSelect.setIcon(new ImageIcon(buttonSelectImg));
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
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screensize.getWidth() / 2 - Controller.f.getWidth() / 2;
        int y = (int) screensize.getHeight() / 2 - Controller.f.getHeight() / 2;
        Controller.f.setLocation(x, y);
        Controller.f.add(s);
        Controller.f.setVisible(true);
        Controller.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panelButton = new JPanel();
        buttonSelect = new JButton();

        //======== this ========
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 200));

        //======== panelButton ========
        {
            panelButton.setOpaque(false);
            panelButton.setLayout(new FlowLayout());

            //---- buttonSelect ----
            buttonSelect.setPreferredSize(new Dimension(152, 50));
            buttonSelect.setIconTextGap(0);
            buttonSelect.setContentAreaFilled(false);
            buttonSelect.setBorderPainted(false);
            buttonSelect.setBorder(null);
            buttonSelect.setIcon(null);
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
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screensize.getWidth() / 2 - Controller.f.getWidth() / 2;
        int y = (int) screensize.getHeight() / 2 - Controller.f.getHeight() / 2;
        Controller.f.setLocation(x, y);
        Controller.f.add(p);
        Controller.f.setVisible(true);
        Controller.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - unknown
        private JPanel panelButton;
        private JButton buttonSelect;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private final Image backgroundImg;
    private final Image buttonSelectImg;
}
