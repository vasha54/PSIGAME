package cu.innovat.psigplus.ui.fragment;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import org.jetbrains.annotations.NotNull;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 1/10/23
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    protected View m_viewFragment;

    public BaseFragment(){
        super();
    }

    public void prepareUI(){}

    @Override
    public void onClick(View _view) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


}
