package com.prueba.kometsales.controller;

import com.prueba.kometsales.model.Flower;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flowers")
public class FlowerController {

    static List<Flower> flowers = new ArrayList<Flower>();

    @PostMapping
    public void listFlowers(@RequestBody List<Flower> flowers){
        this.flowers = flowers;
    }

    @GetMapping
    public List<Flower> allFlowers (){
        return this.flowers;
    }

    @GetMapping("/kometsales")
    public List<FlowerResponse> getKometFlowers() {
       List<FlowerResponse> response = new ArrayList<>();
        for (Flower flower: flowers) {
            FlowerResponse flowerResponse = mapFlowerToFlowerResponse(flower.getId(), flower.getPrice(), flower.getName().concat("-kometsales"));
            response.add(flowerResponse);
        }

        return response ;

    }

    @GetMapping("/flower-prices-less-than-20")
    public List<FlowerResponse> flowersPrice(){
        List<FlowerResponse> response = new ArrayList<>();
        for (Flower flower: flowers) {
            if(flower.getPrice() > 20){
                FlowerResponse flowerResponse = mapFlowerToFlowerResponse(flower.getId(), flower.getPrice(), flower.getName());
                response.add(flowerResponse);
            }
        }

        return response ;
    }


    @GetMapping("/list-for-name/{name}")
    public List<FlowerResponse> listForName(@PathVariable String name){

        List<FlowerResponse> response = new ArrayList<>();

        for (Flower flower: flowers) {
            if (flower.getName().contains(name)){
                FlowerResponse flowerResponse = mapFlowerToFlowerResponse(flower.getId(), flower.getPrice(), flower.getName());
                response.add(flowerResponse);
            }
        }
        return response ;

    }

    @DeleteMapping("/deleteflower/{id}")
    public void deleteFlower(@PathVariable String id){
        for (int i = 0; i < flowers.size(); i++) {
            if (flowers.get(i).getId().equals(id)){
                flowers.remove(i);
            }
        }
    }

    private static FlowerResponse mapFlowerToFlowerResponse(String id, double price, String name) {
        FlowerResponse flowerResponse = new FlowerResponse();
        flowerResponse.id = id;
        flowerResponse.price = price;
        flowerResponse.name = name;
        return flowerResponse;
    }



}
