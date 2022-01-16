package com.zyh.javatraining.weekone.mustone;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Hello.xlass Class Loader
 *
 * @author ZYH
 */
public class XlassLoader extends ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassLoader xlassLoader = new XlassLoader();
        Class xlassClass = xlassLoader.loadClass("Hello");
        Object object = xlassClass.getDeclaredConstructor().newInstance();
        for (Method method :xlassClass.getDeclaredMethods()) {
            method.invoke(object);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(name + ".xlass")){
            byte[] inputBytes = new byte[inputStream.available()];
            inputStream.read(inputBytes);
            byte[] newBytes = new byte[inputBytes.length];
            for (int i = 0; i < inputBytes.length; i++) {
                newBytes[i] = (byte)(255-inputBytes[i]);
            }
            return defineClass(name, newBytes, 0, newBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Class" + name + "is not found!");
        }
    }
}
