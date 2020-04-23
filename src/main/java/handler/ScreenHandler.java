package handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScreenHandler extends AbstractHandler implements Runnable {
    private int multiplier = 5;
    private AtomicBoolean flag;

    public ScreenHandler(AtomicBoolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        startScreenshotting();
    }

    public void startScreenshotting()  {
        int time = 0;
        while (true) {
            System.out.println(String.format("Running %d sec", time++ * multiplier));
            if (ImageHandler.getInstance().checkSubImage(takeScreenshot())) {
                MouseHandler.getInstance().acceptGame();
                System.out.println("Game accepted");
                flag.getAndSet(false);
                break;
            } else {
                try {
                    Thread.sleep(1000 * multiplier);
                } catch (InterruptedException e) {
                    System.out.println("Stopped");
                    flag.getAndSet(false);
                    break;
                }
            }
        }
    }

    /**
     * Takes single screenshot
     *
     * @return screenshot
     */
    public BufferedImage takeScreenshot() {
        return robot.createScreenCapture(new Rectangle(screenSize));
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }
}
