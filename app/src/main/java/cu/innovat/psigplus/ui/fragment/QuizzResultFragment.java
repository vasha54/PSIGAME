package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.cim.ResultQuizz;
import cu.innovat.psigplus.interfaces.IClickButtonResultQuizz;
import cu.innovat.psigplus.interfaces.IObserverClickButtonResultQuizz;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 16/11/23
 */
public class QuizzResultFragment extends BaseFragment implements IClickButtonResultQuizz {
    private String title;
    private String description;

    private TextView tviewTitle;
    private TextView tviewDescription;

    private Button buttonContinue;

    private List<IObserverClickButtonResultQuizz> observersOCBRQ;

    private ResultQuizz result;

    public QuizzResultFragment(String title,String description,ResultQuizz result) {
        super();
        this.title = title;
        this.description = description;
        this.result = result;
        this.observersOCBRQ = new ArrayList<IObserverClickButtonResultQuizz>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        m_viewFragment = inflater.inflate(R.layout.layout_quizz_result,container, false);
        prepareUI();
        return m_viewFragment;
    }

    @Override
    public void prepareUI() {
        tviewTitle = (TextView) m_viewFragment.findViewById(R.id.text_view_title_result);
        tviewDescription = (TextView) m_viewFragment.findViewById(R.id.text_view_description_result);
        buttonContinue = (Button) m_viewFragment.findViewById(R.id.button_continue_quizz);

        if(buttonContinue!=null) buttonContinue.setOnClickListener(this);
        if(tviewDescription!=null) tviewDescription.setText(Html.fromHtml( this.description));
        if(tviewTitle!=null) tviewTitle.setText(Html.fromHtml( this.title));
    }

    @Override
    public void onClick(View _view) {
        if(_view != null){
            int ID = _view.getId();
            switch (ID){
                case R.id.button_continue_quizz:
                    notifyClickButtonResultQuizz();
                    break;
            }
        }
    }

    @Override
    public void attachOCBRQ(IObserverClickButtonResultQuizz observer) {
        observersOCBRQ.add(observer);
    }

    @Override
    public void detachOCBRQ(IObserverClickButtonResultQuizz observer) {
        observersOCBRQ.remove(observer);
    }

    @Override
    public void notifyClickButtonResultQuizz() {
        for(IObserverClickButtonResultQuizz obj: observersOCBRQ){
            obj.clickedButtonResultQuizz();
        }
    }
}
