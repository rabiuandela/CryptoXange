package com.rabiu.myapplication;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import adapter.DataAdapter;
import model.Currency;
import okhttp3.OkHttpClient;
import utils.Util;

import static com.rabiu.myapplication.Constants.BASE_URL;
import static com.rabiu.myapplication.Constants.ENDPOINT;


/**
 * A simple {@link Fragment} subclass.
 */
public class ETH extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Currency> currencyList;
    SwipeRefreshLayout swipeRefreshLayout;
    private DataAdapter adapter;
    private String Cryptocurrency = "ETH";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eth, container, false);

        recyclerView = view.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);

                if(utils.ConnectionDetector.isConnectingToInternet(getActivity())) {
                    getETHPricing();
                } else {
                    utils.Util.showToastL(getActivity(), getString(R.string.NoInternet));
                }
            }
        });
        return  view;
    }


  @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Make sure that we are currently visible
        if (this.isVisible()) {

            if (isVisibleToUser) {
                if(utils.ConnectionDetector.isConnectingToInternet(getActivity())) {
                    getETHPricing();
                } else {
                    utils.Util.showToastL(getActivity(), getString(R.string.NoInternet));
                }
            }
        }
    }


    private void getETHPricing() {

        Util.showLoadingDialog(getActivity(),"please wait..");
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();
        AndroidNetworking.post(BASE_URL)
                .addQueryParameter("fsym","ETH")
                .addQueryParameter("tsyms",ENDPOINT)
                .setPriority(Priority.IMMEDIATE)
                .setOkHttpClient(okHttpClient)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {

                    Util.hideLoadingDialog();

                        JSONObject parse = JSON.parseObject(response);

                        currencyList = new ArrayList<Currency>();
                        for (Map.Entry<String, Object> entry : parse.entrySet()) {
                            Currency currency = new Currency();
                            currency.setName(entry.getKey());
                            currency.setPrice(entry.getValue().toString());
                            currencyList.add(currency);
                        }
                        adapter = new DataAdapter(getActivity(),currencyList ,Cryptocurrency);
                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onError(ANError anError) {

                        Util.hideLoadingDialog();
                    }
                });
    }

}
