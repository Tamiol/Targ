package com.example.projekt.model;

public class BouquetOfFlowers extends Plant{

    private int amountOfBouquets;
    private boolean isGreenery;
    private boolean isRibbon;
//    private List<Flower> listOfFlowers;


    public BouquetOfFlowers(String name, double price, Colors flowerColor, int amountOfBouquets, boolean isGreenery, boolean isRibbon) {
        super(name, price, flowerColor);
        this.amountOfBouquets = amountOfBouquets;
        this.isGreenery = isGreenery;
        this.isRibbon = isRibbon;
    }

    public BouquetOfFlowers() {
        super("", 0, Colors.GREEN);
    }

    public int getAmountOfBouquets() {
        return amountOfBouquets;
    }

    public void setAmountOfBouquets(int amountOfBouquets) {
        this.amountOfBouquets = amountOfBouquets;
    }

    public boolean getGreenery() {
        return isGreenery;
    }

    public void setGreenery(boolean greenery) {
        isGreenery = greenery;
    }

    public boolean getRibbon() {
        return isRibbon;
    }

    public void setRibbon(boolean ribbon) {
        isRibbon = ribbon;
    }

//    @Override
//    public boolean isInstanceOfPlant(String instance) {
//        return instance.equals("BOUQUETOFFLOWERS");
//    }

//    public List<Flower> getListOfFlowers() {
//        return listOfFlowers;
//    }
//
//    public void setListOfFlowers(List<Flower> listOfFlowers) {
//        this.listOfFlowers = listOfFlowers;
//    }
//
//    public void mostCommonColorOfFlowers() {
//        this.mainColor = listOfFlowers.stream().collect(Collectors.groupingBy(Flower::getFlowerColor, Collectors.counting()))
//                .entrySet().stream().max(Map.Entry.comparingByValue())
//                .get().getKey();
//    }
//
//    public void calcutatePrice(){
//        listOfFlowers.stream().mapToDouble(Flower::getPrice).sum();
//    }
//@Override
//public boolean dot() {
//    return true;
//}
}
