package javathree.hw2;

import java.lang.reflect.Field;
import java.time.*;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class RandomDateProcessor {

    public static void processRandomDate(Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(RandomDate.class)) {
                RandomDate annotation = field.getAnnotation(RandomDate.class);
                String fieldClassName = field.getType().getSimpleName();
                try {
                    switch (fieldClassName) {
                        case "Date" -> {
                            field.setAccessible(true);
                            field.set(object, getDate(annotation));
                        }
                        case "Instant" -> {
                            field.setAccessible(true);
                            field.set(object, getInstant(annotation));
                        }
                        case "LocalDate", "LocalDateTime" -> {
                            field.setAccessible(true);
                            field.set(object, getLocalDate(annotation, field.getType()));
                        }
                    }
                } catch (IllegalAccessException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    private static Date getDate(RandomDate annotation) {
        return new Date(ThreadLocalRandom.current().nextLong(annotation.min(), annotation.max()));
    }

    private static Instant getInstant(RandomDate annotation) {
        return getDate(annotation).toInstant();
    }

    private static <T> T getLocalDate(RandomDate annotation, Class<T> clazz) {
        ZonedDateTime moscow = getInstant(annotation).atZone(ZoneId.of("Europe/Moscow"));
        switch (clazz.getSimpleName()) {
            case "LocalDate" -> {
                return (T) moscow.toLocalDate();
            }
            case "LocalDateTime" -> {
                return (T) moscow.toLocalDateTime();
            }
            default -> {
                return null;
            }
        }
    }
}
