package cu.innovat.psigplus.ui.fragment;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import cu.innovat.psigplus.cim.GameLevel;
import cu.innovat.psigplus.interfaces.IClickButtonGameLevel;
import cu.innovat.psigplus.interfaces.IObserverClickButtonGameLevel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 1/10/23
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener, IClickButtonGameLevel {
    protected View m_viewFragment;
    private List<IObserverClickButtonGameLevel> observerCBGLs;

    public BaseFragment(){
        super();
        observerCBGLs =new ArrayList<IObserverClickButtonGameLevel>();
    }

    public void prepareUI(){}

    @Override
    public void onClick(View _view) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void attachObserverCBGL(IObserverClickButtonGameLevel observer) {
        observerCBGLs.add(observer);
    }

    @Override
    public void detachObserverCBGL(IObserverClickButtonGameLevel observer) {
        observerCBGLs.remove(observer);
    }

    @Override
    public void notifyClickButtonGameLevel(GameLevel level) {
        for (IObserverClickButtonGameLevel observer: observerCBGLs) {
            observer.clickedButtonGameLevel(level);
        }

    }
}
