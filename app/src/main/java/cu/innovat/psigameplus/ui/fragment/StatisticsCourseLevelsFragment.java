package cu.innovat.psigameplus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import cu.innovat.psigameplus.R;
import cu.innovat.psigameplus.adapter.AdapterItemLevel;
import cu.innovat.psigameplus.cim.item.ItemLevel;
import cu.innovat.psigameplus.ui.fragment.pageradapter.FragmentPagerAdapter;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 4/10/23
 */
public class StatisticsCourseLevelsFragment extends BaseFragment{

    private ListView listLevel;

    public StatisticsCourseLevelsFragment(){
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_statistics_course_levels,container, false);
        listLevel = (ListView) view.findViewById(R.id.list_level);
        ItemLevel [] levels = {new ItemLevel(),new ItemLevel(),new ItemLevel()};
        AdapterItemLevel adaptador=new AdapterItemLevel(getActivity(),
                levels,R.layout.layout_statistics_level);

        listLevel.setAdapter(adaptador);
        return view;

    }


}
