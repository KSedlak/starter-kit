package pl.spring.demo.service.impl;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.dao.mapper.BookMapper;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Autowired
    private BookDao bookDao;
	

	

    @Override
    public List<BookTo> findAllBooks() {
    	ArrayList<BookTo> res=new ArrayList<BookTo>();
    	for(BookEntity b: bookDao.findAll()){
    		res.add(BookMapper.mappedBookEntity(b));
    	}
        return res;
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
    	ArrayList<BookTo> res=new ArrayList<BookTo>();
    	for(BookEntity b: bookDao.findBookByTitle(title)){
    		res.add(BookMapper.mappedBookEntity(b));
    	}
        return res;
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
    	ArrayList<BookTo> res=new ArrayList<BookTo>();
    	for(BookEntity b: bookDao.findBooksByAuthor(author)){
    		res.add(BookMapper.mappedBookEntity(b));
    	}
        return res;
    }

    @Override
    public BookTo saveBook(BookTo book) {
        return BookMapper.mappedBookEntity(bookDao.save(BookMapper.mappedBookTo(book)));
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
