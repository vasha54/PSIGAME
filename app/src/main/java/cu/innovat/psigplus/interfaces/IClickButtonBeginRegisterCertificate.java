package cu.innovat.psigplus.interfaces;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 12/10/23
 */
public interface IClickButtonBeginRegisterCertificate {
    void attach(IObserverClickButtonBeginRegisterCertificate observer);
    void detach(IObserverClickButtonBeginRegisterCertificate observer);
    void notifyClickButtonBeginRegisterCertificate();
}
