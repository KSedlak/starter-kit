package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.dao.mapper.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

    
	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	@Autowired
    private Sequence sequence;

	@Resource
    private BookMapper bookMapper;

    public BookDaoImpl() {
  
    }

   

    @Override
    public List<BookTo> findAll() {
    	List<BookTo> result=new ArrayList<BookTo>();
    	for(BookEntity be: ALL_BOOKS){
    		result.add(bookMapper.mappedBookEntity(be));
    	}
        return result;
    }

    @Override
    public List<BookTo> findBookByTitle(String title) {
    	List<BookTo> result=new ArrayList<BookTo>();

  
    	String bookTitle="";
    	BookTo temp;
    	for(BookEntity be: ALL_BOOKS){
    		temp=bookMapper.mappedBookEntity(be);
    		bookTitle= temp.getTitle();
  
	    	if(bookTitle.equalsIgnoreCase(title)){//or equals o co dokladnie chodzi z prefiksem
	    		result.add(temp);
	    	}
    	}
        return result;
    
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
    	List<BookTo> result=new ArrayList<BookTo>();
    	BookTo temp;
    	for(BookEntity be: ALL_BOOKS){
    	temp = bookMapper.mappedBookEntity(be);
	    	if(temp.getAuthors().equalsIgnoreCase(author)){
	    		result.add(temp);
	    	}
    	}
        return result;
    }

    @Override
    @NullableId
    public BookTo save(BookTo book) {
        ALL_BOOKS.add(bookMapper.mappedBookTo(book));
        return book;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    public Set<BookEntity> getALL_BOOKS() {
		return ALL_BOOKS;
	}



	public Sequence getSequence() {
		return sequence;
	}



	public BookMapper getBookMapper() {
		return bookMapper;
	}



	public void setMapper(BookMapper bm) {
        this.bookMapper = bm;
    }
    @PostConstruct
    private void addTestBooks() {

        ALL_BOOKS.add(bookMapper.mappedBookTo(new BookTo(1L, "Romeo i Julia", "Wiliam Szekspir")));
        ALL_BOOKS.add(bookMapper.mappedBookTo(new BookTo(2L, "Opium w rosole", "Hanna Ożogowska")));
        ALL_BOOKS.add(bookMapper.mappedBookTo(new BookTo(3L, "Przygody Odyseusza", "Jan Parandowski")));
        ALL_BOOKS.add(bookMapper.mappedBookTo(new BookTo(4L, "Awantura w Niekłaju", "Edmund Niziurski")));
        ALL_BOOKS.add(bookMapper.mappedBookTo(new BookTo(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki")));
        ALL_BOOKS.add(bookMapper.mappedBookTo(new BookTo(6L, "Zemsta", "Aleksander Fredro")));
    }
}
