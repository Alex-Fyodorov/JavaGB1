package oop.hw3.alphabets;

import oop.hw3.AbstractGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RussianGame extends AbstractGame {
    @Override
    protected List<String> generateCharList() {
        return new ArrayList<>(Arrays.asList("А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И",
                "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш",
                "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я"));
    }
}
