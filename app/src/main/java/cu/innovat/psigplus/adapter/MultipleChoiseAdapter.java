package cu.innovat.psigplus.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cu.innovat.psigplus.cim.questions.Sentence;
import cu.innovat.psigplus.interfaces.IObserverSelectUnSelectChoise;
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

    public MultipleChoiseAdapter(Context context, List<Sentence> sentences) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.sentences = sentences;
    }

    @Override
    public ViewHolderMultipleChoise onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_checkbox_choise,parent,false);
        ViewHolderMultipleChoise viewHolder = new ViewHolderMultipleChoise(view);
        viewHolder.attach(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderMultipleChoise holder, int position) {

        holder.setTextChoise(sentences.get(position).getText());
        holder.setChecked(sentences.get(position).isSelect());
        holder.setIdChoise(sentences.get(position).getId());
    }

    @Override
    public int getItemCount() {
        Log.i("TAG_DB_PSIGAME_PLUS"," Cantidad de opciones:"+String.valueOf(this.sentences.size()));
        return this.sentences.size();
    }

    @Override
    public void selectChoise(String idChoise) {
        for(Sentence s : sentences){
            if(s.getId().compareTo(idChoise) == 0)
                s.setSelect(true);
        }
    }

    @Override
    public void unselectChoise(String idChoise) {
        for(Sentence s : sentences){
            if(s.getId().compareTo(idChoise) == 0)
                s.setSelect(false);
        }
    }

    public List<Sentence> getSentences() {
        return sentences;
    }
}
