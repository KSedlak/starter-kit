package pl.spring.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.IdAware;

import java.lang.reflect.Method;

@Component("bookDaoAdvisor")
@Aspect
public class BookDaoAdvisor{

	

	@Before("@annotation(pl.spring.demo.annotation.NullableId)")
	public void beforeWithoutArgs(JoinPoint pjp) throws Throwable{
		 final MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
		 
		 Object[] objects=pjp.getArgs();
		 Object o =pjp.getTarget();


         checkNotNullId(objects[0], o);
		 
	 }

		
    private int checkNotNullId(Object o, Object classObject) {
    	
        if (o instanceof IdAware) {
        	if(((IdAware) o).getId() != null){
            throw new BookNotNullIdException();//id is set
            }
        		setId(o, classObject);
        }
        return 0;
    }
   
    private void setId(Object o,Object m){
       	if(m instanceof BookDaoImpl){
    		if(o instanceof BookEntity){
    			BookDaoImpl helper = (BookDaoImpl) m;
    		long res=	helper.getSequence().nextValue(helper.findAll());
    		BookEntity be= (BookEntity) o;
    		be.setId(res);
    		System.out.println(res);
    	}}
    }



}
