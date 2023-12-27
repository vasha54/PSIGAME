package cu.innovat.psigplus.viewholder;

import android.view.View;

import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import cu.innovat.psigplus.R;
import cu.innovat.psigplus.util.Util;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 6/10/23
 */
public class ViewHolderStatisticsLevel extends RecyclerView.ViewHolder {

    TextView m_titleView;
    TextView m_tvCountGame;
    TextView m_tvFirstGame;
    TextView m_tvLastGame;

    public ViewHolderStatisticsLevel(View itemView) {
        super(itemView);
        m_titleView = itemView.findViewById(R.id.text_view_name_level);
        m_tvCountGame = itemView.findViewById(R.id.v_count_games);
        m_tvFirstGame = itemView.findViewById(R.id.v_first_games);
        m_tvLastGame = itemView.findViewById(R.id.v_last_game);
//        myTextView = itemView.findViewById(R.id.cmTextView);
//        myImageView = itemView.findViewById(R.id.cmImageView);
    }

    public void setTitle(String name){
        if(m_titleView !=null){
            m_titleView.setText(name);
        }
    }

    public void setCountGame(int countGame){
        if(m_tvCountGame != null){
            m_tvCountGame.setText(Util.convertToStringFillZeros(countGame,10));
        }
    }

    public void setFirstGame(long timestampFirstGame){
        if(m_tvFirstGame != null){
            m_tvFirstGame.setText(Util.formatTimeStamp(timestampFirstGame));
        }
    }

    public void setLastGame(long timestampLastGame){
        if(m_tvLastGame != null){
            m_tvLastGame.setText(Util.formatTimeStamp(timestampLastGame));
        }
    }
}
