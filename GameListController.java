package sample;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GameListController implements Initializable, Runnable, Navigate
{
    @FXML
    private Circle ball1, ball2, ball3, ball4, ball5;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private VBox ListContainer;
    private Player p;
    private Label getTextLabel()
    {
        Label label = new Label();
        label.setFont(Font.font("Montserrat-Regular",20.0));
        label.setTextFill(Paint.valueOf("WHITE"));
        return label;
    }
    private CustomHBox getMyHBox()
    {
        CustomHBox h = new CustomHBox();
        h.setPrefWidth(443);
        h.setAlignment(Pos.CENTER);
        h.setSpacing(10);
        h.setStyle("-fx-background-color: #2F363F");
        h.setCursor(Cursor.HAND);
        h.setOnMouseEntered(mouseEvent -> {
            DropShadow shadow = new DropShadow();
            shadow.setBlurType(BlurType.GAUSSIAN);
            shadow.setColor(Color.rgb(0,255,99,0.6));
            shadow.setSpread(0.24);
            shadow.setWidth(21.16); shadow.setHeight(21.27);
            shadow.setRadius(10);
            h.setEffect(shadow);
        });
        h.setOnMouseExited(mouseEvent -> h.setEffect(null));
        h.setOnMouseClicked(mouseEvent -> {
            try {
                LoadGame(h.getGame());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return h;
    }
    private void AddGame()
    {
        ArrayList<Game> list = p.getList();
        if(list.size()>0)
        for(Game g : list)
        {
            Label score = getTextLabel();
            score.setText("Score : "+g.getScore());
            Label timeStamp = getTextLabel();
            timeStamp.setText("Time: "+g.getTimeStamp().toString());
            CustomHBox h = getMyHBox();
            h.setGame(g);
            h.getChildren().addAll(score,timeStamp);
            ListContainer.getChildren().addAll(h);
        }

    }
    public void AcceptData(Player p)
    {
        this.p = p;
        AddGame();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        run();
    }

    private void startAnimation()
    {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1));
        scaleTransition.setCycleCount(1000);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setNode(ball1);
        scaleTransition.setByY(10.5f);
        scaleTransition.setByX(10.5f);
        scaleTransition.setDelay(Duration.seconds(0));
        scaleTransition.play();

        ScaleTransition scaleTransition1 = new ScaleTransition(Duration.seconds(1));
        scaleTransition1.setCycleCount(1000);
        scaleTransition1.setAutoReverse(true);
        scaleTransition1.setNode(ball2);
        scaleTransition1.setByY(-1.5f);
        scaleTransition1.setByX(-1.5f);
        scaleTransition1.setDelay(Duration.seconds(0.1));
        scaleTransition1.play();

        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.seconds(1));
        scaleTransition2.setCycleCount(1000);
        scaleTransition2.setAutoReverse(true);
        scaleTransition2.setNode(ball3);
        scaleTransition2.setByY(5.5f);
        scaleTransition2.setByX(5.5f);
        scaleTransition2.setDelay(Duration.seconds(0));
        scaleTransition2.play();

        ScaleTransition scaleTransition3 = new ScaleTransition(Duration.seconds(1));
        scaleTransition3.setCycleCount(1000);
        scaleTransition3.setAutoReverse(true);
        scaleTransition3.setNode(ball4);
        scaleTransition3.setByY(3.5f);
        scaleTransition3.setByX(3.5f);
        scaleTransition3.setDelay(Duration.seconds(0.2));
        scaleTransition3.play();

        ScaleTransition scaleTransition4 = new ScaleTransition(Duration.seconds(1));
        scaleTransition4.setCycleCount(1000);
        scaleTransition4.setAutoReverse(true);
        scaleTransition4.setNode(ball5);
        scaleTransition4.setByY(-1.5f);
        scaleTransition4.setByX(-1.5f);
        scaleTransition4.setDelay(Duration.seconds(0));
        scaleTransition4.play();
    }

    @Override
    public void run()
    {
        startAnimation();
    }
    public void close()
    {
        Stage window = (Stage) rootPane.getScene().getWindow();
        window.close();
    }
    private void SendData(FXMLLoader loader)
    {
        HomeScreenController cont = loader.getController();
        cont.AcceptData(p);
    }

    public void GoToHomeScreen()throws IOException
    {
        FXMLLoader loader = Switch("HomeScreen.fxml",(Stage)rootPane.getScene().getWindow());
        SendData(loader);
    }
    public void LoadGame(Game g)throws IOException
    {
        FXMLLoader loader = Switch("MainGame.fxml",(Stage)rootPane.getScene().getWindow());
        GameMain cont = loader.getController();
        cont.AcceptData(p);
        cont.AcceptGame(g);
    }
}
