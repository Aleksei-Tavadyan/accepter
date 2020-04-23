package screen;

import service.EventService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainScreen extends Application {
    @Override
    public void start(Stage primaryStage) {
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

        EventService.changeStdOutTo(textArea);

        acceptButton.setOnMouseClicked(event -> EventService.setupAcceptEventHandler());
        stopButton.setOnMouseClicked(event -> EventService.setupStopEventHandler());

        root.getChildren().addAll(acceptButton, stopButton, textArea);
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Dota Accepter");
        stage.setScene(scene);
        stage.show();

        stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST,
                event -> EventService.setupShutdownEventHandler());
    }
}