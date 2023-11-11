package oop.hw2;

import oop.hw2.obstructions.Track;
import oop.hw2.obstructions.Wall;
import oop.hw2.runners.Cat;
import oop.hw2.runners.Human;
import oop.hw2.runners.Robot;
import oop.hw2.runners.Runner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RunToFinishApp {
    public static void main(String[] args) {
        List<Runner> runners = new ArrayList<>();
        runners.add(new Robot(3000, 0.5f));
        runners.add(new Cat(50, 1));
        runners.add(new Human(42000, 1.5f));

        List<Object> obstructions = new ArrayList<>();
        obstructions.add(new Track(45));
        obstructions.add(new Wall(0.75f));
        obstructions.add(new Track(5000));
        obstructions.add(new Wall(1.2f));

        for (Runner runner : runners) {
            runner.start();
            boolean isRan = true;
            Iterator<Object> iterator = obstructions.listIterator();
            while(isRan && iterator.hasNext()) {
                Object obstruction = iterator.next();
                if (obstruction instanceof Track) {
                    isRan = runner.run((Track) obstruction);
                }
                if (obstruction instanceof Wall) {
                    isRan = runner.jump((Wall) obstruction);
                }
            }
            if (isRan) runner.finish();
            else runner.lose();
        }
    }
}
