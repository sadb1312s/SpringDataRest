package com.company.controller;

import com.company.entity.tableentity.Buy;
import com.company.service.BuyService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("buy")
public class BuyController {
    @Autowired
    private BuyService buyService;

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBuy(@PathVariable int id){
        buyService.delete(id);
    }

    @PostMapping("/create")
    @ResponseStatus (code = HttpStatus.CREATED)
    public void createBuy(@RequestBody Buy buy){
        buyService.save(buy);
    }

    @GetMapping ("/find/all")
    public List<Buy> findAll () {
        List<Buy> list =  buyService.findAll();
        System.out.println(list);
        return list;
    }

    @GetMapping("/find/{id}")
    public Buy findById(@PathVariable int id){
        Optional<Buy> optional = buyService.findById(id);
        return optional.orElse(null);
    }

    @PutMapping("/updateFull")
    public void updateFull(@RequestBody Buy buy){
        buyService.updateFull(buy);
    }

    @PatchMapping("/update")
    public void update(@RequestParam int id,@RequestBody LinkedHashMap<String,String> data){
        buyService.update(id, data);
    }

    @GetMapping("/Months")
    public List<String> months(){
        return buyService.getMonth();
    }

    @GetMapping("/buyerNameStoreTitle")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "return value is lastname='value', store title = 'value'")
    }
    )
    public List<String> joinNameTitle(){
        return buyService.getJoinTable();
    }

    @GetMapping("/buyerInfoBookInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "return value is lastname='value', store title = 'value'")
    }
    )
    public List<String> joinBuyerInfoBookInfo(){
        return buyService.getBuyerBookInfo();
    }
}
