package cu.innovat.psigplus.viewholder;

import android.view.View;

import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import cu.innovat.psigplus.R;
/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 6/10/23
 */
public class ViewHolderStatisticsLevel extends RecyclerView.ViewHolder {

    TextView m_titleView;

    public ViewHolderStatisticsLevel(View itemView) {
        super(itemView);
        m_titleView = itemView.findViewById(R.id.text_view_name_level);
//        myTextView = itemView.findViewById(R.id.cmTextView);
//        myImageView = itemView.findViewById(R.id.cmImageView);
    }

    public void setTitle(String name){
        if(m_titleView !=null){
            m_titleView.setText(name);
        }
    }
}
