package logic;

import gui.GamePanel;

import java.awt.*;

public class GameManager {
    private int[][] grid;
    private int[][] nextGen;
    private int columns;
    private int rows;
    private int cellWidth;
    private int cellHeight;

    private static final int[][] NEIGHBOURS = {
            {-1, -1}, {-1, 0}, {-1, +1},
            { 0, -1},/* y  x */{ 0, +1},
            {+1, -1}, {+1, 0}, {+1, +1}};

    private GamePanel gamePanel;

    public GameManager(int width, int height, int rows, int columns, GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.columns = columns;
        this.rows = rows;
        cellWidth = width / columns;
        cellHeight = height / rows;

        grid = new int[columns][rows];
        nextGen = new int[columns][rows];
        initializeGrid();

        GameThread gameThread = new GameThread(this);
        gameThread.startGame();
    }

    private void initializeGrid() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                grid[y][x] = (int)Math.round(Math.random());
            }
        }
    }

    public void gameLoop() {
        gamePanel.repaint();
    }

    public void paintGrid(Graphics graphics) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                Rectangle cell = new Rectangle(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
                Color color = grid[y][x] == 1 ? Color.WHITE : Color.BLACK;
                renderCell(cell, graphics, color);
            }
        }

        getNextGeneration();
        grid = nextGen;
        nextGen = new int[columns][rows];
    }

    private void getNextGeneration() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                int neighbours = countNeighbours(y, x);
                int currentState = grid[y][x];
                int nextState = getNextState(currentState, neighbours);
                nextGen[y][x] = nextState;
            }
        }
    }

    private int getNextState(int currentState, int neighbours) {
        if (currentState == 1) {
            if (neighbours == 2 || neighbours == 3)
                return 1;
            else
                return 0;
        } else {
            if (neighbours == 3)
                return 1;
            else
                return 0;
        }
    }

    private int countNeighbours(int y, int x) {
        int neighbours = 0;
        for (int[] offset : NEIGHBOURS) {
            int row = (y + offset[0] + rows) % rows;
            int col = (x + offset[1] + columns) % columns;
            neighbours += grid[row][col];
        }
        return neighbours;
    }

    private void renderCell(Rectangle cell, Graphics graphics, Color color) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(color);
        graphics2D.fillRect(cell.x, cell.y, cell.width, cell.height);
        if (color.equals(Color.WHITE)) {
            graphics2D.setColor(Color.BLACK);
            graphics2D.draw(cell);
        }
    }
}
