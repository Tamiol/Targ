package com.example.projekt.service;
import com.example.projekt.model.Product;
import com.example.projekt.model.Stall;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketServiceImpl implements MarketService {

    //return: stalls id of given instance
    @Override
    public List<Long> findPossibleIdProduct(List<Stall> listOFStalls, String instance) {
        return listOFStalls.stream().filter(e -> e.searchForInstance().equals(instance) || e.searchForInstance().equals("CHOOSE")).map(e -> e.getId()).collect(Collectors.toList());
    }

    @Override
    public Stall findStallById(List<Stall> listOFStalls, Long index) {
        return listOFStalls.stream().filter(e -> e.getId() == index).findFirst().orElse(null);
    }

    @Override
    public Product findProductById(List<Stall> listOFStalls, Long index) {
        return listOFStalls.stream().filter(e -> e.getProductById(index) != null).findFirst().map(e -> e.getProductById(index)).orElse(null);
    }

    // znając id produktu znajduje id straganu
    @Override
    public Long findIdOfStall(List<Stall> listOFStalls, Long index) {
        return listOFStalls.stream().filter(e -> e.getProductById(index) != null).map(e ->e.getId()).findFirst().orElse(null);
    }

    @Override
    public <T extends Product> List<Object> findName(List<Stall> listOFStalls, String name){
        List<Object> newList = new ArrayList<>();
        List pup = listOFStalls.stream().filter(e -> !e.findByName(name.toLowerCase()).isEmpty()).map(e -> e.findByName(name.toLowerCase())).collect(Collectors.toList());
        for(Object elem: pup){
            newList.addAll((Collection<? extends T>) elem);
        }
        return newList;
    }

    @Override
    public <T extends Product> List<Object> findByInstance(List<Stall> listOFStalls, String instance) {
        List<Object> newList = new ArrayList<>();
        List<List> pup;

        if (instance.equals("FLOWER") || instance.equals("BOUQUETOFFLOWERS")) {
            pup = listOFStalls.stream().filter(e -> e.searchForInstance().equals("PLANT")).map(e -> e.getInstancesOfPlant(instance)).collect(Collectors.toList());
        }
        else {
            pup = listOFStalls.stream().filter(e -> e.searchForInstance().equals(instance)).map(Stall::getListOfProducts).collect(Collectors.toList());
        }
        for(Object elem: pup){
            if(elem != null)
                newList.addAll((Collection<? extends T>) elem);
        }
        return newList;
    }
}
