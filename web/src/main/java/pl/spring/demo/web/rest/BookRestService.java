package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;

@RestController
public class BookRestService {

	
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/books-by-title", method = RequestMethod.GET)
	public List<BookTo> findBooksByTitle(@RequestParam("titlePrefix") String titlePrefix) {
		return bookService.findBooksByTitle(titlePrefix);
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)//original method to JSON test
	public BookTo saveBook(@RequestBody BookTo book) {
		return bookService.saveBook(book);
	}

	@RequestMapping(value = { "/books", "/books/" }, method = RequestMethod.POST)///called by POST request on list
	public ModelAndView bookAdd(@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "author", required = true) String author) {
		
		BookTo book = new BookTo(null, title, author);
		bookService.saveBook(book);
		
		return new ModelAndView("bookList");
	}

	@RequestMapping(value = { "/books", "/books/" }, method = RequestMethod.PUT)///called by PUT request on list
	public ModelAndView bookEdit(@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "author", required = false) String author) {
		
		BookTo book = bookService.findBookById(id);
		book.setAuthors(author);
		book.setTitle(title);
		bookService.saveBook(book);
		
		return new ModelAndView("bookList");

	}

	@RequestMapping(value = { "/books", "/books/" }, method = RequestMethod.DELETE)///called by DELETE request on list
	public ModelAndView bookDelete(@RequestParam(value = "id", required = true) Long id) {
		
		BookTo book = bookService.findBookById(id);
		bookService.deleteBook(book);
		
		return new ModelAndView("bookDelete");

	}
}
