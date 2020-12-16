package MyTreads;

import javafx.scene.paint.Paint;
import obst.RectangleObst;
import obst.TriangleObst;

public class ColourChanger implements Runnable
{
    private String[] colors = { "#ff1fb3", "#ad00f8", "#0084ff", "#00ff44" };
    private RectangleObst rect;
    private TriangleObst tri;
    public ColourChanger(RectangleObst rect, TriangleObst tri)
    {
        this.rect = rect; this.tri = tri;
    }
    private void ChangeColor() {
        rect.setFill(Paint.valueOf(colors[rect.current]));
        tri.setFill(Paint.valueOf(colors[tri.current]));
        rect.current = (rect.current + 1) % 4;
        tri.current = (tri.current +1 )%4;
    }
    @Override
    public void run() {
        while(true)
        {
            ChangeColor();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
