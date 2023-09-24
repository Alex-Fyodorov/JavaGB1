package oop.hw2.runners;

import oop.hw2.obstructions.Track;
import oop.hw2.obstructions.Wall;

public abstract class Runner {
    private int runLength;
    private float jumpHeight;

    public Runner(int runLength, float jumpHeight) {
        this.runLength = runLength;
        this.jumpHeight = jumpHeight;
    }

    public int getRunLength() {
        return runLength;
    }

    public float getJumpHeight() {
        return jumpHeight;
    }

    /**
     * Метод возвращает true, если this может пробежать по предложенной дорожке.
     * @param track Дорожка, по которой должен пробежать объект
     */
    public abstract boolean run(Track track);

    /**
     * Метод возвращает true, если this может перепрыгнуть предложенную стену.
     * @param wall Стена, через которую должен перепрыгнуть объект.
     */
    public abstract boolean jump(Wall wall);

    /**
     * Метод объявляет, что объект стартовал.
     */
    public abstract void start();

    /**
     * Метод объявляет, что объект смог дойти до финиша.
     */
    public abstract void finish();

    /**
     * Метод объявляет, что объект не смог дойти до финиша.
     */
    public abstract void lose();
}
