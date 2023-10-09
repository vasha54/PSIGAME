package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cu.innovat.psigplus.R;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 3/10/23
 */
public class CourseMedicalLevelFragment extends BaseFragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_course_medical_level,container, false);
        return view;

    }
}
