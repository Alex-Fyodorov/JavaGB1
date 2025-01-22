package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel {

    private int panelWidth;
    private int panelHeight;
    private int cellWidth;
    private int cellHeight;
    private int size = 1;
    private Game2 game;
    private char[][] map;
    private int answer = 0;

    public Map() {
        //setBackground(Color.BLACK);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
    }

    public void startNewGame(int mode, int fieldSize, int wLen) {
        size = fieldSize;
        game = new Game2(fieldSize, wLen);
        map = game.getMap();
        //System.out.printf("Mode: %d;\nSize: x = %d, y = %d;\nWin Length: %d\n",
        //        mode, fieldSize, fieldSize, wLen);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        panelWidth = getWidth();
        panelHeight = getHeight();
        cellHeight = panelHeight / size;
        cellWidth = panelWidth / size;

        g.setColor(Color.BLACK);
        for (int i = 1; i < size; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }
        for (int i = 1; i < size; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        if (map != null) printField(g);

    }

    private void update(MouseEvent e) {
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        answer = game.game(cellX, cellY);
        //System.out.println("=== " + answer);
        map = game.getMap();
        //System.out.printf("x = %d, y = %d\n", cellX, cellY);
        repaint();
    }

    private void printField(Graphics g) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == '.') continue;
                if (map[i][j] == 'X') g.setColor(Color.GREEN);
                else if (map[i][j] == 'O') g.setColor(Color.MAGENTA);
                g.fillOval((int) (0.2 * cellWidth + i * cellWidth),
                        (int) (0.2 * cellHeight + j * cellHeight),
                        (int) (0.6 * cellWidth),
                        (int) (0.6 * cellHeight));
            }
        }
        printAnswer(g);
    }

    private void printAnswer(Graphics g) {
        if (answer > 0 && answer < 4) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, panelHeight / 3, panelWidth, panelHeight / 3);
            g.setColor(Color.CYAN);
            g.setFont(new Font("Times new Roman", Font.BOLD, 70));
            switch (answer) {
                case 1: {
                    g.drawString("Вы победили!", panelWidth / 2 - 220, 30 + panelHeight / 2);
                    break;
                }
                case 2: {
                    g.drawString("Вы проиграли!", panelWidth / 2 - 238, 30 + panelHeight / 2);
                    break;
                }
                case 3: {
                    g.drawString("Ничья", panelWidth / 2 - 105, 30 + panelHeight / 2);
                    break;
                }
            }
            answer = 0;
        }
    }

}
