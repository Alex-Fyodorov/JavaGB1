package javatwo.tictactoe;

import java.util.Random;

public class Game {

    private static final char DOT_EMPTY = '.';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private final int size;
    private final int dotsToWin;
    private final char[][] map;
    /*  Создаём переменную, которая показывает, когда
     * пора начинать блокировать ходы игрока. */
    public final int checkToLock;

    public Game(int size, int dotsToWin) {
        this.size = size;
        this.dotsToWin = dotsToWin;
        this.checkToLock = Math.round(0.6f * dotsToWin);
        this.map = new char[size][size];
        completionMap();
    }

    /**
     * Первичное заполнение поля точками.
     */
    private void completionMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать поля.
     */
    public char[][] getMap() {
        return map;
    }

    /**
     * Непосредственно игра. После каждого хода происходит
     * отсылка к процессу,который проверяет, выиграл ли
     * кто-нибудь, и есть ли ещё место на поле.
     */
    public int game(int x, int y) {
        if (isCoordValid(x, y)) return 4;
        playersMove(x, y);
        if (chekWinner(DOT_X)) return 1;
        if (isFieldFull()) return 3;
        moveOfArtifIntel();
        if (chekWinner(DOT_O)) return 2;
        if (isFieldFull()) return 3;
        return 0;
    }

    /**
     * Счётчик фишек. При движении циклов решает,
     * увеличить или обнулить количество посчитанных фишек.
     *
     * @param y     координата фишки y
     * @param x     координата фишки x
     * @param count счётчик фишек
     * @param dot   идентификатор фишки (X или O)
     * @return новое значение счётчика
     */
    private int counterOfChips(int y, int x, int count, char dot) {
        if (map[y][x] == dot) {
            count++;
        } else {
            count = 0;
        }
        return count;
    }

    /**
     * Ищем победителя по диагоналям.
     *
     * @param dot фишка игрока
     * @return true, если победил
     */
    private boolean isWinDiagon(char dot) {
        for (int i = 0; i <= size - dotsToWin; i++) {
            int[] count = new int[4];
            for (int j = 0; j < size - i; j++) {
                count[0] = counterOfChips(j + i, j, count[0], dot);
                count[1] = counterOfChips(j, j + i, count[1], dot);
                count[2] = counterOfChips(size - 1 - j - i, j, count[2], dot);
                count[3] = counterOfChips(size - 1 - j, j + i, count[3], dot);
                for (int k = 0; k <= 3; k++) {
                    if (count[k] >= dotsToWin) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

//    private void printMap() {
//        for (int i = 0; i <= size; i++) {
//            System.out.print(i + " ");
//        }
//        System.out.println();
//        for (int i = 0; i < size; i++) {
//            System.out.print(i + 1 + " ");
//            for (int j = 0; j < size; j++) {
//                System.out.print(map[j][i] + " ");
//            }
//            System.out.println();
//        }
//    }

    /**
     * Ищем победителя по вертикалям и горизонталям.
     *
     * @param dot фишка игрока
     * @return true, если победил
     */
    private boolean isWinVertAndHoriz(char dot) {
        for (int i = 0; i < size; i++) {
            int xWin = 0;
            int yWin = 0;
            for (int j = 0; j < size; j++) {
                xWin = counterOfChips(i, j, xWin, dot);
                yWin = counterOfChips(j, i, yWin, dot);
                if (xWin >= dotsToWin || yWin >= dotsToWin) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Процесс проверяет, выиграл ли последний ходивший игрок,
     * и есть ли ещё место на поле.
     *
     * @param dot фишка игрока или компуктера
     * @return true, если победа
     */
    private boolean chekWinner(char dot) {
        // Проверяем победу по вертикалям, горизонталям и диагоналям.
        return isWinVertAndHoriz(dot) || isWinDiagon(dot);
    }

    // Проверяем, всё ли поле заполнено.
    private boolean isFieldFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Проверяет строчку, столбец или диагональ и в случае
     * необходимости блокирует ход игрока.
     *
     * @param lock счётчик фишек игрока
     * @param i    специальный коэффициент для диагоналей
     * @param j    координата на которой находится цикл
     * @param x1   координата х блокировки в большую сторону
     * @param x2   координата х блокировки в меньшую сторону
     * @param y1   координата y блокировки в большую сторону
     * @param y2   координата y блокировки в меньшую сторону
     * @return true, если ход сделан
     */
    private boolean lockInspector
    (int lock, int i, int j, int x1, int x2, int y1, int y2) {
        if (lock >= checkToLock) {
            /* Проверяем с обеих сторон возможность блокировки в большую
             * сторону. Например, если строчка выглядит как ". Х Х . .",
             * то нолик нужно ставить не к стенке, а так, чтобы заблокировать
             * дальнейшее продвижение, то есть ". Х Х О .".
             */
            if (j + 1 < dotsToWin && map[y1][x1] == DOT_EMPTY) {
                map[y1][x1] = DOT_O;
                return true;
            } else if (j - lock >= size - dotsToWin - i
                    && map[y2][x2] == DOT_EMPTY) {
                map[y2][x2] = DOT_O;
                return true;
            }
            /* Если строчка уже заблокирована, к примеру ". Х Х О .",
             * то чтобы не ставить бесполезный нолик к стенке и не тратить
             * таким образом ход, проверяем и перешагиваем.
             */
            else if (j + 1 < dotsToWin && map[y1][x1] == DOT_O ||
                    j - lock >= size - dotsToWin - i && map[y2][x2] == DOT_O) {
                return false;
            }
            /* Проверяем остальные возможности заблокировать ход игроку.
             */
            else if (j + 1 < size - i && map[y1][x1] == DOT_EMPTY) {
                map[y1][x1] = DOT_O;
                return true;
            } else if (j - lock >= 0 && map[y2][x2] == DOT_EMPTY) {
                map[y2][x2] = DOT_O;
                return true;
            }
        }
        return false;
    }

    /**
     * Процесс проверяет диагонали на возможность блокировки ходов игрока.
     * В первую итерацию внешнего цикла проверяются центральные диагонали,
     * с каждой последующей проверяются второстепенные диагонали, лежащие
     * по обеим сторонам от центральных.
     *
     * @return true, если ход сделан
     */
    private boolean lockDiagonals() {
        for (int i = 0; i <= size - dotsToWin; i++) {
            int[] count = new int[4];
            for (int j = 0; j < size - i; j++) {
                count[0] = counterOfChips(j + i, j, count[0], DOT_X);
                if (lockInspector(count[0], i, j, j + 1,
                        j - count[0], j + i + 1, j + i - count[0])) {
                    return true;
                }
                count[1] = counterOfChips(j, j + i, count[1], DOT_X);
                if (lockInspector(count[1], i, j + i, j + i + 1,
                        j + i - count[1], j + 1, j - count[1])) {
                    return true;
                }
                count[2] = counterOfChips(size - 1 - j - i, j, count[2], DOT_X);
                if (lockInspector(count[2], i, j, j + 1,
                        j - count[2], size - 2 - j - i,
                        size - 1 - j - i + count[2])) {
                    return true;
                }
                count[3] = counterOfChips(size - 1 - j, j + i, count[3], DOT_X);
                if (lockInspector(count[3], i, j + i, j + i + 1,
                        j + i - count[3], size - 2 - j,
                        size - 1 - j + count[3])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Ход ИИ. Сначала проверяется возможность заблокировать ход игроку.
     * Если такой возможности или необходимости нет, делается ход наобум.
     */
    private void moveOfArtifIntel() {

        // Проверяем вертикали и горизонтали
        for (int i = 0; i < size; i++) {
            int xLock = 0;
            int yLock = 0;
            for (int j = 0; j < size; j++) {

                // Проверяем и блокируем горизонтали
                xLock = counterOfChips(i, j, xLock, DOT_X);
                if (lockInspector(xLock, 0, j, j + 1, j - xLock, i, i)) {
                    getMap();
                    return;
                }

                // Проверяем и блокируем вертикали
                yLock = counterOfChips(j, i, yLock, DOT_X);
                if (lockInspector(yLock, 0, j, i, i, j + 1, j - yLock)) {
                    getMap();
                    return;
                }
            }
        }
        // Проверяем и блокируем диагонали
        if (lockDiagonals()) {
            getMap();
            return;
        }
        // Если ничего блокировать не нужно, делаем ход куда попало.
        Random rand = new Random();
        int x;
        int y;
        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
        } while (isCoordValid(x, y));
        map[x][y] = DOT_O;
        getMap();
    }

//    /**
//     * Начинаем игру с того, что делаем несколько ходов
//     * без проверки на победу.
//     */
//    public void gamesBegin() {
//        for (int i = 1; i < dotsToWin; i++) {
//            playersMove();
//            System.out.println();
//            moveOfArtifIntel();
//            System.out.println();
//        }
//    }


    private void playersMove(int x, int y) {
        map[x][y] = DOT_X;
    }

    /**
     * Процесс проверяет введённые координаты на валидность.
     *
     * @param x координата Х
     * @param y координата Y
     * @return возвращает false, если координаты валидны
     */
    private boolean isCoordValid(int x, int y) {
        if (x < 0 || x > size - 1 || y < 0 || y > size - 1) {
            return true;
        }
        return map[x][y] != DOT_EMPTY;
    }
}
