package cu.innovat.psigplus.interfaces;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 12/10/23
 */
public interface IClickButtonRegisterCertificate {
    void attachOCBRC(IObserverClickButtonRegisterCertificate observer);
    void detachOCBRC(IObserverClickButtonRegisterCertificate observer);
    void notifyClickButtonRegisterCertificate();
}
