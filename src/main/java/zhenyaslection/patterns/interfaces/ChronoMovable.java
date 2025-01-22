package zhenyaslection.patterns.interfaces;

import differents.patterns.models.ColoredPoint;

public class ChronoMovable implements Movable {

    // прокси
    private final Movable movable;

    public ChronoMovable(Movable movable) {
        this.movable = movable;
    }

    @Override
    public ColoredPoint move(ColoredPoint point, int dx, int dy) {
        long start = System.currentTimeMillis();
        ColoredPoint coloredPoint =  movable.move(point, dx, dy);
        System.out.println("Execution time = " + (System.currentTimeMillis() - start));
        return coloredPoint;
    }

    @Override
    public ColoredPoint moveRight(ColoredPoint point, int dx) {
        return move(point, dx, 0);
    }
}
