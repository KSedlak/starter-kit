package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/book")
public class BookRestService {



	@Autowired
	private BookService bookService;
	@ResponseBody
	@RequestMapping(value = "/books-by-title", method = RequestMethod.GET)
	public List<BookTo> findBooksByTitle(@RequestParam("titlePrefix") String titlePrefix) {
		return bookService.findBooksByTitle(titlePrefix);
	}
	@ResponseBody
	@RequestMapping(value = "/bookJSON", method = RequestMethod.POST)//original method to JSON test
	public BookTo saveBook(@RequestBody BookTo book) {
		return bookService.saveBook(book);
	}

	@RequestMapping(method = RequestMethod.POST)///called by POST request on list
	public ModelAndView bookAdd(@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "author", required = true) String author	) {
		
		BookTo book = new BookTo(title, author);
		BookTo saved=bookService.saveBook(book);
		
		ModelAndView mod= new ModelAndView("bookList");
		mod.addObject("saved",saved);
			
		return mod;
	}

	@RequestMapping( method = RequestMethod.PUT)///called by PUT request on list
	public ModelAndView bookEdit(@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "author", required = false) String author) {
		
		BookTo book = bookService.findBookById(id);
		book.setAuthors(author);
		book.setTitle(title);
		BookTo saved=bookService.saveBook(book);
		
		ModelAndView mod= new ModelAndView("bookList");
		mod.addObject("saved",saved);
		
		return mod;

	}

	@RequestMapping(value="/{bookId}", method = RequestMethod.DELETE)///called by DELETE request on list
	public String bookDelete(@PathVariable Long bookId) {
		BookTo book = bookService.findBookById(bookId);

		bookService.deleteBook(book);


        return "redirect:/book/confirmation/"+book.getTitle();
	}
	

	@RequestMapping(value="/confirmation/{title}", method = RequestMethod.DELETE)///called by DELETE request on list
	public ModelAndView bookConf(@PathVariable String title) {
		
		ModelAndView mod= new ModelAndView("confirmation");
		String msg="Ksiazka o tytule "+title+" zostala usunieta.";
		mod.addObject("bookDeleteMsg", msg);

        return mod;
	}
    @RequestMapping(method = RequestMethod.GET) //book list
    public String bookList(Map<String, Object> params) {
        final List<BookTo> allBooks = bookService.findAllBooks();
        params.put("books", allBooks);
        return "bookList";
    }

}
