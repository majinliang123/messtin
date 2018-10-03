package org.messtin.framework.web.loader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.messtin.framework.core.container.BeanContainer;
import org.messtin.framework.core.loader.iface.MesstinLoader;
import org.messtin.framework.core.util.StringUtil;
import org.messtin.framework.web.annotation.Adapter;
import org.messtin.framework.web.annotation.RequestMapping;
import org.messtin.framework.web.config.Config;
import org.messtin.framework.web.container.MappingContainer;
import org.messtin.framework.web.entity.MvcEntity;
import org.messtin.framework.web.util.AnnotationUtil;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * The loader for controller.
 * It will load all controller into {@link MappingContainer}.
 * <p>
 * Will build into {@link MvcEntity} according to its bean, {@link Method}...
 * And then put into into {@link MappingContainer}.
 *
 * @author majinliang
 */
public class WebAppLoader implements MesstinLoader {

    private static final Logger logger = LogManager.getLogger(WebAppLoader.class);

    @Override
    public void load(Set<Class<?>> clazzs) throws IllegalAccessException, InstantiationException {
        for (Object bean : BeanContainer.getBeans()) {
            if (StringUtil.isNullOrEmpty(bean)) {
                continue;
            }

            Class<?> clazz = bean.getClass();
            if (!AnnotationUtil.hasRequestMappingAnnotation(clazz)) {
                continue;
            }

            RequestMapping clazzMappingAnno = clazz.getAnnotation(RequestMapping.class);
            Adapter clazzAdapterAnno = clazz.getAnnotation(Adapter.class);
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
                Adapter methodAdapterAnno = method.getAnnotation(Adapter.class);
                String methodPath = methodMappingAnno.value();
                if (StringUtil.isNullOrEmpty(methodPath)) {
                    continue;
                }
                String path = clazzPath + methodPath;
                Class<? extends org.messtin.framework.web.adapter.iface.Adapter> adapter = Config.DEFAULT_ADAPTER;

                if (!StringUtil.isNullOrEmpty(methodAdapterAnno)) {
                    adapter = methodAdapterAnno.value();
                } else if (!StringUtil.isNullOrEmpty(clazzAdapterAnno)) {
                    adapter = clazzAdapterAnno.value();
                }

                MvcEntity mvcEntity = new MvcEntity();
                mvcEntity.setBean(bean);
                mvcEntity.setPath(path);
                mvcEntity.setMethod(method);
                mvcEntity.setAdapter(adapter.newInstance());
                mvcEntity.setParameters(method.getParameters());

                logger.info("Add {} >> MappingContainer", mvcEntity);
                MappingContainer.add(mvcEntity);
            }
        }
    }
}
