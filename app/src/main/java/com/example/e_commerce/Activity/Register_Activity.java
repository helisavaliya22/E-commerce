package com.example.e_commerce.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.e_commerce.Activity.Model.RegistrationData_Model;
import com.example.e_commerce.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_Activity extends AppCompatActivity {

    EditText name,regEmail,regPasswod;
    Button btnRegister;
    String str1,str2,str3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        regEmail = findViewById(R.id.regEmail);
        regPasswod = findViewById(R.id.regPassword);
        btnRegister = findViewById(R.id.btnRegister);

        str2 = getIntent().getStringExtra("email");
        str3 = getIntent().getStringExtra("password");

        regEmail.setText(""+str2);
        regPasswod.setText(""+str3);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = name.getText().toString();

                Instance_Class.CallAPI().userRegister(str1,str2, str3).enqueue(new Callback<RegistrationData_Model>() {
                    @Override
                    public void onResponse(Call<RegistrationData_Model> call, Response<RegistrationData_Model> response) {
                        Log.d("AAA", "onResponse: " + response.body());
                        if (response.body().getConnection() == 1)
                        {
                            if (response.body().getResult() == 1)
                            {
                                Toast.makeText(Register_Activity.this, "User Registered", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else if (response.body().getResult() == 2)
                            {
                                Toast.makeText(Register_Activity.this, "User already exists", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Register_Activity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegistrationData_Model> call, Throwable t) {
                        Log.e("AAA", "onFailure: "+t.getLocalizedMessage());
                    }
                });
            }
        });
    }
}