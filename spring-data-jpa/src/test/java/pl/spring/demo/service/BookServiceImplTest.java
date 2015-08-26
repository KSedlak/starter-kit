package pl.spring.demo.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring/spring-context.xml"})
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testShouldFindAllBooks() {
        // when
        List<BookTo> allBooks = bookService.findAllBooks();
        // then
        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertEquals(6, allBooks.size());
    }

    @Test
    public void testShouldFindAllBooksByTitle() {
        // given
        final String title = "Opium w rosole";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
    }
    
    @Test
    public void testShouldFindAllBooksByTitlePrefix() {
        // given
        final String title = "opium";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
    }
    
    @Test
    public void testShouldFindAllBooksByTitleNotFound() {
        // given
        final String title = "testowe zapytanie";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertTrue(booksByTitle.isEmpty());
    }
    
    @Test
    public void testShouldFindAllBooksByAuthor() {
        // given
        final String auth = "Zbigniew Nienacki";
        // when
        List<BookTo> booksByAuthor= bookService.findBooksByAuthor(auth);
        // then
        assertNotNull(booksByAuthor);
        assertFalse(booksByAuthor.isEmpty());
    }

    @Test
    public void testShouldFindAllBooksByAuthorNotFound() {
        // given
        final String auth = "Zbigniew Abacki";
        // when
        List<BookTo> booksByAuthor= bookService.findBooksByAuthor(auth);
        // then
        assertNotNull(booksByAuthor);
        assertTrue(booksByAuthor.isEmpty());
    }
    
    @Test(expected = BookNotNullIdException.class)
    public void testShouldThrowBookNotNullIdException() {
        // given
        final BookTo bookToSave = new BookTo();
        bookToSave.setId(22L);
        // when
        bookService.saveBook(bookToSave);
        // then
        fail("test should throw BookNotNullIdException");
    }
 
    
}
