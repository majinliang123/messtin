package org.messtin.framework.web.adapter.iface;

import org.messtin.framework.core.exception.IllegalBeanNameException;
import org.messtin.framework.web.entity.MvcEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The adapter to build parameters which need at method according to
 * {@link MvcEntity}, {@link HttpSession}, {@link HttpServletRequest}, {@link HttpServletResponse}.
 *
 * @author majinliang
 */
public interface Adapter {

    /**
     * @param mvcEntity
     * @param session
     * @param request
     * @param response
     * @return the params we need when we call the method.
     * @throws IllegalBeanNameException
     */
    Object[] adapt(MvcEntity mvcEntity, HttpSession session,
                   HttpServletRequest request, HttpServletResponse response) throws IllegalBeanNameException;
}
