package obst;

import javafx.animation.RotateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.concurrent.ExecutorService;

public class RectangleObst extends Rectangle implements Runnable {
    RotateTransition rot1;
    public int current = 0;
    private final Circle ball;
    private final AnchorPane deathScreen;
    private boolean collisionStatus=false;
    private ExecutorService exe;

    public RectangleObst(Circle ball, AnchorPane deathScreen,ExecutorService exe)
    {
        this.ball = ball; this.deathScreen = deathScreen;
        genfullObst();
        this.exe = exe;
    }

    private void genfullObst() {
        this.setWidth(200);
        this.setHeight(200);
        this.setLayoutX(300);
        this.setLayoutY(206);
        this.setFill(Paint.valueOf("#7028e4"));
        rot1 = new RotateTransition();
        rot1.setNode(this);
        rot1.setCycleCount(20);
        rot1.setToAngle(360);
        rot1.setFromAngle(0);
        rot1.setDuration(Duration.seconds(2));
        rot1.play();
    }

    private boolean checkCollision()
    {
        int i = (int) Shape.intersect(ball,this).getBoundsInParent().getWidth();
        if(i>-1 && !checkColor(this.getFill(),ball.getFill()) && !collisionStatus)
        {
            System.out.println("Game Over");
            deathScreen.setVisible(true);
            rot1.stop();
            collisionStatus = true;
            exe.shutdown();
            return true;

        }
        return false;
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
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (!collisionStatus) {
            if(checkCollision())break;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}
