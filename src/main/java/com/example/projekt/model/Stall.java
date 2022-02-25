package com.example.projekt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Stall<T extends Product> implements LegalImpl{

    private int priceOfStall;
    private int capacity;
    private short taxes;
    private double monthlyUtilityCosts;
    private boolean isLegal;
    private long id;
    private static long index = 1;
    private List<T> listOfProducts;

    public Stall(int priceOfStall, int capacity, short taxes, List<T> listOfProducts) {
        this.priceOfStall = priceOfStall;
        this.capacity = capacity;
        this.taxes = taxes;
        this.listOfProducts = listOfProducts;
        this.id = index;
        index++;
        checkLegality();
        this.monthlyUtilityCosts = (double)priceOfStall * (double)taxes;
    }

    public Stall(int priceOfStall, int capacity, short taxes) {
        this.priceOfStall = priceOfStall;
        this.capacity = capacity;
        this.taxes = taxes;
        this.id = index;
        index++;
        this.isLegal = true;
        this.monthlyUtilityCosts = (double)priceOfStall * (double)taxes;
        listOfProducts = new ArrayList<>();
    }

    public Stall() {
    }

    // obliczanie wartości ceny
    // dokończyć meat
    // sprawdzanie czy jest legalny interfejs stall i meat i może plant

    public int getPriceOfStall() {
        return priceOfStall;
    }

    public void setPriceOfStall(int priceOfStall) {
        this.priceOfStall = priceOfStall;
        this.monthlyUtilityCosts = (double)priceOfStall * (double)taxes;
    }

    public int getCapasity() {
        return capacity;
    }

    public void setCapasity(int capacity) {
        this.capacity = capacity;
        this.monthlyUtilityCosts = (double)priceOfStall * (double)taxes;
    }

    public short getTaxes() {
        return taxes;
    }

    public void setTaxes(short taxes) {
        this.taxes = taxes;
        this.monthlyUtilityCosts = (double)priceOfStall * (double)taxes;
    }

    public double getDailyUtilityCosts() {
        return monthlyUtilityCosts;
    }

    public boolean isLegal() {
        return isLegal;
    }

    public List<T> getListOfProducts() {
        return listOfProducts;
    }

    public long getId() {
        return id;
    }

    public void setListOfProducts(List<T> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public void addProduct(T product) {
        listOfProducts.add(product);
        checkLegality();
    }

    public T getProductById(long id){
        return listOfProducts.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public void removeProductById(long id){
        listOfProducts.remove(getProductById(id));
        checkLegality();
    }

    public List<T> findByName(String name){
        return listOfProducts.stream().filter(e -> e.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public String searchForInstance(){
        if(listOfProducts.isEmpty()){
            return "CHOOSE";
        }else if(listOfProducts.get(0) instanceof Fruit){
            return "FRUIT";
        }else if(listOfProducts.get(0) instanceof Meat){
            return "MEAT";
        }else if(listOfProducts.get(0) instanceof Flower || listOfProducts.get(0) instanceof BouquetOfFlowers){
            return "PLANT";
        }else {
            return "NONE";
        }
    }

    public List<T> getInstancesOfPlant(String searchingInstance){
        if(searchForInstance().equals("PLANT")){
            switch (searchingInstance){
                case "FLOWER":
                    return listOfProducts.stream().filter(e -> e instanceof Flower).collect(Collectors.toList());
                case "BOUQUETOFFLOWERS":
                    return listOfProducts.stream().filter(e -> e instanceof BouquetOfFlowers).collect(Collectors.toList());
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Stall{" +
                "priceOfStall=" + priceOfStall +
                ", capasity=" + capacity +
                ", taxes=" + taxes +
                ", dailyUtilityCosts=" + monthlyUtilityCosts +
                ", isLegal=" + isLegal +
                ", id=" + id +
                ", listOfProducts=" + listOfProducts +
                '}';
    }

    @Override
    public void checkLegality() { //do poprawiwenia

        switch(searchForInstance()){
            case "PLANT":
                List<Flower> flo;
                Flower flo2 = null;
                try {
                    flo = (List<Flower>) getInstancesOfPlant("PLANT");
                }catch (NullPointerException e){
                    e.printStackTrace();
                    this.isLegal = true;
                    break;
                }
                try {
                    if(flo != null ) flo2 = flo.stream().filter(e ->e.getLegal() == false).findFirst().get();
                    if(flo2 != null ) this.isLegal = false;
                    else this.isLegal = true;
                }
                catch(NullPointerException e){
                    e.printStackTrace();
                    this.isLegal = true;
                }
                break;
            case "MEAT":
                List<Meat> me;
                Meat me2;
                try {
                    me = (List<Meat>) listOfProducts;
                    me2 = me.stream().filter(e -> e.getLegal() == false).findFirst().get();
                    if(me2 != null ) this.isLegal = false;
                    else this.isLegal = true;
                }
                catch(NullPointerException e){
                    e.printStackTrace();
                    this.isLegal = true;
                }
                break;
            default:
                this.isLegal = true;
        }

    }
}
