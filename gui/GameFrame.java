package gui;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final int FRAME_WIDTH = 640;
    private final int FRAME_HEIGHT = 650;
    private final int PANEL_WIDHT = 600;
    private final int PANEL_HEIGHT = 600;
    private final int ROWS = 60;
    private final int COLUMNS = 60;

    private GamePanel gamePanel;

    public GameFrame() throws HeadlessException {
        this.setTitle("Game of life");
        this.setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(51, 51, 51));

        gamePanel = new GamePanel(PANEL_WIDHT, PANEL_HEIGHT, ROWS, COLUMNS);
        gamePanel.setBounds(20, 20, PANEL_WIDHT, PANEL_HEIGHT);
        this.add(gamePanel);

        this.setVisible(true);
    }
}
