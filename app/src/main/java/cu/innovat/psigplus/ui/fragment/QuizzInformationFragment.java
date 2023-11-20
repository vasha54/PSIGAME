package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import cu.innovat.psigplus.R;
import cu.innovat.psigplus.interfaces.IClickButtonStartQuizz;
import cu.innovat.psigplus.interfaces.IObserverClickButtonStartQuizz;
import cu.innovat.psigplus.ui.activity.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 19/11/23
 */
public class QuizzInformationFragment extends  BaseFragment implements IClickButtonStartQuizz {

    private String informationQuizz;
    private List<IObserverClickButtonStartQuizz> observerCBSQ;

    public QuizzInformationFragment(String informationQuizz) {
        super();
        this.informationQuizz = informationQuizz;
        observerCBSQ = new ArrayList<IObserverClickButtonStartQuizz>();
    }

    @Override
    public void prepareUI() {
        if(m_viewFragment!=null){
            TextView textView = (TextView) m_viewFragment.findViewById(R.id.text_view_information_quizz);
            if(textView != null) textView.setText(Html.fromHtml( this.informationQuizz));
            Button button = (Button) m_viewFragment.findViewById(R.id.button_start_quizz);
            if(button != null) button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View _view) {
        if(_view!= null){
            int ID = _view.getId();
            switch (ID){
                case R.id.button_start_quizz:
                    this.notifyClickButtonStartQuizz();
                    break;
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        m_viewFragment = inflater.inflate(R.layout.layout_quizz_information,container, false);
        prepareUI();
        return m_viewFragment;
    }

    @Override
    public void attachOCBSQ(IObserverClickButtonStartQuizz observer) {
        observerCBSQ.add(observer);
    }

    @Override
    public void detachOCBSQ(IObserverClickButtonStartQuizz observer) {
        observerCBSQ.remove(observer);
    }

    @Override
    public void notifyClickButtonStartQuizz() {
        for (IObserverClickButtonStartQuizz o : observerCBSQ) {
            o.clickedButtonStartQuizz();
        }
    }
}
