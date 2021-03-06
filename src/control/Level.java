package control;

// 游戏关卡
public class Level {
    // 关卡难度
    private Difficulty difficulty;
    // 怪物波数
    private int numWaves;
    private int numMonsters;
    private int monsterSpeed;
    private int monsterHP;

    Level(){
        setDifficulty(Difficulty.EASY);
    }
    Level(Difficulty difficulty){
        setDifficulty(difficulty);
    }
    // 设置难度
    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
        switch (this.difficulty){
            case EASY:
                numWaves = 10;
                numMonsters = 15;
                monsterHP = 100;
                monsterSpeed = 1;
                break;
            case MEDIUM:
                numWaves = 15;
                numMonsters = 20;
                monsterHP = 150;
                monsterSpeed = 2;
                break;
            case DIFFICULTY:
                numWaves = 15;
                numMonsters = 25;
                monsterHP = 200;
                monsterSpeed = 3;
                break;
            default:
                break;
        }
    }

    public void setMonsterHP(){
        monsterHP = (int)(monsterHP*1.15);
    }

    public int getNumWaves(){
        return numWaves;
    }
    public int getNumMonsters() {
        return numMonsters;
    }
    public int getMonsterSpeed(){
        return monsterSpeed;
    }
    public int getMonsterHP(){
        return monsterHP;
    }
}
