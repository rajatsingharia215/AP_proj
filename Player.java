package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int Star;
    private int HighScore;
    private final ArrayList<Game> List;
    Player()
    {
        this.List = new ArrayList<>();
        this.HighScore = 0;
        this.Star = 0;
    }
    public int getHighScore() {
        return HighScore;
    }

    public int getStar() {
        return Star;
    }

    public void setHighScore(int highScore) {
        HighScore = highScore;
    }

    public void setStar(int star) {
        Star = star;
    }

    public void AddGame(Game g)
    {
        boolean status = false;
        for(Game G:List)
        {
            if(G==g){status=true; break;}
        }
        if(!status) this.List.add(g);
        long star = 0;
        for(Game G:List)
        {
            star+=G.getScore();
            if(G.getScore()>HighScore)HighScore = G.getScore();
        }
        Star = (int)star;
    }
    public ArrayList<Game> getList()
    {
        return this.List;
    }
}
