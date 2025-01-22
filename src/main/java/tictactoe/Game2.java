package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game2 {

    private static final char DOT_EMPTY = '.';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private final int size;
    private final int dotsToWin;
    private int stepCount;
    private final List<Raw> raws;
    private final char[][] map;
    /*  Создаём переменную, которая показывает, когда
     * пора начинать блокировать ходы игрока. */
    public final int checkToLock;

    public Game2(int size, int dotsToWin) {
        this.size = size;
        this.dotsToWin = dotsToWin;
        this.checkToLock = Math.round(0.6f * dotsToWin);
        this.stepCount = 0;
        this.raws = new ArrayList<>();
        toFillRawsArray();
        this.map = new char[size][size];
        completionMap();
    }

    /**
     * Заполнение массива строк.
     */
    private void toFillRawsArray() {
        for (int i = 0; i < size * 2 + 2; i++) {
            raws.add(new Raw(size));
        }
        for (int i = 1; i <= size - dotsToWin; i++) {
            for (int j = 0; j < 4; j++) {
                raws.add(new Raw(size - i));
            }
        }
        toFillRaws();
    }

    /**
     * Заполнение строк.
     */
    private void toFillRaws() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Point point = new Point(i, j, DOT_EMPTY);
                raws.get(j).getPointList().add(point);
                raws.get(size + i).getPointList().add(point);
                if (i == j) {
                    raws.get(size * 2).getPointList().add(point);
                }
                if (i == size - j - 1) {
                    raws.get(size * 2 + 1).getPointList().add(point);
                }
            }
        }
        for (int i = 1; i <= size - dotsToWin; i++) {
            for (int j = 0; j < size - i; j++) {
                Point point1 = raws.get(j + 1).getPointList().get(j);
                raws.get(size * 2 + 4 * (i - 1) + 2).getPointList().add(point1);
                Point point2 = raws.get(j).getPointList().get(j + 1);
                raws.get(size * 2 + 4 * (i - 1) + 3).getPointList().add(point2);
                Point point3 = raws.get(size - 1 - j - i).getPointList().get(j);
                raws.get(size * 2 + 4 * (i - 1) + 4).getPointList().add(point3);
                Point point4 = raws.get(size - 1 - j).getPointList().get(j + i);
                raws.get(size * 2 + 4 * (i - 1) + 5).getPointList().add(point4);
            }
        }
    }

    /**
     * Изменение поля в результате хода игрока или ИИ.
     *
     * @param x   координата Х
     * @param y   координата Y
     * @param dot вставляемый символ
     */
    private void putPoint(int x, int y, char dot) {
        raws.get(y).putChar(x, dot);
        stepCount++;
        completionMap(); // TODO ?
    }

    /**
     * Процесс проверяет, выиграл ли последний ходивший игрок.
     *
     * @param dot фишка игрока или компуктера
     * @return true, если победа
     */
    private boolean chekWinner(char dot) {
        for (Raw raw : raws) {
            int count = 0;
            List<Point> array = raw.getPointList();
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).getBody() == dot) count++;
                else count = 0;
            }
            if (count >= dotsToWin) return true;
        }
        return false;
    }

    /**
     * Проверяем, всё ли поле заполнено.
     */
    private boolean isFieldFull() {
        return stepCount == size * size;
    }

    /**
     * Проверка ИИ на возможность победить одним ходом.
     * Если такая возможность есть, делает ход.
     * @return true, если ИИ побеждает одним ходом.
     */
    private boolean checkToWin() {
        Point requiredPoint;
        for (Raw raw : raws) {
            int part1 = 0;
            int part2 = 0;
            requiredPoint = null;
            List<Point> pointList = raw.getPointList();
            for (Point point : pointList) {
                if (point.getBody() == DOT_O) part2++;
                else if (point.getBody() == DOT_EMPTY) {
                    part1 = part2;
                    part2 = 0;
                    requiredPoint = point;
                } else if (point.getBody() == DOT_X) {
                    part1 = 0;
                    part2 = 0;
                    requiredPoint = null;
                }
                // В случае возможности победы ИИ делает ход.
                if (part1 + part2 >= dotsToWin - 1 && requiredPoint != null) {
                    putPoint(requiredPoint.getX(), requiredPoint.getY(), DOT_O);
                    return true;
                }
            }
        }
        return false;
    }

    //============================================================================

    /**
     * Непосредственно игра. После каждого хода происходит
     * отсылка к процессу,который проверяет, выиграл ли
     * кто-нибудь, и есть ли ещё место на поле.
     */
    public int game2(int x, int y) { // TODO rename
        //System.out.printf("game: x = %d, y = %d\n", x, y);
        if (isCoordNotValid(x, y)) return 4; // TODO rewrite
        putPoint(x, y, DOT_X);
        if (chekWinner(DOT_X)) return 1;
        if (isFieldFull()) return 3;
        moveOfArtifIntel2(); // TODO rename
        if (chekWinner(DOT_O)) return 2;
        if (isFieldFull()) return 3;
        return 0;
    }

    private void moveOfArtifIntel2() {
        if (checkToWin()) return;
    }

    private void lock() {
        // Находим самую длинную последовательность фишек игрока в каждой строке.
        List<Integer> maxCountList = new ArrayList<>();
        for (Raw raw : raws) {
            maxCountList.add(counterOfDots(raw.getPointList(), DOT_X));
        }

        // Определяем порядок в коттором будем просматривать строки для блокировки.
        int max = 0;
        int index = 0;
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < maxCountList.size(); i++) {
            for (int j = 0; j < maxCountList.size(); j++) {
                if (maxCountList.get(j) > max) {
                    max = maxCountList.get(j);
                    index = j;
                }
            }
            if (max == 0) break;
            indexes.add(index);
            max = 0;
            maxCountList.set(index, -1);
        }

        // TODO not ended
    }

    /**
     * Метод считает количество фишек определённого вида в строке.
     *
     * @param pointList массив, составляющий строку
     * @param dot       искомый вид фишек
     * @return количество нужных фишек в строке
     */
    private int counterOfDots(List<Point> pointList, char dot) {
        int count = 0;
        int max = 0;
        for (int i = 0; i < pointList.size(); i++) {
            if (pointList.get(i).getBody() == dot) {
                count++;
                if (count > max) max = count;
            } else count = 0;
        }
        return max;
    }


    /**
     * Заполнение поля.
     */
    private void completionMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = raws.get(i + size).getPointList().get(j).getBody();
            }
        }
    }

    /**
     * Возврат поля.
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
        //System.out.printf("game: x = %d, y = %d\n", x, y);
        if (isCoordNotValid(x, y)) return 4;
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

    private void printMap() {
        System.out.println("====================================");
        for (int i = 0; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("====================================");
        for (int i = 1; i <= raws.size(); i++) {
            System.out.println("" + i + " " + raws.get(i - 1).getPointList().toString());
        }
        System.out.println("====================================");
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
        if (checkToWin()) return;
        lock();

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
        } while (isCoordNotValid(x, y));
        //map[x][y] = DOT_O;
        putPoint(x, y, DOT_O);
        printMap();
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
        //map[x][y] = DOT_X;
        putPoint(x, y, DOT_X);
        printMap();
    }

    /**
     * Процесс проверяет введённые координаты на валидность.
     *
     * @param x координата Х
     * @param y координата Y
     * @return возвращает false, если координаты валидны
     */
    private boolean isCoordNotValid(int x, int y) {
        if (x < 0 || x > size - 1 || y < 0 || y > size - 1) {
            return true;
        }
        return map[x][y] != DOT_EMPTY;
    }
}
