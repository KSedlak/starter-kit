package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.AutoGenenareteID;
import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import java.util.ArrayList;
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
		for (BookEntity book : ALL_BOOKS) {	
			if(book.getAuthors().toLowerCase().contains(author.toLowerCase())){
				result.add(book);
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

	public Set<BookEntity> getALL_BOOKS() {
		return ALL_BOOKS;
	}

	public Sequence getSequence() {
		return sequence;
	}

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", "Wiliam Szekspir"));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", "Hanna Ożogowska"));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", "Jan Parandowski"));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", "Edmund Niziurski"));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki"));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", "Aleksander Fredro"));
	}
}
