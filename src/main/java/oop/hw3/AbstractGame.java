package oop.hw3;

import java.util.List;
import java.util.Random;

public abstract class AbstractGame implements Game {
    Integer sizeWord;
    Integer maxTry;
    String computerWord;
    GameStatus gameStatus = GameStatus.INIT;
    GameLogger logger;

    protected abstract List<String> generateCharList();

    @Override
    public void start(Integer sizeWord, Integer maxTry) {
        logger = new GameLogger();
        this.sizeWord = sizeWord;
        this.maxTry = maxTry;
        computerWord = generateWord();
        logger.addNote(String.format("New game, computerWord = %s, maxTry = %d.", computerWord, maxTry));
        gameStatus = GameStatus.START;
        logger.addNote(String.format("GameStatus = %s.", gameStatus.toString()));
        //System.out.println(computerWord);
    }

    private String generateWord() {
        List<String> alphabet = generateCharList();
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sizeWord; i++) {
            String str = alphabet.get(random.nextInt(alphabet.size()));
            if (sb.toString().contains(str)) i--;
            else sb.append(str);
        }
        return sb.toString();
    }

    @Override
    public Answer inputValue(String value) {
        value = value.substring(0, sizeWord);
        int cows = 0;
        int bulls = 0;
        for (int i = 0; i < value.length(); i++) {
            if (computerWord.contains(String.valueOf(value.charAt(i)))) cows++;
            if (computerWord.charAt(i) == value.charAt(i)) bulls++;
        }
        if (bulls == computerWord.length()) gameStatus = GameStatus.WIN;
        maxTry--;
        if (maxTry == 0 && gameStatus != GameStatus.WIN) gameStatus = GameStatus.LOSE;
        if (gameStatus == GameStatus.LOSE || gameStatus == GameStatus.WIN) {
            logger.addNote(String.format("Gamers word: %s, GameStatus = %s.", value, gameStatus.toString()));
        }
        else logger.addNote(String.format("Gamers word: %s, bulls = %d, cows = %d, maxTry = %d.", value, bulls, cows, maxTry));
        return new Answer(bulls, cows, maxTry);
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public List<String> getHistory() {
        return logger.getHistory();
    }
}
