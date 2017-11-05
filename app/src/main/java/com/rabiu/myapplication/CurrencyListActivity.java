package com.rabiu.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import adapter.CustomListAdapter;
import model.Currency;

public class CurrencyListActivity extends ListActivity {

    public static final String RESULT_CURRENCY_NAME = "name";
    public static final String RESULT_CURRENCY_PRICE = "price";
    private ArrayList<Currency> currencyArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateCurrencyList();
        ArrayAdapter<Currency> adapter = new CustomListAdapter(this, currencyArrayList);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Currency c = currencyArrayList.get(position);
                Intent returnIntent = new Intent();
                returnIntent.putExtra(RESULT_CURRENCY_NAME, c.getName());
                returnIntent.putExtra(RESULT_CURRENCY_PRICE, c.getPrice());
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });



    }

    private void populateCurrencyList() {

        currencyArrayList = new ArrayList<Currency>();

        Intent intent = getIntent();
        if(intent != null) {

            currencyArrayList = (ArrayList<Currency>)getIntent().getSerializableExtra("currencyArrayList");
        }
    }

}
