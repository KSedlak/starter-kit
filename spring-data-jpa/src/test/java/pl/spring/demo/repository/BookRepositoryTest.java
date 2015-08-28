package pl.spring.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.entity.BookEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testShouldFindBookById() {
        // given
        final long bookId = 1;
        // when
        BookEntity bookEntity = bookRepository.findOne(bookId);
        // then
        assertNotNull(bookEntity);
        assertEquals("Pierwsza książka", bookEntity.getTitle());
    }

    @Test
    public void testShouldFindBooksByTitle() {
        // given
        final String bookTitle = "p";
        // when
        List<BookEntity> booksEntity = bookRepository.findBookByTitle(bookTitle);
        // then
        assertNotNull(booksEntity);
        assertFalse(booksEntity.isEmpty());
        assertEquals("Pierwsza książka", booksEntity.get(0).getTitle());
    }
    
    @Test
    public void testShouldAddBook() {
        // given
    		BookEntity book = new BookEntity(null,"Potop", "Henryk Sienkiewicz");
        // when
    		int sizeBefore=bookRepository.findAll().size();
    		bookRepository.save(book);
    		int sizeAfter=bookRepository.findAll().size();
        // then
        assertEquals(sizeBefore+1,sizeAfter);
    }
    
    @Test
    public void testShouldDeleteBook() {
        // given
    		BookEntity book = bookRepository.findAll().get(0);
        // when
    		int sizeBefore=bookRepository.findAll().size();
    		bookRepository.delete(book);
    		int sizeAfter=bookRepository.findAll().size();
        // then
        assertEquals(sizeBefore-1,sizeAfter);
    }
}
