package handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.PrintStream;

public class ScreenHandler extends AbstractHandler implements Runnable {
    private boolean isStopped = false;

    public ScreenHandler(PrintStream ps) {
        System.setOut(ps);
        System.setErr(ps);
    }

    @Override
    public void run() {
        startScreenshotting();
    }

    public void startScreenshotting()  {
        while (!isStopped) {
            System.out.println("Running...");
            if (ImageHandler.getInstance().checkSubImage(takeScreenshot())) {
                MouseHandler.getInstance().acceptGame();
                System.out.println("Game accepted");
                break;
            } else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Some problems happened... Trying to continue working");
                }
            }
        }

        isStopped = false;
    }

    /**
     * Takes single screenshot
     *
     * @return screenshot
     */
    public BufferedImage takeScreenshot() {
        return robot.createScreenCapture(new Rectangle(screenSize));
    }

    public void setStopped() {
        isStopped = true;
        System.out.println("Stopped");
    }
}
