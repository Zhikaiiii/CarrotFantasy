/*
 * Created by JFormDesigner on Wed Dec 16 20:33:26 CST 2020
 */

package ui;

import control.Controller;
import control.Difficulty;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class LevelSelectWindow extends JPanel {
    public LevelSelectWindow() {
        initComponents();
        levelImg = new Image[3];
        levelImg[0] = (new ImageIcon(getClass().getResource("/resources/selectMap1.png"))).getImage().getScaledInstance(250, 170, Image.SCALE_DEFAULT);
        levelImg[1] = (new ImageIcon(getClass().getResource("/resources/selectMap2.png"))).getImage().getScaledInstance(250, 170, Image.SCALE_DEFAULT);
        levelImg[2] = (new ImageIcon(getClass().getResource("/resources/selectMap3.png"))).getImage().getScaledInstance(250, 170, Image.SCALE_DEFAULT);
        levelText = new String[3];
        levelText[0] = "\u7b80\u5355";
        levelText[1] = "\u4e2d\u7b49";
        levelText[2] = "\u56f0\u96be";
        backgroundImg = (new ImageIcon(getClass().getResource("/resources/selectBackground.png"))).getImage();
        buttonLeftImg = (new ImageIcon(getClass().getResource("/resources/selectLeft.png"))).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        buttonRightImg = (new ImageIcon(getClass().getResource("/resources/selectRight.png"))).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        buttonStartImg = (new ImageIcon(getClass().getResource("/resources/selectStart.png"))).getImage().getScaledInstance(120, 45, Image.SCALE_DEFAULT);
        buttonBackImg = (new ImageIcon(getClass().getResource("/resources/buttonBack.png"))).getImage().getScaledInstance(120, 45, Image.SCALE_DEFAULT);

        buttonLeft.setIcon(new ImageIcon(buttonLeftImg));
        buttonRight.setIcon(new ImageIcon(buttonRightImg));
        buttonStart.setIcon(new ImageIcon(buttonStartImg));
        buttonBack.setIcon(new ImageIcon(buttonBackImg));

        labelLevel.setIcon(new ImageIcon(levelImg[0]));
        currDifficulty = Difficulty.EASY;
//        labelLevel2.setIcon(new ImageIcon(levelImg2));
//        labelLevel3.setIcon(new ImageIcon(levelImg3));
    }
    

    private void buttonLeftActionPerformed(ActionEvent e) {
        // TODO add your code here
        currDifficulty = Difficulty.values()[(currDifficulty.ordinal() - 1)];
        if(currDifficulty == Difficulty.EASY){
            buttonLeft.setEnabled(false);
        }
        buttonRight.setEnabled(true);
        labelLevel.setIcon(new ImageIcon(levelImg[currDifficulty.ordinal()]));
        labelLevel.setText(levelText[currDifficulty.ordinal()]);
    }

    private void buttonRightActionPerformed(ActionEvent e) {
        // TODO add your code here
        currDifficulty = Difficulty.values()[(currDifficulty.ordinal() + 1)];
        if(currDifficulty == Difficulty.DIFFICULTY){
            buttonRight.setEnabled(false);
        }
        buttonLeft.setEnabled(true);
        labelLevel.setIcon(new ImageIcon(levelImg[currDifficulty.ordinal()]));
        labelLevel.setText(levelText[currDifficulty.ordinal()]);
    }

    private void buttonStartMouseClicked(MouseEvent e) {
        // TODO add your code here
        Controller.setDifficulty(currDifficulty);
        Controller.start();
    }

    private void buttonBackActionPerformed(ActionEvent e) {
        // TODO add your code here
        Controller.f.dispose();
        Controller.f = new JFrame();
        StartWindow s = new StartWindow();
        Controller.f.setSize(700, 500);
        Controller.f.add(s);
        Controller.f.setVisible(true);
    }

//    private void labelLevelMouseClicked(MouseEvent e) {
//        // TODO add your code here
//
//        if(e.getSource() == labelLevel1){
//            Controller.setDifficulty(Difficulty.EASY);
//        }
//        else if(e.getSource() == labelLevel2){
//            Controller.setDifficulty(Difficulty.MEDIUM);
//        }
//        else{
//            Controller.setDifficulty(Difficulty.DIFFICULTY);
//        }
//        Controller.start();
//    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panelLevel = new JPanel();
        buttonLeft = new JButton();
        labelLevel = new JLabel();
        buttonRight = new JButton();
        panelButton = new JPanel();
        buttonBack = new JButton();
        buttonStart = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(650, 450));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
        javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax
        . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
        . awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt
        . Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans .
        PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "bord\u0065r" .
        equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));

        //======== panelLevel ========
        {
            panelLevel.setPreferredSize(new Dimension(630, 200));
            panelLevel.setOpaque(false);
            panelLevel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

            //---- buttonLeft ----
            buttonLeft.setContentAreaFilled(false);
            buttonLeft.setIconTextGap(0);
            buttonLeft.setBorder(null);
            buttonLeft.setPreferredSize(new Dimension(40, 40));
            buttonLeft.addActionListener(e -> buttonLeftActionPerformed(e));
            panelLevel.add(buttonLeft);

            //---- labelLevel ----
            labelLevel.setText("\u7b80\u5355");
            labelLevel.setPreferredSize(new Dimension(250, 200));
            labelLevel.setFont(new Font("\u5e7c\u5706", Font.BOLD, 20));
            labelLevel.setHorizontalAlignment(SwingConstants.CENTER);
            labelLevel.setHorizontalTextPosition(SwingConstants.CENTER);
            labelLevel.setVerticalTextPosition(SwingConstants.BOTTOM);
            labelLevel.setForeground(Color.white);
            panelLevel.add(labelLevel);

            //---- buttonRight ----
            buttonRight.setContentAreaFilled(false);
            buttonRight.setIconTextGap(0);
            buttonRight.setBorder(null);
            buttonRight.setPreferredSize(new Dimension(40, 40));
            buttonRight.addActionListener(e -> buttonRightActionPerformed(e));
            panelLevel.add(buttonRight);
        }
        add(panelLevel);

        //======== panelButton ========
        {
            panelButton.setPreferredSize(new Dimension(630, 100));
            panelButton.setOpaque(false);
            panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

            //---- buttonBack ----
            buttonBack.setBorder(null);
            buttonBack.setIconTextGap(0);
            buttonBack.setContentAreaFilled(false);
            buttonBack.setPreferredSize(new Dimension(120, 45));
            buttonBack.addActionListener(e -> buttonBackActionPerformed(e));
            panelButton.add(buttonBack);

            //---- buttonStart ----
            buttonStart.setBorder(null);
            buttonStart.setIconTextGap(0);
            buttonStart.setContentAreaFilled(false);
            buttonStart.setPreferredSize(new Dimension(120, 45));
            buttonStart.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonStartMouseClicked(e);
                }
            });
            panelButton.add(buttonStart);
        }
        add(panelButton);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
        g.drawImage(backgroundImg, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panelLevel;
    private JButton buttonLeft;
    private JLabel labelLevel;
    private JButton buttonRight;
    private JPanel panelButton;
    private JButton buttonBack;
    private JButton buttonStart;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private final Image [] levelImg;
    private final String [] levelText;
    private final Image backgroundImg;
    private final Image buttonLeftImg;
    private final Image buttonRightImg;
    private final Image buttonStartImg;
    private final Image buttonBackImg;
    private Difficulty currDifficulty;
}
