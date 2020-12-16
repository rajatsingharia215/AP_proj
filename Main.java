package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main extends Application 
{

    // reading player object from the file
    private static Player DeSerialize()
    {
        ObjectInputStream in = null;
        Player p = null;
        try{
            in = new ObjectInputStream(new FileInputStream("player.txt"));
            p = (Player)in.readObject();
            in.close();
        }
        catch (Exception e){e.printStackTrace();}
        return p;
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {

        Player p = DeSerialize();
        //System.out.println(p.getHighScore());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loading.fxml"));

        Parent root = loader.load();//FXMLLoader.load(getClass().getResource("loading.fxml"));
        loadingController controller = loader.getController();
        controller.AcceptData(p);
        primaryStage.setTitle("Color Switch");
        primaryStage.setScene(new Scene(root,1280,720));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

    }


    public static void main(String[] args) 
    {
        launch(args);
    }
}
