package cu.innovat.psigplus.cim.questions;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 10/10/23
 */
public abstract class Question {
    private String uuid;

    public Question(String uuid){
        this.uuid = uuid;
    }

    public Question(){
        this.uuid = null;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
