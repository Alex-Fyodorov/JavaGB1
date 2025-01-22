package tictactoe.models;

import java.util.ArrayList;
import java.util.List;

public class Raw {
    private final List<Point> pointList;
    private boolean toLookX;
    private boolean toLookO;

    public Raw(int length) {
        this.pointList = new ArrayList<>();
        this.toLookX = true;
        this.toLookO = true;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public boolean isToLookX() {
        return toLookX;
    }

    public void setToLookX(boolean toLookX) {
        this.toLookX = toLookX;
    }

    public boolean isToLookO() {
        return toLookO;
    }

    public void setToLookO(boolean toLookO) {
        this.toLookO = toLookO;
    }

    public void putChar(int index, char dot) {
        pointList.get(index).setBody(dot);
    }
}
