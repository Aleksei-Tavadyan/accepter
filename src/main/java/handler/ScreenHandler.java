package handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenHandler extends AbstractHandler {
    protected static Logger logger = LoggerFactory.getLogger(ScreenHandler.class);
    private static final ScreenHandler instance = new ScreenHandler();

    public static ScreenHandler getInstance() {
        return instance;
    }


    private boolean active;

    public ScreenHandler() {

    }

    public void startScreenshotting() throws InterruptedException {
        active = true;
        while (active) {
            new ImageHandler().getSubImage(takeScreenshot());
            Thread.sleep(5000);
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}