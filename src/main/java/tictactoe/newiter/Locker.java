package tictactoe.newiter;

public class Locker {

    private final CounterOfChips counterOfChips;

    public Locker(CounterOfChips counterOfChips) {
        this.counterOfChips = counterOfChips;
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
    public boolean lockInspector
    (int lock, int i, int j, int x1, int x2, int y1, int y2) {
        if (lock >= Map.chekToLock) {
            /* Проверяем с обеих сторон возможность блокировки в большую
             * сторону. Например, если строчка выглядит как ". Х Х . .",
             * то нолик нужно ставить не к стенке, а так, чтобы заблокировать
             * дальнейшее продвижение, то есть ". Х Х О .".
             */
            if (j + 1 < Map.dotsToWin && Map.map[y1][x1] == Map.DOT_EMPTY) {
                Map.map[y1][x1] = Map.DOT_O;
                return true;
            } else if (j - lock >= Map.size - Map.dotsToWin - i
                    && Map.map[y2][x2] == Map.DOT_EMPTY) {
                Map.map[y2][x2] = Map.DOT_O;
                return true;
            }
            /* Если строчка уже заблокирована, к примеру ". Х Х О .",
             * то чтобы не ставить бесполезный нолик к стенке и не тратить
             * таким образом ход, проверяем и перешагиваем.
             */
            else if (j + 1 < Map.dotsToWin && Map.map[y1][x1] == Map.DOT_O ||
                    j - lock >= Map.size - Map.dotsToWin - i && Map.map[y2][x2] == Map.DOT_O) {
                return false;
            }
            /* Проверяем остальные возможности заблокировать ход игроку.
             */
            else if (j + 1 < Map.size - i && Map.map[y1][x1] == Map.DOT_EMPTY) {
                Map.map[y1][x1] = Map.DOT_O;
                return true;
            } else if (j - lock >= 0 && Map.map[y2][x2] == Map.DOT_EMPTY) {
                Map.map[y2][x2] = Map.DOT_O;
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
    public boolean lockDiagonals() {
        for (int i = 0; i <= Map.size - Map.dotsToWin; i++) {
            int[] count = new int[4];
            for (int j = 0; j < Map.size - i; j++) {
                count[0] = counterOfChips.count(j + i, j, count[0], Map.DOT_X);
                if (lockInspector(count[0], i, j, j + 1,
                        j - count[0], j + i + 1, j + i - count[0])) {
                    return true;
                }
                count[1] = counterOfChips.count(j, j + i, count[1], Map.DOT_X);
                if (lockInspector(count[1], i, j + i, j + i + 1,
                        j + i - count[1], j + 1, j - count[1])) {
                    return true;
                }
                count[2] = counterOfChips.count(Map.size - 1 - j - i, j, count[2], Map.DOT_X);
                if (lockInspector(count[2], i, j, j + 1,
                        j - count[2], Map.size - 2 - j - i,
                        Map.size - 1 - j - i + count[2])) {
                    return true;
                }
                count[3] = counterOfChips.count(Map.size - 1 - j, j + i, count[3], Map.DOT_X);
                if (lockInspector(count[3], i, j + i, j + i + 1,
                        j + i - count[3], Map.size - 2 - j,
                        Map.size - 1 - j + count[3])) {
                    return true;
                }
            }
        }
        return false;
    }
}
