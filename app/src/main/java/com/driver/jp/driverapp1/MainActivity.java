package com.driver.jp.driverapp1;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText FName, Password, LName, Email;
    String server_url = "http://192.168.50.4/DriverApp/new_user.php";
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.bRegisterSubmit);
        FName = (EditText)findViewById(R.id.fName);
        LName = (EditText)findViewById(R.id.lName);
        Email = (EditText)findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);
        builder = new AlertDialog.Builder(MainActivity.this);
        button.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               final String fName, lName, email, password;
                fName = FName.getText().toString();
                lName = LName.getText().toString();
                email = Email.getText().toString();
                password = Password.getText().toString();
               StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                       new Response.Listener<String>(){
                           @Override
                           public void onResponse(String response){
                            builder.setTitle("Server Response");
                               builder.setMessage("Response : "+response);
                               builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                                   @Override
                                   public void onClick(DialogInterface dialog, int which){
                                       FName.setText("");
                                       LName.setText("");
                                       Email.setText("");
                                       Password.setText("");
                                   }
                               });
                               AlertDialog alertDialog = builder.create();
                               alertDialog.show();
                           }
                       }
               , new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error){
                       Toast.makeText(MainActivity.this, "I'm afraid I can't do that Dave.", Toast.LENGTH_SHORT).show();
                       error.printStackTrace();
                   }
               }){
                   @Override
                   protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("fName", fName);
                        params.put("lName", lName);
                        params.put("email", email);
                        params.put("password", password);
                        return params;
                   }
               };

               MySingleton.getInstance(MainActivity.this).addTorequestque(stringRequest);


           }
        });
    }
}
