package control;


public class Map {
    private int [][] map;
    private int [] dColumn;
    private int [] dRow;
    private int [] pathColumn;
    private int [] pathRow;
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
        dColumn = new int[]{1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1};
        dRow = new int[]{0, 0, 1, 1, 1, 1, 0, 0, -1, -1, -1, -1, -1, 0, 0, 1, 1, 1, 1, 1, 0, 0, -1, -1, -1, 0, 0, 0};
        pathColumn = new int[]{0, 1, 2, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4, 4, 5, 6, 6, 6, 6, 6, 6, 7, 8, 8, 8, 8, 9, 10};
        pathRow = new int[]{1, 1, 1, 2, 3, 4, 5, 5, 5, 4, 3, 2, 1, 0, 0, 0, 1, 2, 3, 4, 5, 5, 5, 4, 3, 2, 2, 2};
        interval = 80;

    }
    public MapElement getMapElement(int row, int column){
        return MapElement.values()[map[row][column]];
    }
    public void setMapElement(int row, int column, MapElement element){
        map[row][column] = element.ordinal();
    }
    public int getStartColumn(){
        return 0;
    }
    public int getStartRow(){
        return 1;
    }
    public int getEndColumn(){
        return 10;
    }
    public int getEndRow(){
        return 2;
    }


    // 得到 下一步的方向
    public int getDColumn(int position){
        return dColumn[position];
    }
    public int getDRow(int position){
        return dRow[position];
    }
    public int getRow(int position){
        return pathRow[position];
    }
    public int getColumn(int position){
        return pathColumn[position];
    }


    public int getInterval(){
        return interval;
    }
    public void setInterval(int interval){
        this.interval = interval;
    }

}

// 0代表起点，1代表终点，2代表道路，3代表空地，4代表路障, 5代表有防御塔
enum MapElement {START, END, PATH, EMPTY, BARRIER, HAVETOWER}
