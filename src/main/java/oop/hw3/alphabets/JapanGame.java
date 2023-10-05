package oop.hw3.alphabets;

import oop.hw3.AbstractGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JapanGame extends AbstractGame {
    @Override
    protected List<String> generateCharList() {
        return new ArrayList<>(Arrays.asList(
                "ア", "イ", "ウ", "エ", "オ",
                "カ", "キ", "ク", "ケ", "コ",
                "サ", "シ", "ス", "セ", "ソ",
                "タ", "チ", "ツ", "テ", "ト",
                "ナ", "ニ", "ヌ", "ネ", "ノ",
                "ハ", "ヒ", "フ", "ヘ", "ホ",
                "マ", "ミ", "ム", "メ", "モ",
                "ヤ", "ユ", "ヨ",
                "ラ", "リ", "ル", "レ", "ロ",
                "ワ", "ヲ", "ン"
                ));
    }
}
