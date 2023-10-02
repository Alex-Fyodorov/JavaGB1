package oop.hw3;

import java.util.List;

public interface Game {

    void start(Integer sizeWord, Integer maxTry);

    Answer inputValue(String value);

    GameStatus getGameStatus();

    List<String> getHistory();
}
