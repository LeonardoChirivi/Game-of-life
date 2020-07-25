package gui;

import logic.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    private GameManager gameManager;

    GamePanel(int width, int height, int rows, int columns) {
        this.setOpaque(true);
        this.setBackground(new Color(51, 51, 51));
        this.setFocusable(true);
        this.requestFocus();

        gameManager = new GameManager(width, height, rows, columns, this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics bufferedGraphics = bufferedImage.getGraphics();

        gameManager.paintGrid(bufferedGraphics);

        g.drawImage(bufferedImage, 0, 0, this);
    }
}
