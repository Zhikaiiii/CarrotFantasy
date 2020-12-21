/*
 * Created by JFormDesigner on Sun Dec 06 21:40:36 CST 2020
 */

package ui;

import control.Controller;
import control.Difficulty;
import control.MapElement;
import control.TowerType;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class MainWindow extends JPanel {
    public MainWindow() {
        initComponents();
        backgroundMenuImg = (new ImageIcon(getClass().getResource("/resources/menuBackground.png"))).getImage();
        backgroundMapImg = (new ImageIcon(getClass().getResource("/resources/mapBackground1.png"))).getImage();
        fireImg = (new ImageIcon(getClass().getResource("/resources/fire.png"))).getImage();
        towerBottleImg = (new ImageIcon(getClass().getResource("/resources/bottlePlace.png"))).getImage();
        towerSunflowerImg = (new ImageIcon(getClass().getResource("/resources/sunflowerPlace.png"))).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        bottleHitImg = (new ImageIcon(getClass().getResource("/resources/bottleAttack.png"))).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        sunflowerHitImg = (new ImageIcon(getClass().getResource("/resources/sunflowerAttack.png"))).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        labelCarrotImg = (new ImageIcon(getClass().getResource("/resources/carrot.png"))).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        labelCarrotHitImg = (new ImageIcon(getClass().getResource("/resources/carrotAttack.png"))).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        forbiddenImg = (new ImageIcon(getClass().getResource("/resources/forbidden.png"))).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);

        Image labelSelectImage = (new ImageIcon(getClass().getResource("/resources/addTower.png"))).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        Image buttonBottleImage = (new ImageIcon(getClass().getResource("/resources/bottleDark.png"))).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        Image buttonSunflowerImage = (new ImageIcon(getClass().getResource("/resources/sunflowerDark.png"))).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        Image buttonBottleEnableImage = (new ImageIcon(getClass().getResource("/resources/bottle.png"))).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        Image buttonSunflowerEnableImage = (new ImageIcon(getClass().getResource("/resources/sunflower.png"))).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        Image buttonDeleteImage = (new ImageIcon(getClass().getResource("/resources/sell.png"))).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        Image buttonMenuImage = (new ImageIcon(getClass().getResource("/resources/buttonMenu.png"))).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);

        buttonPauseImg = (new ImageIcon(getClass().getResource("/resources/buttonPause.png"))).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        buttonPauseImg2 = (new ImageIcon(getClass().getResource("/resources/buttonPause2.png"))).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);

        panelMap.setBounds(panelMenu.getX(), panelMenu.getY() + panelMenu.getHeight(), 880, 480);

        labelSelect.setIcon(new ImageIcon(labelSelectImage));
        labelCarrot.setIcon(new ImageIcon(labelCarrotImg));
        panelMap.add(labelSelect);

        buttonBottle.setDisabledIcon(new ImageIcon(buttonBottleImage));
        buttonBottle.setIcon(new ImageIcon(buttonBottleEnableImage));
        buttonSunflower.setDisabledIcon(new ImageIcon(buttonSunflowerImage));
        buttonSunflower.setIcon(new ImageIcon(buttonSunflowerEnableImage));
        buttonDelete.setIcon(new ImageIcon(buttonDeleteImage));

        buttonPause.setIcon(new ImageIcon(buttonPauseImg));
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
            if(Controller.getMapElement(row, column) == MapElement.EMPTY){
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
            else if(Controller.getMapElement(row, column) == MapElement.HAVETOWER){
                buttonDelete.setEnabled(true);
                towerDeleteX = column * 80;
                towerDeleteY = row * 80;
            }
            else if(Controller.getMapElement(row, column) == MapElement.BARRIER){
                new Thread(() -> {
                    JLabel forbiddenLabel = new JLabel();
                    forbiddenLabel.setBounds(column*80, row*80, 80, 80);
                    forbiddenLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    forbiddenLabel.setOpaque(false);
                    forbiddenLabel.setBorder(null);
                    forbiddenLabel.setIconTextGap(0);
                    forbiddenLabel.setIcon(new ImageIcon(forbiddenImg));
                    forbiddenLabel.setVisible(true);
                    panelMap.add(forbiddenLabel);
                    panelMap.updateUI();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    forbiddenLabel.setVisible(false);
                    panelMap.remove(forbiddenLabel);
                }).start();//开启线程
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
        int column = labelSelect.getX() / 80;
        int row = labelSelect.getY() / 80;
        if(e.getSource() == buttonSunflower){
            Controller.addTower(TowerType.SUNFLOWER, column, row);
        }
        else{
            Controller.addTower(TowerType.BOTTLE, column, row);
        }
        buttonSunflower.setEnabled(false);
        buttonBottle.setEnabled(false);
    }

    private void buttonPauseActionPerformed(ActionEvent e) {
        // TODO add your code here
        Controller.pause();
        if(Controller.isGamePause()){
            buttonPause.setIcon(new ImageIcon(buttonPauseImg2));
            buttonMenu.setEnabled(false);
        }
        else{
            buttonPause.setIcon(new ImageIcon(buttonPauseImg));
            buttonMenu.setEnabled(true);
        }

    }

    private void buttonMenuActionPerformed(ActionEvent e) {
        // TODO add your code here
        GameMenuDialog gameMenuDialog = new GameMenuDialog(Controller.f);
        Controller.pause();
        buttonPause.setEnabled(false);
        buttonMenu.setEnabled(false);
        gameMenuDialog.setVisible(true);
    }

    private void buttonDeleteActionPerformed(ActionEvent e) {
        // TODO add your code here
        Controller.removeTower(towerDeleteX, towerDeleteY);
        buttonDelete.setEnabled(false);
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
        labelCarrot = new JLabel();
        labelHP = new JLabel();
        labelSelect = new JLabel();
        panelTower = new JPanel();
        buttonBottle = new JButton();
        buttonSunflower = new JButton();
        buttonDelete = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(960, 660));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
        . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder
        . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .
        awt . Font. BOLD ,12 ) ,java . awt. Color .red ) , getBorder () ) )
        ;  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
        ) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
        ;
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

        //======== panelMenu ========
        {
            panelMenu.setOpaque(false);
            panelMenu.setPreferredSize(new Dimension(960, 60));
            panelMenu.setLayout(new GridLayout(1, 3, 100, 10));

            //---- labelMoney ----
            labelMoney.setHorizontalAlignment(SwingConstants.CENTER);
            labelMoney.setText("500");
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
            panelMap.setPreferredSize(new Dimension(960, 480));
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

                //---- labelCarrot ----
                labelCarrot.setAlignmentX(0.5F);
                labelCarrot.setIconTextGap(0);
                labelCarrot.setBorder(null);
                labelCarrot.setPreferredSize(new Dimension(80, 80));
                panelCarrot.add(labelCarrot);

                //---- labelHP ----
                labelHP.setAlignmentX(0.5F);
                labelHP.setIconTextGap(0);
                labelHP.setBorder(null);
                labelHP.setPreferredSize(new Dimension(80, 40));
                panelCarrot.add(labelHP);
            }
            panelMap.add(panelCarrot);
            panelCarrot.setBounds(800, 80, 160, panelCarrot.getPreferredSize().height);

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
            panelTower.setPreferredSize(new Dimension(960, 80));
            panelTower.setOpaque(false);
            panelTower.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 0));

            //---- buttonBottle ----
            buttonBottle.setPreferredSize(new Dimension(80, 80));
            buttonBottle.setContentAreaFilled(false);
            buttonBottle.setIconTextGap(0);
            buttonBottle.setBorder(null);
            buttonBottle.setEnabled(false);
            buttonBottle.addActionListener(e -> buttonTowerActionPerformed(e));
            panelTower.add(buttonBottle);

            //---- buttonSunflower ----
            buttonSunflower.setPreferredSize(new Dimension(80, 80));
            buttonSunflower.setContentAreaFilled(false);
            buttonSunflower.setIconTextGap(0);
            buttonSunflower.setBorder(null);
            buttonSunflower.setEnabled(false);
            buttonSunflower.addActionListener(e -> buttonTowerActionPerformed(e));
            panelTower.add(buttonSunflower);

            //---- buttonDelete ----
            buttonDelete.setPreferredSize(new Dimension(80, 80));
            buttonDelete.setContentAreaFilled(false);
            buttonDelete.setIconTextGap(0);
            buttonDelete.setBorder(null);
            buttonDelete.setEnabled(false);
            buttonDelete.addActionListener(e -> buttonDeleteActionPerformed(e));
            panelTower.add(buttonDelete);
        }
        add(panelTower);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // 重绘背景图
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundMapImg, 0, 0, this.getWidth(), this.getHeight(), this);
        g.drawImage(backgroundMenuImg, panelMenu.getX(), panelMenu.getY(), panelMenu.getWidth(), panelMenu.getHeight(), this);
//        g.drawImage(backgroundMapImg, panelMap.getX(), panelMap.getY(), panelMap.getWidth(), panelMap.getHeight(), this);
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
    public RotateJLabel addTower(int x, int y, TowerType type){
        RotateJLabel towerLabel = new RotateJLabel();
        towerLabel.setBounds(x, y, 80, 80);
        towerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        towerLabel.setOpaque(false);
        towerLabel.setBorder(null);
        towerLabel.setIconTextGap(0);
        if(type == TowerType.BOTTLE){
            towerLabel.setIcon(new ImageIcon(towerBottleImg));
        }
        else if(type == TowerType.SUNFLOWER){
            towerLabel.setIcon(new ImageIcon(towerSunflowerImg));
            // 生成火焰
            JLabel fireLabel = new JLabel();
            fireLabel.setBounds(x-160, y-160, 400, 400);
            fireLabel.setHorizontalAlignment(SwingConstants.CENTER);
            fireLabel.setVerticalAlignment(SwingConstants.CENTER);
            fireLabel.setOpaque(false);
            fireLabel.setBorder(null);
            fireLabel.setIconTextGap(0);
            fireLabel.setVisible(false);
            panelMap.add(fireLabel);
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

    public void rotateTower(RotateJLabel bottleLabel, double angle) {
        bottleLabel.setAngle(angle);
        bottleLabel.updateUI();
    }

    // 太阳花火焰扩散
    public void updateFire(int x, int y){
        JLabel fireLabel = (JLabel) panelMap.getComponentAt(x-160, y-160);
        fireLabel.setVisible(true);
        for(int imgSize = 1; imgSize < 200; imgSize++){
            Image fireImgResized = fireImg.getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT);
            fireLabel.setIcon(new ImageIcon(fireImgResized));
            try {
                Thread.sleep((long) 0.1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        fireLabel.setVisible(false);
    }

    // 怪物击中效果
    public void monsterHit(int x, int y, TowerType type){
        new Thread(() -> {
            Image hitImage = null;
            JLabel hitLabel = new JLabel();
            hitLabel.setBounds(x, y, 80, 80);
            hitLabel.setHorizontalAlignment(SwingConstants.CENTER);
            hitLabel.setOpaque(false);
            hitLabel.setBorder(null);
            hitLabel.setIconTextGap(0);
            hitLabel.setVisible(true);
            switch (type){
                case SUNFLOWER:
                    hitImage = sunflowerHitImg;
                    hitLabel.setIcon(new ImageIcon(hitImage));
                    panelMap.add(hitLabel, -1);
                    break;
                case BOTTLE:
                    hitImage = bottleHitImg;
                    hitLabel.setIcon(new ImageIcon(hitImage));
                    panelMap.add(hitLabel, 0);
                    break;
                default:
                    break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            hitLabel.setVisible(false);
            panelMap.remove(hitLabel);
        }).start();//开启线程
    }

    // 萝卜被攻击效果
    public void carrotAttack(){
        new Thread(() -> {
            synchronized (labelCarrot){
                labelCarrot.setIcon(new ImageIcon(labelCarrotHitImg));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                labelCarrot.setIcon(new ImageIcon(labelCarrotImg));
            }
        }).start();//开启线程
    }

    public void setLevel(Difficulty d){
        if(d == Difficulty.EASY){
            backgroundMapImg = (new ImageIcon(getClass().getResource("/resources/mapBackground1.png"))).getImage();
        }
        else if(d == Difficulty.MEDIUM){
            backgroundMapImg = (new ImageIcon(getClass().getResource("/resources/mapBackground2.png"))).getImage();
        }
        else{
            backgroundMapImg = (new ImageIcon(getClass().getResource("/resources/mapBackground3.png"))).getImage();
        }
        panelCarrot.setLocation(Controller.map.getEndColumn()*80, Controller.map.getEndRow()*80);
    }

    public void setMoney(int money){
        labelMoney.setText("" + money);
    }

    public void setCarrotHP(int hp){
        if(hp > 0){
            String ImageDir = "/resources/CarrotHP" + hp +".png";
            labelHP.setIcon(new ImageIcon(getClass().getResource(ImageDir)));
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    public JPanel panelMenu;
    public JLabel labelMoney;
    public JLabel labelWave;
    public JPanel panelButton;
    public JButton buttonPause;
    public JButton buttonMenu;
    public JPanel panelMap;
    public JPanel panelCarrot;
    public JLabel labelCarrot;
    public JLabel labelHP;
    public JLabel labelSelect;
    public JPanel panelTower;
    private JButton buttonBottle;
    private JButton buttonSunflower;
    private JButton buttonDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private final Image backgroundMenuImg;
    private final Image buttonPauseImg;
    private final Image buttonPauseImg2;
    private final Image towerBottleImg;
    private final Image towerSunflowerImg;
    private final Image labelCarrotImg;
    private final Image labelCarrotHitImg;
    private final Image fireImg;
    private final Image bottleHitImg;
    private final Image sunflowerHitImg;
    private final Image forbiddenImg;
    private Image backgroundMapImg;

    private int towerDeleteX;
    private int towerDeleteY;
}
