package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;
import java.util.List;
import java.util.Map;


@Controller
@Secured("ROLE_ADMIN")
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
	@RequestMapping(method = RequestMethod.POST, produces={"application/xml", "application/json"})//original method to JSON test
	public BookTo saveBook(@RequestBody BookTo book) {
		return bookService.saveBook(book);
	}

	@RequestMapping(method = RequestMethod.POST)///called by POST request on list
	public ModelAndView bookAdd(@RequestBody BookTo book) {
		
		BookTo saved=bookService.saveBook(book);
		
		ModelAndView mod= new ModelAndView("bookListAdminView");
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
    public String bookListAdminView(Map<String, Object> params) {
        final List<BookTo> allBooks = bookService.findAllBooks();
        params.put("books", allBooks);
        return "bookListAdminView";
    }

}
