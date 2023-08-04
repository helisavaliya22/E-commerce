package com.example.e_commerce.Activity;

import com.example.e_commerce.Activity.Model.AddProduct_Model;
import com.example.e_commerce.Activity.Model.Deletedata_Model;
import com.example.e_commerce.Activity.Model.LoginData_Model;
import com.example.e_commerce.Activity.Model.RegistrationData_Model;
import com.example.e_commerce.Activity.Model.UpdateData_Model;
import com.example.e_commerce.Activity.Model.ViewProduct_Model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Retro_Interface {

    @FormUrlEncoded
    @POST("Register.php")
    Call<RegistrationData_Model> userRegister(@Field("name") String name,
                                              @Field("email") String email,
                                              @Field("password") String password);

    @FormUrlEncoded
    @POST("Login.php")
    Call<LoginData_Model> userLogin(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("AddProduct.php")
    Call<AddProduct_Model> addProduct(@Field("userid") String userid,
                                      @Field("pro_name") String pname,
                                      @Field("pro_price") String pprice,
                                      @Field("pro_des") String pdes,
                                      @Field("pro_image") String pimg);

    @FormUrlEncoded
    @POST("ViewProduct.php")
    Call<ViewProduct_Model> viewProduct(@Field("userid") String userid);

    @FormUrlEncoded
    @POST("DeleteProduct.php")
    Call<Deletedata_Model> deleteProduct(@Field("id") int id);

    @FormUrlEncoded
    @POST("UpdateProduct.php")
    Call<UpdateData_Model> updateProduct(@Field("id") int id,
                                         @Field("name") String name,
                                         @Field("price") String price,
                                         @Field("description") String des,
                                         @Field("imagedata") String imagedata,
                                         @Field("imagename") String imagename);
}
