package tictactoe.newiter;

import java.util.Random;

public class ArtificialIntelligence {

    private final CounterOfChips counterOfChips;
    private final Locker locker;

    public ArtificialIntelligence(CounterOfChips counterOfChips) {
        this.counterOfChips = counterOfChips;
        this.locker = new Locker(counterOfChips);
    }

//    /**
//     * Ход ИИ. Сначала проверяется возможность заблокировать ход игроку.
//     * Если такой возможности или необходимости нет, делается ход наобум.
//     */
//    public void moveOfArtifIntel() {
//        System.out.println("Ход ИИ:");
//
//        // Проверяем вертикали и горизонтали
//        for (int i = 0; i < Map.size; i++) {
//            int xLock = 0;
//            int yLock = 0;
//            for (int j = 0; j < Map.size; j++) {
//
//                // Проверяем и блокируем горизонтали
//                xLock = counterOfChips.count(i, j, xLock, Map.DOT_X);
//                if (locker.lockInspector(xLock, 0, j, j + 1, j - xLock, i, i)) {
//                    printMap();
//                    return;
//                }
//
//                // Проверяем и блокируем вертикали
//                yLock = counterOfChips.count(j, i, yLock, Map.DOT_X);
//                if (locker.lockInspector(yLock, 0, j, i, i, j + 1, j - yLock)) {
//                    printMap();
//                    return;
//                }
//            }
//        }
//        // Проверяем и блокируем диагонали
//        if (locker.lockDiagonals()) {
//            printMap();
//            return;
//        }
//        // Если ничего блокировать не нужно, делаем ход куда попало.
//        Random rand = new Random();
//        int x;
//        int y;
//        do {
//            x = rand.nextInt(Map.size);
//            y = rand.nextInt(Map.size);
//        } while (isCoordValid(x, y, false));
//        Map.map[y][x] = Map.DOT_O;
//        printMap();
//    }
}
