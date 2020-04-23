package handler;

import java.awt.*;

public abstract class AbstractHandler {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // screen resolution
    public static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
