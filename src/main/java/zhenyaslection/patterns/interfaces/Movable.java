package zhenyaslection.patterns.interfaces;

import differents.patterns.models.ColoredPoint;

public interface Movable {
    ColoredPoint move(ColoredPoint point, int dx, int dy);
    ColoredPoint moveRight(ColoredPoint point, int dx);
}
