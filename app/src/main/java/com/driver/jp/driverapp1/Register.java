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
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    Button bnReg;
    EditText FName, Password, ConPassword, LName, Email;
    String fName, lName, email, password, conPassword;
    String server_url = "http://192.168.50.4/DriverApp/new_user.php";
    AlertDialog.Builder builder;
    String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FName = (EditText)findViewById(R.id.fName);
        LName = (EditText)findViewById(R.id.lName);
        Email = (EditText)findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);
        ConPassword = (EditText)findViewById(R.id.conPassword);

        bnReg = (Button)findViewById(R.id.bnRegisterSubmit);
        builder = new AlertDialog.Builder(Register.this);

        bnReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fName = FName.getText().toString();
                lName = LName.getText().toString();
                email = Email.getText().toString();
                password = Password.getText().toString();
                conPassword = ConPassword.getText().toString();

                matcher = pattern.matcher(email);

                //At least one field is empty
                if(fName.equals("")||lName.equals("")||email.equals("")||password.equals("")||conPassword.equals("")){
                    builder.setTitle("Error");
                    builder.setMessage("Fill in all Fields.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                //Password does not match
                }else if(!password.equals(conPassword)) {

                    builder.setTitle("Error");
                    builder.setMessage("Passwords do not match.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Password.setText("");
                            ConPassword.setText("");
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                //Email not valid
                }else if(!matcher.matches()){

                    builder.setTitle("Error");
                    builder.setMessage("Email does not match pattern.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Email.setText("");
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                //All Validations True, Process Registration
                }else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    builder.setTitle("Server Response");
                                    builder.setMessage("Response : " + response);
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            FName.setText("");
                                            LName.setText("");
                                            Email.setText("");
                                            Password.setText("");
                                            ConPassword.setText("");
                                        }
                                    });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                }
                            }
                            , new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Register.this, "I'm afraid I can't do that Dave.", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    })
                    {
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

                    MySingleton.getInstance(Register.this).addTorequestque(stringRequest);
                }



            }
        });
    }
}
