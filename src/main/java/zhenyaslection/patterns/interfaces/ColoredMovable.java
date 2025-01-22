package zhenyaslection.patterns.interfaces;

import differents.patterns.models.ColoredPoint;

public class ColoredMovable implements Movable {

    @Override
    public ColoredPoint move(ColoredPoint point, int dx, int dy) {
        return ColoredPoint.builder()
                .setX(point.getX() + dx)
                .setY(point.getY() + dy)
                .setColor("strong red")
                .setName(point.getName())
                .setMovable(point.isMovable())
                .build();
    }

    @Override
    public ColoredPoint moveRight(ColoredPoint point, int dx) {
        return move(point, dx, 0);
    }
}
