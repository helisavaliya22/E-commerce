package com.example.e_commerce.Activity.Model;

public class Productdata_Model {
    int ID;
    String UID;
    String PNAME;
    String PDES;
    String PPRICE;
    String PIMG;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getPNAME() {
        return PNAME;
    }

    public void setPNAME(String PNAME) {
        this.PNAME = PNAME;
    }

    public String getPDES() {
        return PDES;
    }

    public void setPDES(String PDES) {
        this.PDES = PDES;
    }

    public String getPPRICE() {
        return PPRICE;
    }

    public void setPPRICE(String PPRICE) {
        this.PPRICE = PPRICE;
    }

    public String getPIMG() {
        return PIMG;
    }

    public void setPIMG(String PIMG) {
        this.PIMG = PIMG;
    }

    @Override
    public String toString() {
        return "Productdata_Model{" +
                "ID=" + ID +
                ", UID='" + UID + '\'' +
                ", PNAME='" + PNAME + '\'' +
                ", PDES='" + PDES + '\'' +
                ", PPRICE='" + PPRICE + '\'' +
                ", PIMG='" + PIMG + '\'' +
                '}';
    }
}
