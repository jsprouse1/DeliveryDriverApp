package com.driver.jp.driverapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class OrderEntry extends AppCompatActivity {

    RadioGroup radioPaymentType;
    RadioButton radioCredit, radioCash, radioPayPal, radioSplit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_entry);



    }
}
