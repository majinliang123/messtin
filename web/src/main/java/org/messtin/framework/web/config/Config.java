package org.messtin.framework.web.config;

import org.messtin.framework.web.adapter.FormMealAdapt;
import org.messtin.framework.web.adapter.iface.Adapter;

/**
 * The default config.
 *
 * @author majinliang
 */
public class Config {

    public static Class<? extends Adapter> DEFAULT_ADAPTER = FormMealAdapt.class;
}
