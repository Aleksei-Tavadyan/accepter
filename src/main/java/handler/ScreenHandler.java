package handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenHandler extends AbstractHandler implements Runnable {
    int multiplier = 5;

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
                break;
            } else {
                try {
                    Thread.sleep(1000 * multiplier);
                } catch (InterruptedException e) {
                    System.out.println("Stopped");
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
}
