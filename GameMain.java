package sample;

import MyTreads.ColourChanger;
import MyTreads.MoveDown;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import obst.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameMain implements Initializable, Navigate
{
    @FXML
    private AnchorPane rootPane;
    @FXML
    private AnchorPane saveContainer;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FontAwesomeIcon play, pause;
    @FXML
    private Label starLabel;
    private Player p;
    @FXML
    private Button Savebtn, Exit, Home;
    @FXML
    private VBox ObstacleContainer;
    @FXML
    private AnchorPane deathScreen;
    @FXML
    private Circle ball;
    private ObstacleGen gen;
    private Game currentGame;
    private Scene scene;
    private ExecutorService exe;
    private Star st;
    private Star st1;
    private Star st2;
    private Star st3;
    public void AcceptData(Player p)
    {
        this.p = p;
        scrollPane.setViewOrder(1);
        this.scene = rootPane.getScene();
        scene.setOnKeyPressed(KeyEvent ->
        {
            if(KeyEvent.getCode()== KeyCode.SPACE)
            {
                ball.setLayoutY(ball.getLayoutY()-75);
                scrollPane.setVvalue(scrollPane.getVvalue()+0.01);
                starLabel.setText(currentGame.getScore()+st.Score + st1.Score + st2.Score + st3.Score+"");
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        currentGame = new Game();
        gen = new ObstacleGen(ball);
        exe = Executors.newFixedThreadPool(11);
        TriangleObst tri = gen.getTriangleObst(deathScreen,exe);
        ColorSwitcher cl1 = gen.getColorSwitcher();
        ColorSwitcher cl2 = gen.getColorSwitcher();
        ColorSwitcher cl3 = gen.getColorSwitcher();
        Obstacle circ = gen.getCircleObst(deathScreen, exe);
        PlusObst plus = gen.getPlusObst(deathScreen, exe);
        RectangleObst rect = gen.getRectangleObst(deathScreen,exe);
        ColourChanger c = new ColourChanger(rect,tri);
        try {
            st = new Star(ball);
            st1 = new Star(ball);
            st2 = new Star(ball);
            st3 = new Star(ball);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObstacleContainer.getChildren().addAll(circ, st,cl1, rect, st1, cl3, tri, st2, cl2 , plus, st3);


        MoveDown down = new MoveDown(ball, scrollPane);
        exe.execute(plus);
        exe.execute(c);
        exe.execute(circ);
        exe.execute(st);
        exe.execute(st1);
        exe.execute(st2);
        exe.execute(st3);
        exe.execute(down);
        exe.execute(tri);
        exe.execute(cl1);
        exe.execute(cl2);
        exe.execute(cl3);
        exe.execute(rect);
    }

    public void Pause()
    {
        scrollPane.setDisable(true);
        pause.setVisible(false);
        play.setVisible(true);
        saveContainer.setVisible(true);
    }
    public void ClosePauseScreen()
    {
        play.setVisible(false);
        pause.setVisible(true);
        scrollPane.setDisable(false);
        saveContainer.setVisible(false);
    }
    private void SendData(FXMLLoader loader)
    {
        HomeScreenController cont = loader.getController();
        cont.AcceptData(p);
    }

    public void AcceptGame(Game g)
    {
        this.currentGame = g;
        starLabel.setText(""+g.getScore());
    }
    public void SaveGame()throws IOException
    {
        currentGame.setScore(Integer.parseInt(starLabel.getText()));
        Date date = new Date();
        long timeStamp = date.getTime();
        Timestamp ts = new Timestamp(timeStamp);
        currentGame.setTimeStamp(ts);
        p.AddGame(currentGame);
        exe.shutdown();
        FXMLLoader loader = Switch("HomeScreen.fxml",(Stage)rootPane.getScene().getWindow());
        SendData(loader);

    }
    @FXML
    void GoToHome() throws IOException
    {
        int ScoreOfThisGame = Integer.parseInt(starLabel.getText().toString());
        if(ScoreOfThisGame>p.getHighScore())p.setHighScore(ScoreOfThisGame);
        FXMLLoader loader = Switch("HomeScreen.fxml",(Stage)rootPane.getScene().getWindow());
        SendData(loader);
        exe.shutdown();
    }
    @FXML
    void loadObstacle()
    {

    }

    @FXML
    void HomeDeselect()
    {
        Home.setStyle("-fx-background-color: #FF362E;");
    }

    @FXML
    void HomeSelect()
    {
        Home.setStyle("-fx-background-color :#45CE30;");
    }
    @FXML
    void SaveSelect() {
        Savebtn.setStyle("-fx-background-color :#45CE30;");
    }
    @FXML
    void SaveDeselect() {
        Savebtn.setStyle("-fx-background-color: #FF362E;");
    }
    @FXML
    void ExitDeselect() {
        Exit.setStyle("-fx-background-color: #FF362E;");
    }

    @FXML
    void ExitSelect() {
        Exit.setStyle("-fx-background-color :#45CE30;");
    }
}
