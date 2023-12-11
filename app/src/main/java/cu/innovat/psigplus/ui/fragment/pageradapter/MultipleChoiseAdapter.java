package cu.innovat.psigplus.ui.fragment.pageradapter;

import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cu.innovat.psigplus.cim.questions.Sentence;
import cu.innovat.psigplus.viewholder.ViewHolderMultipleChoise;
import org.jetbrains.annotations.NotNull;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 10/12/23
 */
public class MultipleChoiseAdapter extends RecyclerView.Adapter<ViewHolderMultipleChoise> {
    private Context context;
    private List<Sentence> sentences;

    public MultipleChoiseAdapter(Context context, List<Sentence> sentences) {
        super(context);
        this.context = context;
        this.sentences = sentences;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolderMultipleChoise onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
