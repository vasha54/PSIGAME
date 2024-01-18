package cu.innovat.psigplus.ui.fragment;

import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.cim.Format;
import cu.innovat.psigplus.cim.Player;
import cu.innovat.psigplus.controller.PsiGameController;
import cu.innovat.psigplus.interfaces.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 1/10/23
 */
public class CertificateFragment extends BaseFragment implements IObserverClickButtonBeginRegisterCertificate,
        IObserverClickButtonRegisterCertificate, IObserverClickButtonGenerateCertificate,
        IObserverRegisterPlayer, IClickButtonGenerateCertificate,IClickButtonRegisterCertificate {

    private CertificatePresentationFragment m_fPresentation;
    private CertificateRegisterFragment m_fRegister;
    private CertificateGenerateFragment m_fGenerate;
    private String IMEI;
    private String numberPhone;

    private List<IObserverClickButtonGenerateCertificate> observersOCBGC;
    private List<IObserverClickButtonRegisterCertificate> observersOCBRC;

    public CertificateFragment(String _IMEI, String _numberPhone){
        super();
        this.IMEI = _IMEI;
        this.numberPhone = _numberPhone;
        m_fGenerate = new CertificateGenerateFragment();
        m_fPresentation = new CertificatePresentationFragment();
        m_fRegister = new CertificateRegisterFragment(this.IMEI,this.numberPhone);

        observersOCBGC = new ArrayList<IObserverClickButtonGenerateCertificate>();
        observersOCBRC = new ArrayList<IObserverClickButtonRegisterCertificate>();
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
        m_fRegister.attachOCBRC(this);
        m_fRegister.attachORP(this);
        m_fGenerate.attach(this);
        String idPlayerActive = PsiGameController.getInstance(getContext()).getIDCurrentPlayer();
        if(idPlayerActive == null){
            FragmentManager fragmentManager = getChildFragmentManager();
            fragmentManager.beginTransaction().
                replace(R.id.containerFragmentCertificate,m_fPresentation).addToBackStack(m_fPresentation.toString()).
                commit();
        }else{
            FragmentManager fragmentManager = getChildFragmentManager();
            fragmentManager.beginTransaction().
                    replace(R.id.containerFragmentCertificate,m_fGenerate).addToBackStack(m_fPresentation.toString()).
                    commit();
        }

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
        notifyClickButtonRegisterCertificate();
    }

    @Override
    public void clickedButtonGenerateCertificate(List<Format> formats) {
        for(IObserverClickButtonGenerateCertificate obj :observersOCBGC)
            obj.clickedButtonGenerateCertificate(formats);
    }


    @Override
    public boolean registerPlayer(Player player) {
        return PsiGameController.getInstance(getContext()).registerPlayer(player);
    }

    @Override
    public void attach(IObserverClickButtonGenerateCertificate observer) {
        observersOCBGC.add(observer);
    }

    @Override
    public void detach(IObserverClickButtonGenerateCertificate observer) {
        observersOCBGC.remove(observer);
    }

    @Override
    public void notifyClickButtonGenerateCertificate() {

    }

    @Override
    public void attachOCBRC(IObserverClickButtonRegisterCertificate observer) {
        observersOCBRC.add(observer);
    }

    @Override
    public void detachOCBRC(IObserverClickButtonRegisterCertificate observer) {
        observersOCBRC.remove(observer);
    }

    @Override
    public void notifyClickButtonRegisterCertificate() {
        for(IObserverClickButtonRegisterCertificate obj : observersOCBRC)
            obj.clickedButtonRegisterCertificate();
    }
}
