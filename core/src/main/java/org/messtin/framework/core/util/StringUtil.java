package org.messtin.framework.core.util;

import java.util.Collection;
import java.util.Map;

/**
 * The util for string.
 *
 * @author majinliang
 */
public final class StringUtil {

    /**
     * Check if the object is null or empty
     *
     * @param obj the {@link Object} we need check.
     * @return if the object is null or empty
     */
    public static boolean isNullOrEmpty(Object obj) {
        try {
            if (obj == null) {
                return true;
            }
            if (obj instanceof CharSequence) {
                return ((CharSequence) obj).length() == 0;
            }
            if (obj instanceof Collection) {
                return ((Collection<?>) obj).isEmpty();
            }
            if (obj instanceof Map) {
                return ((Map<?, ?>) obj).isEmpty();
            }
            if (obj instanceof Object[]) {
                Object[] object = (Object[]) obj;
                if (object.length == 0) {
                    return true;
                }
                boolean empty = true;
                for (int i = 0; i < object.length; i++) {
                    if (!isNullOrEmpty(object[i])) {
                        empty = false;
                        break;
                    }
                }
                return empty;
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
