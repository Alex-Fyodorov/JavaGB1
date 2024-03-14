package tictactoe.newiter;

public class Map {

    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final int MIN = 3;
    public static final int MAX = 10;

    public static int size;
    public static int dotsToWin;
    public static char[][] map;

    public static void init() {
        map = new char[size][size];
        completionMap();
    }

    /**
     * Первичное заполнение поля точками.
     */
    public static void completionMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
}
