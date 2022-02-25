package com.example.projekt.model;

import java.util.Date;

public class Meat extends Product implements LegalImpl{

    private int weight;
    private boolean isLegal;
//    private Date expirationDate;
    private boolean isFresh;

    public Meat(String name, double price, int weight, boolean isLegal, boolean isFresh) {
        super(name, price);
        this.weight = weight;
        this.isLegal = isLegal;
        this.isFresh = isFresh;
        checkLegality();
//        this.expirationDate = expirationDate;
    }

    public Meat() {
        super("", 0);
    }

    // check if is legal fresh must be true
    // is fresh - new parameter
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean getLegal() {
        return isLegal;
    }

    public void setLegal(boolean legal) {
        this.isLegal = legal;
        checkLegality();
    }

    public boolean getFresh() {
        return isFresh;
    }

    public void setFresh(boolean fresh) {
        isFresh = fresh;
    }

    //    public Date getExpirationDate() {
//        return expirationDate;
//    }
//
//    public void setExpirationDate(Date expirationDate) {
//        this.expirationDate = expirationDate;
//    }

    @Override
    public void checkLegality() {
        if(!isFresh) this.isLegal = false;
    }
}
