package obst;

import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.concurrent.ExecutorService;

public class Obstacle extends Group implements Runnable
{
    private RotateTransition rot;
    private Circle ball;
    private ExecutorService exe;
    private AnchorPane deathScreen;
    private boolean collisionStatus=false;
    public Obstacle(Circle ball, AnchorPane deathScreen, ExecutorService exe)
    {
        this.ball = ball; this.deathScreen = deathScreen; this.exe = exe;
        rot = new RotateTransition();
        genFullObst();
    }   
    private Arc genArc(double angle, String color)
    {
        Arc  arc = new Arc();
        arc.setRadiusX(100);
        arc.setRadiusY(100);
        arc.setLength(90);
        arc.setStartAngle(angle);
        // arc.setType(ArcType.ROUND);
        arc.setFill(Paint.valueOf(color));
        rot.setNode(this);
        rot.setToAngle(360);
        rot.setFromAngle(0);
        rot.setDuration(Duration.seconds(2));
        rot.setCycleCount(40);
        rot.play();
        return arc;
    }
    private void genFullObst()
    {
        Arc ar1 = genArc(0, "#ff1fb3");
        Arc ar2 = genArc(90, "#ad00f8");
        Arc ar3 = genArc(180, "#0084ff");
        Arc ar4 = genArc(270, "#00ff44");
        this.getChildren().addAll(ar1,ar2,ar3,ar4);
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
