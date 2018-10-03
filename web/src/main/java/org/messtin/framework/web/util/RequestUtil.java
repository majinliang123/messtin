package org.messtin.framework.web.util;

import javax.servlet.http.HttpServletRequest;

/**
 * The util for request.
 *
 * @author majinliang
 */
public final class RequestUtil {

    /**
     * Get param value from request according to param name.
     *
     * @param request
     * @param name
     * @return
     */
    public static Object getParam(HttpServletRequest request, String name){
        return request.getParameter(name);
    }
}
