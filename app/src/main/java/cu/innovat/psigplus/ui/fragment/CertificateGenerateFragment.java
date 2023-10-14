package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.interfaces.IClickButtonGenerateCertificate;
import cu.innovat.psigplus.interfaces.IObserverClickButtonGenerateCertificate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 12/10/23
 */
public class CertificateGenerateFragment extends BaseFragment implements IClickButtonGenerateCertificate {

    private List<IObserverClickButtonGenerateCertificate> observers;
    public CertificateGenerateFragment(){

        super();
        observers =new ArrayList<IObserverClickButtonGenerateCertificate>();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        m_viewFragment = inflater.inflate(R.layout.layout_cerficate_generate,container, false);
        prepareUI();
        return m_viewFragment;

    }

    @Override
    public void prepareUI() {
        if(m_viewFragment !=null){
            Button button = (Button) m_viewFragment.findViewById(R.id.button_generate_certificate);
            if(button!=null)
                button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View _view) {
        if(_view!=null){
            int ID = _view.getId();
            switch (ID){
                case R.id.button_generate_certificate:
                    this.notifyClickButtonGenerateCertificate();
                    break;
                default:break;
            }
        }
    }

    @Override
    public void attach(IObserverClickButtonGenerateCertificate observer) {
        observers.add(observer);
    }

    @Override
    public void detach(IObserverClickButtonGenerateCertificate observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyClickButtonGenerateCertificate() {
        for (IObserverClickButtonGenerateCertificate observer: observers) {
            observer.clickedButtonGenerateCertificate();
        }
    }
}
