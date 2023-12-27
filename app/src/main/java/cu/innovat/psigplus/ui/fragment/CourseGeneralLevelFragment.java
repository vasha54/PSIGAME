package cu.innovat.psigplus.ui.fragment;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.cim.GameLevel;
import cu.innovat.psigplus.cim.LevelGame;
import cu.innovat.psigplus.controller.PsiGameController;
import cu.innovat.psigplus.interfaces.IClickButtonGameLevel;
import cu.innovat.psigplus.interfaces.IObserverClickButtonGameLevel;
import cu.innovat.psigplus.interfaces.IObserverClickButtonRegisterCertificate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 3/10/23
 */
public class CourseGeneralLevelFragment extends BaseFragment {

    public CourseGeneralLevelFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        m_viewFragment = inflater.inflate(R.layout.layout_course_general_level,container, false);
        prepareUI();
        return m_viewFragment;

    }

    @Override
    public void prepareUI() {
        ImageView iButtonRookie = (ImageView) m_viewFragment.findViewById(R.id.button_rookie_general);
        ImageView iButtonCompetent = (ImageView) m_viewFragment.findViewById(R.id.button_competent_general);
        ImageView iButtonProfessional = (ImageView) m_viewFragment.findViewById(R.id.button_professional_general);

        if( iButtonProfessional !=null){
            iButtonProfessional.setOnClickListener(this);
            iButtonProfessional.setVisibility(View.INVISIBLE);
        }
        if( iButtonCompetent != null){
            iButtonCompetent.setOnClickListener(this);
            iButtonCompetent.setVisibility(View.INVISIBLE);
        }
        if( iButtonRookie != null) iButtonRookie.setOnClickListener(this);

        List<GameLevel> levels = PsiGameController.getInstance(getContext()).getLevelAviableCurrentPlayer();

        for(GameLevel level : levels){
            if(level == GameLevel.ROOKIE_GENERAL){
                if (iButtonRookie!=null) iButtonRookie.setVisibility(View.VISIBLE);
                if (iButtonCompetent!=null) iButtonCompetent.setVisibility(View.VISIBLE);
            }
            if(level == GameLevel.COMPETENT_GENERAL){
                if (iButtonCompetent!=null) iButtonCompetent.setVisibility(View.VISIBLE);
                if (iButtonProfessional!=null) iButtonProfessional.setVisibility(View.VISIBLE);
            }
            if(level == GameLevel.PROFESSIONAL_GENERAL){
                if (iButtonProfessional!=null) iButtonProfessional.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onClick(View _view) {
        if(_view !=null){
            int ID = _view.getId();
            switch (ID){
                case R.id.button_competent_general:
                    this.notifyClickButtonGameLevel(GameLevel.COMPETENT_GENERAL); break;
                case R.id.button_professional_general:
                    this.notifyClickButtonGameLevel(GameLevel.PROFESSIONAL_GENERAL);break;
                case R.id.button_rookie_general:
                    this.notifyClickButtonGameLevel(GameLevel.ROOKIE_GENERAL);break;
            }
        }
    }
}
