package zhenyaslection.reflectionAPI;

import java.lang.reflect.Field;

public class FieldReflectionApp {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class<Bike> bikeClass = Bike.class;

        Field[] fields = bikeClass.getDeclaredFields();
        System.out.println(fields.length);
        for (Field field : fields) {
            System.out.println("name = " + field.getName() + " type = " + field.getType().getName());
        }

        System.out.println("=========================================");

        Bike bike = new Bike();
        System.out.println(bike);

        Field yearField = bikeClass.getField("year");
        yearField.set(bike, 2021);

        Field field = bikeClass.getDeclaredField("model");
        field.setAccessible(true);
        field.set(bike, "Canyon");
        System.out.println(bike);

    }
}
