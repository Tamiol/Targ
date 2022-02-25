package com.example.projekt.model;

public abstract class Plant extends Product{

    private Colors flowerColor;

    public Plant(String name, double price, Colors flowerColor) {
        super(name, price);
        this.flowerColor = flowerColor;
    }

    public Colors getFlowerColor() {
        return flowerColor;
    }

    public void setFlowerColor(Colors flowerColor) {
        this.flowerColor = flowerColor;
    }
}
