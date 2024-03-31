package javatwo.lec2.common;

import java.awt.*;

public abstract class Sprite implements Interactable {
    protected float x;
    protected float y;
    protected float halfWidth;
    protected float halfHeight;

    public float getLeft() {
        return x - halfWidth;
    }
    public float getRight() {
        return x + halfWidth;
    }
    public float getTop() {
        return y - halfHeight;
    }
    public float getBottom() {
        return y + halfHeight;
    }
    public float getWidth() {
        return 2f * halfWidth;
    }
    public float getHeight() {
        return 2f * halfHeight;
    }

    public void setLeft(float left) {
        x = left + halfWidth;
    }
    public void setRight(float right) {
        x = right - halfWidth;
    }
    public void setTop(float top) {
        y = top + halfHeight;
    }
    public void setBottom(float bottom) {
        y = bottom - halfHeight;
    }
    public void update(MainCanvas canvas, float deltaTime) {}
    public void render(MainCanvas canvas, Graphics g) {}
}
