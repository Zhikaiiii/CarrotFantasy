import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sun Nov 29 21:34:18 CST 2020
 */



/**
 * @author unknown
 */
public class MainWindow extends JFrame {
    public MainWindow() {
        initComponents();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel2 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setIconImage(null);
        var contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 190));

        //======== panel2 ========
        {
            panel2.setPreferredSize(new Dimension(130, 130));
            panel2.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing
            . border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder
            . CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .
            awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel2. getBorder () ) )
            ; panel2. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
            ) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } )
            ;
            panel2.setLayout(new GridLayout(2, 1, 0, 20));

            //---- button1 ----
            button1.setText("\u5f00\u59cb\u6e38\u620f");
            button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
            panel2.add(button1);

            //---- button2 ----
            button2.setText("\u9009\u62e9\u5173\u5361");
            button2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
            panel2.add(button2);
        }
        contentPane.add(panel2);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public static void main(String[] args) {
        new MainWindow().setVisible(true);
    }
//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.drawImage(image, 0, 0, d.width, d.height, this);
//        MainFrame.instance().repaint();
//    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel2;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
