package pl.spring.demo.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;


import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private BookService bookService;
    

    @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
    public String bookList(Map<String, Object> params) {
        final List<BookTo> allBooks = bookService.findAllBooks();
        params.put("books", allBooks);
        return "bookList";
    }
    @RequestMapping(value = "/Confirmation", method = RequestMethod.GET)
    public String bookDelete(Map<String, Object> params,
    		@RequestParam(value="id", required=true) Long idBook) {
    	
    	BookTo book=bookService.findBookById(idBook);
		bookService.deleteBook(book);
		
    	String msg="Ksiazka o tytule "+book.getTitle()+" zostala usunieta.";
        params.put("bookDeleteMsg", msg);
        return "bookDelete";
    }

    
    
}
