package sample;

import java.io.*;
import java.util.Scanner;

public class Buffer
{
    public static void PrintData(Player p)
    {
        System.out.println("HighScore : "+p.getHighScore());
        System.out.println("Star : "+p.getStar());
    }
    public static void Serialize(int HighScore, int Stars)throws IOException
    {
        Player p = new Player();
        p.setHighScore(HighScore);
        p.setStar(Stars);
        ObjectOutputStream out;
        try{
            out = new ObjectOutputStream(new FileOutputStream("player.txt"));
            out.writeObject(p);
            out.close();
        }
        catch (NotSerializableException e){e.printStackTrace();}
    }
    public static void DeSerialize()throws IOException
    {
        Player p;
        ObjectInputStream in;
        try{
            in = new ObjectInputStream(new FileInputStream("player.txt"));
            p = (Player)in.readObject();
            in.close();
            PrintData(p);
        }
        catch (NotSerializableException | ClassNotFoundException e){e.printStackTrace();}
    }

    public static void main(String[] args)throws IOException
    {
        Scanner scan = new Scanner(System.in);
        Serialize(scan.nextInt(),scan.nextInt());
        DeSerialize();
        scan.close();

    }
}
