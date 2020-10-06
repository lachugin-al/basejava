package webapp;

import webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field declaredField = r.getClass().getDeclaredFields()[0];
        // переопределяем private final поле uuid в классе Resume !!! но так делать не стоит :))))
        declaredField.setAccessible(true);
        System.out.println(declaredField.getName());
        declaredField.get(r);
        System.out.println(r);
        // засетили значение через рефлекшн
        declaredField.set(r, "new_uuid");
        System.out.println(r);

        Class<? extends Resume> resumeClass = r.getClass();
        Field field = resumeClass.getDeclaredFields()[0];
        Method method = resumeClass.getMethod("toString");
        Object result = method.invoke(r);
        System.out.println(result);
    }
}
