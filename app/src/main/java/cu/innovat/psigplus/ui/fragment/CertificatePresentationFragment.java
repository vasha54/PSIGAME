package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.interfaces.IClickButtonBeginRegisterCertificate;
import cu.innovat.psigplus.interfaces.IObserverClickButtonBeginRegisterCertificate;

import java.util.*;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 12/10/23
 */
public class CertificatePresentationFragment extends BaseFragment implements IClickButtonBeginRegisterCertificate {

    private List<IObserverClickButtonBeginRegisterCertificate> observers;
    
    public CertificatePresentationFragment(){
        super();
        observers = new ArrayList<IObserverClickButtonBeginRegisterCertificate>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        m_viewFragment = inflater.inflate(R.layout.layout_certificate_presentation,container, false);
        prepareUI();
        return m_viewFragment;
    }

    public void prepareUI(){
        if(m_viewFragment !=null){
            Button button = (Button) m_viewFragment.findViewById(R.id.button_begin_register_certificate);
            if(button!=null)
                button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View _view) {
        if(_view!=null){
            int ID = _view.getId();
            switch (ID){
                case R.id.button_begin_register_certificate:
                    this.notifyClickButtonBeginRegisterCertificate();
                    break;
                default:break;
            }
        }
    }

    @Override
    public void attach(IObserverClickButtonBeginRegisterCertificate observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(IObserverClickButtonBeginRegisterCertificate observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyClickButtonBeginRegisterCertificate(){
        for (IObserverClickButtonBeginRegisterCertificate observer: observers) {
            observer.clickedButtonBeginRegisterCertificate();
        }
    }
}
