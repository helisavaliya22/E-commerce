package com.example.e_commerce.Activity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Instance_Class
{
    public static Retro_Interface CallAPI()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://helisavaliya.000webhostapp.com/helisavaliya/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Retro_Interface.class);
    }
}
