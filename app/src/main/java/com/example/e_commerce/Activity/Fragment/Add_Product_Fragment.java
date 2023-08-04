package com.example.e_commerce.Activity.Fragment;

import static com.example.e_commerce.Activity.MainActivity.*;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.e_commerce.Activity.Instance_Class;
import com.example.e_commerce.Activity.MainActivity;
import com.example.e_commerce.Activity.Model.AddProduct_Model;
import com.example.e_commerce.R;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Product_Fragment extends Fragment {

    AppCompatEditText pname,pprice,pdes;
    AppCompatImageView pimg;
    AppCompatButton addButton,updateButton;
    String n1,n2,n3,n4;
    Uri resultUri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add__product, container, false);

        pname = view.findViewById(R.id.pname);
        pprice = view.findViewById(R.id.pprice);
        pdes = view.findViewById(R.id.pdes);
        pimg = view.findViewById(R.id.pimg);
        addButton = view.findViewById(R.id.addButton);
        updateButton = view.findViewById(R.id.updateButton);

        pname.setText(getArguments().getString("name"));


        pimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .start(getContext(), Add_Product_Fragment.this);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n1 = preferences.getString("userid",null);
                n2 = pname.getText().toString();
                n3 = pprice.getText().toString();
                n4 = pdes.getText().toString();

                Bitmap bitmap=((BitmapDrawable)pimg.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); // bm is the bitmap object
                byte[] b = baos.toByteArray();

                // Get the Base64 string
                String imgString = Base64.encodeToString(b, Base64.DEFAULT);

                Log.d("UUU", "onClick: imgString="+imgString);

                Instance_Class.CallAPI().addProduct(n1,n2,n3,n4,imgString).enqueue(new Callback<AddProduct_Model>() {
                    @Override
                    public void onResponse(Call<AddProduct_Model> call, Response<AddProduct_Model> response)
                    {
                        if (response.body().getConnection() == 1 && response.body().getProductaddd() == 1)
                        {
                            Log.d("TTT", "Product Added...");
                            Toast.makeText(Add_Product_Fragment.this.getContext(), "Product Added...", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Add_Product_Fragment.this.getContext(),View_Product_Fragment.class);
                            startActivity(intent);
                        }
                        else if (response.body().getProductaddd() == 0)
                        {
                            Log.d("TTT", "Product not added");
                            Toast.makeText(Add_Product_Fragment.this.getContext(), "Product not added", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Log.d("TTT", "Something went wrong.");
                            Toast.makeText(Add_Product_Fragment.this.getContext(), "Something went wrong..", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddProduct_Model> call, Throwable t) {
                        Log.e("TTT", "onFailure: " + t.getLocalizedMessage());
                    }
                });
            }
        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                pimg.setImageURI(resultUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}