package pl.spring.demo.dao.mapper;

import java.util.ArrayList;
import org.springframework.stereotype.Component;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;



@Component(value="bookMap")
public class BookMapper {

	private static String separator = ", ";

	
	public BookTo mappedBookEntity(BookEntity be) {
		long id = be.getId();
		String title = be.getTitle();
		String authors = AuthorsListToString(be.getAuthors());
		return new BookTo(id, title, authors);

	}

	public BookEntity mappedBookTo(BookTo bt) {
		long id = bt.getId();
		String title = bt.getTitle();
		
		return new BookEntity(id, title, stringToAuthors(bt.getAuthors()));

	}
	
	public ArrayList<AuthorTo> stringToAuthors(String input){
		ArrayList<AuthorTo> authors = new ArrayList<AuthorTo>();
		if(input==null){
			return authors;
		}
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
		if(list.isEmpty()){//empty list
			return "";
		}
	return authors.substring(0,authors.length()-separator.length()); //remove last seperator
	}

}
