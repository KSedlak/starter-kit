package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		ModelAndView mod= new ModelAndView("bookList");
		mod.addObject("saved",saved);
			
		return mod;
	}


	@RequestMapping(value="/{bookId}", method = RequestMethod.DELETE)///called by DELETE request on list
	public String bookDelete(@PathVariable Long bookId,@ModelAttribute("book") BookTo book, RedirectAttributes redirectAttrs) {
		
		 book = bookService.findBookById(bookId);
		 redirectAttrs.addFlashAttribute("book", book.getTitle());
		bookService.deleteBook(book);
        return "redirect:/book/confirmation/";
	}
	

	@RequestMapping(value="/confirmation", method = RequestMethod.DELETE)///called by DELETE request on list
	public ModelAndView bookConf(Model model, RedirectAttributes redirectAttrs,HttpServletRequest request) {
		String title = null;
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if (flashMap != null) {
				title=(String)flashMap.get("book");
		}
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
