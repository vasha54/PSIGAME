package cu.innovat.psigplus.interfaces;

public interface IResetRegisterPlayer {
    void attach(IObserverResetRegisterPlayer observer);
    void detach(IObserverResetRegisterPlayer observer);
    void notifyResetRegisterPlayer();
}
