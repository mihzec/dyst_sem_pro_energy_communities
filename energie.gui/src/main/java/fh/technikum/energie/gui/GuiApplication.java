package fh.technikum.energie.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));

        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Energie Communities");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
