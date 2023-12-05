package cu.innovat.psigplus.cim.questions;

import cu.innovat.psigplus.cim.GameLevel;
import cu.innovat.psigplus.util.Util;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 3/11/23
 */
public class TrueOrFalse extends Question{

    private boolean answer;

   public TrueOrFalse(String uuid, String sentence, long lastUse, int used, String idlevel, boolean answer){
        super(uuid,sentence,lastUse,used,idlevel);
        this.answer = answer;
    }
    public TrueOrFalse(String uuid, String sentence, long lastUse,GameLevel level, boolean answer){
       super(uuid,sentence,lastUse,level);
       this.answer=answer;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "TrueOrFalse{" +
                "uuid='" + uuid + '\'' +
                ", sentence='" + sentence + '\'' +
                ", used=" + used +
                ", lastUse=" + lastUse +
                ", level=" + level +
                ", idLevel='" + idLevel + '\'' +
                ", answer=" + answer +
                '}';
    }
}
