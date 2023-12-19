package cu.innovat.psigplus.interfaces;

import cu.innovat.psigplus.cim.Player;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 17/12/23
 */
public interface IRegisterPlayer {
    void attachORP(IObserverRegisterPlayer observer);
    void detachORP(IObserverRegisterPlayer observer);
    boolean registerPlayer(Player player);
}
