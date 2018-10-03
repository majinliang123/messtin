package org.messtin.framework.web.loader;

import org.messtin.framework.core.config.Constants;
import org.messtin.framework.core.container.BeanContainer;
import org.messtin.framework.core.loader.iface.MesstinLoader;
import org.messtin.framework.core.util.StringUtil;
import org.messtin.framework.web.annotation.RequestMapping;
import org.messtin.framework.web.container.MappingContainer;
import org.messtin.framework.web.entity.MvcEntity;
import org.messtin.framework.web.util.AnnotationUtil;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * The loader for controller.
 * It will load all controller into {@link MappingContainer}
 *
 * @author majinliang
 */
public class WebAppLoader implements MesstinLoader {
    public void load(Set<Class<?>> clazzs) {
        for (Object bean : BeanContainer.getBeans()) {
            if (StringUtil.isNullOrEmpty(bean)) {
                continue;
            }

            Class<?> clazz = bean.getClass();
            if (!AnnotationUtil.hasRequestMappingAnnotation(clazz)) {
                continue;
            }

            RequestMapping clazzMappingAnno = clazz.getAnnotation(RequestMapping.class);
            String clazzPath = clazzMappingAnno.value();
            if (StringUtil.isNullOrEmpty(clazzPath)) {
                continue;
            }
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (!AnnotationUtil.hasRequestMappingAnnotation(method)) {
                    continue;
                }
                RequestMapping methodMappingAnno = method.getAnnotation(RequestMapping.class);
                String methodPath = methodMappingAnno.value();
                if (StringUtil.isNullOrEmpty(methodPath)) {
                    continue;
                }
                String path = clazzPath + Constants.SLASH + methodPath;
                MvcEntity mvcEntity = new MvcEntity();
                mvcEntity.setBean(bean);
                mvcEntity.setPath(path);
                mvcEntity.setMethod(method);
                MappingContainer.add(mvcEntity);
            }

        }
    }
}
