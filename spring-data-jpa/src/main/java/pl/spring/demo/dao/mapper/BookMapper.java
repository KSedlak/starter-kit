package pl.spring.demo.dao.mapper;

import java.util.ArrayList;
import java.util.List;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

public class BookMapper {

	private static String separator = ", ";

	
	public static BookTo mappedBookEntity(BookEntity be) {

		return new BookTo(be.getId(), be.getTitle(), stringToAuthors(be.getAuthors()));
	}

	public static BookEntity mappedBookTo(BookTo bt) {

		return new BookEntity(bt.getId(), bt.getTitle(), AuthorsListToString(bt.getAuthors()));
	}

	private static List<AuthorTo> stringToAuthors(String input) {
		List<AuthorTo> authors = new ArrayList<AuthorTo>();
		
		if (input == null) {
			return authors;
		}
		
		String[] authorsTable = input.split(separator);
		for (int i = 0; i < authorsTable.length; i++) {
			authors.add(new AuthorTo((long) i, ////////////////////////////////////// id?????????????
				authorsTable[i].split(" ")[0], authorsTable[i].split(" ")[1]));
		}
		return authors;
	}

	private static String AuthorsListToString(List<AuthorTo> list) {
		String authors = "";
		if(list==null || list.isEmpty()){
			return "";
		}
		for (int i = 0; i <= list.size() - 1; i++) {
			authors = authors + list.get(i).getFirstName() + " " + list.get(i).getLastName() + separator;
		}
		return authors.substring(0, authors.length() - separator.length()); // remove last seperator
	}
}
