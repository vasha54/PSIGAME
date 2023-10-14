package cu.innovat.psigplus.ui.fragment;

import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.interfaces.IObserverClickButtonBeginRegisterCertificate;
import cu.innovat.psigplus.interfaces.IObserverClickButtonGenerateCertificate;
import cu.innovat.psigplus.interfaces.IObserverClickButtonRegisterCertificate;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 1/10/23
 */
public class CertificateFragment extends BaseFragment implements IObserverClickButtonBeginRegisterCertificate,
        IObserverClickButtonRegisterCertificate, IObserverClickButtonGenerateCertificate {

    private CertificatePresentationFragment m_fPresentation;
    private CertificateRegisterFragment m_fRegister;
    private CertificateGenerateFragment m_fGenerate;

    public CertificateFragment(){
        super();
        m_fGenerate = new CertificateGenerateFragment();
        m_fPresentation = new CertificatePresentationFragment();
        m_fRegister = new CertificateRegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        m_viewFragment = inflater.inflate(R.layout.layout_certificate,container, false);
        prepareUI();
        return m_viewFragment;

    }

    @Override
    public void prepareUI(){

        m_fPresentation.attach(this);
        m_fRegister.attach(this);
        m_fGenerate.attach(this);

        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().
                replace(R.id.containerFragmentCertificate,m_fPresentation).addToBackStack(m_fPresentation.toString()).
                commit();

    }

    @Override
    public void clickedButtonBeginRegisterCertificate() {
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().
                replace(R.id.containerFragmentCertificate,m_fRegister).addToBackStack(m_fRegister.toString()).
                commit();
    }

    @Override
    public void clickedButtonRegisterCertificate() {
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().
                replace(R.id.containerFragmentCertificate,m_fGenerate).addToBackStack(m_fGenerate.toString()).
                commit();
    }

    @Override
    public void clickedButtonGenerateCertificate() {

    }
}
