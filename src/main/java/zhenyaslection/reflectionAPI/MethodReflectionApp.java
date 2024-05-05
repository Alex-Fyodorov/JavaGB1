package zhenyaslection.reflectionAPI;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodReflectionApp {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<Bike> bikeClass = Bike.class;

        Method[] methods = bikeClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getAnnotation(SimpleAnnotation.class) != null) {
                System.out.println("method " + method.getName() + " with annotation.");
            }
            System.out.println("name = " + method.getName() + " return type = " +
                    method.getReturnType().getSimpleName() + " parameters " +
                    Arrays.toString(method.getParameterTypes()));
        }

        System.out.println("=========================================");

        Method method = bikeClass.getDeclaredMethod("setYearAndModel", int.class, String.class);
        SimpleAnnotation annotation = method.getAnnotation(SimpleAnnotation.class);
        System.out.println(annotation.name() + " " + annotation.value());
        Bike bike = new Bike();
        System.out.println(bike);
        method.setAccessible(true);
        method.invoke(bike, 2021, "Pinarello");
        System.out.println(bike);


    }
}
