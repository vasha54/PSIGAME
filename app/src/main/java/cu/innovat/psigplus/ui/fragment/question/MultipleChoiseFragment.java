package cu.innovat.psigplus.ui.fragment.question;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.cim.questions.Question;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 3/12/23
 */
public class MultipleChoiseFragment extends QuestionFragment{

    public MultipleChoiseFragment(Question q) {
        super(q);
    }

    @Override
    public int checkQuestion() {
        return 0;
    }

    @Override
    public void loadQuestion() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        m_viewFragment = inflater.inflate(R.layout.layout_q_multiple_choise,container, false);
        prepareUI();
        loadQuestion();
        return m_viewFragment;
    }

    @Override
    public void prepareUI() {

    }

    @Override
    public void onClick(View _view) {

    }
}
