package zhenyaslection.reflectionAPI;

import java.lang.reflect.Modifier;

public class ReflectionApp {

    public static void main(String[] args) {
        Class clazz = "Java".getClass();

        Class strClass = String.class;

        Class intClass = Integer.class;

        Class smallIntClass = int.class;

        Class smallIntArrayClass = int[][].class;

        System.out.println(clazz.getName() + " " + clazz.getSimpleName());
        System.out.println(smallIntArrayClass.getName() + " " + smallIntArrayClass.getSimpleName());
        System.out.println(smallIntClass.getName() + " " + smallIntClass.getSimpleName());

        int modifiers = clazz.getModifiers();
        if (Modifier.isAbstract(modifiers)) {
            System.out.println(clazz.getName() + " is abstract.");
        }
        if (Modifier.isFinal(modifiers)) {
            System.out.println(clazz.getName() + " is final.");
        }
        if (Modifier.isPublic(modifiers)) {
            System.out.println(clazz.getName() + " is public.");
        }
        System.out.println(modifiers);

        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface);
        }

        Class superClass = clazz.getSuperclass();

    }
}
