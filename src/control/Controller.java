package control;

import actor.*;
import ui.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {
    private static AtomicInteger currMoney;  // 金钱
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
    public static GameEndDialog d;
    public static ArrayList<JPanel> allMonsterPanels;
    public static ArrayList<JLabel> allTowerLabels;
    public static ArrayList<JLabel> allBulletLabels;

    public static boolean gameEndFlag;
    public static boolean gamePauseFlag;
    public static Thread t1;
    public static Thread t2;
    public static Thread t3;
    public static Thread t4;

    public static void initialize(){
        currWave = 0;
        currMonster = 0;
        currMoney = new AtomicInteger(100);
        arrivedMonster = 0;
        gameEndFlag = false;
        gamePauseFlag = false;
        level = new Level(Difficulty.EASY);
        map = new Map();

//        f = new JFrame();
//        d = new GameEndDialog(f);

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

        String text = w.labelWave.getText();
        text = text.replaceFirst("10", ""+ level.getNumWaves());
        w.labelWave.setText(text);
    }

    public static void run(){
        TowerAttackThread towerAttackThread = new TowerAttackThread();
        t1 = new Thread(towerAttackThread);
        MonsterMoveThread monsterMoveThread = new MonsterMoveThread();
        t2 = new Thread(monsterMoveThread);
        BulletAttackThread bulletAttackThread = new BulletAttackThread();
        t3 = new Thread(bulletAttackThread);
        GameStateJudgeThread gameStateJudgeThread = new GameStateJudgeThread();
        t4 = new Thread(gameStateJudgeThread);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    public static void pause(){
        gamePauseFlag = !gamePauseFlag;
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
                    currMoney.addAndGet(-Bottle.getCost());
                    break;
                case SUNFLOWER:
                    t = new SunFlower(row, column, x, y);
                    tLabel = w.addTower(x, y, type.ordinal());
                    currMoney.addAndGet(-Bottle.getCost());
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
        // 更新金钱
        w.labelMoney.setText("" + currMoney.get());
        w.progressBarCarrot.setValue(carrot.getHp());

        // 当前一波所有怪物已经生成且被消灭
        if ((currWave == 0 || (currMonster == level.getNumMonsters() && allMonster.isEmpty())) && currWave <= level.getNumWaves()){
            currWave += 1;
            String text = w.labelWave.getText();
            if (currWave < 10){
                text = text.replaceFirst(text.substring(0, 2), "0" + currWave);
            }
            else{
                text = text.replaceFirst(text.substring(0, 2), "" + currWave);
            }
            w.labelWave.setText(text);
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

                // 被消灭
                if(m.getHp() == 0){
                    monsterIterator.remove();
                    mPanel.setVisible(false);
                    w.panelMap.remove(mPanel);
                    w.repaint();
                    monsterPanelIterator.remove();
                    currMoney.addAndGet(m.getMoney());
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
                        carrot.setHp(carrot.getHp() - 1);
                        monsterIterator.remove();
                        mPanel.setVisible(false);
                        w.panelMap.remove(mPanel);
                        w.repaint();
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
    public static boolean isGameWin(){
        return currWave == level.getNumWaves() && allMonster.isEmpty();
    }
    public static boolean isGameLost(){
        return carrot.getHp() == 0;
    }
    public static boolean isGameEnd(){
        return gameEndFlag;
    }
    public static boolean isGamePause(){
        return gamePauseFlag;
    }

    public static boolean isPositionAvailable(int row, int column){
        return map.getMapElement(row, column) == MapElement.EMPTY;
    }
    public static boolean isTowerAvailable(TowerType type){
//        TowerType t =
        switch (type){
            case BOTTLE -> {
                return currMoney.get() >= Bottle.getCost();
            }
            case SUNFLOWER -> {
                return currMoney.get() >= SunFlower.getCost();
            }
            default -> {
                return false;
            }
        }
    }
}

// 所有防御塔线程
class TowerAttackThread implements Runnable{
    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while(!Controller.isGameEnd()){
            while(Controller.isGamePause()){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
        while(!Controller.isGameEnd()){
            while(Controller.isGamePause()){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Controller.allMonsterMove();
            try {
                Thread.sleep(1);
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
        while(!Controller.isGameEnd()){
            while(Controller.isGamePause()){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Controller.allBulletAttack();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class GameStateJudgeThread implements Runnable{
    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while(true){
            while(Controller.isGamePause()){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(Controller.isGameWin()){
                Controller.d = new GameEndDialog(Controller.f);
                Controller.d.setStatus(true);
                Controller.gameEndFlag = true;
                break;
            }
            if(Controller.isGameLost()){
                Controller.d = new GameEndDialog(Controller.f);
                Controller.d.setStatus(false);
                Controller.gameEndFlag = true;
                break;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}