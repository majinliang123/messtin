package org.messtin.framework.core.loader;

import org.messtin.framework.core.annotation.Autowired;
import org.messtin.framework.core.container.BeanContainer;
import org.messtin.framework.core.exception.IllegalBeanNameException;
import org.messtin.framework.core.loader.iface.MesstinLoader;
import org.messtin.framework.core.util.AnnotationUtil;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * The loader uses to set value to object field which has {@link org.messtin.framework.core.annotation.Autowired}.
 * But the class which contains this field <strong>must</strong> is a bean.
 * The final result will update object at {@link org.messtin.framework.core.container.BeanContainer}
 * If the class doesn't have {@link org.messtin.framework.core.annotation.Bean},
 * it will not be processed.
 *
 * @author majinliang
 */
public class FieldLoader implements MesstinLoader {

    @Override
    public void load(Set<Class<?>> clazzs) throws IllegalBeanNameException, IllegalAccessException {
        for (Class<?> clazz : clazzs) {
            if (!AnnotationUtil.hasBeanAnnotation(clazz)) {
                continue;
            }
            Field[] fields = clazz.getDeclaredFields();
            Object bean = BeanContainer.get(clazz);
            for (Field field : fields) {
                if (!AnnotationUtil.hasAutowiredAnnotation(field)) {
                    continue;
                }
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }

                Autowired autowired = field.getAnnotation(Autowired.class);
                field.set(bean, BeanContainer.get(autowired.value(), field.getType()));
            }
        }

    }
}
