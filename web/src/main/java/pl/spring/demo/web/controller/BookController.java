package pl.spring.demo.web.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.web.jetty.EmbeddedJetty;

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
    @RequestMapping(value = "/d", method = RequestMethod.GET)
    public String bookDelete(Map<String, Object> params,
    		@RequestParam(value="id", required=true) Long idBook) {
    	BookTo book=bookService.findBookById(idBook);
    	String msg="Ksiazka o tytule "+book.getTitle()+" zostala usunieta.";
        params.put("bookDeleteMsg", msg);
        return "bookDelete";
    }

    @RequestMapping(value = {"/",""}, method = RequestMethod.POST)
    public String bookLadd() {
       BookTo book =new BookTo(4L, "Potop", "Henryk Sienkiewicz");
    	bookService.saveBook(book);
        return "bookList";
    }
    

    @RequestMapping(value = {"/",""}, method = RequestMethod.PUT)
    public String bookLEdit() {
    	Long id=4L;
     	BookTo book=bookService.findBookById(id);
     	book.setTitle("Krzyzacy");
    	bookService.saveBook(book);
        return "bookList";
    }
    
    @RequestMapping(value = {"/",""}, method = RequestMethod.DELETE)
    public String bookLDelete2() {
    	Long id=4L;
     	BookTo book=bookService.findBookById(id);
     	EmbeddedJetty.getLogger().debug("size before: "+bookService.findAllBooks().size());
     	bookService.deleteBook(book);
     	EmbeddedJetty.getLogger().debug("size after: "+bookService.findAllBooks().size());
        return "bookList";
    }
    
    
}
