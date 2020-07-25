package logic;

class GameThread implements Runnable {
    private Thread thread;
    private GameManager gameManager;

    GameThread(GameManager gameManager) {
        this.gameManager = gameManager;
        thread = new Thread(this, "Game thread");
    }

    void startGame() {
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            long start = System.nanoTime();

            gameManager.gameLoop();

            long stop = System.nanoTime();
            long elapsed = stop - start;
            long fps = 10;
            long targetTime = 1000 / fps;
            long wait = targetTime - elapsed / 1000000;
            if (wait > 0) {
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
