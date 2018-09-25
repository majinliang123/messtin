package org.messtin.framework.core.util;

import org.messtin.framework.core.config.Constants;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

/**
 * The util for class.
 *
 * @author majinliang
 */
public final class ClassUtil {

    /**
     * Get all the classes in the package.
     *
     * @param packet the package name
     * @return all the class in the package
     * @throws IOException the exception when read files failed
     */
    public static Set<Class<?>> getClasses(String packet) throws IOException, ClassNotFoundException {
        /**
         * will search all files recursively.
         */
        Set<Class<?>> clazzs = new HashSet<>();

        String path = packet.replace(Constants.DOT, Constants.SLASH);
        URL dir = Thread.currentThread().getContextClassLoader().getResource(path);
        String protocol = dir.getProtocol();
        if (Constants.FILE.equals(protocol)) {
            path = URLDecoder.decode(dir.getFile(), "UTF-8");
            findAndAddClazzesByPath(packet, path, clazzs);
        }
        return clazzs;
    }

    /**
     * Recursively search all class in this dir and add to {@code clazzs}.
     *
     * @param path   the path will be searched
     * @param clazzs search result will add into clazzs
     */
    public static void findAndAddClazzesByPath(String packet, String path, Set<Class<?>> clazzs)
            throws ClassNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            /**
             * delete .class from name, only left class name.
             */
            String clazzName = file.getName().substring(0, file.getName().length() - 6);
            clazzs.add(
                    Thread.currentThread().getContextClassLoader().loadClass(
                            packet + Constants.DOT + clazzName)
            );
        } else {
            File[] clazzFiles = file.listFiles(clazz -> clazz.isFile());
            File[] clazzDirs = file.listFiles(clazz -> clazz.isDirectory());
            for (File clazzFile : clazzFiles) {
                String clazzName = clazzFile.getName().substring(0, clazzFile.getName().length() - 6);
                clazzs.add(
                        Thread.currentThread().getContextClassLoader().loadClass(
                                packet + Constants.DOT + clazzName)
                );
            }
            for (File clazzDir : clazzDirs) {
                findAndAddClazzesByPath(packet + Constants.DOT + clazzDir.getName(),
                        clazzDir.getPath(), clazzs);
            }
        }
    }

    /**
     * Check if the clazz is an abstract class
     *
     * @param clazz the class need check
     * @return if the clazz is an abstract class.
     */
    public static boolean isAbstractClass(Class<?> clazz) {
        int modifier = clazz.getModifiers();
        return Modifier.isAbstract(modifier);
    }

    /**
     * Check if the clazz is an interface
     *
     * @param clazz the class need check
     * @return if the clazz is an interface
     */
    public static boolean isInterface(Class<?> clazz) {
        int modifier = clazz.getModifiers();
        return Modifier.isInterface(modifier);
    }

    /**
     * Get the full name of the method.
     * classname.methodname(paramType paramName)
     *
     * @param method
     * @return
     */
    public static String getMethodUnionKey(Method method) {
        Class<?> clazz = method.getDeclaringClass();
        String clazzName = clazz.getName();
        String methodName = method.getName();
        Parameter[] parameters = method.getParameters();
        List<String> paramList = new ArrayList<>();
        for (Parameter parameter : parameters) {
            paramList.add(parameter.getType() + " " + parameter.getName());
        }
        return clazzName + "." + methodName + "(" + String.join(",", paramList) + ")";
    }
}
