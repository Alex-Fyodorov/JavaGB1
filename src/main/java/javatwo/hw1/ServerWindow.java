package javatwo.hw1;

import javax.swing.*;
import java.awt.*;

public class ServerWindow extends JFrame {

    private static final int WINDOW_HEIGHT = 200;
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_POSX = 200;
    private static final int WINDOW_POSY = 300;
    private static boolean isServerWorking;

    public ServerWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("GB Chat");
        setResizable(false);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JTextArea textArea = new JTextArea();
        addButtonPanel(buttonPanel, textArea);
        add(buttonPanel, BorderLayout.SOUTH);
        add(textArea);

        setVisible(true);
    }

    private void addButtonPanel(JPanel buttonPanel, JTextArea textArea) {
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            if (!isServerWorking) {
                isServerWorking = true;
                textArea.append("Старт работы сервера.\n");
            } else {
                textArea.append("Сервер уже работает.\n");
            }
        });
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> {
            isServerWorking = false;
            textArea.append("Сервер остановлен.\n");
        });
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
    }
}
