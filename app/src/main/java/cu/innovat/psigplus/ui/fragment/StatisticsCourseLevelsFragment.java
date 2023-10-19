package cu.innovat.psigplus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import cu.innovat.psigplus.R;
import cu.innovat.psigplus.adapter.RecyclerViewAdapterStatisticsLevel;
import cu.innovat.psigplus.cim.Statistics;
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

        m_viewFragment = inflater.inflate(R.layout.layout_statistics_course_levels,container, false);
        List<Statistics> levels = new ArrayList<Statistics>();


        if( m_modeGame == GameMode.GENERAL){
            levels.add(new Statistics(m_modeGame,GameLevel.ROOKIE_GENERAL,getString(R.string.title_level_rookie)));
            levels.add(new Statistics(m_modeGame,GameLevel.COMPETENT_GENERAL,getString(R.string.title_level_competent)));
            levels.add(new Statistics(m_modeGame,GameLevel.PROFESSIONAL_GENERAL,getString(R.string.title_level_professional)));
        }else{
            levels.add(new Statistics(m_modeGame,GameLevel.ROOKIE_MEDICAL,getString(R.string.title_level_rookie)));
            levels.add(new Statistics(m_modeGame,GameLevel.COMPETENT_MEDICAL,getString(R.string.title_level_competent)));
            levels.add(new Statistics(m_modeGame,GameLevel.PROFESSIONAL_MEDICAL,getString(R.string.title_level_professional)));
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView recyclerView = m_viewFragment.findViewById(R.id.list_level);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapterStatisticsLevel(getContext(), levels);
        recyclerView.setAdapter(adapter);

        return m_viewFragment;

    }


}
