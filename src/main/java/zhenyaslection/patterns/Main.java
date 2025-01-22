package zhenyaslection.patterns;

import differents.patterns.interfaces.Movable;
import differents.patterns.models.ColoredPoint;
import differents.patterns.models.Point;
import differents.patterns.models.PointFactory;

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
