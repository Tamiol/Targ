package com.example.projekt.model;

public class Fruit extends Product {

    private Colors color;
    private int amount;
    private Size size;

    public Fruit(String name, double price, Colors color, int amount, Size size) {
        super(name, price);
        this.color = color;
        this.amount = amount;
        this.size = size;
    }

    public Fruit() {
        super("",0);
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "color=" + color +
                ", amount=" + amount +
                ", size=" + size +
                '}';
    }

    //    full price of single bunch of fruit??
//    public int calcutatePrice {
//
//    }
}
