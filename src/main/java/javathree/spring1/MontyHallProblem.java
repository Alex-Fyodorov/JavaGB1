package javathree.spring1;

import java.util.List;

public class MontyHallProblem {

    public static void main(String[] args) {
        System.out.printf("При неизменном решении игрок выигрывает в %.2f процентах случаев.\n",
                collectStatistics(false));
        System.out.printf("При изменении решения игрок выигрывает в %.2f процентах случаев.\n",
                collectStatistics(true));
    }

    public static boolean game(boolean changingSelection) {
        List<Boolean> doors = List.of(true, false);
        int gamersSelection = (int) (Math.random() * 10000) % 3;
        // Игрок выбирает из трёх дверей, но в массиве doors третья дверь удалена заранее.
        // Если игрок выбиреат проигрышную дверь с индексом 1, то "удаляется" дверь с индексом 2,
        // которой уже не было заранее. Если же игрок выбирает проигрышную дверь с индексом 2,
        // то "удаляется" дверь с индексом 1, в результате чего индекс выбранный игроком смещается
        // на единицу.
        if (gamersSelection == 2) {
            gamersSelection = 1;
        }
        if (changingSelection) {
            gamersSelection = Math.abs(gamersSelection - 1);
        }
        return doors.get(gamersSelection);
    }

    public static double collectStatistics(boolean changingSelection) {
        int count = 0;
        int times = 1_000_000;
        for (int i = 0; i < times; i++) {
            if (game(changingSelection)) {
                count++;
            }
        }
        return 100d * count / times;
    }
}
