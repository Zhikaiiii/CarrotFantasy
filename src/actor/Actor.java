package actor;

public abstract class Actor {
    // 位置
    protected int x;
    protected int y;
    // 在地图中的行列
    protected int row;
    protected int column;
    // 血量
    protected int hp;

    // 构造函数
    Actor(int x, int y){
        this.x = x;
        this.y = y;
        this.hp = 1;
    }
    Actor(int x, int y, int hp){
        this.x = x;
        this.y = y;
        this.hp = hp;
    }

    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public int getHp(){
        return hp;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
}
