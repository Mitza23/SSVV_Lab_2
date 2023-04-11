package ssvv.gogiacobgrebla.service;

import java.lang.reflect.Method;

public class MethodDump {
    public static void main(String[] args) {
        try {
            Class thisClass = ServiceTest.class;
            Method[] methods = thisClass.getDeclaredMethods();

            for (int i = 0; i < methods.length; i++) {
                System.out.println(methods[i].toString());
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
