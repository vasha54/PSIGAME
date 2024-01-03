package cu.innovat.psigplus.ui.fragment.question;

import cu.innovat.psigplus.cim.questions.Question;
import cu.innovat.psigplus.ui.fragment.BaseFragment;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 10/11/23
 */
public abstract class QuestionFragment extends BaseFragment {

    protected Question question;

    public QuestionFragment(Question q){

        super();
        this.question = q;
    }

    public abstract int checkQuestion();
    public abstract void loadQuestion();

    public String getUuid(){
        String id = toString();
        if(this.question != null) id = this.question.getUuid();
        return id;
    }

    public String getIdQuestion(){
        String id = null;
        if(this.question!=null) id = this.question.getUuid();
        return id;
    }

    public int getTimeQuestion(){
        int time = 0;
        if(this.question != null) time = this.question.durationQuestion();
        return time;
    }

    @Override
    public String toString() {
        return "QuestionFragment{" +
                "question=" + question +
                '}';
    }


}
