package cu.innovat.psigplus.cim;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 15/10/23
 */
public class Statistics {

    private GameMode modeGame;
    private GameLevel levelGame;
    private String titleStatistics;
    private int countGames;
    private long timeStampFGame;
    private long timeStampLGame;

    public Statistics(GameMode modeGame,GameLevel level,String title){
        this.levelGame = level;
        this.modeGame = modeGame;
        this.titleStatistics = title;
        this.countGames = 0;
        this.timeStampLGame = Long.MIN_VALUE;
        this.timeStampFGame = Long.MIN_VALUE;
    }

    public GameLevel getLevelGame() {
        return levelGame;
    }

    public String getTitleStatistics() {
        return titleStatistics;
    }

    public int getCountGames() {return countGames;}

    public void setCountGames(int countGames) {
        this.countGames = countGames;
    }

    public long getTimeStampFGame() {return timeStampFGame;}

    public void setTimeStampFGame(long timeStampFGame) {this.timeStampFGame = timeStampFGame;}

    public long getTimeStampLGame() {return timeStampLGame;}

    public void setTimeStampLGame(long timeStampLGame) {this.timeStampLGame = timeStampLGame;}
}
