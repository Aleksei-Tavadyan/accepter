import handler.ScreenHandler;

public class Main {
    public static void main(String[] args) {
        try {
            new ScreenHandler().startScreenshotting();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
