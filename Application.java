import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application
{
    public static void main(String[] args)
    {
        JFXPanel panel = new JFXPanel();        
        Platform.runLater(() -> start());  
    }
    
    public static void start()
    {
        try
        {         
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("LoginScene.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Login Demo");
            stage.setScene(new Scene(loader.load()));
            stage.show();           
            loginController controller = loader.getController();
            controller.prepareStageEvents(stage);
        }
        catch (Exception ex)    // If anything goes wrong starting the application then call the terminate method.
        {
            System.out.println(ex.getMessage());
            terminate();
        }        
    }
    public static void terminate()    
    {
       System.exit(0);                                 
    }
}