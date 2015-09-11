package pl.spring.demo.web.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.spring.demo.service.AuthorService;
import pl.spring.demo.to.AuthorTo;


@RestController
@RequestMapping(value="/authors")
public class AuthorRestService {

    @Autowired
    private AuthorService authorService;



    @RequestMapping(value = "/author", method = RequestMethod.POST)
    public AuthorTo saveBook(@RequestBody AuthorTo author) {
        return authorService.saveAuthor(author);
    }


    @RequestMapping(value = "/authors-list", method = RequestMethod.GET)
    public List<AuthorTo> findAll() {
        return authorService.findAll();
    }

}
