package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    public CertificateRegisterFragment(){
        super();
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
            Button button = (Button) m_viewFragment.findViewById(R.id.button_register_certificate);
            if(button!=null)
                button.setOnClickListener(this);
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
