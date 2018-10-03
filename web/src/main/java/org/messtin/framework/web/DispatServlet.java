package org.messtin.framework.web;

import org.messtin.framework.core.config.Constants;
import org.messtin.framework.web.container.MappingContainer;
import org.messtin.framework.web.entity.MvcEntity;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

/**
 * The dispatcher servlet.
 *
 * @author majinliang
 */
public class DispatServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        request.setCharacterEncoding(Constants.UTF_8);
        response.setCharacterEncoding(Constants.UTF_8);

        String path = request.getServletPath();
        MvcEntity mvcEntity = MappingContainer.get(path);

        Object[] params = mvcEntity.getAdapter().adapt();
        try {
            Object result = mvcEntity.getMethod().invoke(mvcEntity.getBean(), params);
            response.setContentType("application/Json");
            response.getWriter().write("{a:1}");
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
