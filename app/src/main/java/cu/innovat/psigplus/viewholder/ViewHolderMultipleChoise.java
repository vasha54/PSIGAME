package cu.innovat.psigplus.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import cu.innovat.psigplus.R;
import cu.innovat.psigplus.interfaces.IObserverSelectUnSelectChoise;
import cu.innovat.psigplus.interfaces.ISelectUnSelectChoise;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 10/12/23
 */
public class ViewHolderMultipleChoise extends RecyclerView.ViewHolder implements View.OnClickListener, ISelectUnSelectChoise {

    private CheckBox checkbox;
    private ConstraintLayout layout;
    private String idChoise;

    private List<IObserverSelectUnSelectChoise> observers;

    public ViewHolderMultipleChoise(View itemView) {
        super(itemView);
        checkbox = (CheckBox) itemView.findViewById(R.id.check_box_sentence_choise);
        layout = (ConstraintLayout) itemView.findViewById(R.id.layout_sentence_choise);

        if(layout !=null ) layout.setOnClickListener(this);
        if(checkbox != null) checkbox.setOnClickListener(this);

        observers = new ArrayList<IObserverSelectUnSelectChoise>();
    }

    @Override
    public void onClick(View _view) {
        if(_view != null){
            int ID = _view.getId();
            switch (ID){
                case R.id.check_box_sentence_choise:
                    clickCheckBox();
                    break;
                case R.id.layout_sentence_choise:
                    updateChoise();
            }
        }
    }

    private void clickCheckBox(){
        if(checkbox!=null){
            checkbox.setChecked(!checkbox.isChecked());
            updateChoise();
        }
    }

    private void updateChoise(){
        if(checkbox!=null){
            checkbox.setChecked(!checkbox.isChecked());
            if(layout != null){
                int drawable = ( (checkbox.isChecked() ==true) ? R.drawable.choise_select : R.drawable.choise_unselect);
                layout.setBackground(ContextCompat.getDrawable(itemView.getContext(),drawable));
            }

            if (checkbox.isChecked() == true)  selectChoise(this.idChoise) ; else unselectChoise(this.idChoise) ;
        }

    }

    public void setTextChoise(String str){
        if(checkbox != null){
            checkbox.setText(str);
        }
    }

    public void setChecked(boolean check){
        if(checkbox != null){
            checkbox.setChecked(check);
            if(layout != null){
                int drawable = ( (checkbox.isChecked() == true) ? R.drawable.choise_select: R.drawable.choise_unselect);
                layout.setBackground(ContextCompat.getDrawable(itemView.getContext(), drawable));
            }
        }
    }

    @Override
    public void attach(IObserverSelectUnSelectChoise observer) {
        observers.add(observer);
    }

    @Override
    public void detach(IObserverSelectUnSelectChoise observer) {
        observers.remove(observer);
    }

    @Override
    public void selectChoise(String idChoise) {
        for(IObserverSelectUnSelectChoise obv : observers)
            obv.selectChoise(idChoise);
    }

    @Override
    public void unselectChoise(String idChoise) {
        for(IObserverSelectUnSelectChoise obv : observers)
            obv.unselectChoise(idChoise);
    }

    public void setIdChoise(String idChoise) {
        this.idChoise = idChoise;
    }
}
