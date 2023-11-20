package cu.innovat.psigplus.interfaces;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 19/11/23
 */
public interface IClickButtonStartQuizz {
    void attachOCBSQ(IObserverClickButtonStartQuizz observer);
    void detachOCBSQ(IObserverClickButtonStartQuizz observer);
    void notifyClickButtonStartQuizz();
}
