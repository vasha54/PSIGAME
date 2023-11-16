package cu.innovat.psigplus.cim.questions;

import cu.innovat.psigplus.cim.GameLevel;

import java.util.Comparator;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 10/10/23
 */
public abstract class Question  {
    protected String uuid;
    protected String sentence;
    protected int used;
    protected long lastUse;
    protected GameLevel level;
    protected String idLevel;

    public Question(String uuid, String sentence, long lastUse, int used,String idlevel){
        this.uuid = uuid;
        this.sentence = sentence;
        this.used = used;
        this.lastUse = lastUse;
        this.level = GameLevel.UNDEFINED;
        this.idLevel = idlevel;
    }

    public Question(String uuid, String sentence, long lastUse, GameLevel level){
        this.uuid = uuid;
        this.sentence = sentence;
        this.used = 0;
        this.lastUse = lastUse;
        this.level = level;
        this.idLevel = null;
    }

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSentence() { return sentence; }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public long getLastUse() {
        return lastUse;
    }

    public void setLastUse(long lastUse) {
        this.lastUse = lastUse;
    }

    public GameLevel getLevel() { return level; }

    public void setLevel(GameLevel level) { this.level = level;}

    public String getIdLevel() { return idLevel; }

    public void setIdLevel(String idLevel) { this.idLevel = idLevel; }



    @Override
    public String toString() {
        return "Question{" +
                "uuid='" + uuid + '\'' +
                ", sentence='" + sentence + '\'' +
                ", used=" + used +
                ", lastUse=" + lastUse +
                ", level=" + level +
                ", idLevel='" + idLevel + '\'' +
                '}';
    }
}
