package control;


public class Map {
    private int [][] map;
    private int [] dColumn;
    private int [] dRow;
    private int [] pathColumn;
    private int [] pathRow;
    // 每格之间的间隔
    private int interval;
    Map(Difficulty difficulty){
        if(difficulty == Difficulty.EASY){
            map = new int[][]
                    {{3, 3, 3, 4, 4, 4, 4, 4, 4, 3, 3, 3},
                    {3, 0, 3, 4, 4, 4, 4, 4, 4, 4, 1, 4},
                    {3, 2, 3, 3, 4, 3, 3, 4, 3, 3, 2, 3},
                    {3, 2, 4, 3, 2, 2, 2, 2, 3, 4, 2, 3},
                    {3, 2, 2, 2, 2, 4, 4, 2, 2, 2, 2, 3},
                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}};
//            dColumn = new int[]{0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0};
//            dRow = new int[]{1, 1, 1, 0, 0, 0, -1, 0, 0, 0, 1, 0, 0, 0, -1, -1, -1, -1};
            pathColumn = new int[]{1, 1, 1, 1, 2, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 10, 10, 10};
            pathRow = new int[]{1, 2, 3, 4, 4, 4, 4, 3, 3, 3, 3, 4, 4, 4, 4, 3, 2, 1};
        }
        else if(difficulty == Difficulty.MEDIUM){
            map = new int[][]
                    {{3, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                    {3, 4, 4, 3, 0, 2, 2, 2, 2, 2, 3, 4},
                    {3, 3, 3, 3, 4, 3, 4, 3, 4, 2, 4, 4},
                    {3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 4, 4},
                    {4, 4, 2, 4, 3, 4, 3, 3, 4, 4, 3, 3},
                    {3, 3, 2, 2, 2, 2, 2, 1, 4, 3, 3, 3}};
//            dColumn = new int[]{1, 1, 1, 1, 1};
//            dRow = new int[]{0, 0, 0, 0, };
            pathColumn = new int[]{4, 5, 6, 7, 8, 9, 9, 9, 8, 7, 6, 5, 4, 3, 2, 2, 2, 3, 4, 5, 6, 7};
            pathRow = new int[]{1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 5, 5, 5, 5, 5, 5};
        }
        else{
            map = new int[][]
                    {{3, 3, 4, 4, 3, 3, 3, 3, 3, 4, 4, 3},
                    {3, 3, 4, 4, 3, 4, 4, 2, 2, 4, 4, 3},
                    {3, 0, 3, 4, 3, 4, 4, 2, 2, 2, 1, 4},
                    {3, 2, 4, 4, 4, 2, 2, 2, 3, 4, 3, 3},
                    {3, 2, 3, 4, 4, 2, 4, 4, 4, 4, 3, 3},
                    {3, 2, 2, 2, 2, 2, 3, 4, 4, 4, 3, 3}};
//            dColumn = new int[]{0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0};
//            dRow = new int[]{1, 1, 1, 0, 0, 0, -1, 0, 0, 0, 1, 0, 0, 0, -1, -1, -1, -1};
            pathColumn = new int[]{1, 1, 1, 1, 2, 3, 4, 5, 5, 5, 6, 7, 7, 7, 8, 8, 9, 10};
            pathRow = new int[]{2, 3, 4, 5, 5, 5, 5, 5, 4, 3, 3, 3, 2, 1, 1, 2, 2, 2};

        }
        dRow = new int[pathRow.length - 1];
        dColumn = new int[pathColumn.length - 1];
        for(int i = 0; i < dRow.length; i++){
            dRow[i] = pathRow[i+1] - pathRow[i];
            dColumn[i] = pathColumn[i+1] - pathColumn[i];
        }
        interval = 80;
    }
    public MapElement getMapElement(int row, int column){
        return MapElement.values()[map[row][column]];
    }
    public void setMapElement(int row, int column, MapElement element){
        map[row][column] = element.ordinal();
    }
    public int getStartColumn(){
        return pathColumn[0];
    }
    public int getStartRow(){
        return pathRow[0];
    }
    public int getEndColumn(){
        return pathColumn[pathColumn.length - 1];
    }
    public int getEndRow(){
        return pathRow[pathRow.length - 1];
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

