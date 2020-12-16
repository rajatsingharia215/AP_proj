package sample;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import obst.*;

import java.util.concurrent.ExecutorService;

public class ObstacleGen
{
    private final Circle ball;

    public ObstacleGen(Circle ball) {
        this.ball = ball;
    }

    public Obstacle getCircleObst(AnchorPane deathScreen, ExecutorService exe){return new Obstacle(ball, deathScreen, exe);}
    public PlusObst getPlusObst(AnchorPane deathScreen, ExecutorService exe) {
        return new PlusObst(ball, deathScreen, exe);
    }
    public RectangleObst getRectangleObst(AnchorPane deathScreen, ExecutorService exe) {
        return new RectangleObst(ball,deathScreen,exe);
    }
    public TriangleObst getTriangleObst(AnchorPane deathScreen, ExecutorService exe) {
        return new TriangleObst(ball,deathScreen,exe);
    }
    public ColorSwitcher getColorSwitcher() {return new ColorSwitcher(ball);}

}
