package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import cu.innovat.psigplus.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import cu.innovat.psigplus.ui.fragment.pageradapter.FragmentPagerAdapter;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 1/10/23
 */
public class HomeFragment extends BaseFragment {

    private ViewPager m_viewPager;
    private TabLayout m_tabs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

         m_viewFragment = inflater.inflate(R.layout.layout_home,container, false);
         prepareUI();




        return m_viewFragment;

    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new CourseGeneralLevelFragment(), getString(R.string.title_tab_course_general_psychology));
        adapter.addFragment(new CourseMedicalLevelFragment(), getString(R.string.title_tab_course_medical_psychology));
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
}
