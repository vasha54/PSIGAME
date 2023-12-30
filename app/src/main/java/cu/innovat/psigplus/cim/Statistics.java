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
    private int countGamesBeforeWin;

    private long timeStampFGame;
    private long timeStampLGame;
    private long timeStampFWGame;

    private double bestScore;
    private double worstScore;
    private double meanScore;

    public Statistics(GameMode modeGame,GameLevel level,String title){
        this.levelGame = level;
        this.modeGame = modeGame;
        this.titleStatistics = title;
        this.countGames = 0;
        this.countGamesBeforeWin = Integer.MIN_VALUE;
        this.timeStampLGame = Long.MIN_VALUE;
        this.timeStampFGame = Long.MIN_VALUE;
        this.timeStampFWGame = Long.MIN_VALUE;
        this.bestScore = Double.MIN_VALUE;
        this.worstScore = Double.MIN_VALUE;
        this.meanScore = Double.MIN_VALUE;
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

    public long getTimeStampFWGame() { return timeStampFWGame;}

    public void setTimeStampFWGame(long timeStampFWGame) { this.timeStampFWGame = timeStampFWGame;}

    public double getBestScore() {return bestScore;}

    public void setBestScore(double bestScore) {this.bestScore = bestScore;}

    public double getWorstScore() {return worstScore;}

    public void setWorstScore(double worstScore) {this.worstScore = worstScore;}

    public double getMeanScore() {return meanScore;}

    public void setMeanScore(double meanScore) {this.meanScore = meanScore;}

    public int getCountGamesBeforeWin() {return countGamesBeforeWin;}

    public void setCountGamesBeforeWin(int countGamesBeforeWin) {this.countGamesBeforeWin = countGamesBeforeWin;}
}
