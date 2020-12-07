/*
 * Created by JFormDesigner on Sun Dec 06 21:40:36 CST 2020
 */

package ui;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class MainWindow extends JPanel {
    public MainWindow() {
        backgroundMenuImg = (new ImageIcon(getClass().getResource("/resources/menuBackground.png"))).getImage();
        backgroundMapImg = (new ImageIcon(getClass().getResource("/resources/mapBackground.jpg"))).getImage();
        initComponents();
        panelMap.setBounds(panelMenu.getX(), panelMenu.getY() + panelMenu.getHeight(), 880, 480);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panelMenu = new JPanel();
        panelMap = new JPanel();

        //======== this ========
        setPreferredSize(new Dimension(880, 540));
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border
        .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e" , javax. swing .border . TitledBorder. CENTER ,javax
        . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dialo\u0067", java .awt . Font. BOLD ,
        12 ) ,java . awt. Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans
        .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "borde\u0072" .equals ( e.
        getPropertyName () ) )throw new RuntimeException( ) ;} } );
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
            panelMap.setLayout(null);
        }
        add(panelMap);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // 重绘背景图
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundMenuImg, panelMenu.getX(), panelMenu.getY(), panelMenu.getWidth(), panelMenu.getHeight(), this);
        g.drawImage(backgroundMapImg, panelMap.getX(), panelMap.getY(), panelMap.getWidth(), panelMap.getHeight(), this);
    }

    // 添加怪物
    public JLabel addMonster(int x, int y){
        JLabel monsterLabel = new JLabel();
//        monsterLabel.setLocation(x, y);
        monsterLabel.setBounds(x, y, 80, 80);
        monsterLabel.setOpaque(false);
        monsterLabel.setBorder(null);
        monsterLabel.setIconTextGap(0);
        monsterLabel.setIcon(new ImageIcon(getClass().getResource("/resources/monster.png")));
        monsterLabel.setVisible(true);
        panelMap.add(monsterLabel);
        return monsterLabel;
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    public JPanel panelMenu;
    public JPanel panelMap;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Image backgroundMenuImg;
    private Image backgroundMapImg;
}
