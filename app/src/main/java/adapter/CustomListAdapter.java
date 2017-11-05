package adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rabiu.myapplication.R;

import java.util.ArrayList;

import model.Currency;

/**
 * Created by rabiu on 02/11/2017.
 */

public class CustomListAdapter extends ArrayAdapter<Currency> {

    private ArrayList<Currency> currencyArrayList;
    Context context;


    public CustomListAdapter(Context context, ArrayList<Currency> currencyArrayList) {
        super(context,R.layout.spinner_item, currencyArrayList);
        this.context = context;
        this.currencyArrayList = currencyArrayList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.spinner_item,null);

        TextView tvName =  convertView.findViewById(R.id.tv_name);
        TextView tvPrice = convertView.findViewById(R.id.tv_price);
        ImageView ivFlag = convertView.findViewById(R.id.iv_country_flag);

        tvName.setText(currencyArrayList.get(position).getName());
        tvPrice.setText(currencyArrayList.get(position).getPrice());
        String uri = "@drawable/_"+currencyArrayList.get(position).getName().toLowerCase();
        int id = context.getResources().getIdentifier(uri, null, context.getPackageName());
        ivFlag.setImageResource(id);


        return convertView;
    }
}
