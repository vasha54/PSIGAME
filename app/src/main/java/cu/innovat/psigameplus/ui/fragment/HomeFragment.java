package cu.innovat.psigameplus.ui.fragment;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import cu.innovat.psigameplus.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.AppBarLayout;
import androidx.viewpager.widget.ViewPager;
import cu.innovat.psigameplus.ui.fragment.pageradapter.AdapterHome;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 1/10/23
 */
public class HomeFragment extends BaseFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_home,container, false);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
//        // Set Tabs inside Toolbar
       TabLayout tabs = (TabLayout) view.findViewById(R.id.result_tabs);
       tabs.setupWithViewPager(viewPager);


        return view;

    }

    private void setupViewPager(ViewPager viewPager) {
        AdapterHome adapter = new AdapterHome(getChildFragmentManager());
        adapter.addFragment(new Fragment(), getString(R.string.title_tab_course_general_psychology));
        adapter.addFragment(new Fragment(), getString(R.string.title_tab_course_medical_psychology));
        viewPager.setAdapter(adapter);
    }
}
