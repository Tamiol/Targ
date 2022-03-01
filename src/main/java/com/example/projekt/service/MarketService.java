package com.example.projekt.service;

import com.example.projekt.model.Product;
import com.example.projekt.model.Stall;

import java.util.List;

public interface MarketService {

    List<Long> findPossibleIdProduct(List<Stall> listOFStalls, String instance);
    Stall findStallById(List<Stall> listOFStalls, Long index);
    Product findProductById(List<Stall> listOFStalls, Long index);
    Long findIdOfStall(List<Stall> listOFStalls, Long index);
    <T extends Product> List<Object> findName(List<Stall> listOFStalls, String name);
    <T extends Product> List<Object> findByInstance(List<Stall> listOFStalls, String instance);
}
