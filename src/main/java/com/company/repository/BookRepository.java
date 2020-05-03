package com.company.repository;

import com.company.entity.tableentity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query(value = "select * from book where book.title like '%Windows%' or book.price > 20000 " +
            "order by book.title,book.price desc",nativeQuery = true)
    List<Book> getBooksCondition();


}

