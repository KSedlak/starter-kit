package pl.spring.demo.aop;




import org.springframework.aop.MethodBeforeAdvice;


import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.IdAware;

import java.lang.reflect.Method;



public class BookDaoAdvisor implements MethodBeforeAdvice {

    @Override

    public void before(Method method, Object[] objects, Object o) throws Throwable {
  
        if (hasAnnotation(method, o, NullableId.class)) {
            checkNotNullId(objects[0], o);
        }
    }

    private void checkNotNullId(Object o, Object classObject) {
    	
        if (o instanceof IdAware) {
        	if(((IdAware) o).getId() != null){
            throw new BookNotNullIdException();//id is set
            }

        	setId(o, classObject);
        
        }
    }

    private void setId(Object o,Object m){
       	if(m instanceof BookDaoImpl){
    		if(o instanceof BookEntity){//instanceof czy dobrze tak robic?
    			BookDaoImpl helper = (BookDaoImpl) m;
    		long res=	helper.getSequence().nextValue(helper.findAll());
    		BookEntity be= (BookEntity) o;
    		be.setId(res);
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
