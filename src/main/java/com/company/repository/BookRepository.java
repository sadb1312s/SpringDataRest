package com.company.repository;

import com.company.entity.helpentity.BookBuy;
import com.company.entity.helpentity.NamePrice;
import com.company.entity.tableentity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query(value = "select b.title, b.price from book b",nativeQuery = true)
    List<NamePrice> getBookTitlePrice();

    @Query(value = "select title, price from book where book.title like '%Windows%' or book.price > 20000 " +
            "order by book.title,book.price desc",nativeQuery = true)
    List<NamePrice> getBooksCondition();

    @Query(value = "select b.title, b.price, b.warehouse, bk.buysum from book b " +
            "join (select b.idbook, sum(b.count) as buysum from buy b " +
            "group by b.idbook) as bk on b.id = bk.idbook " +
            "where b.count > 10 " +
            "order by b.price", nativeQuery = true)
    List<BookBuy> getBookBuyInfo();

}

