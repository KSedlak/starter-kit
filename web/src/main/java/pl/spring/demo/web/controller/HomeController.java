package pl.spring.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@Autowired
	private BookService bookService;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map<String, Object> parameters) {
    	 final List<BookTo> allBooks = bookService.findAllBooks();
         parameters.put("books", allBooks);
        return "bookList";
    }
    
    
    // Error page
    @RequestMapping("/error")
    public String error(HttpServletRequest request, Model model) {
      model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
      Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
      String errorMessage = null;
      if (throwable != null) {
        errorMessage = throwable.getMessage();
      }
      model.addAttribute("errorMessage", errorMessage);
      return "error";
    }
    
    // Login form
    @RequestMapping({"/loginPage","login"})
    public String login() {
      return "login";
    }
    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
      model.addAttribute("loginError", true);
      return "login";
    }
   
    @RequestMapping("/logout-success")
    public String logoutSuccess() {

      return "redirect:/";
    }
   
   
}

