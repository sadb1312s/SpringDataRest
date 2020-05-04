package com.company.controller;

import com.company.entity.helpentity.BookBuy;
import com.company.entity.helpentity.NamePrice;
import com.company.entity.tableentity.Book;
import com.company.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @DeleteMapping("/delete/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable int id){
        bookService.delete(id);
    }

    @PostMapping("/create")
    @ResponseStatus (code = HttpStatus.CREATED)
    public void createBook(@RequestBody Book book){
        System.out.println(book);
        bookService.save(book);
    }

    @GetMapping ("/find/all")
    public List<Book> findAll () {
        return bookService.findAll();
    }

    @GetMapping("/find/{id}")
    public Book findById(@PathVariable int id){
        Optional<Book> optional = bookService.findById(id);
        return optional.orElse(null);
    }

    @PutMapping("/updateFull")
    public void updateFull(@RequestBody Book book){
        bookService.updateFull(book);
    }

    @PatchMapping("/update")
    public void update(@RequestParam int id,@RequestBody LinkedHashMap<String,String> data){
        bookService.update(id, data);
    }

    @GetMapping("/namePrice")
    public List<NamePrice> namePrice(){
        List<Book> books = findAll();
        System.out.println(books);


        List<NamePrice> result = new ArrayList<>();
        books.forEach((Book b) ->{result.add(new NamePrice(b.getTitle(),b.getPrice()));});

       return result;
    }

    @GetMapping("/namePrice/condition")
    public List<NamePrice> namePricesCondition(){
        return bookService.getWithCondition();
    }

    @GetMapping("/bookBuySummaryInfo")
    public List<BookBuy> getBookBuySummaryInfo(){
        return bookService.getBookBuyInfo();
    }

}
