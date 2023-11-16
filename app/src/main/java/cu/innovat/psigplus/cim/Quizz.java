package cu.innovat.psigplus.cim;

import cu.innovat.psigplus.cim.questions.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 10/11/23
 */


public class Quizz {

    private List<Question> questions;
    private GameLevel level;
    private String idLevel;
    private String idUsser;
    private String idQuizz;
    private long date;
    private int durationQuestion;
    private int lifes;

    public Quizz(String idQuizz, List<Question> questions, GameLevel level, String idLevel, String idUsser, long date) {
        this.questions = questions;
        this.level = level;
        this.idLevel = idLevel;
        this.idUsser = idUsser;
        this.date = date;
        this.idQuizz = idQuizz;
        this.durationQuestion =10;
        this.lifes = 1;
    }

    public Quizz() {
        this.questions = new ArrayList<Question>();
        this.level = GameLevel.UNDEFINED;
        this.idLevel = null;
        this.idUsser = null;
        this.idQuizz = null;
        this.date = -1;
        this.durationQuestion = 10;
    }

    public GameLevel getLevel() {
        return level;
    }

    public void setLevel(GameLevel level) {
        this.level = level;
    }

    public String getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(String idLevel) {
        this.idLevel = idLevel;
    }

    public String getIdUsser() {
        return idUsser;
    }

    public void setIdUsser(String idUsser) {
        this.idUsser = idUsser;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getIdQuizz() {
        return idQuizz;
    }

    public void setIdQuizz(String idQuizz) {
        this.idQuizz = idQuizz;
    }

    public List<Question> getQuestions() { return questions; }

    public void setQuestions(List<Question> questions) { this.questions = questions; }

    public int getDurationQuestion() { return durationQuestion; }

    public void setDurationQuestion(int durationQuestion) { this.durationQuestion = durationQuestion; }

    public int getLifes() { return lifes;}

    public void setLifes(int lifes) { this.lifes = lifes;}
}
