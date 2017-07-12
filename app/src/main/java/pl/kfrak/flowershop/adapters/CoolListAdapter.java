package pl.kfrak.flowershop.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import pl.kfrak.flowershop.R;
import pl.kfrak.flowershop.models.Flower;

/**
 * Created by RENT on 2017-07-12.
 */

public class CoolListAdapter extends ArrayAdapter<Flower> {

    private final List<Flower> flowerList;
    private final LayoutInflater inflater;

    public CoolListAdapter(Context context, List<Flower> flowerList) {
        super(context, R.layout.flower_item);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.flowerList = flowerList;
    }


    @Override
    public int getCount() {
        return flowerList.size();
    }

    @Override
    public boolean isEmpty() {
        return flowerList.isEmpty();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Flower flower = flowerList.get(position);
        View rowView = convertView;
        if(rowView == null)
            rowView = inflater.inflate(R.layout.flower_item, parent, false);
        TextView textView1 = (TextView) rowView.findViewById(R.id.flowerItem_nameText);
        TextView textView2 = (TextView) rowView.findViewById(R.id.flowerItem_instructionsText);
        textView1.setText(flower.getName());
        textView2.setText(flower.getInstructions());
        return rowView;
    }

}
