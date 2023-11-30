package cu.innovat.psigplus.cim.questions;

import cu.innovat.psigplus.cim.GameLevel;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoise extends Question{

    private List<Sentence> sentences;

    public MultipleChoise(String uuid, String sentence, long lastUse, int used, String idlevel) {
        super(uuid, sentence, lastUse, used, idlevel);
        sentences = new ArrayList<Sentence>();
    }

    public MultipleChoise(String uuid, String sentence, long lastUse, GameLevel level) {
        super(uuid, sentence, lastUse, level);
        sentences = new ArrayList<Sentence>();
    }

    public MultipleChoise(String uuid, String sentence, long lastUse, int used, String idlevel, List<Sentence> sentences) {
        super(uuid, sentence, lastUse, used, idlevel);
        this.sentences = sentences;
    }

    public MultipleChoise(String uuid, String sentence, long lastUse, GameLevel level, List<Sentence> sentences) {
        super(uuid, sentence, lastUse, level);
        this.sentences = sentences;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }
}
