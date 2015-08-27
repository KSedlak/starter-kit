package pl.spring.demo.dao;

import pl.spring.demo.entity.BookEntity;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface BookDao {

    List<BookEntity> findAll();

    List<BookEntity> findBookByTitle(String title);

    List<BookEntity> findBooksByAuthor(String author);

    BookEntity save(BookEntity book);
}
