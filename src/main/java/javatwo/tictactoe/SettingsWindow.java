package javatwo.tictactoe;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {

    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 300;
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 10;
    private static final String LENGTH_TEXT = " Установленная длина: ";
    private static int gameMode;
    private static int fieldSize = MIN_SIZE;
    private static int winSize = MIN_SIZE;

    JButton btnStart = new JButton("Start new game");

    public SettingsWindow(GameWindow gameWindow) {
        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        JPanel settingsPanel = new JPanel(new GridLayout(9, 1));

        settingsPanel.add(new JLabel(" Выберите режим игры"));
        ButtonGroup modeButtonGroup = new ButtonGroup();
        JRadioButton pvc = new JRadioButton("Человек против компьютера");
        pvc.addActionListener(e -> gameMode = 0);
        JRadioButton pvp = new JRadioButton("Человек против человека");
        pvp.addActionListener(e -> gameMode = 1);
        modeButtonGroup.add(pvc);
        modeButtonGroup.add(pvp);
        settingsPanel.add(pvc);
        settingsPanel.add(pvp);

        changeFieldAndWinSize(settingsPanel);

        add(settingsPanel, BorderLayout.NORTH);
        btnStart.addActionListener(e -> {
            gameWindow.startNewGame(gameMode,  fieldSize, winSize);
            setVisible(false);
        });
        add(btnStart);
    }

    private void changeFieldAndWinSize(JPanel settingsPanel) {
        JLabel fieldSizeLabel = new JLabel(LENGTH_TEXT + MIN_SIZE + " x " + MIN_SIZE);
        JLabel winSizeLabel = new JLabel(LENGTH_TEXT + MIN_SIZE + " x " + MIN_SIZE);

        JSlider fieldSizeSlider = new JSlider(MIN_SIZE, MAX_SIZE, MIN_SIZE);
        fieldSizeSlider.addChangeListener(e -> {
            fieldSize = fieldSizeSlider.getValue();
            fieldSizeLabel.setText(LENGTH_TEXT + fieldSize + " x " + fieldSize);
            if (winSize > fieldSize) {
                winSize = fieldSize;
                winSizeLabel.setText(LENGTH_TEXT + winSize + " x " + winSize);
            }
        });

        JSlider winSizeSlider = new JSlider(MIN_SIZE, MAX_SIZE, MIN_SIZE);
        winSizeSlider.addChangeListener(e -> {
            winSizeSlider.setMaximum(fieldSize);
            winSize = winSizeSlider.getValue();
            winSizeLabel.setText(LENGTH_TEXT + winSize + " x " + winSize);
        });

        settingsPanel.add(new JLabel(" Выберите размер поля"));
        settingsPanel.add(fieldSizeLabel);
        settingsPanel.add(fieldSizeSlider);
        settingsPanel.add(new JLabel(" Выберите размер поля"));
        settingsPanel.add(winSizeLabel);
        settingsPanel.add(winSizeSlider);
    }

}
