package zhenyaslection.patterns.model.interfaces;

import zhenyaslection.patterns.model.ColoredPoint;

public interface Movable {
    ColoredPoint move(ColoredPoint point, int deltaX, int deltaY);

    ColoredPoint moveRight(ColoredPoint point, int deltaX);
}
