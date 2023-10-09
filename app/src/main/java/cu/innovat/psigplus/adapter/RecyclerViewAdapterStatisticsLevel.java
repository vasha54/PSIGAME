package cu.innovat.psigplus.adapter;

import cu.innovat.psigplus.cim.item.ItemLevel;
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

    private List<ItemLevel> myList;
    private LayoutInflater layoutInflater;
    public RecyclerViewAdapterStatisticsLevel(Context context, List<ItemLevel> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.myList = data;
    }
    @Override
    public ViewHolderStatisticsLevel onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_statistics_level, parent, false);
        return new ViewHolderStatisticsLevel(view);
    }
    @Override
    public void onBindViewHolder(ViewHolderStatisticsLevel holder, int position) {
        ItemLevel item = myList.get(position);
//        holder.myImageView.setImageResource(item.getImageId());
//        holder.myTextView.setText(item.getName());
    }
    @Override
    public int getItemCount() {
        return myList.size();
    }
}
