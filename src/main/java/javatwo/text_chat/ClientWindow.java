package javatwo.text_chat;

import javax.swing.*;
import java.awt.*;

public class ClientWindow extends JFrame implements Authable {

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

    private final ChatServer server;

    public ClientWindow() {
        server = new Server(this);
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
        textField = new JTextField();
        textField.addActionListener(e -> sendMessage(textField.getText()));
        panel.add(textField, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendMessage(textField.getText()));
        panel.add(sendButton, BorderLayout.EAST);

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

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            login = loginField.getText().strip();
            password = passwordField.getText().strip();
            ipAddress = ipAddressField.getText().strip();
            portNumber = Integer.parseInt(portNumberField.getText().strip());
            auth(login, password, ipAddress, portNumber);
        });
        buttonPanel.add(loginButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> server.callLogout());
        buttonPanel.add(logoutButton);

        loginPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void sendMessage(String str) {
        if (str.isBlank()) {
            return;
        }
        String message = str + "\n";
        textField.setText("");
        textArea.append(message);
        System.out.print(message);
        server.writeHistory(message);
    }

    @Override
    public void auth(String login, String password,
                      String ipAddress, int portNumber) {
        this.setTitle(login);
        System.out.println("login: " + login);
        System.out.println("password: " + password);
        System.out.println("ipAddress: " + ipAddress);
        System.out.println("portNumber: " + portNumber);
        server.getHistory().forEach(textArea::append);
        server.callLogin();
    }

    @Override
    public void login(String str) {
        sendMessage(str);
    }

    @Override
    public void logout(String str) {
        sendMessage(str);
    }
}
