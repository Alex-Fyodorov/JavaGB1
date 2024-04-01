package javatwo.sem2.task4;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExceptionApp extends JFrame implements Thread.UncaughtExceptionHandler {

    private static final int WINDOW_HEIGHT = 200;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_POSX = 200;
    private static final int WINDOW_POSY = 300;
    private static int count = 0;


    public ExceptionApp() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("count: " + count);
        setResizable(false);

        JButton button = new JButton("Push Me");
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    setTitle("count: " + count++);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    throw new RuntimeException("Not right button.");
                }
            }
        });
        add(button);

        setVisible(true);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        JOptionPane.showMessageDialog(null,
                e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        new ExceptionApp();
    }
}
