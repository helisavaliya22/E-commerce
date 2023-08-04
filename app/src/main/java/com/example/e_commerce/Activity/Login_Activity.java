package com.example.e_commerce.Activity;

import static com.example.e_commerce.Activity.MainActivity.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commerce.Activity.Model.LoginData_Model;
import com.example.e_commerce.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Activity extends AppCompatActivity {

    EditText loginEmail,loginPassword;
    Button btnLogin,btnReg;
    String str1,str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnReg = findViewById(R.id.btnReg);

        loginEmail.addTextChangedListener(textWatcher);
        loginPassword.addTextChangedListener(textWatcher);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = preferences.getString("email",null);
                String name1 = preferences.getString("name",null);

                Log.d("AAA", "Email from pref: "+email1);
                Log.d("AAA", "Password from pref: "+name1);
                editor.commit();

                Instance_Class.CallAPI().userLogin(str1,str2).enqueue(new Callback<LoginData_Model>() {
                    @Override
                    public void onResponse(Call<LoginData_Model> call, Response<LoginData_Model> response) {
                        Log.d("AAA", "onResponse: response"+response.body());
                        if (response.body().getConnection() == 1)
                        {
                            if (response.body().getResult() == 1)
                            {
                                Toast.makeText(Login_Activity.this, "User Logged in", Toast.LENGTH_SHORT).show();
                                editor.putBoolean("LoginStatus",true);
                                editor.putString("userid", response.body().getUserdata().getId());
                                editor.putString("name", response.body().getUserdata().getName());
                                editor.putString("email", response.body().getUserdata().getEmail());
                                editor.commit();
                                Intent intent = new Intent(Login_Activity.this, HomePage_Activity.class);
                                startActivity(intent);
                            }
                            else if(response.body().getResult()==2)
                            {
                                Toast.makeText(Login_Activity.this, "User already Logged in", Toast.LENGTH_SHORT).show();
                            }
                            else if(response.body().getResult()==0)
                            {
                                Toast.makeText(Login_Activity.this, "Invalid Email or Password or Register first", Toast.LENGTH_LONG).show();
                                btnReg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Login_Activity.this,Register_Activity.class);
                                        intent.putExtra("email",loginEmail.getText().toString());
                                        intent.putExtra("password",loginPassword.getText().toString());
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(Login_Activity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginData_Model> call, Throwable t) {

                    }
                });
            }
        });

    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            str1 = loginEmail.getText().toString();
            str2 = loginPassword.getText().toString();
            btnLogin.setEnabled(!str1.isEmpty() && !str2.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}