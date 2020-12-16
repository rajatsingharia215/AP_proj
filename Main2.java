package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        Parent root = FXMLLoader.load(getClass().getResource("obstacle.fxml"));
        primaryStage.setScene(new Scene(root,1280,720));
        primaryStage.show();
    }

        
}
