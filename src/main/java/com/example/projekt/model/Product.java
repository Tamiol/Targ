package com.example.projekt.model;
/*import java.util.Date;*/

public abstract class Product {

    private String name;
    private long id;
    private double price;
    private static long index = 1;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.id = index;
        index++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    //after adding new product
    public static void fixId(){
        index-=2;
    }

//    public boolean isInstanceOfPlant(String instance){
//        return false;
//    }

    /*public abstract int getAmount();*/

    /*public abstract class freshnessDate {};*/
    // public abstract void isLegal();
    //calculate price
}
