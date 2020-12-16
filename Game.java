package sample;

import java.io.Serializable;
import java.sql.Timestamp;

public class Game implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int Score;
    private Timestamp timestamp;

    public Timestamp getTimeStamp() {
        return timestamp;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        timestamp = timeStamp;
    }
}
