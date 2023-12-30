package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.core.content.ContextCompat;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.cim.Player;
import cu.innovat.psigplus.interfaces.*;
import cu.innovat.psigplus.util.LOG;
import cu.innovat.psigplus.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 12/10/23
 */
public class CertificateRegisterFragment extends BaseFragment implements IClickButtonRegisterCertificate,
        IRegisterPlayer {

    private List<IObserverClickButtonRegisterCertificate> observersCBRC;
    private List<IObserverRegisterPlayer> observersRP;

    private EditText m_editTextIMEI;
    private EditText m_editTextPhoneNumber;
    private EditText m_editTextName;
    private EditText m_editTextSurname;
    private EditText m_editTextCI;
    private Spinner spinnerGruop;

    private String IMEI;
    private String numberPhone;

    public CertificateRegisterFragment(String _IMEI, String _numberPhone){
        super();
        this.IMEI = _IMEI;
        this.numberPhone = _numberPhone;
        observersCBRC = new ArrayList<IObserverClickButtonRegisterCertificate>();
        observersRP = new ArrayList<IObserverRegisterPlayer>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        m_viewFragment = inflater.inflate(R.layout.layout_certificate_register,container, false);
        prepareUI();
        return m_viewFragment;

    }
    public void prepareUI(){
        if(m_viewFragment !=null){

            m_editTextIMEI = (EditText) m_viewFragment.findViewById(R.id.edit_text_emi);
            m_editTextPhoneNumber = (EditText) m_viewFragment.findViewById(R.id.edit_text_phone_number);
            m_editTextName = (EditText) m_viewFragment.findViewById(R.id.edit_text_name);
            m_editTextSurname = (EditText) m_viewFragment.findViewById(R.id.edit_text_surname);
            m_editTextCI = (EditText) m_viewFragment.findViewById(R.id.edit_text_ci);
            spinnerGruop = (Spinner) m_viewFragment.findViewById(R.id.spinner_gruop);

            Button button = (Button) m_viewFragment.findViewById(R.id.button_register_certificate);

            if(button != null) button.setOnClickListener(this);
            if(m_editTextIMEI != null) m_editTextIMEI.setText(this.IMEI);
            if(m_editTextPhoneNumber != null) m_editTextPhoneNumber.setText(this.numberPhone);
            if(spinnerGruop != null){
                ArrayAdapter<CharSequence> adapterGroups = ArrayAdapter.createFromResource(
                        getContext(), R.array.list_names_group_student, R.layout.custom_spinner_item);
                adapterGroups.setDropDownViewResource(R.layout.custom_spinner_item_list);
                spinnerGruop.setAdapter(adapterGroups);
                spinnerGruop.setSelected(true);
            }
        }
    }

    @Override
    public void attachOCBRC(IObserverClickButtonRegisterCertificate observer) {
        observersCBRC.add(observer);
    }

    @Override
    public void detachOCBRC(IObserverClickButtonRegisterCertificate observer) {

        observersCBRC.remove(observer);

    }

    @Override
    public void onClick(View _view) {
        if(_view!=null){
            int ID = _view.getId();
            switch (ID){
                case R.id.button_register_certificate:
                    if( this.registerUser() ) this.notifyClickButtonRegisterCertificate();
                    break;
                default:break;
            }
        }
    }

    @Override
    public void notifyClickButtonRegisterCertificate() {
        for (IObserverClickButtonRegisterCertificate observer: observersCBRC) {
            observer.clickedButtonRegisterCertificate();
        }
    }

    public boolean registerUser(){
        boolean register = false;
        if(m_editTextCI != null && m_editTextName != null && m_editTextSurname != null &&
                m_editTextIMEI != null && m_editTextPhoneNumber != null && spinnerGruop != null){
            String name = m_editTextName.getText().toString();
            String surname = m_editTextSurname.getText().toString();
            String ci = m_editTextCI.getText().toString();
            String numberPhone  = m_editTextPhoneNumber.getText().toString();
            String imei = m_editTextIMEI.getText().toString();
            String group = spinnerGruop.getSelectedItem().toString();
            String groupSlug = Util.toSlug(group);
            String error = "";
            String idPlayer = Util.generateUUID();

            LOG.i("TAG_DB_PSIGAME_PLUS","El grupo seleccionado es: "+group);

            boolean isValidName = !Util.isEmptyString(name);
            boolean isValidSurname = !Util.isEmptyString(surname);
            LOG.i("TAG_DB_PSIGAME_PLUS","El numero de carnet es :"+ci+".");
            boolean isValidCI = !Util.isEmptyString(ci) & Util.isValidCI(ci);
            boolean isValidIMEI = true;//!Util.isEmptyString(imei) & Util.isValidIMEI(imei);
            boolean isValidNumberPhone = true; // !Util.isEmptyString(numberPhone) &
//                    Util.isValidNumberPhone(numberPhone);
            boolean isValidGroup = !Util.isEmptyString(groupSlug);

            boolean isValidData = isValidName & isValidSurname & isValidCI & isValidIMEI &
                    isValidNumberPhone & isValidGroup;

            if(!isValidCI){
                error+=getString(R.string.error_ci);
                m_editTextCI.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.border_round_red_8dip));
            }

            if(!isValidGroup){
                error+=getString(R.string.error_group);
                spinnerGruop.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.border_round_red_8dip));
            }

            if(!isValidSurname){
                error+=getString(R.string.error_surname);
                m_editTextSurname.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.border_round_red_8dip));
            }

            if(!isValidName){
                error+=getString(R.string.error_name);
                m_editTextName.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.border_round_red_8dip));
            }

//            if(!isValidIMEI){
//                error+=getString(R.string.error_imei);
//                m_editTextIMEI.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.border_round_red_8dip));
//            }

//            if(!isValidNumberPhone){
//                error+=getString(R.string.error_number_phone);
//                m_editTextPhoneNumber.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.border_round_red_8dip));
//            }

            if( isValidData ){
                Player player = new Player(idPlayer,name,surname,ci,groupSlug,
                        IMEI,numberPhone,true);
                register = registerPlayer(player);
            }else{
                Toast toast= Toast.makeText(this.getContext(),
                        error,
                        Toast.LENGTH_LONG);
                toast.show();
            }
        }
        return register;
    }

    @Override
    public void attachORP(IObserverRegisterPlayer observer) {
        observersRP.add(observer);
    }

    @Override
    public void detachORP(IObserverRegisterPlayer observer) {
        observersRP.remove(observer);
    }

    @Override
    public boolean registerPlayer(Player player) {
        boolean register = true;
        for(IObserverRegisterPlayer obs : observersRP){
            register = register & obs.registerPlayer(player);
        }
        return register;
    }
}
