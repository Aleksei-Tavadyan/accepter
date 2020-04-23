package service;

import handler.ScreenHandler;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EventService {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);
    private static Future<?> runningTask = null;

    public static void setupAcceptEventHandler() {
        if (runningTask == null) {
            runningTask = executorService.submit(new ScreenHandler());
        } else {
            System.out.println("The application is already running");
        }
    }

    public static void setupStopEventHandler() {
        runningTask.cancel(true);
        runningTask = null;
    }

    public static void setupShutdownEventHandler() {
        executorService.shutdownNow();
    }

    public static void changeStdOutTo(TextArea textArea) {
        PrintStream ps = new PrintStream(new EventService.Console(textArea));
        System.setOut(ps);
        System.setErr(ps);
    }

    private static class Console extends OutputStream {
        private TextArea console;

        public Console(TextArea console) {
            this.console = console;
        }

        @Override
        public void write(int b) {
            Platform.runLater(() -> console.appendText(String.valueOf((char) b)));
        }
    }
}
