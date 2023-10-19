package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import cu.innovat.psigplus.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import cu.innovat.psigplus.cim.GameLevel;
import cu.innovat.psigplus.interfaces.IClickButtonGameLevel;
import cu.innovat.psigplus.interfaces.IObserverClickButtonGameLevel;
import cu.innovat.psigplus.ui.activity.MainActivity;
import cu.innovat.psigplus.ui.fragment.pageradapter.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 1/10/23
 */
public class HomeFragment extends BaseFragment implements IObserverClickButtonGameLevel {

    private ViewPager m_viewPager;
    private TabLayout m_tabs;

    private CourseGeneralLevelFragment m_fCGeneralLevelF;
    private CourseMedicalLevelFragment m_fCMedicalLevelF;

    public HomeFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
         m_viewFragment = inflater.inflate(R.layout.layout_home,container, false);
         prepareUI();
        FragmentActivity activity = getActivity();
         if( activity instanceof MainActivity){
             MainActivity main = (MainActivity)  activity;
             if(main !=null){
                 this.attachObserverCBGL(main);
             }
         }
         return m_viewFragment;
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager());
        m_fCGeneralLevelF = new CourseGeneralLevelFragment();
        m_fCMedicalLevelF = new CourseMedicalLevelFragment();

        m_fCMedicalLevelF.attachObserverCBGL(this);
        m_fCGeneralLevelF.attachObserverCBGL(this);

        adapter.addFragment(m_fCGeneralLevelF, getString(R.string.title_tab_course_general_psychology));
        adapter.addFragment(m_fCMedicalLevelF, getString(R.string.title_tab_course_medical_psychology));

        viewPager.setAdapter(adapter);
    }

    @Override
    public void prepareUI() {
        super.prepareUI();
        m_viewPager = (ViewPager) m_viewFragment.findViewById(R.id.viewpager);
        m_tabs = (TabLayout) m_viewFragment.findViewById(R.id.result_tabs);
        setupViewPager(m_viewPager);
        m_tabs.setupWithViewPager(m_viewPager);
    }



    @Override
    public void clickedButtonGameLevel(GameLevel level) {
        this.notifyClickButtonGameLevel(level);
    }
}
