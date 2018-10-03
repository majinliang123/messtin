package org.messtin.framework.web.adapter;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.messtin.framework.core.annotation.Bean;
import org.messtin.framework.core.container.BeanContainer;
import org.messtin.framework.core.exception.IllegalBeanNameException;
import org.messtin.framework.core.util.StringUtil;
import org.messtin.framework.web.adapter.iface.Adapter;
import org.messtin.framework.web.annotation.ParamName;
import org.messtin.framework.web.entity.MvcEntity;
import org.messtin.framework.web.util.AnnotationUtil;
import org.messtin.framework.web.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * Build method params.
 * 1. If the param is {@link HttpSession}, {@link HttpServletRequest} or {@link HttpServletResponse},
 * will use them as its param.
 * 2. If the params has {@link Bean} annotation, will get it from {@link BeanContainer}.
 * 3. If the params has {@link ParamName}, will use {@link ParamName#value()} get the params from {@link HttpServletRequest#getParameter(String)}.
 * 4. If the params has no one above, will use the param's name to get param from {@link HttpServletRequest#getParameter(String)}.
 *
 * @author majinliang
 */
public class FormMealAdapt implements Adapter {
    private static final Logger logger = LogManager.getLogger(FormMealAdapt.class);

    @Override
    public Object[] adapt(MvcEntity mvcEntity, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IllegalBeanNameException {
        if (StringUtil.isNullOrEmpty(mvcEntity.getParameters())) {
            return new Object[0];
        }

        int paramsLen = mvcEntity.getParameters().length;
        Object[] params = new Object[paramsLen];
        for (int i = 0; i < paramsLen; i++) {
            Parameter param = mvcEntity.getParameters()[i];
            Class<?> paramType = param.getType();
            String paramName = param.getName();

            if (paramType.isAssignableFrom(HttpRequest.class)) {
                params[i] = request;
                continue;
            }
            if (paramType.isAssignableFrom(HttpResponse.class)) {
                params[i] = response;
                continue;
            }
            if (paramType.isAssignableFrom(HttpSession.class)) {
                params[i] = session;
                continue;
            }

            if (org.messtin.framework.core.util.AnnotationUtil.hasBeanAnnotation(param)) {
                Bean annotation = param.getAnnotation(Bean.class);
                String beanName = annotation.value();
                params[i] = BeanContainer.get(beanName, paramType);
                continue;
            }
            if (AnnotationUtil.hasParamNameAnnotation(param)) {
                ParamName paramNameAnno = param.getAnnotation(ParamName.class);
                String name = paramNameAnno.value();
                params[i] = RequestUtil.getParam(request, name);
                continue;
            }
            params[i] = RequestUtil.getParam(request, paramName);
        }
        logger.info("Adapt {} >> {}", mvcEntity, Arrays.toString(params));
        return params;
    }
}
