package cu.innovat.psigplus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cu.innovat.psigplus.cim.questions.Sentence;
import cu.innovat.psigplus.interfaces.IObserverSelectUnSelectChoise;
import cu.innovat.psigplus.util.LOG;
import cu.innovat.psigplus.viewholder.ViewHolderMultipleChoise;
import cu.innovat.psigplus.R;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 10/12/23
 */
public class MultipleChoiseAdapter extends RecyclerView.Adapter<ViewHolderMultipleChoise>  implements IObserverSelectUnSelectChoise {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Sentence> sentences;

    /**
     *
     * @param context
     * @param sentences
     */
    public MultipleChoiseAdapter(Context context, List<Sentence> sentences) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.sentences = sentences;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolderMultipleChoise onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_checkbox_choise,parent,false);
        ViewHolderMultipleChoise viewHolder = new ViewHolderMultipleChoise(view);
        viewHolder.attach(this);
        return viewHolder;
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolderMultipleChoise holder, int position) {

        holder.setTextChoise(sentences.get(position).getText());
        holder.setChecked(sentences.get(position).isSelect());
        holder.setIdChoise(sentences.get(position).getId());
    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        LOG.i("TAG_DB_PSIGAME_PLUS"," Cantidad de opciones:"+String.valueOf(this.sentences.size()));
        return this.sentences.size();
    }

    /**
     *
     * @param idChoise
     */
    @Override
    public void selectChoise(String idChoise) {
        for(Sentence s : sentences){
            if(s.getId().compareTo(idChoise) == 0)
                s.setSelect(true);
        }
    }

    /**
     *
     * @param idChoise
     */
    @Override
    public void unselectChoise(String idChoise) {
        for(Sentence s : sentences){
            if(s.getId().compareTo(idChoise) == 0)
                s.setSelect(false);
        }
    }

    /**
     *
     * @return
     */
    public List<Sentence> getSentences() {
        return sentences;
    }
}
