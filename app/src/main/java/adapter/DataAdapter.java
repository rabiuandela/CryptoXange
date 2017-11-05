package adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.rabiu.myapplication.Conversion;
import com.rabiu.myapplication.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Currency;

/**
 * Created by rabiu on 24/10/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Currency> currencyArrayList;
    Activity context;
    DecimalFormat dFormat;
    String Cryptocurrency;

    public DataAdapter(Activity context, ArrayList<Currency> currencyArrayList , String Cryptocurrency) {
        this.currencyArrayList = currencyArrayList;
        this.context = context;
        this.Cryptocurrency = Cryptocurrency;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.tv_name.setText(currencyArrayList.get(i).getName());

        dFormat = new DecimalFormat("####,###,###.00");
        viewHolder.tv_price.setText(dFormat.format(Double.valueOf(currencyArrayList.get(i).getPrice())));

        String uri = "@drawable/_"+currencyArrayList.get(i).getName().toLowerCase();
        int id = context.getResources().getIdentifier(uri, null, context.getPackageName());
        viewHolder.iv_country_flag.setImageResource(id);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Conversion.class);
                intent.putExtra("name",currencyArrayList.get(i).getName());
                intent.putExtra("price",currencyArrayList.get(i).getPrice());
                intent.putExtra("Cryptocurrency",Cryptocurrency);
                Bundle bundle = new Bundle();
                bundle.putSerializable("currencyArrayList", currencyArrayList);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return currencyArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private ImageView iv_country_flag;

        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            iv_country_flag  = view.findViewById(R.id.iv_country_flag);

        }
    }
}
