package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface Navigate
{
    default FXMLLoader Switch(String file_name, Stage window) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(file_name));
        Parent root = loader.load();
        window.setScene(new Scene(root));
        window.show();
        return loader;
    }
}
