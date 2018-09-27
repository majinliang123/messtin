package app.aspect;

import org.messtin.framework.core.annotation.Aspect;
import org.messtin.framework.core.aspect.AbstractAspect;

@Aspect(clazz = "app.bean.House", method = "toString")
public class HouseAspect extends AbstractAspect {

    @Override
    public void beforeMethod(){
        System.out.println("HouseAspect beforeMethod.");
    }

    @Override
    public void beforeMethodReturn(){
        System.out.println("HouseAspect beforeMethodReturn.");
    }

    @Override
    public Object afterMethod(Object o){
        System.out.println("HouseAspect afterMethod.");
        return o;
    }
}
