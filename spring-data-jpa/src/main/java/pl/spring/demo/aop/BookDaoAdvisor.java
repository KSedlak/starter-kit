package pl.spring.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.exception.BookNullIdException;
import pl.spring.demo.to.IdAware;

import java.lang.reflect.Method;

@Component("bookDaoAdvisor")
@Aspect
public class BookDaoAdvisor{

	

	@Before("@annotation(pl.spring.demo.annotation.NullableId)")
	public boolean checkBeforeID(JoinPoint pjp) throws Throwable{
		 final MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
		 
		 Object[] objects=pjp.getArgs();
		 Object o =pjp.getTarget();


         checkId(objects[0], o);
         return true;
	 }
	


	@Before("@annotation(pl.spring.demo.annotation.AutoGenenareteID)")
	public void handlerEmptyID(JoinPoint p){
		
		 Object[] objects=p.getArgs();
		 Object o =p.getTarget();
		  checkId(objects[0], o);  //tez treba sprawdzic czy jest nullem
		  setId(objects[0], o);
		
	}
	
		
    private void checkId(Object o, Object classObject) {
    	
        if (o instanceof IdAware) {
        	if(((IdAware) o).getId() != null){
            throw new BookNotNullIdException();//id is set
            }
        	
        }
     
    }
   
    private void setId(Object objects,Object m){
       	if(objects instanceof BookEntity){
       	if(m instanceof BookDaoImpl){
       		BookEntity o = (BookEntity) objects;
    		BookDaoImpl helper = (BookDaoImpl) m;
    		long res=	helper.getSequence().nextValue(helper.findAll());
    		o.setId(res);

    	}
    }}



}
