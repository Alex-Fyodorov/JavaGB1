package zhenyaslection.patterns;

import zhenyaslection.patterns.interfaces.Movable;
import zhenyaslection.patterns.models.ColoredPoint;
import zhenyaslection.patterns.models.Point;
import zhenyaslection.patterns.models.PointFactory;

public class Main {

    public static void main(String[] args) {
        // статическая инициализация
        Point point = Point.startPoint();

        // билдер или фабричный метод
        ColoredPoint coloredPoint = ColoredPoint.builder()
                .setX(1)
                .setY(1)
                .setName("qwerty")
                .setColor("black")
                .build();

        // фабрика
        ColoredPoint red = PointFactory.startAtRed();

        // интерфейс
        Movable movable = PointFactory.getMovable();
        red = movable.move(red, 10, 10);
    }
}
