/*
 * Created by JFormDesigner on Sun Dec 06 21:40:36 CST 2020
 */

package ui;

import control.Controller;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @author unknown
 */
public class MainWindow extends JPanel {
    public MainWindow() {
        initComponents();

        backgroundMenuImg = (new ImageIcon(getClass().getResource("/resources/menuBackground.png"))).getImage();
        backgroundMapImg = (new ImageIcon(getClass().getResource("/resources/mapBackground.png"))).getImage();
//        backgroundTowerImg = (new ImageIcon(getClass().getResource("/resources/towerBackground.png"))).getImage();
        labelSelectImage = (new ImageIcon(getClass().getResource("/resources/addTower.png"))).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        buttonBottleImage = (new ImageIcon(getClass().getResource("/resources/bottleDark.png"))).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        buttonSunflowerImage = (new ImageIcon(getClass().getResource("/resources/sunflowerDark.png"))).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        buttonBottleEnableImage = (new ImageIcon(getClass().getResource("/resources/bottle.png"))).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        buttonSunflowerEnableImage = (new ImageIcon(getClass().getResource("/resources/sunflower.png"))).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        panelMap.setBounds(panelMenu.getX(), panelMenu.getY() + panelMenu.getHeight(), 880, 480);
        labelSelect = new JLabel();
        labelSelect.setBounds(0, 0, 80, 80);
        labelSelect.setIcon(new ImageIcon(labelSelectImage));
        labelSelect.setVisible(false);
        buttonBottle.setDisabledIcon(new ImageIcon(buttonBottleImage));
        buttonBottle.setIcon(new ImageIcon(buttonBottleEnableImage));
        buttonSunflower.setDisabledIcon(new ImageIcon(buttonSunflowerImage));
        buttonSunflower.setIcon(new ImageIcon(buttonSunflowerEnableImage));
        panelMap.add(labelSelect);
    }

    private void panelMapMouseClicked(MouseEvent e) {
        // TODO add your code here
        // 点击左键
        if(e.getButton() == MouseEvent.BUTTON1){
            // 得到鼠标坐标
            int x = e.getX();
            int y = e.getY();
            int row = y / 80;
            int column = x / 80;
            // 可以放置防御塔
            if(Controller.isAvailable(row, column)){
                // 取消选择
                if(labelSelect.isVisible() && labelSelect.getX() == column*80 && labelSelect.getY() == row*80){
                    labelSelect.setVisible(false);
                    buttonBottle.setEnabled(false);
                    buttonSunflower.setEnabled(false);
                }
                else{
                    labelSelect.setLocation(column*80, row*80);
                    labelSelect.setVisible(true);
                    buttonBottle.setEnabled(true);
                    buttonSunflower.setEnabled(true);
                }
            }
            else{
                labelSelect.setVisible(false);
                buttonBottle.setEnabled(false);
                buttonSunflower.setEnabled(false);
            }
        }
    }

    private void buttonTowerActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (e.getSource() == buttonDelete){
            int column = labelSelect.getX() / 80;
            int row = labelSelect.getY() / 80;
        }
        else{
            int column = labelSelect.getX() / 80;
            int row = labelSelect.getY() / 80;
            if(e.getSource() == buttonSunflower){
                Controller.addTower(1, column, row);
            }
            else{
                Controller.addTower(0, column, row);
            }
            buttonSunflower.setEnabled(false);
            buttonBottle.setEnabled(false);
        }


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panelMenu = new JPanel();
        panelMap = new JPanel();
        panelTower = new JPanel();
        buttonBottle = new JButton();
        buttonSunflower = new JButton();
        buttonDelete = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(880, 620));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
        . swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing
        . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
        Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
        ) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
        public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName (
        ) )) throw new RuntimeException( ); }} );
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        //======== panelMenu ========
        {
            panelMenu.setOpaque(false);
            panelMenu.setPreferredSize(new Dimension(880, 60));
            panelMenu.setLayout(new FlowLayout());
        }
        add(panelMenu);

        //======== panelMap ========
        {
            panelMap.setPreferredSize(new Dimension(880, 480));
            panelMap.setOpaque(false);
            panelMap.setMinimumSize(new Dimension(880, 480));
            panelMap.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    panelMapMouseClicked(e);
                }
            });
            panelMap.setLayout(null);
        }
        add(panelMap);

        //======== panelTower ========
        {
            panelTower.setPreferredSize(new Dimension(880, 100));
            panelTower.setOpaque(false);
            panelTower.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

            //---- buttonBottle ----
            buttonBottle.setPreferredSize(new Dimension(100, 100));
            buttonBottle.setContentAreaFilled(false);
            buttonBottle.setIconTextGap(0);
            buttonBottle.setBorder(null);
            buttonBottle.setEnabled(false);
            buttonBottle.addActionListener(e -> buttonTowerActionPerformed(e));
            panelTower.add(buttonBottle);

            //---- buttonSunflower ----
            buttonSunflower.setPreferredSize(new Dimension(100, 100));
            buttonSunflower.setContentAreaFilled(false);
            buttonSunflower.setIconTextGap(0);
            buttonSunflower.setBorder(null);
            buttonSunflower.setEnabled(false);
            panelTower.add(buttonSunflower);

            //---- buttonDelete ----
            buttonDelete.setPreferredSize(new Dimension(100, 100));
            buttonDelete.setContentAreaFilled(false);
            buttonDelete.setIconTextGap(0);
            buttonDelete.setBorder(null);
            buttonDelete.setEnabled(false);
            panelTower.add(buttonDelete);
        }
        add(panelTower);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // 重绘背景图
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundMenuImg, panelMenu.getX(), panelMenu.getY(), panelMenu.getWidth(), panelMenu.getHeight(), this);
        g.drawImage(backgroundMapImg, panelMap.getX(), panelMap.getY(), panelMap.getWidth(), panelMap.getHeight(), this);
//        g.drawImage(backgroundTowerImg, panelTower.getX(), panelTower.getY(), panelTower.getWidth(), panelTower.getHeight(), this);
    }

    // 添加怪物
    public JPanel addMonster(int x, int y){
        JPanel monsterPanel = new JPanel();
        monsterPanel.setBounds(x, y, 80, 80);
        monsterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        monsterPanel.setOpaque(false);

        JProgressBar monsterProgressBar = new JProgressBar(0, Controller.level.getMonsterHP());
        monsterProgressBar.setPreferredSize(new Dimension(80, 10));
        monsterProgressBar.setValue(Controller.level.getMonsterHP());
        monsterProgressBar.setForeground(Color.GREEN);

        JLabel monsterLabel = new JLabel();
        monsterLabel.setPreferredSize(new Dimension(70, 70));
        monsterLabel.setOpaque(false);
        monsterLabel.setBorder(null);
        monsterLabel.setIconTextGap(0);
        monsterLabel.setIcon(new ImageIcon(getClass().getResource("/resources/monster.png")));

        monsterProgressBar.setVisible(true);
        monsterLabel.setVisible(true);
        monsterPanel.setVisible(true);

        monsterPanel.add(monsterProgressBar);
        monsterPanel.add(monsterLabel);
        panelMap.add(monsterPanel);
        panelMap.updateUI();
        return monsterPanel;
    }

    // 添加防御塔
    public JLabel addTower(int x, int y, int type){
        JLabel towerLabel = new JLabel();
        towerLabel.setBounds(x, y, 80, 80);
        towerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        towerLabel.setOpaque(false);
        towerLabel.setBorder(null);
        towerLabel.setIconTextGap(0);
        if(type == 0){
            towerLabel.setIcon(new ImageIcon(getClass().getResource("/resources/bottlePlace.png")));
        }
        towerLabel.setVisible(true);
        labelSelect.setVisible(false);
        panelMap.add(towerLabel);
        return towerLabel;
    }

    // 添加子弹
    public JLabel addBullet(int x, int y){
        JLabel bulletLabel = new JLabel();
        bulletLabel.setBounds(x, y, 80, 80);
        bulletLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bulletLabel.setOpaque(false);
        bulletLabel.setBorder(null);
        bulletLabel.setIconTextGap(0);
        bulletLabel.setIcon(new ImageIcon(getClass().getResource("/resources/bullet.png")));
        bulletLabel.setVisible(true);
        panelMap.add(bulletLabel);
        panelMap.updateUI();
        return bulletLabel;
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    public JPanel panelMenu;
    public JPanel panelMap;
    private JPanel panelTower;
    private JButton buttonBottle;
    private JButton buttonSunflower;
    private JButton buttonDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Image backgroundMenuImg;
    private Image backgroundMapImg;

    private Image labelSelectImage;
    private Image buttonBottleImage;
    private Image buttonSunflowerImage;
    private Image buttonBottleEnableImage;
    private Image buttonSunflowerEnableImage;

    private JLabel labelSelect;
    private ArrayList<JButton> availableTowerButton;
}
