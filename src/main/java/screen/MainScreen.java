package screen;

import handler.ScreenHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.OutputStream;
import java.io.PrintStream;

public class MainScreen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        showMainScreen();
    }

    public void showMainScreen() {
        VBox root = new VBox();

        Button acceptButton = new Button("Accept game when found");
        acceptButton.setPrefWidth(200);
        acceptButton.setPrefHeight(100);

        Button stopButton = new Button("Stop");
        stopButton.setPrefWidth(200);
        stopButton.setPrefHeight(100);

        TextArea textArea = new TextArea();
        textArea.setPrefWidth(400);
        textArea.setPrefHeight(200);

        PrintStream ps = new PrintStream(new Console(textArea));
        ScreenHandler screenHandler = new ScreenHandler(ps);

        Thread thread = new Thread(screenHandler);
        thread.setDaemon(true);

        acceptButton.setOnMouseClicked(event -> thread.start());

        stopButton.setOnMouseClicked(event -> screenHandler.setStopped());

        root.getChildren().addAll(acceptButton, stopButton, textArea);
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Dota Accepter");
        stage.setScene(scene);
        stage.show();
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