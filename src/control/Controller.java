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
    public static Difficulty difficulty;
    public static Level level;
    public static Map map;
    public static Object lock;

    public static ArrayList<Monster> allMonster;
    public static ArrayList<Tower> allTower;
    public static ArrayList<Bullet> allBullet;
    public static Carrot carrot;

    public static Iterator<Monster> monsterIterator;
    public static Iterator<Bullet> bulletIterator;
    public static Iterator<Tower> towerIterator;
    public static Iterator<JPanel> monsterPanelIterator;
    public static Iterator<RotateJLabel> bulletLabelIterator;
    public static Iterator<RotateJLabel> towerLabelIterator;

    // ui相关
    public static JFrame f;
    public static MainWindow w;
    public static GameEndDialog d;
    public static ArrayList<JPanel> allMonsterPanels;
    public static ArrayList<RotateJLabel> allTowerLabels;
    public static ArrayList<RotateJLabel> allBulletLabels;

    public static boolean gameEndFlag;
    public static boolean gamePauseFlag;
    public static Thread t1;
    public static Thread t2;
    public static Thread t3;
    public static Thread t4;

    public static void initialize(){
        currWave = 0;
        currMonster = 0;
        currMoney = new AtomicInteger(500);
        arrivedMonster = 0;
        gameEndFlag = false;
        gamePauseFlag = false;
        lock = new Object();
//        f = new JFrame();
//        d = new GameEndDialog(f);
        level = new Level(difficulty);
        map = new Map(difficulty);
        w.setLevel(difficulty);
        // carrot
        int row = map.getEndRow();
        int column = map.getEndColumn();
        int interval = map.getInterval();
        int y = row*interval;
        int x = column*interval;
        carrot = new Carrot(row, column, x, y);

        // game element
        allMonster = new ArrayList<Monster>();
        allTower = new ArrayList<Tower>();
        allBullet = new ArrayList<Bullet>();

        // ui
        allMonsterPanels = new ArrayList<JPanel>();
        allTowerLabels = new ArrayList<>();
        allBulletLabels = new ArrayList<>();
        monsterIterator = allMonster.iterator();
        monsterPanelIterator = allMonsterPanels.iterator();

        String text = w.labelWave.getText();
        text = text.replaceFirst("10", ""+ level.getNumWaves());
        w.labelWave.setText(text);
    }

    public static void setDifficulty(Difficulty d){
        difficulty = d;
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

    public static void start(){
        w = new MainWindow();
        w.setVisible(true);
        f.dispose();
        f = new JFrame();
        f.setSize(980, 680);
        f.add(Controller.w);
        f.setVisible(true);
        initialize();
        run();
    }

    public static void pause(){
        gamePauseFlag = !gamePauseFlag;
    }

    // 添加防御塔
    public static void addTower(TowerType type, int column, int row){
        Tower t = null;
        RotateJLabel tLabel = null;
        int interval = map.getInterval();
        int y = row*interval;
        int x = column*interval;
        if(map.getMapElement(row, column) == MapElement.EMPTY){
            map.setMapElement(row, column, MapElement.HAVETOWER);
            switch (type){
                case BOTTLE:
                    t = new Bottle(row, column, x, y);
                    tLabel = w.addTower(x, y, type);
                    currMoney.addAndGet(-Bottle.getBuyPrice());
                    break;
                case SUNFLOWER:
                    t = new SunFlower(row, column, x, y);
                    tLabel = w.addTower(x, y, type);
                    currMoney.addAndGet(-SunFlower.getBuyPrice());
                    break;
                case SNOWFLOWER:
                    t = new SnowFlower(row, column, x, y);
                    tLabel = w.addTower(x, y, type);
                    currMoney.addAndGet(-SnowFlower.getBuyPrice());
                    break;
                case ARROW:
                    t = new Arrow(row, column, x, y);
                    tLabel = w.addTower(x, y, type);
                    currMoney.addAndGet(-Arrow.getBuyPrice());
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

    // 删除防御塔
    public static void removeTower(int x, int y){
        synchronized (allTower){
            towerIterator = allTower.iterator();
            towerLabelIterator = allTowerLabels.iterator();
            while(towerIterator.hasNext()){
                Tower t = towerIterator.next();
                RotateJLabel l = towerLabelIterator.next();
                if(t.getX() == x && t.getY() == y){
                    towerIterator.remove();
                    l.setVisible(false);
                    w.panelMap.remove(l);
                    w.repaint();
                    towerLabelIterator.remove();
                    switch (t.getTowerType()){
                        case BOTTLE -> currMoney.addAndGet(Bottle.getSellPrice());
                        case SUNFLOWER -> currMoney.addAndGet(SunFlower.getSellPrice());
                        case SNOWFLOWER -> currMoney.addAndGet(SnowFlower.getSellPrice());
                    }
                    map.setMapElement(y/80, x/80, MapElement.EMPTY);
                    break;
                }
            }
        }
    }

    // 旋转瓶子
    public static void rotate(Tower t, double angle){
        // 瓶子转向
        RotateJLabel l = allTowerLabels.get(Controller.allTower.indexOf(t));
        w.rotate(l, angle);
    }

    // 选择子弹
    public static void rotate(Bullet b, double angle){
        // 瓶子转向
        RotateJLabel l = allBulletLabels.get(Controller.allBullet.indexOf(b));
        w.rotate(l, angle);
    }


    // 添加下一波怪物
    public static void addWave(){
        // 更新金钱
        w.setMoney(currMoney.get());
        w.setCarrotHP(carrot.getHp());

        // 当前一波所有怪物已经生成且被消灭
        if ((currWave == 0 || (currMonster == level.getNumMonsters() && allMonster.isEmpty())) && currWave < level.getNumWaves()){
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
    public static void addMonster() throws InterruptedException {
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
            if(isGamePause()){
                synchronized (lock){
                    lock.wait();
                }
            }
            // 怪物的生成间歇为一个随机数
            int random = r.nextInt(4000);
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
                JProgressBar p = (JProgressBar) mPanel.getComponent( 0);
                p.setValue(m.getHp());

                // 被消灭
                if(m.getHp() <= 0){
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
                    // 到达终点
                    if(m.isArrived(map.getEndColumn(), map.getEndRow())){
                        carrot.setHp(carrot.getHp() - 1);
                        monsterIterator.remove();
                        mPanel.setVisible(false);
                        w.carrotAttack();
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
                if(t.getCount() == t.getAttackInterval()){
                    t.attack();
                    t.setCount(0);
                }
                else {
                    t.setCount(t.getCount() + 1);
                }
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
                // 如果是太阳花
                if(b.getParentType() == TowerType.SUNFLOWER || b.getParentType() == TowerType.SNOWFLOWER){
                    bLabel.setVisible(false);
                }
                b.update();
                bLabel.setLocation(b.getX(), b.getY());
                w.panelMap.updateUI();
                if(b.getIsCollide()){
                    bulletIterator.remove();
                    bulletLabelIterator.remove();
                    bLabel.setVisible(false);
                    w.panelMap.remove(bLabel);

                    synchronized (allMonster){
                        int idx = allMonster.indexOf(b.getTargetMonster());
                        try{
                            JPanel mPanel = allMonsterPanels.get(idx);
                            // 子弹攻击效果
                            w.monsterHit(b.getTargetMonster().getX(), b.getTargetMonster().getY(), b.getParentType(), mPanel);
                        }
                        catch (IndexOutOfBoundsException e){
                            System.out.println("monster have been eliminated");
                        }
                    }
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

    public static MapElement getMapElement(int row, int column){
        return map.getMapElement(row, column);
    }
    public static int getCurrWave(){
        return currWave;
    }
    public static int getNumWave(){
        return level.getNumWaves();
    }

    public static boolean isTowerAvailable(TowerType type, int row, int column){
//        TowerType t =
        switch (type){
            case BOTTLE -> {
                return currMoney.get() >= Bottle.getBuyPrice();
            }
            case SUNFLOWER -> {
                return currMoney.get() >= SunFlower.getBuyPrice() && row!=0 && column !=0;
            }
            case SNOWFLOWER -> {
                return currMoney.get() >= SnowFlower.getBuyPrice() && row!=0 && column !=0;
            }
            case ARROW -> {
                return currMoney.get() >= Arrow.getBuyPrice();
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
            if(Controller.isGamePause()){
                synchronized (Controller.lock){
                    try {
                        Controller.lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                continue;
            }
//            notify();
            Controller.allTowerAttack();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class MonsterMoveThread implements Runnable{
    @Override
    public void run() {
        while(!Controller.isGameEnd()){
            if(Controller.isGamePause()){
                synchronized (Controller.lock){
                    try {
                        Controller.lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                continue;
            }
            Controller.allMonsterMove();
            try {
//                double sleepTime = 2.0/Controller.level.getMonsterSpeed();
//                System.out.println(sleepTime);
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MonsterAddThread implements Runnable{
    @Override
    public void run() {
        try {
            Controller.addMonster();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class BulletAttackThread implements Runnable{
    @Override
    public void run() {
        while(!Controller.isGameEnd()){
            if(Controller.isGamePause()){
                synchronized (Controller.lock){
                    try {
                        Controller.lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                continue;
            }
            Controller.allBulletAttack();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class GameStateJudgeThread implements Runnable{
    @Override
    public void run() {
        while(true){
            synchronized (Controller.lock){
                if(Controller.isGamePause()){
                    continue;
                }
                else{
                    Controller.lock.notifyAll();
                }
            }
            // 游戏胜利或者失败
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