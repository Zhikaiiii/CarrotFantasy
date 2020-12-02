package control;


import java.util.ArrayList;

public class Map {
    private int [][] map;
    private int [] dx;
    private int [] dy;
    private int [] pathX;
    private int [] pathY;
    // 每格之间的间隔
    private int interval;
    Map(){

        map = new int[][]
                {{3, 3, 3, 3, 2, 2, 2, 3, 4, 4, 3},
                {0, 2, 2, 3, 2, 3, 2, 3, 4, 4, 3},
                {3, 3, 2, 3, 2, 3, 2, 3, 2, 2, 1},
                {3, 3, 2, 3, 2, 3, 2, 3, 2, 4, 4},
                {4, 4, 2, 3, 2, 3, 2, 3, 2, 4, 4},
                {3, 3, 2, 2, 2, 3, 2, 2, 2, 3, 3}};
        dx = new int[]{1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1};
        dy = new int[]{0, 0, 1, 1, 1, 1, 0, 0, -1, -1, -1, -1, -1, 0, 0, 1, 1, 1, 1, 1, 0, 0, -1, -1, -1, 0, 0, 0};
        pathX = new int[]{0, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4, 4, 5, 6, 6, 6, 6, 6, 6, 7, 8, 8, 8, 8, 9, 10};
        pathY = new int[]{1, 1, 1, 2, 3, 4, 5, 5, 5, 4, 3, 2, 1, 0, 0, 0, 1, 2, 3, 4, 5, 5, 5, 4, 3, 2, 2, 2};

    }
    public MapElement getMapElement(int x, int y){
        return MapElement.values()[map[x][y]];
    }
    public void setMapElement(int x, int y, MapElement element){
        map[x][y] = element.ordinal();
    }
    public int getStartX(){
        return 2;
    }
    public int getStartY(){
        return 10;
    }
    // 得到 下一步的方向
    public int getDx(int position){
        return dx[position];
    }
    public int getDy(int position){
        return dy[position];
    }
    public int getRow(int position){
        return pathY[position];
    }
    public int getColumn(int position){
        return pathX[position];
    }


    public int getInterval(){
        return interval;
    }
    public void setInterval(int interval){
        this.interval = interval;
    }

}

class Point{
    int row;
    int column;
    Point(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
}
// 0代表起点，1代表终点，2代表道路，3代表空地，4代表路障, 5代表有防御塔
enum MapElement {START, END, PATH, EMPTY, BARRIER, HAVETOWER}
