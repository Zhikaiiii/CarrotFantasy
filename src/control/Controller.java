package control;

import actor.*;

import java.util.ArrayList;

public class Controller {
    private static int money;
    private static int currWave;
    private static int currMonster;
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
    // 判断游戏条件
    public boolean isGameWin(){
        return false;
    }
    public boolean isGamaLost(){
        return false;
    }
}

enum TowerType {BOTTLE, SUNFLOWER}