package pl.spring.demo.dao.mapper;

import java.util.ArrayList;
import org.springframework.stereotype.Component;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;



@Component(value="bookMap")
public class BookMapper {

	private static String separator = ", ";

	
	public static BookTo mappedBookEntity(BookEntity be) {
		String authors = AuthorsListToString(be.getAuthors());
		return new BookTo(be.getId(),be.getTitle(), authors);

	}

	public static BookEntity mappedBookTo(BookTo bt) {
	
		return new BookEntity(bt.getId(), bt.getTitle(), stringToAuthors(bt.getAuthors()));

	}
	
	public static ArrayList<AuthorTo> stringToAuthors(String input){
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
	public static String AuthorsListToString(ArrayList<AuthorTo> list){
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
