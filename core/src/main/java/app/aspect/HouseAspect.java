package app.aspect;

import org.messtin.framework.core.annotation.Aspect;
import org.messtin.framework.core.aspect.AbstractAspect;

@Aspect(clazz = "*House*", method = "*")
public class HouseAspect extends AbstractAspect {

    @Override
    public void beforeMethod(){
        System.out.println("HouseAspect Log.");
    }
}
