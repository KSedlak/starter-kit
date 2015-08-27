package pl.spring.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.IdAware;

@Aspect
@Component("bookDaoAdvisor")
public class BookDaoAdvisor {

	@Before("@annotation(pl.spring.demo.annotation.NullableId)")
	public void checkBeforeID(JoinPoint pjp){
		
		checkId( pjp.getArgs()[0]);

	}

	@Before("@annotation(pl.spring.demo.annotation.AutoGenenareteID)")
	public void handlerEmptyID(JoinPoint p) {

		Object[] objects = p.getArgs();
		Object o = p.getTarget();

		checkId(objects[0]); //check 

		setId(objects[0], o); // if checkID() not throw exception setID

	}

	private void checkId(Object o) {

		if (o instanceof IdAware) {
			if (((IdAware) o).getId() != null) {
				throw new BookNotNullIdException();// id is set
			}
		}
	}

	private void setId(Object argument, Object classObject) {
		if (argument instanceof BookEntity) {	
			if (classObject instanceof BookDaoImpl) {
				BookEntity o = (BookEntity) argument;
				BookDaoImpl helper = (BookDaoImpl) classObject;
				long res = helper.getSequence().nextValue(helper.findAll());//find next ID
				o.setId(res);
			}
		}
	}

}
