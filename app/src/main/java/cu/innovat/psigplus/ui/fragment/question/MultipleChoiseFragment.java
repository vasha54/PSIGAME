package cu.innovat.psigplus.ui.fragment.question;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.cim.questions.MultipleChoise;
import cu.innovat.psigplus.cim.questions.Question;
import cu.innovat.psigplus.cim.questions.Sentence;
import cu.innovat.psigplus.adapter.MultipleChoiseAdapter;

import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 3/12/23
 */
public class MultipleChoiseFragment extends QuestionFragment{

    TextView tv_sentence;
    RecyclerView rv_listChoise;
    MultipleChoiseAdapter adapterChoise;

    public MultipleChoiseFragment(Question q) {
        super(q);
        adapterChoise = null;
    }

    @Override
    public int checkQuestion() {
        int check = 0;
        if(adapterChoise != null){
            check = 1;
            List<Sentence> sentences = adapterChoise.getSentences();
            for(Sentence s :sentences)
                if (s.isCorrect() != s.isSelect()) check =0;
        }
        return check;
    }

    @Override
    public void loadQuestion() {
        if(question != null){
            if( question instanceof MultipleChoise){
                MultipleChoise mch = (MultipleChoise) question;
                if(mch != null){
                    if(tv_sentence != null) tv_sentence.setText(mch.getSentence());
                    if(rv_listChoise != null){
                        Log.i("TAG_DB_PSIGAME_PLUS",
                                "Cantidad de opciones de la pregunta:"+String.valueOf(mch.getSentences().size()));
                        adapterChoise = new MultipleChoiseAdapter(getContext(),mch.getSentences());
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        rv_listChoise.setLayoutManager(layoutManager);
                        rv_listChoise.setItemAnimator(new DefaultItemAnimator());
                        rv_listChoise.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
                        rv_listChoise.setAdapter(adapterChoise);
                    }
                }
            }
        }
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
        tv_sentence = (TextView) m_viewFragment.findViewById(R.id.text_view_sentence_mc);
        rv_listChoise = (RecyclerView) m_viewFragment.findViewById(R.id.list_choise);
    }

    @Override
    public void onClick(View _view) {

    }
}
