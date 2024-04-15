package zhenyaslection.model.interfaces;

import zhenyaslection.model.ColoredPoint;

public class DefaultMovable implements Movable {

    @Override
    public ColoredPoint move(ColoredPoint point, int deltaX, int deltaY) {
        return ColoredPoint.builder()
                .setX(point.getX() + deltaX)
                .setX(point.getY() + deltaY)
                .setColor(point.getColor())
                .setName(point.getName())
                .setMovable(point.isMovable())
                .build();
    }

    @Override
    public ColoredPoint moveRight(ColoredPoint point, int deltaX) {
        return move(point, deltaX, 0);
    }
}
