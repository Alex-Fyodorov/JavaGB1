package zhenyaslection.patterns;

import zhenyaslection.patterns.model.ColoredPoint;
import zhenyaslection.patterns.model.Point;
import zhenyaslection.patterns.model.PointFactory;
import zhenyaslection.patterns.model.interfaces.Movable;

public class Main {

    public static void main(String[] args) {
        // Неизменяемый объект без new.
        Point point = Point.startPoint();

        // Неизменяемый объект с любым набором полей.
        ColoredPoint coloredPoint = ColoredPoint.builder().setX(0).setY(0).setName("SinglePoint").build();

        ColoredPoint red = PointFactory.startAtRed();

        Movable movable = PointFactory.getMovable();

        red = movable.move(red, 10, 10);
    }
}
