package obst;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ColorSwitcher extends Group implements Runnable
{
    private ArrayList<String> colors;
    private Circle ball;
    private boolean collideStatus = false;
    private Random random;
    RotateTransition rot;
    public ColorSwitcher(Circle ball)
    {
        colors = new ArrayList<>();
        this.ball = ball;
        random = new Random();
        colors.add("#ff1fb3");colors.add("#ad00f8");
        colors.add("#0084ff");colors.add("#00ff44");
        gerFullSwitcher();
    }

    private void gerFullSwitcher()
    {
        Arc arc1 = genArc(50,50,90,0,"#ff1fb3");
        Arc arc2 = genArc(50,50,90,90,"#ad00f8");
        Arc arc3 = genArc(50,50,90,180,"#0084ff");
        Arc arc4 = genArc(50,50,90,270,"#00ff44");
        this.getChildren().addAll(arc1,arc2,arc3,arc4);
        rot = new RotateTransition();
        rot.setNode(this);
        rot.setCycleCount(40);
        rot.setDuration(Duration.seconds(2));
        rot.setToAngle(360);
        rot.setFromAngle(0);
        rot.play();
    }

    private Arc genArc(int i, int i1, int i2, int i3, String s) {
        Arc ar = new Arc();
        ar.setRadiusX(i); ar.setRadiusY(i1);
        ar.setLength(i2); ar.setStartAngle(i3);
        ar.setType(ArcType.ROUND);
        ar.setFill(Paint.valueOf(s));
        return ar;
    }

    private void checkCollision()
    {
        int i = (int) Shape.intersect(ball,(Shape)this.getChildren().get(0)).getBoundsInParent().getWidth();
        if(i>-1 && !collideStatus)
        {
            int x = colors.indexOf(ball.getFill().toString());
            int nextColor = (i++)%4;
            ball.setFill(Paint.valueOf(colors.get(nextColor)));
            collideStatus = true;
        }

    }
    @Override
    public void run()
    {
        while (!collideStatus)
        {
            checkCollision();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
