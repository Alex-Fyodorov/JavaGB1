package zhenyaslection.patterns.model.interfaces;

import zhenyaslection.patterns.model.ColoredPoint;

public class CompositeMovable implements Movable {
    private final Movable uncolored;
    private final Movable colored;

    public CompositeMovable(Movable uncolored, Movable colored) {
        this.uncolored = uncolored;
        this.colored = colored;
    }

    @Override
    public ColoredPoint move(ColoredPoint point, int deltaX, int deltaY) {
        if (point.getColor() == null || point.getColor().equals("black")) {
            return uncolored.move(point, deltaX, deltaY);
        } else {
            return colored.move(point, deltaX, deltaY);
        }
    }

    @Override
    public ColoredPoint moveRight(ColoredPoint point, int deltaX) {
        return move(point, deltaX, 0);
    }
}
