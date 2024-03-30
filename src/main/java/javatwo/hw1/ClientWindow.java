package javatwo.hw1;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ClientWindow extends JFrame {

    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 200;
    private static final int WINDOW_POSY = 300;

    private String login;
    private String password;
    private String ipAddress;
    private int portNumber;

    private final JTextArea textArea;
    private final JTextField textField;


    public ClientWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Неавторизованный пользователь");
        setResizable(true);

        JPanel loginPanel = new JPanel(new BorderLayout());
        fillLoginPanel(loginPanel);
        add(loginPanel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendMessage());
        panel.add(sendButton, BorderLayout.EAST);

        textField = new JTextField();
        textField.addActionListener(e -> sendMessage());
        panel.add(textField, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        JList<String> nickNameList = new JList<>();
        nickNameList.setListData(new String[] {" user1 ", " user2 ", " user3 "});
        add(new JScrollPane(nickNameList), BorderLayout.EAST);

//        JTextArea nickArea = new JTextArea();
//        nickArea.setEditable(false);
//        nickArea.setLineWrap(true);
//        add(new JScrollPane(nickArea), BorderLayout.EAST);

        setVisible(true);
    }

    private void fillLoginPanel(JPanel loginPanel) {
        JPanel labelPanel = new JPanel(new GridLayout(4, 1));
        labelPanel.add(new JLabel(" login: "));
        labelPanel.add(new JLabel(" Password: "));
        labelPanel.add(new JLabel(" IP-address: "));
        labelPanel.add(new JLabel(" Port: "));
        loginPanel.add(labelPanel, BorderLayout.WEST);

        JPanel fieldsPanel = new JPanel(new GridLayout(4, 1));
        JTextField loginField = new JTextField();
        fieldsPanel.add(loginField);
        JTextField passwordField = new JTextField();
        fieldsPanel.add(passwordField);
        JTextField ipAddressField = new JTextField();
        fieldsPanel.add(ipAddressField);
        JTextField portNumberField = new JTextField();
        fieldsPanel.add(portNumberField);
        loginPanel.add(fieldsPanel, BorderLayout.CENTER);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            login = loginField.getText().strip();
            password = passwordField.getText().strip();
            ipAddress = ipAddressField.getText().strip();
            portNumber = Integer.parseInt(portNumberField.getText().strip());
            auth(login, password, ipAddress, portNumber);
        });
        loginButton.setSize(getWidth(), 50);
        loginPanel.add(loginButton, BorderLayout.SOUTH);
    }

    private void sendMessage() {
        if (textField.getText().isBlank()) {
            return;
        }
        String message = textField.getText() + "\n";
        textField.setText("");
        textArea.append(message);
        System.out.print(message);
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("history.txt", true))) {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void auth(String login, String password,
                      String ipAddress, int portNumber) {
        this.setTitle(login);
        System.out.println("login: " + login);
        System.out.println("password: " + password);
        System.out.println("ipAddress: " + ipAddress);
        System.out.println("portNumber: " + portNumber);
        long count = countLinesOfHistory();
        if (count > 0) {
            try (BufferedReader reader = new BufferedReader(
                    new FileReader("history.txt"))) {
                count -= 100;
                if(count < 0) count = 0;
                reader.lines()
                        .skip(count)
                        .map(str -> str = str + "\n")
                        .forEach(textArea::append);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private long countLinesOfHistory() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("history.txt"))) {
            return reader.lines().count();
        } catch (IOException e) {
            return 0;
        }
    }
}
