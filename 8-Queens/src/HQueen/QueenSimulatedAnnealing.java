
package HQueen;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class QueenSimulatedAnnealing extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("StyleTable.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

  
    public static void main(String[] args) {
        launch(args);
    }

}
