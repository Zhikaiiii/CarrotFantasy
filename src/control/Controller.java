package control;

import actor.*;
import ui.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Controller {
    private static int money;  // 金钱
    private static int currTime; // 当前时间
    private static int currWave;
    private static int currMonster;
    private static int arrivedMonster;
    public static Level level;
    public static Map map;

    public static ArrayList<Monster> allMonster;
    public static ArrayList<Tower> allTower;
    public static ArrayList<Bullet> allBullet;
    public static Carrot carrot;

    public static Iterator<Monster> monsterIterator;
    public static Iterator<Bullet> bulletIterator;
    public static Iterator<JPanel> monsterPanelIterator;
    public static Iterator<JLabel> bulletLabelIterator;

    // ui相关
    public static JFrame f;
    public static MainWindow w;
    public static ArrayList<JPanel> allMonsterPanels;
    public static ArrayList<JLabel> allTowerLabels;
    public static ArrayList<JLabel> allBulletLabels;

    public static void initialize(){
        currWave = 0;
        currMonster = 0;
        currTime = 0;
        money = 100;
        arrivedMonster = 0;
        level = new Level(Difficulty.EASY);
        map = new Map();

        // game element
        allMonster = new ArrayList<Monster>();
        allTower = new ArrayList<Tower>();
        allBullet = new ArrayList<Bullet>();
        // carrot
        int row = map.getEndRow();
        int column = map.getEndColumn();
        int interval = map.getInterval();
        int y = row*interval + interval/2;
        int x = column*interval + interval/2;
        carrot = new Carrot(row, column, x, y);

        // ui
        allMonsterPanels = new ArrayList<>();
        allTowerLabels = new ArrayList<>();
        allBulletLabels = new ArrayList<>();
        monsterIterator = allMonster.iterator();
        monsterPanelIterator = allMonsterPanels.iterator();
    }

    public static void run(){
        TowerAttackThread towerAttackThread = new TowerAttackThread();
        Thread t1 = new Thread(towerAttackThread);
        MonsterMoveThread monsterMoveThread = new MonsterMoveThread();
        Thread t2 = new Thread(monsterMoveThread);
        BulletAttackThread bulletAttackThread = new BulletAttackThread();
        Thread t3 = new Thread(bulletAttackThread);
        t1.start();
        t2.start();
        t3.start();
    }

    public static void updateMoney(int newMoney){
        money += newMoney;
    }

    // 添加防御塔
    public static void addTower(int idx, int column, int row){
        Tower t = null;
        JLabel tLabel = null;
        TowerType type = TowerType.values()[idx];
        int interval = map.getInterval();
        int y = row*interval;
        int x = column*interval;
        if(map.getMapElement(row, column) == MapElement.EMPTY){
            map.setMapElement(row, column, MapElement.HAVETOWER);
            switch (type){
                case BOTTLE:
                    t = new Bottle(row, column, x, y);
                    tLabel = w.addTower(x, y, type.ordinal());
                    break;
                case SUNFLOWER:
                    t = new SunFlower(row, column, x, y);
                    tLabel = w.addTower(x, y, type.ordinal());
                    break;
                default:
                    break;
            }
            synchronized (allTower){
                allTower.add(t);
                allTowerLabels.add(tLabel);
            }
            System.out.println("Place successfully!");
        }
        else{
            System.out.println("Can not place here!");
        }
    }
    // 添加下一波怪物
    public static void addWave(){
        // 当前一波所有怪物已经生成且被消灭
        if (currWave == 0 || (currMonster == level.getNumMonsters() && allMonster.isEmpty())){
            currWave += 1;
//            assert allMonster.isEmpty();
            currMonster = 0;
            MonsterAddThread monsterAddThread = new MonsterAddThread();
            Thread t = new Thread(monsterAddThread);
            t.start();
        }

    }
    // 添加怪物
    public static void addMonster(){
        Random r = new Random();
        while(currMonster < level.getNumMonsters()){
            int row = map.getStartRow();
            int column = map.getStartColumn();
            int interval = map.getInterval();
            int y = row * interval;
            int x = column * interval;
            synchronized (allMonster){
                Monster m = new Monster(row, column, x, y, level.getMonsterHP(), level.getMonsterSpeed());
                allMonster.add(m);
                JPanel mPanel = w.addMonster(x, y);
                allMonsterPanels.add(mPanel);
                currMonster += 1;
            }
            // 怪物的生成间歇为一个随机数
            int random = r.nextInt(3000);
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // 移动所有怪物
    public static void allMonsterMove(){
        // 如果当前一波的怪物已经被消灭
        addWave();
        int interval = map.getInterval();
        synchronized (allMonster){
            monsterIterator = allMonster.iterator();
            monsterPanelIterator = allMonsterPanels.iterator();
            while(monsterIterator.hasNext()){
                Monster m = monsterIterator.next();
                // to do: 速度改变
                int row = m.getRow();
                int column = m.getColumn();
                int position = m.getPosition();
                // 得到移动的步长
                int dColumn = map.getDColumn(position);
                int dRow = map.getDRow(position);
                m.move(dColumn, dRow);
                JPanel mPanel = monsterPanelIterator.next();
                mPanel.setLocation(m.getX(), m.getY());
                JProgressBar p = (JProgressBar) mPanel.getComponent(0);
                p.setValue(m.getHp());
                if(m.getHp() == 0){
                    monsterIterator.remove();
                    mPanel.setVisible(false);
                    w.panelMap.remove(mPanel);
                    w.repaint();
                    monsterPanelIterator.remove();
                }
                //如果超过了当前格子的中心
                if(m.getX() == (column + dColumn)*interval && m.getY() == (row + dRow)*interval){
                    position = position + 1;
                    m.setPosition(position);
                    m.setRow(map.getRow(position));
                    m.setColumn(map.getColumn(position));
                    // 到达终点或者被消灭
                    if(m.isArrived(map.getEndColumn(), map.getEndRow())){
//                        allMonster.remove(m);
                        monsterIterator.remove();
                        mPanel.setVisible(false);
                        w.panelMap.remove(mPanel);
//                        allMonsterLabels.get(i).setVisible(false);
//                        w.panelMap.remove(allMonsterLabels.get(i));
                        w.repaint();
//                        allMonsterLabels.remove(i);
                        monsterPanelIterator.remove();
                        arrivedMonster += 1;
                    }
                }

            }
        }

    }
    // 所有防御塔进行攻击
    public static void allTowerAttack(){
        synchronized (allTower){
            for(Tower t: allTower){
//                if(currTime % t.getAttackInterval() == 0){
                if(t.getCount() == t.getAttackInterval()){
                    t.attack();
                    t.setCount(0);
                }
                else {
                    t.setCount(t.getCount() + 1);
                }
//                }
            }
        }
    }
    // 所有子弹攻击
    public static void allBulletAttack(){
        synchronized (allBullet){
            bulletIterator = allBullet.iterator();
            bulletLabelIterator = allBulletLabels.iterator();
            while(bulletIterator.hasNext()){
                Bullet b = bulletIterator.next();
                JLabel bLabel = bulletLabelIterator.next();
                b.move();
                bLabel.setLocation(b.getX(), b.getY());
                w.panelMap.updateUI();
                if(b.getIsCollide()){
                    bulletIterator.remove();
                    bulletLabelIterator.remove();
                    bLabel.setVisible(false);
                    w.panelMap.remove(bLabel);
                }
            }
        }
    }
    // 判断游戏条件
    public boolean isGameWin(){
        return currWave == level.getNumWaves();
    }
    public boolean isGamaLost(){
        return carrot.getHp() > 0;
    }
    public static boolean isAvailable(int row, int column){
        return map.getMapElement(row, column) == MapElement.EMPTY;
    }
}

// 所有防御塔线程
class TowerAttackThread implements Runnable{
    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while(true){
            Controller.allTowerAttack();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//class TowerAddThread implements Runnable{
//    protected TowerType type;
//    protected int column;
//    protected int row;
//    TowerAddThread(TowerType type, int column, int row){
//        this.type = type;
//        this.column = column;
//        this.row = row;
//    }
//    @Override
//    public void run() {
//        Controller.addTower(type, column, row);
//    }
//}

class MonsterMoveThread implements Runnable{
    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while(true){
            Controller.allMonsterMove();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MonsterAddThread implements Runnable{
    @Override
    public void run() {
        Controller.addMonster();
    }
}

class BulletAttackThread implements Runnable{
    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while(true){
            Controller.allBulletAttack();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
enum TowerType {BOTTLE, SUNFLOWER}