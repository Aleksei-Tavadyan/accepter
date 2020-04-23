package handler;

import java.awt.*;
import java.awt.event.InputEvent;

public class MouseHandler extends AbstractHandler {
    private static final MouseHandler instance = new MouseHandler();

    public static MouseHandler getInstance() {
        return instance;
    }

    public MouseHandler() {

    }

    public boolean acceptGame() {
        try {
            Point location = MouseInfo.getPointerInfo().getLocation();
            int xPrev = ((int) location.getX());
            int yPrev = (int) location.getY();
            robot.delay(1000);
            robot.mouseMove(screenSize.width / 2, screenSize.height / 2);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseMove(xPrev, yPrev);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
