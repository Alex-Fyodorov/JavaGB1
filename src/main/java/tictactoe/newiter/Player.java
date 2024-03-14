package tictactoe.newiter;

public class Player {

    /**
     * Игрок осуществляет свой ход, вводит координаты
     * своей фишки. Если координаты не подходят, процесс повторяется.
     */
    public static void playersMove() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты Х и Y.");
            System.out.println("X:");
            x = SCANNER.nextInt() - 1;
            System.out.println("Y:");
            y = SCANNER.nextInt() - 1;
        } while (isCoordValid(x, y, true));
        Map.map[y][x] = Map.DOT_X;
        printMap();
    }
}
