package sample;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public interface SaveState
{
    default void Save(Player O, String File_Name)
    {
        ObjectOutputStream out;
        try{
            out = new ObjectOutputStream(new FileOutputStream(File_Name));
            out.writeObject(O);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
