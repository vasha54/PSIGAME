package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import cu.innovat.psigplus.R;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 1/10/23
 */
public class ConfigurationFragment extends BaseFragment{

    ImageButton resetStadistics;
    ImageButton resetRegisterUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        m_viewFragment = inflater.inflate(R.layout.layout_configuration,container, false);
        prepareUI();
        return m_viewFragment;
    }

    @Override
    public void prepareUI() {
        resetStadistics = (ImageButton) m_viewFragment.findViewById(R.id.button_reset_stadistics);
        resetRegisterUser = (ImageButton) m_viewFragment.findViewById(R.id.button_reset_register_user);

        if(resetStadistics != null) resetStadistics.setOnClickListener(this);
        if(resetRegisterUser != null) resetRegisterUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View _view) {
        if(_view != null){
            int ID = _view.getId();
            switch (ID){
                case R.id.button_reset_register_user: this.confirmResetRegisterUser(); break;
                case R.id.button_reset_stadistics: this.confirmResetStadistics(); break;
            }
        }
    }

    private void confirmResetRegisterUser(){
        //TODO Falta
    }

    private void confirmResetStadistics(){
        //TODO Falta
    }
}
