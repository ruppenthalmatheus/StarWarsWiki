package com.example.matheus.starwarswiki;

import java.io.Serializable;

public class Vehicles implements Serializable {

    private String nName;
    private String nModel;
    private String nManufacter;
    private String nLength;
    private int nMaxSpeed;

    public Vehicles(String nName, String nModel, String nManufacter, String nLength, int nMaxSpeed) {
        this.nName = nName;
        this.nModel = nModel;
        this.nManufacter = nManufacter;
        this.nLength = nLength;
        this.nMaxSpeed = nMaxSpeed;
    }

    public Vehicles() {

    }

    public String getnName() {
        return nName;
    }

    public String getnModel() {
        return nModel;
    }

    public String getnManufacter() {
        return nManufacter;
    }

    public String getnLength() {
        return nLength;
    }

    public int getnMaxSpeed() {
        return nMaxSpeed;
    }
}
