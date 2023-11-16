package cu.innovat.psigplus.ui.fragment.question;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.core.content.ContextCompat;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.cim.questions.Question;
import cu.innovat.psigplus.cim.questions.TrueOrFalse;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 10/11/23
 */
public class TrueOrFalseFragment extends QuestionFragment{

    private ToggleButton toogleButtonTrue;
    private ToggleButton toogleButtonFalse;
    private TextView textViewSetence;

    public TrueOrFalseFragment(Question question){

        super(question);
    }

    @Override
    public int checkQuestion() {

        int check =-1;
        if( question instanceof TrueOrFalse){
            TrueOrFalse tof = (TrueOrFalse) question;
            if(tof != null && toogleButtonFalse != null && toogleButtonTrue != null){
                if( (toogleButtonTrue.isChecked() && tof.isAnswer() ==1) || (toogleButtonFalse.isChecked() && tof.isAnswer() ==0))
                    check = 1;
                if ((toogleButtonTrue.isChecked() && tof.isAnswer() ==0) || (toogleButtonFalse.isChecked() && tof.isAnswer() ==1))
                    check = 0;
            }
        }
        return check;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        m_viewFragment = inflater.inflate(R.layout.layout_q_true_or_false,container, false);
        prepareUI();
        loadQuestion();
        return m_viewFragment;
    }

    @Override
    public void prepareUI() {
        textViewSetence = (TextView) m_viewFragment.findViewById(R.id.text_view_sentence);
        toogleButtonTrue = (ToggleButton) m_viewFragment.findViewById(R.id.toggle_button_true);
        toogleButtonFalse = (ToggleButton) m_viewFragment.findViewById(R.id.toggle_button_false);

        if( toogleButtonTrue != null){
            toogleButtonTrue.setOnClickListener(this);
            toogleButtonTrue.setChecked(false);
        }

        if( toogleButtonFalse != null){
            toogleButtonFalse.setOnClickListener(this);
            toogleButtonFalse.setChecked(false);
        }
    }

    @Override
    public void onClick(View _view) {
        if(_view != null){
            int ID = _view.getId();
            switch (ID){
                case R.id.toggle_button_true:
                    if( toogleButtonTrue != null ){
                        if(toogleButtonTrue.isChecked()){
                            toogleButtonTrue.setEnabled(false);
                            toogleButtonTrue.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_disabled));
                            if(toogleButtonFalse!=null) {
                                toogleButtonFalse.setEnabled(true);
                                toogleButtonFalse.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_false));
                                toogleButtonFalse.setChecked(false);
                            }
                        }
                    }
                    break;
                case R.id.toggle_button_false:
                    if( toogleButtonFalse != null ){
                        if(toogleButtonFalse.isChecked()){
                            toogleButtonFalse.setEnabled(false);
                            toogleButtonFalse.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_disabled));
                            if(toogleButtonTrue!=null) {
                                toogleButtonTrue.setEnabled(true);
                                toogleButtonTrue.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button_true));
                                toogleButtonTrue.setChecked(false);
                            }
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void loadQuestion(){
        if( this.question instanceof TrueOrFalse){
            TrueOrFalse tof = (TrueOrFalse) this.question;
            if(tof !=null && textViewSetence != null){
                textViewSetence.setText(tof.getSentence());
            }
        }
    }
}
