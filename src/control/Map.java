package control;

public class Map {
    private int [][] map =
            {{3,3,3,3,2,2,2,3,4,4,3},
            {0,2,2,3,2,3,2,3,4,4,3},
            {3,3,2,3,2,3,2,3,2,2,1},
            {3,3,2,3,2,3,2,3,2,4,4},
            {4,4,2,3,2,3,2,3,2,4,4},
            {3,3,2,2,2,3,2,2,2,3,3}};
//    control.Map(){
//        this.map = mapEasy;
//    }

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

}

// 0代表起点，1代表终点，2代表道路，3代表空地，4代表路障, 5代表有防御塔
enum MapElement {START, END, PATH, EMPTY, BARRIER, HAVETOWER}
