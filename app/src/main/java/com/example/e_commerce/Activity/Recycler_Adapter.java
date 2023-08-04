package com.example.e_commerce.Activity;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.e_commerce.Activity.Fragment.DataTransfer_Interface;
import com.example.e_commerce.Activity.Fragment.View_Product_Fragment;
import com.example.e_commerce.Activity.Model.Deletedata_Model;
import com.example.e_commerce.Activity.Model.Productdata_Model;
import com.example.e_commerce.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.ViewHolder> {

    View_Product_Fragment view_product_fragment;
    ArrayList<Productdata_Model> productdata;
    DataTransfer_Interface dataTransfer_interface;
    int id;
    public Recycler_Adapter(View_Product_Fragment view_product_fragment, ArrayList<Productdata_Model> productdata, DataTransfer_Interface dataTransfer_interface)
    {
        this.view_product_fragment = view_product_fragment;
        this.productdata = productdata;
        this.dataTransfer_interface=dataTransfer_interface;
    }

    @NonNull
    @Override
    public Recycler_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(view_product_fragment.getContext()).inflate(R.layout.recycler_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.NAME.setText(""+productdata.get(position).getPNAME());
        holder.DES.setText(""+productdata.get(position).getPDES());
        holder.PRICE.setText(""+productdata.get(position).getPPRICE());

        String img = "https://helisavaliya.000webhostapp.com/helisavaliya/"+productdata.get(holder.getAdapterPosition()).getPIMG();

        Glide.with(view_product_fragment.getContext()).load(img)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.IMAGE);

        holder.OPTIONBUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(view_product_fragment.getContext(),holder.OPTIONBUTTON);
                popupMenu.getMenuInflater().inflate(R.menu.edit_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.deleteProduct)
                        {
                            Instance_Class.CallAPI().deleteProduct(productdata.get(holder.getAdapterPosition()).getID()).enqueue(new Callback<Deletedata_Model>() {
                                @Override
                                public void onResponse(Call<Deletedata_Model> call, Response<Deletedata_Model> response) {
                                    Log.d("TAG===", "onResponse: " + productdata.get(holder.getAdapterPosition()).getID());
                                    Log.d("delete", "onResponse: " + response.body().getResult());
                                    if (response.body().getConnection() == 1 && response.body().getResult() == 1)
                                    {
                                        Toast.makeText(view_product_fragment.getContext(), "Product-" + (position + 1) + " no more available..", Toast.LENGTH_LONG).show();
                                        productdata.remove(position);
                                        notifyDataSetChanged();
                                        if (productdata.isEmpty()) {
                                            Toast.makeText(view_product_fragment.getContext(), "No more products available..", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else if (response.body().getResult() == 0)
                                    {
                                        Toast.makeText(view_product_fragment.getContext(), "No more products available..", Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(view_product_fragment.getContext(), "Something went wrong..", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Deletedata_Model> call, Throwable t) {
                                    Log.e("delete", "onResponse: " + t.getLocalizedMessage());
                                    Toast.makeText(view_product_fragment.getContext(), "Something went wrong..", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        if (item.getItemId() == R.id.updateProduct)
                        {
//                            id = productdata.get(holder.getAdapterPosition()).getID();
//                            String name = productdata.get(holder.getAdapterPosition()).getPNAME();
//                            String price = productdata.get(holder.getAdapterPosition()).getPPRICE();
//                            String des = productdata.get(holder.getAdapterPosition()).getPDES();
                            dataTransfer_interface.
                                    transferDataToFragment(
                                            productdata.get(holder.getAdapterPosition()).getID(),
                                            productdata.get(holder.getAdapterPosition()).getPNAME(),
                                            productdata.get(holder.getAdapterPosition()).getPDES(),
                                            productdata.get(holder.getAdapterPosition()).getPPRICE(),
                                            productdata.get(holder.getAdapterPosition()).getPIMG());

                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView NAME,DES,PRICE;
        ImageView IMAGE,OPTIONBUTTON;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NAME = itemView.findViewById(R.id.NAME);
            DES = itemView.findViewById(R.id.DES);
            PRICE = itemView.findViewById(R.id.PRICE);
            IMAGE = itemView.findViewById(R.id.IMAGE);
            OPTIONBUTTON = itemView.findViewById(R.id.OPTIONBUTTON);
        }
    }
}
