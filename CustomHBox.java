package sample;

import javafx.scene.layout.HBox;

public class CustomHBox extends HBox
{
    private Game g;
    public Game getGame(){return g;}
    public void setGame(Game g){this.g = g;}
}

