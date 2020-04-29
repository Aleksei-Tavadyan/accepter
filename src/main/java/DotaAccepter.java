import javafx.application.Application;
import screen.MainScreen;
import server.Server;

public class DotaAccepter {
    public static void main(String[] args) {
        Server server = Server.getServer();
        Thread t = new Thread(server);
        t.start();

        Application.launch(MainScreen.class, args);
    }
}
