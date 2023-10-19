package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.cim.GameLevel;
import cu.innovat.psigplus.interfaces.IClickButtonGameLevel;
import cu.innovat.psigplus.interfaces.IObserverClickButtonGameLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 3/10/23
 */
public class CourseMedicalLevelFragment extends BaseFragment {



    public CourseMedicalLevelFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        m_viewFragment = inflater.inflate(R.layout.layout_course_medical_level,container, false);
        prepareUI();
        return m_viewFragment;
    }

    @Override
    public void prepareUI() {
        ImageView iButtonRookie = (ImageView) m_viewFragment.findViewById(R.id.button_rookie_medical);
        ImageView iButtonCompetent = (ImageView) m_viewFragment.findViewById(R.id.button_competent_medical);
        ImageView iButtonProfessional = (ImageView) m_viewFragment.findViewById(R.id.button_professional_medical);

        if( iButtonProfessional !=null) iButtonProfessional.setOnClickListener(this);
        if( iButtonCompetent != null) iButtonCompetent.setOnClickListener(this);
        if( iButtonRookie != null) iButtonRookie.setOnClickListener(this);
    }

    @Override
    public void onClick(View _view) {
        if(_view !=null){
            int ID = _view.getId();
            switch (ID){
                case R.id.button_competent_medical:
                    this.notifyClickButtonGameLevel(GameLevel.COMPETENT_MEDICAL); break;
                case R.id.button_professional_medical:
                    this.notifyClickButtonGameLevel(GameLevel.PROFESSIONAL_MEDICAL);break;
                case R.id.button_rookie_medical:
                    this.notifyClickButtonGameLevel(GameLevel.ROOKIE_MEDICAL);break;
            }
        }
    }
}
