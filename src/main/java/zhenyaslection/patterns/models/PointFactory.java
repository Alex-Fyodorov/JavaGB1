package zhenyaslection.patterns.models;

import zhenyaslection.patterns.interfaces.*;

public class PointFactory {

    // фабрика
    public static ColoredPoint startAtRed() {
        return ColoredPoint.builder()
                .setX(0)
                .setY(0)
                .setColor("red")
                .build();
    }

    public static Movable getMovable() {
        //return new DefaultMovable();
        //return new ColoredMovable();

        // прокси
        //return new ChronoMovable(new ColoredMovable());

        // композиция
        return new ChronoMovable(new CompositeMovable(
                new DefaultMovable(), new ColoredMovable()));
    }
}
