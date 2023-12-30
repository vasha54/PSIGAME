package cu.innovat.psigplus.cim.questions;

import cu.innovat.psigplus.cim.GameLevel;
import cu.innovat.psigplus.util.Util;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 3/11/23
 */
public class TrueOrFalse extends Question{

    private boolean answer;

    /**
     *
     * @param uuid
     * @param sentence
     * @param lastUse
     * @param used
     * @param idlevel
     * @param answer
     */
   public TrueOrFalse(String uuid, String sentence, long lastUse, int used, String idlevel, boolean answer){
        super(uuid,sentence,lastUse,used,idlevel);
        this.answer = answer;
    }

    /**
     *
     * @param uuid
     * @param sentence
     * @param lastUse
     * @param level
     * @param answer
     */
    public TrueOrFalse(String uuid, String sentence, long lastUse,GameLevel level, boolean answer){
       super(uuid,sentence,lastUse,level);
       this.answer=answer;
    }

    /**
     *
     * @return
     */
    public boolean isAnswer() {
        return answer;
    }

    /**
     *
     * @param answer
     */
    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    /**
     *
     * @return
     */
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
