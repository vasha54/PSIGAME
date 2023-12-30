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
    TextView m_tvBestScore;
    TextView m_tvWorstScore;
    TextView m_tvMeanScore;
    TextView m_tvGameBeforeWin;
    TextView m_tvFirstWinGame;

    public ViewHolderStatisticsLevel(View itemView) {
        super(itemView);
        m_titleView = itemView.findViewById(R.id.text_view_name_level);
        m_tvCountGame = itemView.findViewById(R.id.v_count_games);
        m_tvFirstGame = itemView.findViewById(R.id.v_first_games);
        m_tvLastGame = itemView.findViewById(R.id.v_last_game);
        m_tvBestScore = itemView.findViewById(R.id.v_best_game);
        m_tvWorstScore = itemView.findViewById(R.id.v_worst_score);
        m_tvMeanScore = itemView.findViewById(R.id.v_mean_score);
        m_tvGameBeforeWin = itemView.findViewById(R.id.v_games_before_accept);
        m_tvFirstWinGame = itemView.findViewById(R.id.v_date_first_accept);
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
            m_tvCountGame.setText(Util.convertToStringFillSpace(countGame,10));
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

    public void setBestScore(double bestScore){
        if(m_tvBestScore != null){
            m_tvBestScore.setText(Util.convertToStringFillSpace(bestScore,10,2));
        }
    }

    public void setWorstScore(double worstScore){
        if(m_tvWorstScore != null){
            m_tvWorstScore.setText(Util.convertToStringFillSpace(worstScore,10,2));
        }
    }

    public void setMeanScore(double meanScore){
        if(m_tvMeanScore != null){
            m_tvMeanScore.setText(Util.convertToStringFillSpace(meanScore,10,2));
        }
    }

    public void setGameBeforeWin(int gameBeforeWin){
        if(m_tvGameBeforeWin != null){
            m_tvGameBeforeWin.setText(Util.convertToStringFillSpace(gameBeforeWin,10));
        }
    }

    public void setFirstWinGame(long timestampFirstWinGame){
        if(m_tvFirstWinGame != null){
            m_tvFirstWinGame.setText(Util.formatTimeStamp(timestampFirstWinGame));
        }
    }
}
