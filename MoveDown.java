package MyTreads;

import javafx.scene.control.ScrollPane;
import javafx.scene.shape.Circle;

public class MoveDown implements Runnable
{
    private final Circle ball;
    private final ScrollPane scrollPane;
    private final double StartY = 620;
    public MoveDown(Circle ball, ScrollPane scrollPane){
        this.ball = ball;
        this.scrollPane = scrollPane;
    }
    void Move()
    {
        if(ball.getLayoutY()<StartY)ball.setLayoutY(ball.getLayoutY()+5);
        //if(scrollPane.getVvalue()>0)scrollPane.setVvalue(scrollPane.getVvalue()-0.01);
    }
    @Override
    public void run()
    {
        while(true)
        {
            Move();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
