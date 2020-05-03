package com.company.controller;

import com.company.entity.tableentity.Store;
import com.company.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStore(@PathVariable int id){
        storeService.delete(id);
    }

    @PostMapping("/create")
    @ResponseStatus (code = HttpStatus.CREATED)
    public void createStore(@RequestBody Store store){
        storeService.save(store);
    }

    @GetMapping ("/find/all")
    public List<Store> findAll () {
        return storeService.findAll();
    }

    @GetMapping("/find/{id}")
    public Store findById(@PathVariable int id){
        Optional<Store> optional = storeService.findById(id);
        return optional.orElse(null);
    }

    @PutMapping("/updateFull")
    public void updateFull(@RequestBody Store store){
        storeService.updateFull(store);
    }

    @PatchMapping("/update")
    public void update(@RequestParam int id,@RequestBody LinkedHashMap<String,String> data){
        storeService.update(id, data);
    }

    @GetMapping("storeTitle")
    public List<String> getTitles(){
        return storeService.getTitle();
    }
}
