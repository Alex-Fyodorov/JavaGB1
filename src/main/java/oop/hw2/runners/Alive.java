package oop.hw2.runners;

import oop.hw2.obstructions.Track;
import oop.hw2.obstructions.Wall;

public abstract class Alive extends Runner {
    private String name;

    public Alive(int runLength, float jumpHeight, String name) {
        super(runLength, jumpHeight);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean run(Track track) {
        if (track.getLength() < this.getRunLength()) {
            System.out.printf("%s пробежал дорожку длиной %d.\n", name, track.getLength());
            return true;
        }
        System.out.printf("%s не добежал до конца дорожки длиной %d.\n", name, track.getLength());
        return false;
    }

    @Override
    public boolean jump(Wall wall) {
        if (wall.getHeight() < this.getJumpHeight()) {
            System.out.printf("%s перепрыгнул стену высотой %.2f.\n", name, wall.getHeight());
            return true;
        }
        System.out.printf("%s не смог перепрыгнуть стену высотой %.2f.\n", name, wall.getHeight());
        return false;
    }

    @Override
    public void start() {
        System.out.printf("%s стартовал.\n", name);
    }

    @Override
    public void finish() {
        System.out.printf("%s успешно добежал до финиша.\n", name);
        System.out.println();
    }

    @Override
    public void lose() {
        System.out.printf("%s не смог добежать до финиша.\n", name);
        System.out.println();
    }
}
