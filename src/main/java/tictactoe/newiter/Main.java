package tictactoe.newiter;

import tictactoe.newiter.view.ConsoleView;
import tictactoe.newiter.view.Text;
import tictactoe.newiter.view.TextRu;
import tictactoe.newiter.view.View;

public class Main {

    public static void main(String[] args) {
        Text text = new TextRu();
        View view = new ConsoleView(text);
        Map.size = view.inputParam(text.inputMapsSize(Map.MIN, Map.MAX), Map.MIN, Map.MAX);
        Map.dotsToWin = view.inputParam(text.inputWinsSize(Map.MIN, Map.size), Map.MIN, Map.size);
        Map.init();
        CounterOfChips counterOfChips = new CounterOfChips();
        MapChecker mapChecker = new MapChecker(counterOfChips);
        ArtificialIntelligence ai = new ArtificialIntelligence(counterOfChips);
        view.printMap();
    }

//    /**
//     * Непосредственно игра. После каждого хода происходит
//     * отсылка к процессу,который проверяет, выиграл ли
//     * кто-нибудь, и есть ли ещё место на поле.
//     */
//    public static void game(MapChecker mapChecker) {
//
//        while (true) {
//            playersMove();
//            System.out.println();
//            if (mapChecker.chekWinner(Map.DOT_X)) {
//                break;
//            }
//            moveOfArtifIntel();
//            System.out.println();
//            if (mapChecker.chekWinner(Map.DOT_O)) {
//                break;
//            }
//        }
//    }
//
//
//    /**
//     * Начинаем игру с того, что делаем несколько ходов
//     * без проверки на победу.
//     */
//    public static void gamesBegin() {
//        for (int i = 1; i < Map.dotsToWin; i++) {
//            playersMove();
//            System.out.println();
//            moveOfArtifIntel();
//            System.out.println();
//        }
//    }



}
