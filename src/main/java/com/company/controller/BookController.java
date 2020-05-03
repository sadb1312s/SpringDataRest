package com.company.controller;

import com.company.entity.Book;
import com.company.entity.Change;
import com.company.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

}
