package com.example.e_commerce.Activity.Model;

import android.service.autofill.UserData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData_Model {
    @SerializedName("connection")
    @Expose
    private Integer connection;
    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("userdata")
    @Expose
    private Userdata userdata;

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Userdata getUserdata() {
        return userdata;
    }

    public void setUserdata(Userdata userdata) {
        this.userdata = userdata;
    }
    public class Userdata {

        @SerializedName("ID")
        @Expose
        private String id;
        @SerializedName("NAME")
        @Expose
        private String name;
        @SerializedName("EMAIL")
        @Expose
        private String email;
        @SerializedName("PASSWORD")
        @Expose
        private String password;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }
    @Override
    public String toString() {
        return "DataModelLogin{" +
                "connection=" + connection +
                ", result=" + result +
                ", userdata=" + userdata +
                '}';
    }
}
