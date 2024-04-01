package javatwo.text_chat;

import javax.swing.*;
import java.awt.*;

public class ServerWindow extends JFrame {

    private static final int WINDOW_HEIGHT = 200;
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_POSX = 150;
    private static final int WINDOW_POSY = 200;
    private final Server server;


    public ServerWindow(Server server) {
        this.server = server;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat Server");
        setResizable(false);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        addButtonPanel(buttonPanel, textArea);
        add(buttonPanel, BorderLayout.SOUTH);
        add(textArea);

        setVisible(true);
    }

    private void addButtonPanel(JPanel buttonPanel, JTextArea textArea) {
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            if (!server.isServerWorking()) {
                server.start();
                textArea.append("Старт работы сервера.\n");
            } else {
                textArea.append("Сервер уже работает.\n");
            }
        });
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> {
            server.stop();
            textArea.append("Сервер остановлен.\n");
        });
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
    }
}
