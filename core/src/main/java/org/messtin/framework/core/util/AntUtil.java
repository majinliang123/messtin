package org.messtin.framework.core.util;

import org.messtin.framework.core.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * The util for ant match.
 * The ant match is only for temp use, need improve.
 *
 * @author majinliang
 */
public final class AntUtil {

    /**
     * @param path    the {@link String} we want to check.
     * @param pattern the {@link String} of regex.
     * @return if the original matches the target regex.
     */
    public static boolean isAntMatch(String path, String pattern) {
        return match(path, pattern, true);
    }

    private static boolean match(String path, String pattern, boolean fullMatch) {
        if (path == null || pattern == null) {
            return false;
        }
        if (path.startsWith(Config.DEFAULT_PATH_SEPARATOR) != pattern.startsWith(Config.DEFAULT_PATH_SEPARATOR)) {
            return false;
        }
        String[] pathTokens = tokenizeToStringArray(path, Config.DEFAULT_PATH_SEPARATOR);
        String[] patternTokens = tokenizeToStringArray(pattern, Config.DEFAULT_PATH_SEPARATOR);

        int len = pathTokens.length > patternTokens.length ? pathTokens.length : patternTokens.length;

        for (int i = 0; i < len; i++) {
            if (pathTokens.length > i && patternTokens.length > i) {
                if (!pathTokens[i].equals(patternTokens[i])) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static String[] tokenizeToStringArray(String str, String delimiters) {
        List<String> tokenList = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(str, delimiters);
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            token = token.trim();
            tokenList.add(token);
        }
        String[] tokenArray = new String[tokenList.size()];
        return tokenList.toArray(tokenArray);
    }
}
