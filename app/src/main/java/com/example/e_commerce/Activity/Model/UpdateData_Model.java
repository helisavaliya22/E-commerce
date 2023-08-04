package com.example.e_commerce.Activity.Model;

public class UpdateData_Model {
    int connection;
    int result;

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

    @Override
    public String toString() {
        return "UpdateData_Model{" +
                "connection=" + connection +
                ", result=" + result +
                '}';
    }
}
