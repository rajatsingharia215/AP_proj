package obst;

import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.concurrent.ExecutorService;

public class PlusObst extends Group implements Runnable
{
    RotateTransition rot;
    private Circle ball;
    private AnchorPane deathScreen;
    private ExecutorService exe;
    private boolean collisionStatus=false;
    public PlusObst(Circle ball, AnchorPane deathScreen, ExecutorService exe)
    {
        this.ball = ball; this.deathScreen = deathScreen; this.exe=exe;
        this.rot = new RotateTransition();
        genfullObst();
    }

    private void genfullObst()
    {
        Rectangle rect1 = genRect(40, 151, 151, 0,  "#ff1fb3");
        Rectangle rect2 = genRect(151, 40, 191, 151, "#ad00f8");
        Rectangle rect3 = genRect(40, 151, 151, 191, "#0084ff");
        Rectangle rect4 = genRect(151, 40, 0, 151, "#00ff44");
        this.getChildren().addAll(rect1,rect2,rect3,rect4);
        rot.setNode(this);
        rot.setCycleCount(40);
        rot.setDuration(Duration.seconds(2));
        rot.setFromAngle(0);
        rot.setToAngle(360);
        rot.play();
    }
    private Rectangle genRect(double width, double height, double X, double Y,String color)
    {
        Rectangle rect = new Rectangle();
        rect.setWidth(width);
        rect.setHeight(height);
        rect.setLayoutX(X);
        rect.setLayoutY(Y);
        rect.setFill(Paint.valueOf(color));
        return rect;
    }
    private boolean checkColor(Paint A, Paint B)
    {
        String a = A.toString();
        String b = B.toString();
        boolean Status = true;
        for(int i=0;i<a.length();i++)
        {
            if(a.charAt(i)!=b.charAt(i))
            {
                Status = false;
                break;
            }
        }
        return Status;
    }
    private boolean checkCollision()
    {

        ObservableList arcs = this.getChildren();
        for(Object c : arcs) {

            int i = (int) Shape.intersect(ball,(Shape)c).getBoundsInParent().getWidth();
            if (i > -1 && !checkColor(((Shape)c).getFill(), ball.getFill()) && !collisionStatus) {
                System.out.println("Game Over");
                deathScreen.setVisible(true);
                rot.stop();
                collisionStatus = true;

                exe.shutdown();
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        while(!collisionStatus)
        {
            if(checkCollision())break;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
