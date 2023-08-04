package com.example.e_commerce.Activity.Model;

import java.util.List;

public class ViewProduct_Model {
    int connection;
    int result;
    List<Productdata_Model> productdata;


    public int getConnection() {
        return connection;
    }

    public void setConnection(int connection) {
        this.connection = connection;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<Productdata_Model> getProductdata() {
        return productdata;
    }

    public void setProductdata(List<Productdata_Model> productdata) {
        this.productdata = productdata;
    }

    @Override
    public String toString() {
        return "Viewuser_Model{" +
                "connection=" + connection +
                ", result=" + result +
                ", productdata=" + productdata +
                '}';
    }
}
