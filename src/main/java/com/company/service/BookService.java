package com.company.service;

import com.company.entity.helpentity.BookBuy;
import com.company.entity.helpentity.NamePrice;
import com.company.entity.tableentity.Book;
import com.company.repository.BookRepository;

import com.company.service.updatetable.UpdateException;
import com.company.service.updatetable.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public void delete(int id){
        bookRepository.deleteById(id);
    }

    public void save(Book book){
        bookRepository.save(book);
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findById(int id){
        return bookRepository.findById(id);
    }

    public void updateFull(Book book){
        Book bookOld = bookRepository.getOne(book.getId());

        if (bookOld != null){
            System.out.println("!");
            bookOld.copy(book);
            bookRepository.save(bookOld);
        }
    }

    public void update(int id, Map<String, String> data) throws UpdateException{
        System.out.println(data);

        Book bookForUpdate = bookRepository.findById(id).orElse(null);

        if(bookForUpdate != null){
            Updater.update(bookForUpdate,data,Book.class);

            bookRepository.save(bookForUpdate);
        }
    }


    public List<NamePrice> getBookTitlePrice(){
        return bookRepository.getBookTitlePrice();
    }

    public List<NamePrice> getWithCondition(){

        return bookRepository.getBooksCondition();
    }

    public List<BookBuy> getBookBuyInfo(){
        return bookRepository.getBookBuyInfo();
    }


}
