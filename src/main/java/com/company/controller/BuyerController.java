package com.company.controller;

import com.company.entity.tableentity.Buyer;
import com.company.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("buyer")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @DeleteMapping("/delete/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteBuyer(@PathVariable int id){
        buyerService.delete(id);
    }

    @PostMapping("/create")
    @ResponseStatus (code = HttpStatus.CREATED)
    public void createBuyer(@RequestBody Buyer buyer){
        System.out.println(buyer);
        buyerService.save(buyer);
    }

    @GetMapping ("/find/all")
    public List<Buyer> findAll () {
        return buyerService.findAll();
    }

    @GetMapping ("/find/{id}")
    public Buyer findById(@PathVariable int id){
        Optional<Buyer> optional = buyerService.findById(id);
        return optional.orElse(null);
    }

    @PutMapping ("/updateFull")
    public void updateFull(@RequestBody Buyer buyer){
        buyerService.updateFull(buyer);
    }

    @PatchMapping ("/update")
    public void update(@RequestParam int id,@RequestBody LinkedHashMap<String,String> data){
        buyerService.update(id, data);
    }

    @GetMapping ("/district")
    public List<String> district(){
        return buyerService.getDistricts();
    }

    @GetMapping("/discount")
    public List<String> discounts(){
        return buyerService.getNameDiscount();
    }
}
