package javaone.hw2;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static final int SIZE = 6;
    public static final int DOTS_TO_WIN = 5;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final Scanner SCANNER = new Scanner(System.in);
    public static char[][] map;
    /*  Создаём переменную, которая показывает, когда
     * пора начинать блокировать ходы игрока. */
    public static final int CHEK_TO_LOCK = Math.round(0.6f * DOTS_TO_WIN);

    public static void main(String[] args) {
        System.out.println(String.format("Игра ведётся на поле размером %d X %d.", SIZE, SIZE));
        System.out.println(String.format("Для победы достаточно заполнить %d ячеек.\n", DOTS_TO_WIN));
        map = new char[SIZE][SIZE];
        completionMap();
        printMap();
        gamesBegin();
        game();
    }

    /**
     * Непосредственно игра. После каждого хода происходит
     * отсылка к процессу,который проверяет, выиграл ли
     * кто-нибудь, и есть ли ещё место на поле.
     */
    public static void game() {

        while (true) {
            playersMove();
            System.out.println();
            if (chekWinner(DOT_X)) {
                break;
            }
            moveOfArtifIntel();
            System.out.println();
            if (chekWinner(DOT_O)) {
                break;
            }
        }
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
    public static int counterOfChips(int y, int x, int count, char dot) {
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
    public static boolean isWinDiagon(char dot) {
        for (int i = 0; i <= SIZE - DOTS_TO_WIN; i++) {
            int[] count = new int[4];
            for (int j = 0; j < SIZE - i; j++) {
                count[0] = counterOfChips(j + i, j, count[0], dot);
                count[1] = counterOfChips(j, j + i, count[1], dot);
                count[2] = counterOfChips(SIZE - 1 - j - i, j, count[2], dot);
                count[3] = counterOfChips(SIZE - 1 - j, j + i, count[3], dot);
                for (int k = 0; k <= 3; k++) {
                    if (count[k] >= DOTS_TO_WIN) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Ищем победителя по вертикалям и горизонталям.
     *
     * @param dot фишка игрока
     * @return true, если победил
     */
    public static boolean isWinVertAndHoriz(char dot) {
        for (int i = 0; i < SIZE; i++) {
            int xWin = 0;
            int yWin = 0;
            for (int j = 0; j < SIZE; j++) {
                xWin = counterOfChips(i, j, xWin, dot);
                yWin = counterOfChips(j, i, yWin, dot);
                if (xWin >= DOTS_TO_WIN || yWin >= DOTS_TO_WIN) {
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
    public static boolean chekWinner(char dot) {
        String winner;
        if (dot == DOT_X) {
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
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        System.out.println("Ничья.");
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
    public static boolean lockInspector
    (int lock, int i, int j, int x1, int x2, int y1, int y2) {
        if (lock >= CHEK_TO_LOCK) {
            /* Проверяем с обеих сторон возможность блокировки в большую
             * сторону. Например, если строчка выглядит как ". Х Х . .",
             * то нолик нужно ставить не к стенке, а так, чтобы заблокировать
             * дальнейшее продвижение, то есть ". Х Х О .".
             */
            if (j + 1 < DOTS_TO_WIN && map[y1][x1] == DOT_EMPTY) {
                map[y1][x1] = DOT_O;
                return true;
            } else if (j - lock >= SIZE - DOTS_TO_WIN - i
                    && map[y2][x2] == DOT_EMPTY) {
                map[y2][x2] = DOT_O;
                return true;
            }
            /* Если строчка уже заблокирована, к примеру ". Х Х О .",
             * то чтобы не ставить бесполезный нолик к стенке и не тратить
             * таким образом ход, проверяем и перешагиваем.
             */
            else if (j + 1 < DOTS_TO_WIN && map[y1][x1] == DOT_O ||
                    j - lock >= SIZE - DOTS_TO_WIN - i && map[y2][x2] == DOT_O) {
                return false;
            }
            /* Проверяем остальные возможности заблокировать ход игроку.
             */
            else if (j + 1 < SIZE - i && map[y1][x1] == DOT_EMPTY) {
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
    public static boolean lockDiagonals() {
        for (int i = 0; i <= SIZE - DOTS_TO_WIN; i++) {
            int[] count = new int[4];
            for (int j = 0; j < SIZE - i; j++) {
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
                count[2] = counterOfChips(SIZE - 1 - j - i, j, count[2], DOT_X);
                if (lockInspector(count[2], i, j, j + 1,
                        j - count[2], SIZE - 2 - j - i,
                        SIZE - 1 - j - i + count[2])) {
                    return true;
                }
                count[3] = counterOfChips(SIZE - 1 - j, j + i, count[3], DOT_X);
                if (lockInspector(count[3], i, j + i, j + i + 1,
                        j + i - count[3], SIZE - 2 - j,
                        SIZE - 1 - j + count[3])) {
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
    public static void moveOfArtifIntel() {
        System.out.println("Ход ИИ:");

        // Проверяем вертикали и горизонтали
        for (int i = 0; i < SIZE; i++) {
            int xLock = 0;
            int yLock = 0;
            for (int j = 0; j < SIZE; j++) {

                // Проверяем и блокируем горизонтали
                xLock = counterOfChips(i, j, xLock, DOT_X);
                if (lockInspector(xLock, 0, j, j + 1, j - xLock, i, i)) {
                    printMap();
                    return;
                }

                // Проверяем и блокируем вертикали
                yLock = counterOfChips(j, i, yLock, DOT_X);
                if (lockInspector(yLock, 0, j, i, i, j + 1, j - yLock)) {
                    printMap();
                    return;
                }
            }
        }
        // Проверяем и блокируем диагонали
        if (lockDiagonals()) {
            printMap();
            return;
        }
        // Если ничего блокировать не нужно, делаем ход куда попало.
        Random rand = new Random();
        int x;
        int y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (isCoordValid(x, y, false));
        map[y][x] = DOT_O;
        printMap();
    }

    /**
     * Начинаем игру с того, что делаем несколько ходов
     * без проверки на победу.
     */
    public static void gamesBegin() {
        for (int i = 1; i < DOTS_TO_WIN; i++) {
            playersMove();
            System.out.println();
            moveOfArtifIntel();
            System.out.println();
        }
    }

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
        map[y][x] = DOT_X;
        printMap();
    }

    /**
     * Процесс проверяет введённые координаты на валидность.
     *
     * @param x      координата Х
     * @param y      координата Y
     * @param player true, если игрок, false, если компуктер
     * @return возвращает false, если координаты валидны
     */
    public static boolean isCoordValid(int x, int y, boolean player) {
        if (x < 0 || x > SIZE - 1 || y < 0 || y > SIZE - 1) {
            System.out.println("Введённые данные вне диапазона.");
            return true;
        }
        if (map[y][x] == DOT_EMPTY) {
            return false;
        } else if (player) {
            System.out.println("Ячейка занята.");
            return true;
        } else return true;
    }

    /**
     * Первичное заполнение поля точками.
     */
    public static void completionMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать поля.
     */
    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
