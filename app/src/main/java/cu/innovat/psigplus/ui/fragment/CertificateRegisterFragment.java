package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.interfaces.IClickButtonRegisterCertificate;
import cu.innovat.psigplus.interfaces.IObserverClickButtonBeginRegisterCertificate;
import cu.innovat.psigplus.interfaces.IObserverClickButtonRegisterCertificate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 12/10/23
 */
public class CertificateRegisterFragment extends BaseFragment implements IClickButtonRegisterCertificate {

    private List<IObserverClickButtonRegisterCertificate> observers;
    private EditText m_editTextIMEI;
    private EditText m_editTextPhoneNumber;
    private Spinner spinnerGruop;
    private String IMEI;
    private String numberPhone;

    public CertificateRegisterFragment(String _IMEI, String _numberPhone){
        super();
        this.IMEI = _IMEI;
        this.numberPhone = _numberPhone;
        observers = new ArrayList<IObserverClickButtonRegisterCertificate>();
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
            spinnerGruop = (Spinner) m_viewFragment.findViewById(R.id.spinner_gruop);
            Button button = (Button) m_viewFragment.findViewById(R.id.button_register_certificate);

            if(button != null) button.setOnClickListener(this);
            if(m_editTextIMEI != null) m_editTextIMEI.setText(this.IMEI);
            if(m_editTextPhoneNumber != null) m_editTextPhoneNumber.setText(this.numberPhone);
            if(spinnerGruop != null){
                ArrayAdapter<CharSequence> adapterGroups = ArrayAdapter.createFromResource(
                        getContext(), R.array.list_names_group_student, android.R.layout.simple_spinner_dropdown_item);
                adapterGroups.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerGruop.setAdapter(adapterGroups);
                spinnerGruop.setSelection(0);
            }
        }
    }

    @Override
    public void attach(IObserverClickButtonRegisterCertificate observer) {
        observers.add(observer);
    }

    @Override
    public void detach(IObserverClickButtonRegisterCertificate observer) {
        observers.remove(observer);
    }

    @Override
    public void onClick(View _view) {
        if(_view!=null){
            int ID = _view.getId();
            switch (ID){
                case R.id.button_register_certificate:
                    this.notifyClickButtonRegisterCertificate();
                    break;
                default:break;
            }
        }
    }

    @Override
    public void notifyClickButtonRegisterCertificate() {
        for (IObserverClickButtonRegisterCertificate observer: observers) {
            observer.clickedButtonRegisterCertificate();
        }
    }
}
