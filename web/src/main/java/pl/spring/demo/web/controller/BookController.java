package pl.spring.demo.web.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.web.jetty.EmbeddedJetty;

import java.util.List;
import java.util.Map;

@Controller

public class BookController {
    @Autowired
    private BookService bookService;
    
    private static final Logger logger = LoggerFactory.getLogger(EmbeddedJetty.class);
    
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String bookList(Map<String, Object> params) {
        final List<BookTo> allBooks = bookService.findAllBooks();
        params.put("books", allBooks);
        return "bookList";
    }

    
    @RequestMapping(value="/books/delete", method=RequestMethod.GET)
    public ModelAndView deleteStrategyPage(
    		@RequestParam(value="id", required=true) Integer id, 
            @RequestParam(value="phase", required=true) String phase
            ) {
    	
    	
        ModelAndView modelAndView = null;
        BookTo book = bookService.findBookById(id.longValue());
    	logger.info("Strategy/delete-GET | id = " + id + " | phase = " + phase + " | " + book.getTitle());

     
        if (phase.equals("stage")) {
            modelAndView = new ModelAndView("bookConfirm");
         
            String message = "książka o tytule "+book.getTitle()+" została usunięta";


            modelAndView.addObject("message", message);
        }
     
 /*       if (phase.equals("confirm")) {
            modelAndView = new ModelAndView("redirect:/strategy/list");
            strategyService.deleteStrategy(id);
            String message = "Strategy " + boook.getId() + " was successfully deleted";
            modelAndView.addObject("message", message);
        }
     
        if (phase.equals("cancel")) {
            modelAndView = new ModelAndView("redirect:/strategy/list");
            String message = "Strategy delete was cancelled.";
            modelAndView.addObject("message", message);
        }*/
     
        return modelAndView;
    }
}
