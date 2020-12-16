package MyTreads;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import obst.Obstacle;
import obst.PlusObst;
import obst.RectangleObst;
import obst.TriangleObst;


public class CollisionThread implements Runnable
{
    private PlusObst plusObst;
    private RectangleObst rectangleObst;
    private TriangleObst triangleObst;
    private Obstacle CircularObst;
    private Circle ball;
    private AnchorPane deathScreen;
    CollisionThread(PlusObst plusObst, Obstacle CircularObst, RectangleObst rectangleObst, TriangleObst triangleObst, Circle ball, AnchorPane deathScreen)
    {
        this.CircularObst = CircularObst; this.plusObst = plusObst; this.ball = ball;
        this.rectangleObst = rectangleObst; this.triangleObst = triangleObst;
        this.deathScreen = deathScreen;
    }
    private void collisionCheck()
    {
        
    }
    @Override
    public void run()
    {

    }
}
