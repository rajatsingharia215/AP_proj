package sample;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loadingController implements Initializable, Navigate {
    @FXML
    AnchorPane rootPane;
    @FXML
    Circle ball;
    private Player p;
    public void AcceptData(Player p)
    {
        this.p = p;
    }
    private void SendData(FXMLLoader loader)
    {
        HomeScreenController controller = loader.getController();
        controller.AcceptData(p);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        TranslateTransition rot1 = new TranslateTransition(Duration.seconds(4),ball);
        rot1.setToY(-300);
        rot1.setCycleCount(5);
        rot1.setAutoReverse(true);
        rot1.setRate(10);
        rot1.play();
        FadeOut();
        rot1.setOnFinished(event -> {
            try{
                changeScreen();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        });
    }
    private void changeScreen() throws IOException
    {
        FXMLLoader loader = Switch("HomeScreen.fxml",(Stage)rootPane.getScene().getWindow());
        SendData(loader);
    }
    private void FadeOut()
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }
}

