package obst;
import javafx.animation.RotateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.concurrent.ExecutorService;

public class TriangleObst extends Polygon implements Runnable
{
    RotateTransition rot1;
    private final Circle ball;
    private final String[] colors = { "#ff1fb3", "#ad00f8", "#0084ff", "#00ff44" };
    public int current = 0;
    private boolean collisionStatus = false;
    private AnchorPane deathScreen;
    private ExecutorService exe;
    public TriangleObst(Circle ball, AnchorPane deathScreen, ExecutorService exe)
    {
        genfullObst();
        this.ball = ball;
        this.deathScreen = deathScreen;
        this.exe = exe;
    }
    
    private void genfullObst()
    {
        this.getPoints().addAll(0.0,0.0, 200.0, 0.0, 100.0, 173.20508075688);
        this.setFill(Paint.valueOf(colors[0]));
        //this.setLayoutX(400);
        //this.setLayoutY(400);
        rot1 = new RotateTransition();
        rot1.setNode(this);
        rot1.setDuration(Duration.seconds(4));
        rot1.setCycleCount(20);
        rot1.setFromAngle(0);
        rot1.setToAngle(360);
        rot1.play();
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
