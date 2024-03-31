package javatwo.lec2.circles;

import javatwo.lec2.common.CanvasRepaintListener;
import javatwo.lec2.common.MainCanvas;
import javatwo.lec2.common.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame implements CanvasRepaintListener,
        Thread.UncaughtExceptionHandler {

    private static final int POS_X = 200;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private final List<Sprite> sprites = new ArrayList<>();
    private final Background background;

    public MainWindow() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");

        background = new Background();

        for (int i = 0; i < 10; i++) {
            sprites.add(new Ball());
        }

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3 && !sprites.isEmpty()) {
                    sprites.remove(sprites.size() - 1);
                } else if (e.getButton() == MouseEvent.BUTTON1) {
                    if (sprites.size() < 15) sprites.add(new Ball());
                    else throw new ArrayIndexOutOfBoundsException("Too much balls.");
                }
            }
        });

        MainCanvas canvas = new MainCanvas(this);
        add(canvas);

        setVisible(true);
    }

    @Override
    public void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    public void update(MainCanvas canvas, float deltaTime) {
        background.update(canvas, deltaTime);
        sprites.forEach(s -> s.update(canvas, deltaTime));
    }

    public void render(MainCanvas canvas, Graphics g) {
        background.render(canvas, g);
        sprites.forEach(s -> s.render(canvas, g));
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        JOptionPane.showMessageDialog(null,
                e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
