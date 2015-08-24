package pl.spring.demo.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;



@Component
public class BookMapper {

	private BookEntity be;
	private BookTo bt;
	private static String separator = ", ";

	public BookMapper() {
	}

	public BookMapper(BookEntity b) {
		be = b;
		bt = mappedBookEntity(be);
	}

	public BookMapper(BookTo b) {
		bt = b;
		be = mappedBookTo(bt);
	}

	public BookEntity getBookEntity() {
		return be;
	}

	public BookTo getBookTo() {
		return bt;
	}

	public BookTo mappedBookEntity(BookEntity be) {
		long id = be.getId();
		String title = be.getTitle();
	
		return new BookTo(id, title, AuthorsListToString(be.getAuthors()));

	}

	public BookEntity mappedBookTo(BookTo bt) {
		long id = bt.getId();
		String title = bt.getTitle();
		
		return new BookEntity(id, title, stringToAuthors(bt.getAuthors()));

	}
	
	public ArrayList<AuthorTo> stringToAuthors(String input){
		ArrayList<AuthorTo> authors = new ArrayList<AuthorTo>();
		String[] authorsTable = input.split(separator);
			
		for (int i = 0; i < authorsTable.length; i++) {
			authors.add(new AuthorTo((long) i, ////////////////////////////////////// id?????????????
					authorsTable[i].split(" ")[0], authorsTable[i].split(" ")[1]));
		}
		return authors;
		
		
		
		
	}
	public String AuthorsListToString(ArrayList<AuthorTo> list){
		String authors="";
		
		for (int i = 0; i <= list.size() - 1; i++) {
			authors = authors + list.get(i).getFirstName() + " " + list.get(i).getLastName()
					+ separator;
		}
		if(authors.equals("")){//empty list
			return "";
		}
	return authors.substring(0,authors.length()-separator.length()); //remove last seperator
	}

}
