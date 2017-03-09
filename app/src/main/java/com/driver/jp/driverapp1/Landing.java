package com.driver.jp.driverapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.attr.button;

public class Landing extends AppCompatActivity {

    Button bnNewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


        //Enter New Order Page
        bnNewOrder = (Button)findViewById(R.id.bnNewOrder);
        bnNewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Landing.this, OrderEntry.class));
            }
        });
    }
}
