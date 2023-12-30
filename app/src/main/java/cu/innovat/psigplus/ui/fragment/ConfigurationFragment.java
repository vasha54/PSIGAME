package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.appcompat.app.AlertDialog;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.controller.PsiGameController;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 1/10/23
 */
public class ConfigurationFragment extends BaseFragment{

    private ImageButton resetStadistics;
    private ImageButton resetRegisterPlayer;
    private AlertDialog.Builder m_dialog;
    private AlertDialog m_alertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        m_dialog = null;
        m_alertDialog = null;
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
        resetRegisterPlayer = (ImageButton) m_viewFragment.findViewById(R.id.button_reset_register_player);

        if(resetStadistics != null) resetStadistics.setOnClickListener(this);
        if(resetRegisterPlayer != null) resetRegisterPlayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View _view) {
        if(_view != null){
            int ID = _view.getId();
            switch (ID){
                case R.id.button_reset_register_player: this.confirmResetRegisterPlayer(); break;
                case R.id.button_reset_stadistics: this.confirmResetStadistics(); break;
                case R.id.button_confirm_reset_register_player: this.resetRegisterPlayer();
                case R.id.button_cancel_reset_register_player:
                    if(m_alertDialog != null)
                        m_alertDialog.dismiss();
                    break;
            }
        }
    }

    private void confirmResetRegisterPlayer(){
        m_dialog = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view  = inflater.inflate(R.layout.dialog_confirmation_reset_register_player, null);

        Button confirmResetRegister = (Button) view.findViewById(R.id.button_confirm_reset_register_player);
        Button cancelResetRegister = (Button) view.findViewById(R.id.button_cancel_reset_register_player);

        if(confirmResetRegister != null) confirmResetRegister.setOnClickListener(this);
        if(cancelResetRegister != null) cancelResetRegister.setOnClickListener(this);

        m_dialog.setView(view);

        m_alertDialog = m_dialog.create();
        m_alertDialog.show();
    }

    private void confirmResetStadistics(){
        //TODO Falta
    }

    private void resetRegisterPlayer(){
        PsiGameController.getInstance(getContext()).resetRegisterPlayer();
    }
}
