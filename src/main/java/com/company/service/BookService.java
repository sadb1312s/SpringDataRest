package com.company.service;

import com.company.entity.Book;
import com.company.repository.BookRepository;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

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
        Optional<Book> optional = bookRepository.findById(id);
        return optional;
    }

    public void updateFull(Book book){
        Book bookOld = bookRepository.getOne(book.getId());

        if (bookOld != null){
            System.out.println("!");
            bookOld.copy(book);
            bookRepository.save(bookOld);
        }
    }

    public void update(int id, LinkedHashMap<String, String> data){
        System.out.println(data);


        Book bookForUpdate = bookRepository.findById(id).orElse(null);

        //maybe return false(or http status) if exception was thrown
        if(bookForUpdate != null) {
            data.forEach((k, v) -> {
                try {
                    Field f = Book.class.getDeclaredField(k);

                    f.setAccessible(true);
                    String className = f.getType().getCanonicalName();

                    Class c = ClassUtils.getClass(className);

                    if (c.isPrimitive()){
                        c = ClassUtils.primitiveToWrapper(c);
                    }

                    Constructor<?> ctor = c.getConstructor(String.class);
                    f.set(bookForUpdate,ctor.newInstance(v));

                } catch (NoSuchFieldException | ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });

            bookRepository.save(bookForUpdate);
        }
    }



}
