/*
 * Created by JFormDesigner on Sun Dec 06 21:40:36 CST 2020
 */

package ui;

import control.Controller;
import control.TowerType;
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

        Image labelSelectImage = (new ImageIcon(getClass().getResource("/resources/addTower.png"))).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        Image labelCarrotImage = (new ImageIcon(getClass().getResource("/resources/carrot.png"))).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);

        Image buttonBottleImage = (new ImageIcon(getClass().getResource("/resources/bottleDark.png"))).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        Image buttonSunflowerImage = (new ImageIcon(getClass().getResource("/resources/sunflowerDark.png"))).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        Image buttonBottleEnableImage = (new ImageIcon(getClass().getResource("/resources/bottle.png"))).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        Image buttonSunflowerEnableImage = (new ImageIcon(getClass().getResource("/resources/sunflower.png"))).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);

        buttonPauseImage = (new ImageIcon(getClass().getResource("/resources/buttonPause.png"))).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        buttonPauseImage2 = (new ImageIcon(getClass().getResource("/resources/buttonPause2.png"))).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);

        Image buttonMenuImage = (new ImageIcon(getClass().getResource("/resources/buttonMenu.png"))).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);


        panelMap.setBounds(panelMenu.getX(), panelMenu.getY() + panelMenu.getHeight(), 880, 480);

        labelSelect.setIcon(new ImageIcon(labelSelectImage));
        labelCarrot.setIcon(new ImageIcon(labelCarrotImage));
        panelMap.add(labelSelect);

        buttonBottle.setDisabledIcon(new ImageIcon(buttonBottleImage));
        buttonBottle.setIcon(new ImageIcon(buttonBottleEnableImage));
        buttonSunflower.setDisabledIcon(new ImageIcon(buttonSunflowerImage));
        buttonSunflower.setIcon(new ImageIcon(buttonSunflowerEnableImage));

        buttonPause.setIcon(new ImageIcon(buttonPauseImage));
        buttonMenu.setIcon(new ImageIcon(buttonMenuImage));

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
            if(Controller.isPositionAvailable(row, column)){
                // 取消选择
                if(labelSelect.isVisible() && labelSelect.getX() == column*80 && labelSelect.getY() == row*80){
                    labelSelect.setVisible(false);
                    buttonBottle.setEnabled(false);
                    buttonSunflower.setEnabled(false);
                }
                else{
                    labelSelect.setLocation(column*80, row*80);
                    labelSelect.setVisible(true);
                    buttonBottle.setEnabled(Controller.isTowerAvailable(TowerType.BOTTLE));
                    buttonSunflower.setEnabled(Controller.isTowerAvailable(TowerType.SUNFLOWER));
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

    private void buttonPauseActionPerformed(ActionEvent e) {
        // TODO add your code here
        Controller.pause();
        if(Controller.isGamePause()){
            buttonPause.setIcon(new ImageIcon(buttonPauseImage2));
        }
        else{
            buttonPause.setIcon(new ImageIcon(buttonPauseImage));
        }

    }

    private void buttonMenuActionPerformed(ActionEvent e) {
        // TODO add your code here
        GameMenuDialog gameMenuDialog = new GameMenuDialog(Controller.f);
        Controller.pause();
        gameMenuDialog.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panelMenu = new JPanel();
        labelMoney = new JLabel();
        labelWave = new JLabel();
        panelButton = new JPanel();
        buttonPause = new JButton();
        buttonMenu = new JButton();
        panelMap = new JPanel();
        panelCarrot = new JPanel();
        progressBarCarrot = new JProgressBar();
        labelCarrot = new JLabel();
        labelSelect = new JLabel();
        panelTower = new JPanel();
        buttonBottle = new JButton();
        buttonSunflower = new JButton();
        buttonDelete = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(880, 620));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
        border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER
        , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font
        .BOLD ,12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (
        new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r"
        .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        //======== panelMenu ========
        {
            panelMenu.setOpaque(false);
            panelMenu.setPreferredSize(new Dimension(880, 60));
            panelMenu.setLayout(new GridLayout(1, 3, 100, 10));

            //---- labelMoney ----
            labelMoney.setHorizontalAlignment(SwingConstants.CENTER);
            labelMoney.setText("100");
            labelMoney.setForeground(Color.white);
            labelMoney.setFont(new Font("\u65b9\u6b63\u8212\u4f53", Font.BOLD, 24));
            labelMoney.setHorizontalTextPosition(SwingConstants.CENTER);
            labelMoney.setAlignmentX(0.5F);
            panelMenu.add(labelMoney);

            //---- labelWave ----
            labelWave.setText("01\u6ce2/10\u6ce2\u602a\u7269");
            labelWave.setFont(new Font("\u65b9\u6b63\u8212\u4f53", Font.BOLD, 24));
            labelWave.setForeground(Color.white);
            labelWave.setHorizontalTextPosition(SwingConstants.CENTER);
            labelWave.setAlignmentX(0.5F);
            labelWave.setHorizontalAlignment(SwingConstants.CENTER);
            panelMenu.add(labelWave);

            //======== panelButton ========
            {
                panelButton.setOpaque(false);
                panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

                //---- buttonPause ----
                buttonPause.setIconTextGap(0);
                buttonPause.setBorder(null);
                buttonPause.setContentAreaFilled(false);
                buttonPause.setPreferredSize(new Dimension(40, 40));
                buttonPause.addActionListener(e -> buttonPauseActionPerformed(e));
                panelButton.add(buttonPause);

                //---- buttonMenu ----
                buttonMenu.setIconTextGap(0);
                buttonMenu.setBorder(null);
                buttonMenu.setContentAreaFilled(false);
                buttonMenu.setPreferredSize(new Dimension(40, 40));
                buttonMenu.addActionListener(e -> buttonMenuActionPerformed(e));
                panelButton.add(buttonMenu);
            }
            panelMenu.add(panelButton);
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

            //======== panelCarrot ========
            {
                panelCarrot.setPreferredSize(new Dimension(80, 80));
                panelCarrot.setOpaque(false);
                panelCarrot.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

                //---- progressBarCarrot ----
                progressBarCarrot.setPreferredSize(new Dimension(80, 10));
                progressBarCarrot.setMaximum(10);
                progressBarCarrot.setForeground(Color.green);
                progressBarCarrot.setValue(10);
                panelCarrot.add(progressBarCarrot);

                //---- labelCarrot ----
                labelCarrot.setAlignmentX(0.5F);
                labelCarrot.setIconTextGap(0);
                labelCarrot.setBorder(null);
                labelCarrot.setPreferredSize(new Dimension(70, 70));
                panelCarrot.add(labelCarrot);
            }
            panelMap.add(panelCarrot);
            panelCarrot.setBounds(new Rectangle(new Point(800, 160), panelCarrot.getPreferredSize()));

            //---- labelSelect ----
            labelSelect.setAlignmentX(0.5F);
            labelSelect.setIconTextGap(0);
            labelSelect.setBorder(null);
            labelSelect.setPreferredSize(new Dimension(80, 80));
            labelSelect.setVisible(false);
            panelMap.add(labelSelect);
            labelSelect.setBounds(0, 0, 80, 80);
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
//        g.drawImage(menuWaveImg, panelWave.getX(), panelWave.getY(), panelWave.getWidth(), panelWave.getHeight(), this);
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
    public JLabel labelMoney;
    public JLabel labelWave;
    public JPanel panelButton;
    private JButton buttonPause;
    private JButton buttonMenu;
    public JPanel panelMap;
    public JPanel panelCarrot;
    public JProgressBar progressBarCarrot;
    public JLabel labelCarrot;
    public JLabel labelSelect;
    public JPanel panelTower;
    private JButton buttonBottle;
    private JButton buttonSunflower;
    private JButton buttonDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private final Image backgroundMenuImg;
    private final Image backgroundMapImg;

    private Image buttonPauseImage;
    private Image buttonPauseImage2;

    private ArrayList<JButton> availableTowerButton;
}
