package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.AutoGenenareteID;
import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	@Autowired
	private Sequence sequence;

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookEntity> findAll() {
		return new ArrayList<>(ALL_BOOKS);
	}

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		List<BookEntity> result = new ArrayList<BookEntity>();
		String bookTitle;
		for (BookEntity be : ALL_BOOKS) {
			bookTitle = be.getTitle().toLowerCase();
			if (bookTitle.startsWith(title.toLowerCase())) {							
				result.add(be);
			}
		}
		return result;

	}

	@Override
	public List<BookEntity> findBooksByAuthor(String author) {
		List<BookEntity> result = new ArrayList<BookEntity>();
		String firstName = author.split(" ")[0];
		String lastName = author.split(" ")[1];

		for (BookEntity be : ALL_BOOKS) {
			for (AuthorTo a : be.getAuthors()) {
				if (firstName.equals(a.getFirstName()) && lastName.equals(a.getLastName())) {
					result.add(be);
				}
			}
		}
		return result;
	}

	@Override
	@NullableId
	@AutoGenenareteID
	public BookEntity save(BookEntity book) {
		ALL_BOOKS.add(book);
		
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

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1l, "Wiliam", "Szekspir")))));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(2l, "Hanna", "Szekspir")))));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusz",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(3l, "Jan", "Parandowski")))));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(4l, "Edmund", "Niziurski")))));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(5l, "Zbigniew", "Nienacki")))));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta",
				new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(6l, "Aleksander", "Fredro")))));
	}
}
