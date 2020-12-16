package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

class ColorSwitch implements Runnable {
    private Rectangle box;
    private Circle ball;
    private Button UP;
    private Button DOWN;
    private ArrayList<String> Colors;

    ColorSwitch(Rectangle box, Circle ball, Button UP, Button Down) {
        this.UP = UP;
        this.DOWN = Down;
        this.box = box;
        this.ball = ball;
        Colors = new ArrayList<>();
        Colors.add("#ff1fb3");
        Colors.add("#ad00f8");
        Colors.add("#0084ff");
        Colors.add("#00ff44");
    }

    public void CheckColision() {

        int x = (int) Shape.intersect(box, ball).getBoundsInParent().getWidth();
        System.out.println("x : " + x);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        int x = (int) Shape.intersect(box, ball).getBoundsInParent().getWidth();
        if (x > -1) {
            // UP.setDisable(true);
            // DOWN.setDisable(true);
            System.out.println("Game OVER");
            Random rand = new Random();
            int i = rand.nextInt(Colors.size());
            ball.setFill(Paint.valueOf(Colors.get(i)));

        }
    }
}

class CollisionThread implements Runnable {

    private Circle ball;
    private ObservableList<Node> list;
    private Group g;
    boolean status = false;
    CollisionThread(Group g, Circle ball) {
        this.list = g.getChildren();
        this.g = g;
        this.ball = ball;
    }

    private boolean checkColor(Paint A, Paint B) {
        boolean status = true;
        String a = A.toString();
        String b = B.toString();
        if (a.length() != b.length())
            return false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                status = false;
                break;
            }
        }
        return status;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
        while (!status) {
            for (Node n : list) {
                Rectangle N = (Rectangle) n;
                int i = (int) Shape.intersect(N, ball).getBoundsInParent().getWidth();
                if (i > -1) {
                    boolean color = checkColor(N.getFill(), ball.getFill());
                    if (!color) {
                        status = true;
                        System.out.println("GameOver");
                    }
                }
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        
    }
    
}

public class ballCont implements Initializable {

    private Thread t;
    private Group g;
    //private ColThread col;
    @FXML
    private AnchorPane root;
    @FXML
    private Rectangle box;
    @FXML
    private Circle ball;
    @FXML
    private Button UP, DOWN;
    private boolean Collision= false;
    private RotateTransition rot;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ball.setFill(Paint.valueOf("#00ff44"));
        
        
        // plusObst ob = new plusObst();
        // g = ob.getMyObst();
        // rot = new RotateTransition();
        // rot.setNode(g);

        // rot.setCycleCount(60);
        // rot.setDuration(Duration.seconds(10));
        // rot.setFromAngle(0);
        // rot.setToAngle(360);
        // rot.play();
        // root.getChildren().addAll(g);
        // CollisionThread colt = new CollisionThread(g, ball);
        // t = new Thread(colt);
        // t.start();
        // box.setFill(Paint.valueOf("#F5BCBA"));
        //col = new ColThread(box, ball, UP, DOWN);
        // ball.setOnKeyPressed(new EventHandler<KeyEvent>() {
        //     @Override
        //     public void handle(KeyEvent event) {
        //         if (event.getCode() == KeyCode.A) {
        //             ball.setLayoutY(ball.getLayoutY() - 10);
        //         }
        //     }
        // });
        // TODO Auto-generated method stub
        // root.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
        // if(key.getCode()==KeyCode.ENTER) {
        // System.out.println("Hii");
        // ball.setLayoutY(ball.getLayoutY()-10);
        // }
        // });
    }

    @FXML
    public void MoveDown(ActionEvent event) {
        ball.setLayoutY(ball.getLayoutY() + 10);
        //t = new Thread(col);
        // t.start();
        // if (t.isAlive())
        //     System.out.println("Thread Running");
    }

    private boolean checkColor(Paint A, Paint B)
    {
        boolean status=true;
        String a = A.toString();
        String b = B.toString();
        if(a.length()!=b.length())return false;
        for(int i=0;i<a.length();i++)
        {
            if(a.charAt(i)!=b.charAt(i))
            {
                status=false; break;
            }
        }
        return status;
    }
    @FXML
    public void MoveUp(ActionEvent event) {
        // ObservableList<Node> list = g.getChildren();
        // for(Node n:list)
        // {
        //     int i = (int) Shape.intersect((Shape) n, ball).getBoundsInParent().getWidth();
        //     //System.out.println("value of I : "+i);
        //     if(i>-1 )
        //     {
        //         System.out.println("Color of Rectangle : "+((Rectangle)n).getFill());
        //         System.out.println("Color of Ball : "+ball.getFill());
        //         boolean b = checkColor(((Rectangle)n).getFill(), ball.getFill());
        //         System.out.println("Status of colors : "+b);
        //         if( !b && !Collision)
        //         {
        //             rot.stop();
        //             Collision = true;
        //             System.out.println("Game Over");
        //         // UP.setDisable(true); DOWN.setDisable(true);
        //             break;
        //         }
        //     }
        // }
        if(!Collision)ball.setLayoutY(ball.getLayoutY()-10);
        // if(!Collision)
        // {
        //     ball.setLayoutY(ball.getLayoutY()-10);
        //     t = new Thread(col);
        //     t.start();
        //     if(t.isAlive())System.out.println("Thread Running");
        // }
    }
}
