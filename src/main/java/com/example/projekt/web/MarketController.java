package com.example.projekt.web;

import com.example.projekt.model.*;
import com.example.projekt.service.MarketServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MarketController {

    private List<Stall> listOFStalls;
    private final MarketServiceImpl marketService;

    public MarketController() {
        listOFStalls = new ArrayList<>();
        marketService = new MarketServiceImpl();
        listOFStalls.add(new Stall<Fruit>(500,300, (short) 30,
                new ArrayList<>(List.of(new Fruit("Jabłko", 5, Colors.RED,100, Size.MEDIUM),
                                        new Fruit("Gruszka", 4, Colors.GREEN,50, Size.HUGE),
                                        new Fruit("Banan", 2.5, Colors.YELLOW,200, Size.MEDIUM),
                                        new Fruit("Gruszka", 7.5, Colors.GREEN,400, Size.SMALL)))));
        listOFStalls.add(new Stall<Fruit>(600,400, (short) 40,
                new ArrayList<>(List.of(new Fruit("Banan", 2.5, Colors.YELLOW,200, Size.MEDIUM),
                                        new Fruit("Gruszka", 3, Colors.GREEN,400, Size.SMALL)))));
        listOFStalls.add(new Stall<Plant>(600,400, (short) 40,
                new ArrayList<>(List.of(new Flower("Fiołek", 5.0, Colors.VIOLET, 13.2, true),
                                        new BouquetOfFlowers("Wieniec", 30.99, Colors.YELLOW, 50, true, true)))));
        listOFStalls.add(new Stall<Meat>(800,200, (short) 40,
                new ArrayList<>(List.of(new Meat("Nietoperz", 10, 30, false, true),
                        new Meat("Baranina", 30, 200, true, true)))));

        //listOFStalls.get(3).getListOfProducts().stream().filter(e -> ((Plant) (e)).dot()).forEach(product -> System.out.println(((Plant)product).getId()));

    }

    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("list", listOFStalls);
        return "all";
    }

    /*@GetMapping("/getById/{type}{id}")
    public String getByIdApi(@PathVariable("type") String type, @PathVariable(required=false, name="index") String index, Model model){
        Long id = Long.parseLong(index);
        switch(type){
            case "STALL":
                model.addAttribute("list", MarketManager.findStallById(listOFStalls, id));
                return "all";
            case "PRODUCT":
                model.addAttribute("list", listOFStalls.get(1));
                return "showOne";
        }
        return "all";
    }*/

    @GetMapping("/getById")
    public String getById(){
        return "getById";
    }

    @PostMapping("/getById")
    public String getByIdApi(HttpServletRequest request){
        Long index;
        try {
            index = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "redirect:/";
        }

        switch(request.getParameter("type")){
            case "STALL":
                request.setAttribute("list", marketService.findStallById(listOFStalls, index));
                return "all";
            case "PRODUCT":
                request.setAttribute("list", marketService.findProductById(listOFStalls, index));
                return "showOne";
        }
        return "all";
    }

    // ADDING --------------------------------------------------------------------------------------------------
    @GetMapping("/add")
    public String add(){
        return "add";
    }

    @GetMapping("/addStall")
    public String addStall(Model model){
        //Stall newStall = new Stall();
        model.addAttribute(new Stall());
        return "addStall";
    }

    @PostMapping("/addStall")
    public String saveStall(@ModelAttribute("stall") Stall stall) {
        listOFStalls.add(new Stall(stall.getPriceOfStall(), stall.getCapasity(), stall.getTaxes()));
        return "redirect:/";
    }

    @GetMapping("/addFruit")
    public String addFruit(Model model){
        Fruit newFruit = new Fruit();
        model.addAttribute(newFruit);
        model.addAttribute("colors", Colors.values());
        model.addAttribute("sizes", Size.values());
        model.addAttribute("ids", marketService.findPossibleIdProduct(listOFStalls, "FRUIT"));
        Product.fixId();
        return "addFruit";
    }

    @PostMapping("/addFruit")
    public String saveFruit(@ModelAttribute("stall") Fruit fruit, HttpServletRequest request) {
        long index = Integer.valueOf(request.getParameter("id"));
        marketService.findStallById(listOFStalls, index).addProduct(new Fruit(fruit.getName(), fruit.getPrice(), fruit.getColor(), fruit.getAmount(), fruit.getSize()));
        return "redirect:/";
    }

    @GetMapping("/addFlower")
    public String addFlower(Model model){
        Flower newFlower = new Flower();
        model.addAttribute(newFlower);
        model.addAttribute("colors", Colors.values());
        model.addAttribute("ids", marketService.findPossibleIdProduct(listOFStalls, "PLANT"));
        Product.fixId();
        return "addFlower";
    }

    @PostMapping("/addFlower")
    public String saveFlower(@ModelAttribute("stall") Flower flower, HttpServletRequest request) {
        long index = Integer.valueOf(request.getParameter("id"));
        marketService.findStallById(listOFStalls, index).addProduct(new Flower(flower.getName(), flower.getPrice(), flower.getFlowerColor(), flower.getAvrageLength(), flower.getLegal()));
        return "redirect:/";
    }

    @GetMapping("/addBouquetOfFlowers")
    public String addBouquetOfFlowers(Model model){
        BouquetOfFlowers newBouquetOfFlowers = new BouquetOfFlowers();
        model.addAttribute(newBouquetOfFlowers);
        model.addAttribute("colors", Colors.values());
        model.addAttribute("ids", marketService.findPossibleIdProduct(listOFStalls, "PLANT"));
        Product.fixId();
        return "addBouquetOfFlowers";
    }

    @PostMapping("/addBouquetOfFlowers")
    public String saveBouquetOfFlowers(@ModelAttribute("stall") BouquetOfFlowers bouquet, HttpServletRequest request) {
        long index = Integer.valueOf(request.getParameter("id"));
        marketService.findStallById(listOFStalls, index).addProduct(new BouquetOfFlowers(bouquet.getName(), bouquet.getPrice(), bouquet.getFlowerColor(), bouquet.getAmountOfBouquets(), bouquet.getGreenery(), bouquet.getRibbon()));
        return "redirect:/";
    }

    @GetMapping("/addMeat")
    public String addMeat(Model model){
        Meat newMeat = new Meat();
        model.addAttribute(newMeat);
        model.addAttribute("ids", marketService.findPossibleIdProduct(listOFStalls, "MEAT"));
        Product.fixId();
        return "addMeat";
    }

    @PostMapping("/addMeat")
    public String saveMeat(@ModelAttribute("stall") Meat meat, HttpServletRequest request) {
        long index = Integer.valueOf(request.getParameter("id"));
        marketService.findStallById(listOFStalls, index).addProduct(new Meat(meat.getName(), meat.getPrice(), meat.getWeight(), meat.getLegal(), meat.getFresh()));
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(){
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteById(HttpServletRequest request){
        Long index;
        try {
            index = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "redirect:/";
        }
        switch(request.getParameter("type")){
            case "STALL":
                listOFStalls.remove(marketService.findStallById(listOFStalls,index));
                break;
            case "PRODUCT":
                marketService.findStallById(listOFStalls,marketService.findIdOfStall(listOFStalls,index)).removeProductById(index);
                break;
        }
        return "redirect:/";
    }

    @RequestMapping ("/deleteStall")
    public String deleteStall(@RequestParam(value = "index", required = false) String id){
        try {
            listOFStalls.remove(marketService.findStallById(listOFStalls,Long.parseLong(id)));
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return "redirect:/";
    }

    //id = product, id2 = stall with this product
    @RequestMapping ("/deleteProduct")
    public String deleteProduct(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "id2", required = false) String id2){
        try {
            marketService.findStallById(listOFStalls,Long.parseLong(id2)).removeProductById(Long.parseLong(id));
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return "redirect:/";
    }

    /*@RequestMapping(value = "/getString", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)*//*
    public String getById(@RequestParam int index){
        *//*return listOFStalls.stream().filter(e -> e.getId() == index).findFirst().get();*//*
        return "exam";
    }*/

    @GetMapping("/findByName")
    public String findByName(@RequestParam(value = "searchTerm", required = false) String searchTerm, Model model){
        try {
            model.addAttribute("list",  marketService.findName(listOFStalls, searchTerm));
        }catch (NullPointerException e){
            e.printStackTrace();
            model.addAttribute("list", null);
        }
        return "showOne";
    }

    @PostMapping("/filterBy")
    public String filterBy(@RequestParam(value = "searchTerm", required = false) String searchTerm, Model model){

        switch(searchTerm){
            case "STALL":
                model.addAttribute("list", listOFStalls);
                return "all";
            case "FRUIT":
            case "MEAT":
            case "FLOWER":
            case "BOUQUETOFFLOWERS":
                model.addAttribute("list", marketService.findByInstance(listOFStalls, searchTerm));
                model.addAttribute("instance", searchTerm);
                return "showSingleTypeOfObject";
        }
        return "all";
    }
}
