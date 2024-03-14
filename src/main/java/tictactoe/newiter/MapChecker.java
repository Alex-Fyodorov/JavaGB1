package tictactoe.newiter;

public class MapChecker {

    private final CounterOfChips counterOfChips;

    public MapChecker(CounterOfChips counterOfChips) {
        this.counterOfChips = counterOfChips;
    }

    /**
     * Процесс проверяет введённые координаты на валидность.
     *
     * @param x      координата Х
     * @param y      координата Y
     * @param player true, если игрок, false, если компуктер
     * @return возвращает false, если координаты валидны
     */
    public boolean isCoordValid(int x, int y, boolean player) {
        if (x < 0 || x > Map.size - 1 || y < 0 || y > Map.size - 1) {
            System.out.println("Введённые данные вне диапазона.");
            return true;
        }
        if (Map.map[y][x] == Map.DOT_EMPTY) {
            return false;
        } else if (player) {
            System.out.println("Ячейка занята.");
            return true;
        } else return true;
    }

    /**
     * Процесс проверяет, выиграл ли последний ходивший игрок,
     * и есть ли ещё место на поле.
     *
     * @param dot фишка игрока или компуктера
     * @return true, если победа
     */
    public boolean chekWinner(char dot) {
        String winner;
        if (dot == Map.DOT_X) {
            winner = "игрок.";
        } else {
            winner = "компуктер.";
        }
        // Проверяем победу по вертикалям, горизонталям и диагоналям.
        if (isWinVertAndHoriz(dot) || isWinDiagon(dot)) {
            System.out.println("Победил " + winner);
            return true;
        }
        // Проверяем, всё ли поле заполнено.
        for (int i = 0; i < Map.size; i++) {
            for (int j = 0; j < Map.size; j++) {
                if (Map.map[i][j] == Map.DOT_EMPTY) {
                    return false;
                }
            }
        }
        System.out.println("Ничья.");
        return true;
    }

    /**
     * Ищем победителя по вертикалям и горизонталям.
     *
     * @param dot фишка игрока
     * @return true, если победил
     */
    public boolean isWinVertAndHoriz(char dot) {
        for (int i = 0; i < Map.size; i++) {
            int xWin = 0;
            int yWin = 0;
            for (int j = 0; j < Map.size; j++) {
                xWin = counterOfChips.count(i, j, xWin, dot);
                yWin = counterOfChips.count(j, i, yWin, dot);
                if (xWin >= Map.dotsToWin || yWin >= Map.dotsToWin) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isWinDiagon(char dot) {
        for (int i = 0; i <= Map.size - Map.dotsToWin; i++) {
            int[] count = new int[4];
            for (int j = 0; j < Map.size - i; j++) {
                count[0] = counterOfChips.count(j + i, j, count[0], dot);
                count[1] = counterOfChips.count(j, j + i, count[1], dot);
                count[2] = counterOfChips.count(Map.size - 1 - j - i, j, count[2], dot);
                count[3] = counterOfChips.count(Map.size - 1 - j, j + i, count[3], dot);
                for (int k = 0; k <= 3; k++) {
                    if (count[k] >= Map.dotsToWin) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
