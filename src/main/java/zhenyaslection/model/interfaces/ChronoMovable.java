package zhenyaslection.model.interfaces;

import zhenyaslection.model.ColoredPoint;

public class ChronoMovable implements Movable {
    private final Movable movable;

    public ChronoMovable(Movable movable) {
        this.movable = movable;
    }

    @Override
    public ColoredPoint move(ColoredPoint point, int deltaX, int deltaY) {
        long start = System.currentTimeMillis();
        ColoredPoint coloredPoint = movable.move(point, deltaX, deltaY);
        System.out.println("Execution time = " + (System.currentTimeMillis() - start));
        return coloredPoint;
    }

    @Override
    public ColoredPoint moveRight(ColoredPoint point, int deltaX) {
        return move(point, deltaX, 0);
    }
}
