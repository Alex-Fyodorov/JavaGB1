package tictactoe.front;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 200;
    private static final int WINDOW_POSY = 300;
    JButton btnStart = new JButton("New Game");
    JButton btnExit = new JButton("Exit");
    Map map;
    SettingsWindow settingsWindow;

    public GameWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("TicTacToe");
        setResizable(false);

        map = new Map();
        settingsWindow = new SettingsWindow(this);
        int settingsWindowX = (WINDOW_WIDTH - settingsWindow.getWidth()) / 2 + this.getX();
        int settingsWindowY = (WINDOW_HEIGHT - settingsWindow.getHeight()) / 2 + this.getY();
        settingsWindow.setLocation(settingsWindowX, settingsWindowY);
        btnExit.addActionListener(e -> System.exit(0));
        btnStart.addActionListener(e -> settingsWindow.setVisible(true));

        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnExit);
        add(panBottom, BorderLayout.SOUTH);
        add(map);
        setVisible(true);
    }

    void startNewGame(int mode, int fieldSize, int wLen) {
        map.startNewGame(mode, fieldSize, wLen);
    }
}
