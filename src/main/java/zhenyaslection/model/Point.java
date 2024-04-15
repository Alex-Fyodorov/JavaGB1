package zhenyaslection.model;

import lombok.Getter;

@Getter
public class Point {
    private final int x;
    private final int y;

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point startPoint() {
        return new Point(0, 0);
    }
}
