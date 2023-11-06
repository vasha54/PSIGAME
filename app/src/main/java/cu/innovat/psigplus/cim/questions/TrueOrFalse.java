package cu.innovat.psigplus.cim.questions;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 3/11/23
 */
public class TrueOrFalse extends Question{
    private String sentence;
    private boolean answer;

    public TrueOrFalse(String uuid, String sentence, boolean answer) {
        super(uuid);
        this.sentence = sentence;
        this.answer = answer;
    }

    public TrueOrFalse() {
        super();
        this.sentence = sentence;
        this.answer = false;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
