package org.messtin.framework.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.messtin.framework.core.config.Constants;
import org.messtin.framework.core.exception.IllegalBeanNameException;
import org.messtin.framework.core.util.ClassUtil;
import org.messtin.framework.web.container.MappingContainer;
import org.messtin.framework.web.entity.MvcEntity;
import org.nico.noson.Noson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

/**
 * The dispatcher servlet.
 * The core servlet. Every request will come here and be convert here.
 *
 * @author majinliang
 */
public class DispatServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(DispatServlet.class);

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        logger.info("Handling request from: ", request.getRemoteHost());

        request.setCharacterEncoding(Constants.UTF_8);
        response.setCharacterEncoding(Constants.UTF_8);

        String path = request.getServletPath();
        MvcEntity mvcEntity = MappingContainer.get(path);

        Object[] params = null;

        try {
            params = mvcEntity.getAdapter().adapt(mvcEntity, request.getSession(), request, response);
            Object result = mvcEntity.getMethod().invoke(mvcEntity.getBean(), params);
            if (result instanceof String || ClassUtil.isPrimitive(request)) {
                response.getWriter().write(String.valueOf(result));
            } else {
                response.setContentType("application/Json");
                String jsonResult = Noson.reversal(result);
                response.getWriter().write(jsonResult);
            }
            logger.info("Success handle request from: ", request.getRemoteHost());
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBeanNameException e) {
            e.printStackTrace();
        }
    }

}
