package cu.innovat.psigplus.adapter;

import cu.innovat.psigplus.cim.Statistics;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cu.innovat.psigplus.R;

import androidx.recyclerview.widget.RecyclerView;
import cu.innovat.psigplus.viewholder.ViewHolderStatisticsLevel;
import java.util.*;
/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 6/10/23
 */
public class RecyclerViewAdapterStatisticsLevel extends RecyclerView.Adapter<ViewHolderStatisticsLevel> {


    private List<Statistics> myList;
    private LayoutInflater layoutInflater;

    /**
     *
     * @param context
     * @param data
     */
    public RecyclerViewAdapterStatisticsLevel(Context context, List<Statistics> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.myList = data;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolderStatisticsLevel onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_statistics_level, parent, false);
        return new ViewHolderStatisticsLevel(view);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolderStatisticsLevel holder, int position) {
        Statistics item = myList.get(position);
        holder.setTitle(item.getTitleStatistics());
        if(item.getCountGames() > 0) holder.setCountGame(item.getCountGames());
        if(item.getTimeStampFGame() != Long.MIN_VALUE) holder.setFirstGame(item.getTimeStampFGame());
        if(item.getTimeStampLGame() != Long.MIN_VALUE) holder.setLastGame(item.getTimeStampLGame());
        if(item.getTimeStampFWGame() != Long.MIN_VALUE) holder.setFirstWinGame(item.getTimeStampFWGame());
        if(item.getBestScore() != Double.MIN_VALUE) holder.setBestScore(item.getBestScore());
        if(item.getWorstScore() != Double.MIN_VALUE) holder.setWorstScore(item.getWorstScore());
        if(item.getMeanScore() != Double.MIN_VALUE) holder.setMeanScore(item.getMeanScore());
        if(item.getCountGamesBeforeWin() != Integer.MIN_VALUE) holder.setGameBeforeWin(item.getCountGamesBeforeWin());
    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return myList.size();
    }
}
