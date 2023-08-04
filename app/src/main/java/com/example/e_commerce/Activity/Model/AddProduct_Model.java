package com.example.e_commerce.Activity.Model;

public class AddProduct_Model {
    int connection;
    int productaddd;

    public int getConnection() {
        return connection;
    }

    public void setConnection(int connection) {
        this.connection = connection;
    }

    public int getProductaddd() {
        return productaddd;
    }

    public void setProductaddd(int productaddd) {
        this.productaddd = productaddd;
    }

    @Override
    public String toString() {
        return "AddProduct_Model{" +
                "connection=" + connection +
                ", productaddd=" + productaddd +
                '}';
    }
}
