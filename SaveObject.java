package MyTreads;

import sample.Player;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveObject implements Runnable
{
    private final Player p; private final String filename;
    public SaveObject(Player p, String File_Name)
    {
        this.p = p; this.filename = File_Name;
    }
    public void Save()
    {
        ObjectOutputStream out;
        try{
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(p);
            out.close();
        } catch (NoClassDefFoundError | IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run()
    {
        Save();
    }
}
