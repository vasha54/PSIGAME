package cu.innovat.psigplus.interfaces;

import cu.innovat.psigplus.cim.GameLevel;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 13/10/23
 */
public interface IClickButtonGameLevel {
    void attach(IObserverClickButtonGameLevel observer);
    void detach(IObserverClickButtonGameLevel observer);
    void notifyClickButtonGameLevel(GameLevel level);
}
