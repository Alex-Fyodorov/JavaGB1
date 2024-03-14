package tictactoe.newiter;

public class CounterOfChips {

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
    public int count(int y, int x, int count, char dot) {
        if (Map.map[y][x] == dot) {
            count++;
        } else {
            count = 0;
        }
        return count;
    }
}
