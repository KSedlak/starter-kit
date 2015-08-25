package pl.spring.demo.mock;
/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.dao.mapper.BookMapper;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * TODO The class BookServiceImplTest is supposed to be documented...
 *
 * @author AOTRZONS
 */
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookDao bookDao;

    @Before
    public void setUpt() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShouldSaveBook() {
        // given
        BookEntity book = new BookEntity(null,"Romeo i Julia",new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1l, "Wiliam","Szekspir"))));
        Mockito.when(bookDao.save(book)).thenReturn(new BookEntity(1L,"Romeo i Julia",new ArrayList<AuthorTo>(Arrays.asList(new AuthorTo(1l, "Wiliam","Szekspir")))));
        // when

        BookTo result = bookService.saveBook(BookMapper.mappedBookEntity(book));
        // then
        Mockito.verify(bookDao).save(book);
        assertEquals(1L, result.getId().longValue());
    }
}
