package sample;
import MyTreads.SaveObject;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable, Runnable, Navigate
{
    private Player p;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private FontAwesomeIcon setting, help;
    @FXML
    private Label HighScore, stars;
    @FXML
    private Circle ball1, ball2;

    public void AcceptData(Player p)
    {
        this.p = p;
        setFields(p);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(3800));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        run();
    }

    private void setFields(Player deSerialize) {
        HighScore.setText(""+deSerialize.getHighScore());
        stars.setText(""+deSerialize.getStar());
    }

    public void close()
    {
        SaveObject savePlayer = new SaveObject(p,"player.txt");
        Thread t = new Thread(savePlayer);
        t.start();

        Stage window = (Stage) rootPane.getScene().getWindow();
        window.close();
    }
    // this method is for directing to the New Game
    public void LaunchNewGame()throws IOException
    {
        FXMLLoader loader = Switch("MainGame.fxml",(Stage) rootPane.getScene().getWindow());
        GameMain cont = loader.getController();
        cont.AcceptData(p);
    }

    public void SavedGame() throws IOException
    {
        FXMLLoader loader = Switch("GameList.fxml", (Stage) rootPane.getScene().getWindow());
        GameListController cont = loader.getController();
        cont.AcceptData(p);
    }

    private void startAnimation()
    {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1));
        scaleTransition.setCycleCount(1000);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setNode(setting);
        scaleTransition.setByY(0.5f);
        scaleTransition.setByX(0.5f);
        scaleTransition.setDelay(Duration.seconds(0));
        scaleTransition.play();

        ScaleTransition scaleTransition1 = new ScaleTransition(Duration.seconds(1));
        scaleTransition1.setCycleCount(1000);
        scaleTransition1.setAutoReverse(true);
        scaleTransition1.setNode(help);
        scaleTransition1.setByY(0.5f);
        scaleTransition1.setByX(0.5f);
        scaleTransition1.setDelay(Duration.seconds(0.5));
        scaleTransition1.play();
        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.seconds(1));
        scaleTransition2.setCycleCount(1000);
        scaleTransition2.setAutoReverse(true);
        scaleTransition2.setNode(ball1);
        scaleTransition2.setByY(12.5f);
        scaleTransition2.setByX(12.5f);
        scaleTransition2.setDelay(Duration.seconds(0.5));
        scaleTransition2.play();

        ScaleTransition scaleTransition3 = new ScaleTransition(Duration.seconds(1));
        scaleTransition3.setCycleCount(1000);
        scaleTransition3.setAutoReverse(true);
        scaleTransition3.setNode(ball2);
        scaleTransition3.setByY(12.5f);
        scaleTransition3.setByX(12.5f);
        scaleTransition3.setDelay(Duration.seconds(0.5));
        scaleTransition3.play();
    }
    @Override
    public void run() {
        startAnimation();
    }
}
