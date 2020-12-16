package obst;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Star extends Circle implements Runnable
{
    public int Score;
    private boolean collideStatus = false;
    private final Circle ball;
    public Star(Circle ball)throws IOException
    {
        Score = 0;
        this.ball = ball;
        genStar();
    }
    private void genStar()throws IOException
    {
        InputStream stream = new FileInputStream("D://college work//Sem-3//AP//colorSwitch//src//sample//Starimg.png");
        Image image = new Image(stream);
        this.setFill(new ImagePattern(image));
        this.setRadius(50);
    }
    private void checkCollision()
    {
        if((int)Shape.intersect(this,ball).getBoundsInParent().getWidth()>-1)
        {
            Score++;
            System.out.println("Collide");
            collideStatus = true;
            this.setVisible(false);
        }
    }
    @Override
    public void run() {
        while(!collideStatus)
        {
            checkCollision();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
