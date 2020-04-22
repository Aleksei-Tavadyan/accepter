package handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public abstract class AbstractHandler {
    protected static Logger logger = LoggerFactory.getLogger(AbstractHandler.class);
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
