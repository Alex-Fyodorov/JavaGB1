package zhenyaslection.reflectionAPI;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ConstructorReflectionApp {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Class<Bike> bikeClass = Bike.class;

        Constructor[] constructors = bikeClass.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(Arrays.toString(constructor.getParameterTypes()));
        }

        Constructor constructor = bikeClass.getDeclaredConstructor(String.class, String.class, int.class);
        Bike bike = (Bike) constructor.newInstance("Canyon", "12345", 2018);

        System.out.println(bike);
    }
}
