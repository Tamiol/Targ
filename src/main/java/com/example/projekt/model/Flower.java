package com.example.projekt.model;

public class Flower extends Plant implements LegalImpl{
    private double avrageLength;
    private boolean isLegal;

    public Flower(String name, double price, Colors flowerColor, double avrageLength, boolean isLegal) {
        super(name, price, flowerColor);
        this.avrageLength = avrageLength;
        this.isLegal = isLegal;
        checkLegality();
    }

    public Flower() {
        super("", 0, Colors.GREEN);
    }

    public double getAvrageLength() {
        return avrageLength;
    }

    public void setAvrageLength(double avrageLength) {
        //System.out.println("test");
        this.avrageLength = avrageLength;
    }

    public boolean getLegal() {
        return isLegal;
    }

    public void setLegal(boolean legal) {
        isLegal = legal;
    }

    @Override
    public void checkLegality() {
        if(getName().equals("Cebulica dwulistna")) this.isLegal = false;
    }

//    @Override
//    public boolean dot() {
//        return true;
//    }

//    @Override
//    public boolean isInstanceOfPlant(String instance) {
//        return instance.equals("FLOWER");
//    }

}
