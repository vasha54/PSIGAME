package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.adapter.RecyclerViewAdapterStatisticsLevel;
import cu.innovat.psigplus.cim.item.ItemLevel;
import cu.innovat.psigplus.cim.GameLevel;
import cu.innovat.psigplus.cim.GameMode;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.*;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 4/10/23
 */
public class StatisticsCourseLevelsFragment extends BaseFragment{

    private RecyclerViewAdapterStatisticsLevel adapter;
    private GameMode m_modeGame;

    public StatisticsCourseLevelsFragment(GameMode mode){
        super();
        this.m_modeGame = mode;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_statistics_course_levels,container, false);
        List<ItemLevel> levels = new ArrayList<ItemLevel>();


        levels.add(new ItemLevel());
        levels.add(new ItemLevel());
        levels.add(new ItemLevel());


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.list_level);
        recyclerView.setLayoutManager(layoutManager);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);

        adapter = new RecyclerViewAdapterStatisticsLevel(getContext(), levels);
        recyclerView.setAdapter(adapter);

        return view;

    }


}
