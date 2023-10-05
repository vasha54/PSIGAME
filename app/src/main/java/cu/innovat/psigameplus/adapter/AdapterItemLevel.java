package cu.innovat.psigameplus.adapter;

import cu.innovat.psigameplus.cim.item.ItemLevel;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 4/10/23
 */
public class AdapterItemLevel extends ArrayAdapter{

    private int m_layout;
    private Activity m_context;
    private ItemLevel[] m_items;

    public static class ViewHolder {

    }


    public AdapterItemLevel(Activity context, ItemLevel[] _items, int _layout) {
        super(context, _layout, _items);
        this.m_layout = _layout;
        this.m_context = context;
        this.m_items = _items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ViewHolder holder;

        if (item == null) {
            LayoutInflater inflater = m_context.getLayoutInflater();
            item = inflater.inflate(m_layout, null);
        }
        item.setId(position);
        return (item);
    }
}
