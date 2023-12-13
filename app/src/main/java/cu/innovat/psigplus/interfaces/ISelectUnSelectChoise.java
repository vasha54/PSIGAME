package cu.innovat.psigplus.interfaces;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 12/12/23
 */
public interface ISelectUnSelectChoise {
    void attach(IObserverSelectUnSelectChoise observer);
    void detach(IObserverSelectUnSelectChoise observer);
    void selectChoise(String idChoise);
    void unselectChoise(String idChoise);
}
