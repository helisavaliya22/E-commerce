package com.example.e_commerce.Activity.Fragment;

import static com.example.e_commerce.Activity.MainActivity.*;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.e_commerce.Activity.Instance_Class;
import com.example.e_commerce.Activity.MainActivity;
import com.example.e_commerce.Activity.Model.Productdata_Model;
import com.example.e_commerce.Activity.Model.ViewProduct_Model;
import com.example.e_commerce.Activity.Recycler_Adapter;
import com.example.e_commerce.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class View_Product_Fragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Productdata_Model> productdata = new ArrayList<>();
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view__product, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        id = preferences.getString("userid",null);
        Instance_Class.CallAPI().viewProduct(id).enqueue(new Callback<ViewProduct_Model>() {
            @Override
            public void onResponse(Call<ViewProduct_Model> call, Response<ViewProduct_Model> response) {
                Log.e("aaa", "onResponse: " + response.body());
                if (response.body().getConnection() == 1 && response.body().getResult() == 1)
                {
                    productdata.addAll(response.body().getProductdata());
                    Log.d("UUU", "onResponse: "+productdata.get(0).getPNAME());
                    Recycler_Adapter adapter = new Recycler_Adapter(View_Product_Fragment.this, productdata, new DataTransfer_Interface() {
                        @Override
                        public void transferDataToFragment(int id, String pName, String pDes, String pPrice, String pImg) {
                            Log.d("uuu", "transferDataToFragment: Name="+pName);
                            Bundle bundle=new Bundle();
                            bundle.putInt("id",id);
                            bundle.putString("name",pName);
                            bundle.putString("des",pDes);
                            bundle.putString("price",pPrice);
                            bundle.putString("img",pImg);
                            Add_Product_Fragment fragment=new Add_Product_Fragment();
                            fragment.setArguments(bundle);
                            FragmentManager manager=getActivity().getSupportFragmentManager();
                            FragmentTransaction transaction=manager.beginTransaction();
                            transaction.replace(R.id.content_view,fragment);
                            transaction.commit();

                        }
                    });
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(View_Product_Fragment.this.getContext(), "Data Found...", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Log.d("TTT", "onResponse: No Data Found...");
                    Toast.makeText(View_Product_Fragment.this.getContext(), "No Data Found...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ViewProduct_Model> call, Throwable t) {
                Log.e("TTT", "onFailure: " + t.getLocalizedMessage());
            }
        });
        return view;
    }
}