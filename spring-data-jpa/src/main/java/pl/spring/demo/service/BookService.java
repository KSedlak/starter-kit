package pl.spring.demo.service;

import pl.spring.demo.to.BookTo;

import java.util.List;

import org.springframework.stereotype.Component;


public interface BookService {

    List<BookTo> findAllBooks();
    List<BookTo> findBooksByTitle(String title);
    List<BookTo> findBooksByAuthor(String author);

    BookTo saveBook(BookTo book);
}
