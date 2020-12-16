package obst;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application 
{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
//        AnchorPane pane = new AnchorPane();
//
//        Obstacle obst = new Obstacle();
//        PlusObst obst2 = new PlusObst();
//        obst2.setLayoutX(200);
//        obst2.setLayoutY(200);
//        obst.setLayoutX(640.0);
//        obst.setLayoutY(360.0);
//        RotateTransition rot = new RotateTransition();
//        rot.setNode(obst);
//        rot.setFromAngle(0);
//        rot.setToAngle(360);
//        rot.setCycleCount(10);
//        rot.setDuration(Duration.seconds(2));
//        rot.setAutoReverse(true);
//        rot.play();
//        RectangleObst rect = new RectangleObst();
//        TriangleObst tri = new TriangleObst();
//        Thread t2 = new Thread(tri);
//        Thread t = new Thread(rect);
//        t.start();
//        t2.start();
//        Scene root = new Scene(pane, 1280, 720);
//        pane.getChildren().addAll(obst,obst2, rect, tri);
//        primaryStage.setScene(root);
//        primaryStage.show();
    }
    
}
