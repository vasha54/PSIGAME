package cu.innovat.psigplus.cim;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 15/10/23
 */
public class Statistics {

    private GameMode modeGame;
    private GameLevel levelGame;



    private String titleStatistics;

    public Statistics(GameMode modeGame,GameLevel level,String title){
        this.levelGame = level;
        this.modeGame = modeGame;
        this.titleStatistics = title;
    }

    public GameLevel getLevelGame() {
        return levelGame;
    }

    public String getTitleStatistics() {
        return titleStatistics;
    }
}
