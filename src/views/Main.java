package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       Parent root = FXMLLoader.load(getClass().getResource("../views/sample.fxml"));
        primaryStage.setTitle("Registro de Contactos");
        primaryStage.setScene(new Scene(root, 516, 365));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("resourses/icon.png"));
        primaryStage.show();


        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
