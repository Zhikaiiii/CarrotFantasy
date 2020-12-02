package control;

import actor.*;

import java.util.ArrayList;

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
    public static Carrot carrot;

    public void initialize(){

    }

    public static void updateMoney(int newMoney){
        money += newMoney;
    }

    // 添加防御塔
    public static void addTower(TowerType type, int x, int y){
        Tower t;
        if(map.getMapElement(x, y) == MapElement.EMPTY){
            map.setMapElement(x, y, MapElement.HAVETOWER);
            switch (type){
                case BOTTLE:
                    t = new Bottle(x, y);
                    break;
                case SUNFLOWER:
                    t = new SunFlower(x, y);
                    break;
                default:
                    break;
            }
            System.out.println("Place successfully!");
        }
        else{
            System.out.println("Can not place here!");
        }
    }
    // 添加下一波怪物
    public static void addWave(){
        currWave += 1;
        assert allMonster.isEmpty();
        currMonster = 0;
    }
    // 添加怪物
    public static void addMonster(){
        if(currMonster <= level.getNumMonsters()){
            Monster m = new Monster(map.getStartX(), map.getStartY(), level.getMonsterHP(), level.getMonsterSpeed());
            allMonster.add(m);
            currMonster += 1;
        }
    }
    // 移动所有怪物
    public static void allMonsterMove(){
        int interval = map.getInterval();
        for(Monster m: allMonster){
            // to do: 速度改变
            int row = m.getRow();
            int column = m.getColumn();
            int position = m.getPosition();
            // 得到移动的步长
            int dx = map.getDx(position);
            int dy = map.getDy(position);
            m.move(dx, dy);
            //如果超过了当前格子的中心
            if(m.getX() == (column*interval + interval/2) || m.getY() == (row*interval + interval/2)){
                // 到达终点
                if(m.isArrived()){
                    arrivedMonster += 1;
                }
                position = position + 1;
                m.setPosition(position);
                m.setRow(map.getRow(position));
                m.setColumn(map.getColumn(position));
            }
        }
    }
    // 所有防御塔进行攻击
    public static void allTowerAttack(){
        for(Tower t: allTower){
            if(currTime % t.getAttackInterval() == 0){
                t.attack();
            }
        }
    }
    // 判断游戏条件
    public boolean isGameWin(){
        return false;
    }
    public boolean isGamaLost(){
        return false;
    }
}

enum TowerType {BOTTLE, SUNFLOWER}