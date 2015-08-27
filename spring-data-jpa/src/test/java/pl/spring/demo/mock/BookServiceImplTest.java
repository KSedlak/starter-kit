package pl.spring.demo.mock;
/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */


import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.dao.mapper.BookMapper;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;
import static org.junit.Assert.assertEquals;

/**
 * TODO The class BookServiceImplTest is supposed to be documented...
 *
 * @author AOTRZONS
 */
public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	@Autowired
	private BookDao bookDao;

	private ArgumentMatcher<BookEntity> matchNullIdBookEnities;

	@Before
	public void setUpt() {
		MockitoAnnotations.initMocks(this);
		initNullIdMatcher();
	}

	private void initNullIdMatcher() {
		matchNullIdBookEnities = new ArgumentMatcher<BookEntity>() {
			@Override
			public boolean matches(final Object argument) {
				assertEquals(null, ((BookEntity) argument).getId());
				return true;
			}
		};
	}

	@Test
    public void testShouldSaveBook() {
        // given
        BookEntity book =new BookEntity(null,"title","Zbigniew Nienacki");
        BookEntity bookWithID=new BookEntity(1L,"title","Zbigniew Nienacki");       
        BookTo in=BookMapper.mappedBookEntity(book);

        // when
        Mockito.when(bookDao.save(Mockito.argThat(matchNullIdBookEnities))).thenReturn(bookWithID);
        BookTo result = bookService.saveBook(in);
        
        // then
        Mockito.verify(bookDao).save(Mockito.argThat(matchNullIdBookEnities));
        assertEquals(1L, result.getId().longValue());
    }

}
