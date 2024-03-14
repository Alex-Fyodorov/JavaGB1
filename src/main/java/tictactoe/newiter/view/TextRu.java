package tictactoe.newiter.view;

public class TextRu implements Text {

    @Override
    public String inputMapsSize(int min, int max) {
        return String.format("Введите размер поля (число от %d до %d): ", min, max);
    }

    @Override
    public String inputWinsSize(int min, int max) {
        return String.format("Введите количество фишек, " +
                "необходимое для победы (число от %d до %d): ", min, max);
    }

    @Override
    public String reInputParam() {
        return "Введённый параметр не соответствует требованиям. Попробуйте ещё раз.";
    }

    @Override
    public String notDigit() {
        return "Вы ввели не число, попробуйте ещё раз.";
    }
}
