package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.ui.fragment.pageradapter.FragmentPagerAdapter;
import cu.innovat.psigplus.cim.GameMode;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 4/10/23
 */
public class StatisticsFragment extends BaseFragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_statistics,container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.statistics_course_viewpager);
        setupViewPager(viewPager);
        TabLayout tabs = (TabLayout) view.findViewById(R.id.statistics_course_tabs);
        tabs.setupWithViewPager(viewPager);
        return view;

    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new StatisticsCourseLevelsFragment(GameMode.GENERAL), getString(R.string.title_tab_course_general_psychology));
        adapter.addFragment(new StatisticsCourseLevelsFragment(GameMode.MEDICAL), getString(R.string.title_tab_course_medical_psychology));
        viewPager.setAdapter(adapter);
    }
}
