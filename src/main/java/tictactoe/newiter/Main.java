package tictactoe.newiter;

import tictactoe.newiter.view.ConsoleView;
import tictactoe.newiter.view.Text;
import tictactoe.newiter.view.TextRu;
import tictactoe.newiter.view.View;

public class Main {

    public static void main(String[] args) {
        View view = new ConsoleView(new TextRu());
        Text text = new TextRu();
        Map.size = view.inputParam(text.inputMapsSize(Map.MIN, Map.MAX), Map.MIN, Map.MAX);
        Map.dotsToWin = view.inputParam(text.inputWinsSize(Map.MIN, Map.size), Map.MIN, Map.size);
        Map.init();
        view.printMap();
    }
}
