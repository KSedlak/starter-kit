package pl.spring.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
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
public class BookDaoAdvisor implements MethodBeforeAdvice {

	
	 @Before("execution(* save(..))")
	 public void beforeWithoutArgs(JoinPoint pjp) throws Throwable{
		 final String methodName = pjp.getSignature().getName();
		 final MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
		 
		 Method method = methodSignature.getMethod();
		 Object[] objects=pjp.getArgs();
		 Object o =pjp.getTarget();

		 before(method,objects,o);
	 }

	
	public void before(Method method, Object[] objects, Object o) throws Throwable {
        if (hasAnnotation(method, o, NullableId.class)) {
            checkNotNullId(objects[0], o);
        }
  
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

 //   @AfterReturning("checkNotNullId()")
    private void setId(Object o,Object m){
       	if(m instanceof BookDaoImpl){
    		if(o instanceof BookEntity){//instanceof czy dobrze tak robic?
    			BookDaoImpl helper = (BookDaoImpl) m;
    		long res=	helper.getSequence().nextValue(helper.findAll());
    		BookEntity be= (BookEntity) o;
    		be.setId(res);
    		System.out.println(res);
    	}}
    }
    private boolean hasAnnotation (Method method, Object o, Class annotationClazz) throws NoSuchMethodException {
        boolean hasAnnotation = method.getAnnotation(annotationClazz) != null;

        if (!hasAnnotation && o != null) {
            hasAnnotation = o.getClass().getMethod(method.getName(), method.getParameterTypes()).getAnnotation(annotationClazz) != null;
        }
        return hasAnnotation;
    }
}
