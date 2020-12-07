package control;

import actor.*;
import ui.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private static int money;  // 金钱
    private static int currTime; // 当前时间
    private static int currWave;
    private static int currMonster;
    private static int arrivedMonster;
    private static Level level;
    private static Map map;

    public static ArrayList<Monster> allMonster;
    public static ArrayList<Tower> allTower;
    public static ArrayList<Thread> allBullet;
    public static Carrot carrot;

    // ui相关
    public static JFrame f;
    public static MainWindow w;
    public static ArrayList<JLabel> allMonsterLabels;

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
        allBullet = new ArrayList<Thread>();
        // carrot
        int row = map.getEndRow();
        int column = map.getEndColumn();
        int interval = map.getInterval();
        int y = row*interval + interval/2;
        int x = column*interval + interval/2;
        carrot = new Carrot(row, column, x, y);

        // ui
        allMonsterLabels = new ArrayList<JLabel>();
    }

    public static void run(){
//        TowerAttackThread towerAttackThread = new TowerAttackThread();
//        Thread t1 = new Thread(towerAttackThread);
        MonsterMoveThread monsterMoveThread = new MonsterMoveThread();
        Thread t2 = new Thread(monsterMoveThread);
//        BulletAttackThread bulletAttackThread = new BulletAttackThread();
//        Thread t3 = new Thread(bulletAttackThread);
//        t1.start();
        t2.start();
//        t3.start();
    }

    public static void updateMoney(int newMoney){
        money += newMoney;
    }

    // 添加防御塔
    public static void addTower(TowerType type, int column, int row){
        Tower t = null;
        int interval = map.getInterval();
        int y = row*interval + interval/2;
        int x = column*interval + interval/2;
        if(map.getMapElement(column, row) == MapElement.EMPTY){
            map.setMapElement(column, row, MapElement.HAVETOWER);
            switch (type){
                case BOTTLE:
                    t = new Bottle(row, column, x, y);
                    break;
                case SUNFLOWER:
                    t = new SunFlower(row, column, x, y);
                    break;
                default:
                    break;
            }
            synchronized (allTower){
                allTower.add(t);
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
        while(currMonster <= level.getNumMonsters()){
            int row = map.getStartRow();
            int column = map.getStartColumn();
            int interval = map.getInterval();
            int y = row * interval;
            int x = column * interval;
            synchronized (allMonster){
                Monster m = new Monster(row, column, x, y, level.getMonsterHP(), level.getMonsterSpeed());
                allMonster.add(m);
                JLabel mLabel = w.addMonster(x, y);
                allMonsterLabels.add(mLabel);
                currMonster += 1;
            }
            // 怪物的生成间歇为一个随机数
            int random = r.nextInt(1000);
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
            for(Monster m: allMonster){
                // to do: 速度改变
                int row = m.getRow();
                int column = m.getColumn();
                int position = m.getPosition();
                // 得到移动的步长
                int dColumn = map.getDColumn(position);
                int dRow = map.getDRow(position);
                m.move(dColumn, dRow);
                int i = allMonster.indexOf(m);
                allMonsterLabels.get(i).setLocation(m.getX(), m.getY());
                //如果超过了当前格子的中心
                if(m.getX() == (column + dColumn)*interval && m.getY() == (row + dRow)*interval){
                    // 到达终点
                    if(m.isArrived(map.getEndColumn(), map.getEndRow())){
                        arrivedMonster += 1;
                    }
                    position = position + 1;
                    m.setPosition(position);
                    m.setRow(map.getRow(position));
                    m.setColumn(map.getColumn(position));
                }

            }
        }

    }
    // 所有防御塔进行攻击
    public static void allTowerAttack(){
        synchronized (allTower){
            for(Tower t: allTower){
                if(currTime % t.getAttackInterval() == 0){
                    t.attack();
                }
            }
        }
    }
    // 所有子弹攻击
    public static void allBulletAttack(){
        synchronized (allBullet){
            allBullet.removeIf(t -> t.getState() == Thread.State.TERMINATED);
        }
//        synchronized (allBullet){
//            for(Bullet b: allBullet){
//                b.attack();
//            }
//        }
    }
    // 判断游戏条件
    public boolean isGameWin(){
        return currWave == level.getNumWaves();
    }
    public boolean isGamaLost(){
        return carrot.getHp() > 0;
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

class TowerAddThread implements Runnable{
    protected TowerType type;
    protected int column;
    protected int row;
    TowerAddThread(TowerType type, int column, int row){
        this.type = type;
        this.column = column;
        this.row = row;
    }
    @Override
    public void run() {
        Controller.addTower(type, column, row);
    }
}

class MonsterMoveThread implements Runnable{
    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while(true){
            Controller.allMonsterMove();
            try {
                Thread.sleep(20);
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
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
enum TowerType {BOTTLE, SUNFLOWER}