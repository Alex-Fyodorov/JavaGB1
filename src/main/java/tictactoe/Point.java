package tictactoe;

public class Point {
    private final int x;
    private final int y;
    private char body;

    public Point(int x, int y, char body) {
        this.x = x;
        this.y = y;
        this.body = body;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getBody() {
        return body;
    }

    public void setBody(char body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + body + ")";
    }
}
