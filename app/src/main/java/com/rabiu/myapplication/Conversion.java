package com.rabiu.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import com.rabiu.myapplication.databinding.ActivityConversionBinding;
import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Currency;


public class Conversion extends AppCompatActivity implements View.OnClickListener {

    private ActivityConversionBinding binding;
    private double exchangeRate;
    private String name, Cryptocurrency;
    private ArrayList<Currency> currencyArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_conversion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.btnOne.setOnClickListener(this);
        binding.btnTwo.setOnClickListener(this);
        binding.btnThree.setOnClickListener(this);
        binding.btnFour.setOnClickListener(this);
        binding.btnFive.setOnClickListener(this);
        binding.btnSix.setOnClickListener(this);
        binding.btnSeven.setOnClickListener(this);
        binding.btnEight.setOnClickListener(this);
        binding.btnNine.setOnClickListener(this);
        binding.btnZero.setOnClickListener(this);
        binding.btnDecimal.setOnClickListener(this);
        binding.btnBackspace.setOnClickListener(this);
        binding.two.setOnClickListener(this);
        binding.tvAmount.addTextChangedListener(watch);



      getData();

    }


    private void getData(){

        Intent intent = getIntent();
        if(intent != null) {
            name =  intent.getStringExtra("name");
            binding.toView.setText(name);

            Cryptocurrency = intent.getStringExtra("Cryptocurrency");
            binding.fromView.setText(Cryptocurrency);
            exchangeRate = Double.valueOf(intent.getStringExtra("price"));


            currencyArrayList = (ArrayList<Currency>)getIntent().getSerializableExtra("currencyArrayList");

             String uri = "@drawable/_"+name.toLowerCase();
             int id = getResources().getIdentifier(uri, null, getPackageName());
             binding.ivCountryFlag.setImageResource(id);


            String uriLogo = "@drawable/_"+Cryptocurrency.toLowerCase()+"_logo";
            int idLogo = getResources().getIdentifier(uriLogo, null, getPackageName());
            binding.ivCryptoLogo.setImageResource(idLogo);

        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_one:
               clearInitialZero();
                binding.tvAmount.setText(binding.tvAmount.getText() + "1");
                break;
            case R.id.btn_two:
                clearInitialZero();
                binding.tvAmount.setText(binding.tvAmount.getText() + "2");
                break;
            case R.id.btn_three:
                clearInitialZero();
                binding.tvAmount.setText(binding.tvAmount.getText() + "3");
                break;
            case R.id.btn_four:
                clearInitialZero();
                binding.tvAmount.setText(binding.tvAmount.getText() + "4");
                break;
            case R.id.btn_five:
                clearInitialZero();
                binding.tvAmount.setText(binding.tvAmount.getText() + "5");
                break;
            case R.id.btn_six:
                clearInitialZero();
                binding.tvAmount.setText(binding.tvAmount.getText() + "6");
                break;
            case R.id.btn_seven:
                clearInitialZero();
                binding.tvAmount.setText(binding.tvAmount.getText() + "7");
                break;
            case R.id.btn_eight:
                clearInitialZero();
                binding.tvAmount.setText(binding.tvAmount.getText() + "8");
                break;
            case R.id.btn_nine:
                clearInitialZero();
                binding.tvAmount.setText(binding.tvAmount.getText() + "9");
                break;
            case R.id.btn_zero:
                clearInitialZero();
                binding.tvAmount.setText(binding.tvAmount.getText() + "0");
                break;
            case R.id.btn_decimal:
                clearInitialZero();
                binding.tvAmount.setText(binding.tvAmount.getText() + ".");
                if(binding.tvAmount.getText().length() == 1 && binding.tvAmount.getText().toString().equals(".")){
                     binding.tvAmount.setText("0");
                }
                break;
            case R.id.btn_backspace:

                    if (binding.tvAmount.getText().length() > 0 && !binding.tvAmount.getText().toString().equals("0")) {
                        CharSequence currentText = binding.tvAmount.getText();
                        binding.tvAmount.setText(currentText.subSequence(0, currentText.length() - 1));
                    } else {
                        binding.tvAmount.setText("0");
                        binding.tvCalculatedRate.setText("0");
                    }

                break;
            case R.id.two:

                Intent intent = new Intent(this, CurrencyListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("currencyArrayList", currencyArrayList);
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
                break;
        }
    }

    private void clearInitialZero(){
        if(binding.tvAmount.length() == 1
                && binding.tvAmount.getText().toString().equals("0"))
        {
         binding.tvAmount.setText("");
        }
    }

    TextWatcher watch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {

                if (!s.toString().equals("") || !s.toString().equals(".")) {
                    double amount = Double.valueOf(binding.tvAmount.getText().toString());
                    double result = amount * exchangeRate;

                    DecimalFormat dFormat = new DecimalFormat("####,###,###");
                    dFormat.setMaximumFractionDigits(8);
                    binding.tvCalculatedRate.setText(""+ dFormat.format(result));
                }

            } catch (NumberFormatException e){}

        }

        @Override
        public void afterTextChanged(Editable s) {
            try {

                if (s.length() < 1 || s.toString().equals("0") || s.toString().equals(".")) {
                    binding.tvCalculatedRate.setText("0");
                }
            }catch (NumberFormatException e){

            }

        }
    };

    private void computeResult(){

        try {
            double amount = Double.valueOf(binding.tvAmount.getText().toString());
            double result = amount * exchangeRate;

            DecimalFormat dFormat = new DecimalFormat("####,###,###");
            dFormat.setMaximumFractionDigits(8);
            binding.tvCalculatedRate.setText(""+ dFormat.format(result));

        } catch (NumberFormatException e){}

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){

            name = data.getStringExtra(CurrencyListActivity.RESULT_CURRENCY_NAME);
            binding.toView.setText(name);

            String uri = "@drawable/_"+name.toLowerCase();
            int id = getResources().getIdentifier(uri, null, getPackageName());
            binding.ivCountryFlag.setImageResource(id);

            exchangeRate = Double.valueOf(data.getStringExtra(CurrencyListActivity.RESULT_CURRENCY_PRICE));

            computeResult();
        }
    }
}

