package com.company.service;

import com.company.entity.helpentity.InBuyerDistrictBuy;
import com.company.entity.helpentity.jointables.BuyJoin;
import com.company.entity.tableentity.Book;
import com.company.entity.tableentity.Buy;
import com.company.entity.tableentity.Buyer;
import com.company.repository.BuyJoinRepository;
import com.company.repository.BuyRepository;
import com.company.service.updatetable.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BuyService {
    @Autowired
    BuyRepository buyRepository;
    @Autowired
    BuyJoinRepository buyJoinRepository;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private BookService bookService;

    public void create(Buy buy){
        Buyer b = buyerService.findById(buy.getIdbuyer()).orElse(null);
        Book book = bookService.findById(buy.getIdbook()).orElse(null);

        if(b != null & book != null){
            double sum = book.getPrice()*buy.getCount();
            sum -= (sum/100)*b.getDiscount();

            if(sum < 0){
                sum = 0;
            }
            buy.setSum(sum);
        }

        save(buy);
    }

    public void delete(int id){
        buyRepository.deleteById(id);
    }

    public void save(Buy buy){
        buyRepository.save(buy);
    }

    public List<Buy> findAll(){
        return buyRepository.findAll();
    }

    public Optional<Buy> findById(int id){
        return buyRepository.findById(id);
    }

    public void updateFull(Buy buy){
        Buy buyOld = buyRepository.getOne(buy.getId());

        if (buyOld != null){
            buyOld.copy(buy);
            buyRepository.save(buyOld);
        }
    }

    public void update(int id, Map<String, String> data){
        System.out.println(data);

        Buy buyForUpdate = buyRepository.findById(id).orElse(null);

        //maybe return false(or http status) if exception was thrown
        if(buyForUpdate != null) {
            Updater.update(buyForUpdate,data, Buy.class);

            create(buyForUpdate);
        }
    }

    public List<String> getMonth(){
        return buyRepository.getMonth();
    }

    public List<String> getJoinTable() {
        List<BuyJoin> buys = buyJoinRepository.findAll();

        List<String> result = new ArrayList<>();
        buys.forEach((buyJoin -> result.add("lastname = '"+buyJoin.getBuyer().getLastname()+"', " +
                "store title = '"+buyJoin.getStore().getTitle()+"'")
        ));

        return result;
    }

    public List<String> getBuyerBookInfo() {
        List<BuyJoin> buys = buyJoinRepository.findAll();

        List<String> result = new ArrayList<>();
        buys.forEach((buyJoin -> result.add("date = '"+buyJoin.getDate()+"', " +
                "last name = '"+buyJoin.getBuyer().getLastname()+"' " +
                "discount = '"+buyJoin.getBuyer().getDiscount()+"' " +
                "book Title = '"+buyJoin.getBook().getTitle()+"' " +
                "count = '"+buyJoin.getCount()+"'")
        ));

        return result;

    }

    public List<String> getBigBuy(double condition){
        List<BuyJoin> buys = buyJoinRepository.findAllBySumGreaterThanEqual(condition);

        List<String> result = new ArrayList<>();
        buys.forEach((buyJoin -> result.add("buy id = '"+buyJoin.getId()+"', " +
                "last name = '"+buyJoin.getBuyer().getLastname()+"' " +
                "date = '"+buyJoin.getDate()+"' ")
        ));

        return result;


    }

    public List<InBuyerDistrictBuy> getBuyInBuyerDistrict(){
        return buyRepository.getBuyInBuyerDistrict();
    }

}
