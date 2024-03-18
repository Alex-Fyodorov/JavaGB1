package oop.hw2.runners;

import oop.hw2.obstructions.Track;
import oop.hw2.obstructions.Wall;

public class Robot extends Runner {
    private String model;

    public Robot(int runLength, float jumpHeight) {
        super(runLength, jumpHeight);
        this.model = "Робот";
    }

    @Override
    public boolean run(Track track) {
        if (track.getLength() < this.getRunLength()) {
            System.out.printf("%s пробежал дорожку длиной %d.\n", model, track.getLength());
            return true;
        }
        System.out.printf("%s не добежал до конца дорожки длиной %d.\n", model, track.getLength());
        return false;
    }

    @Override
    public boolean jump(Wall wall) {
        if (wall.getHeight() < this.getJumpHeight()) {
            System.out.printf("%s перепрыгнул стену высотой %.2f.\n", model, wall.getHeight());
            return true;
        }
        System.out.printf("%s не смог перепрыгнуть стену высотой %.2f.\n", model, wall.getHeight());
        return false;
    }

    @Override
    public void start() {
        System.out.printf("%s стартовал.\n", model);
    }

    @Override
    public void finish() {
        System.out.printf("%s успешно добежал до финиша.\n", model);
        System.out.println();
    }

    @Override
    public void lose() {
        System.out.printf("%s не смог добежать до финиша.\n", model);
        System.out.println();
    }
}
