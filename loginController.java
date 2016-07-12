import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;

public class loginController
{
    private static Stage stage;
    private Login login;
    
    @FXML private Pane backgroundPane;
    @FXML private TextField userID;
    @FXML private PasswordField passID;
    @FXML private Button loginID;
    @FXML private Button cancelID;
    @FXML private Button newUserID;
    
    public loginController()
    {
        System.out.println("Login page loaded");
        if(stage != null)
        {
            System.out.println("Error, duplicate controller - terminating application!");
            System.exit(-1);
        }
    }
    
    @FXML   void initialize()
    {
        try
        {
            assert backgroundPane != null : "Can't find background pane.";
            assert userID != null : "Can't find username box.";
            assert passID != null : "Can't find password box.";
            assert loginID != null : "Can't find login button.";
            assert cancelID != null : "Can't find cancel button.";
            assert newUserID != null : "Can't find new user button.";
        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }
    }
    
    public void prepareStageEvents(Stage stage)
    {
        System.out.println("Preparing login events...");

        this.stage = stage;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Close button was clicked!");
                    Application.terminate();
                }
            });
    }
    
    @FXML   void loginClicked()
    {
        login = Login.getByUserId(userID.getText());
        System.out.println("Password is " + login.password);
        if(passID.getText().equals(login.password)){
            System.out.println("New user button clicked");
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("MainMenuScene.fxml"));
    
            try
            {
                Stage stage3 = new Stage();
                stage3.setTitle("Main Menu");
                stage3.setScene(new Scene(loader.load()));
                stage3.show();           
                newUserController controller3 = loader.getController();
                controller3.prepareStageEvents(stage3);
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }             
        }else{
            System.out.println("Login not verified");
        }
    }
    
    @FXML   void cancelClicked()
    {
        System.out.println("Login cancelled, closing application");
        Application.terminate();
    }
    
    @FXML   void newUserClicked()
    {
        System.out.println("New user button clicked");
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("NewUserScene.fxml"));

        try
        {
            Stage stage2 = new Stage();
            stage2.setTitle("New User");
            stage2.setScene(new Scene(loader.load()));
            stage2.show();           
            newUserController controller2 = loader.getController();
            controller2.prepareStageEvents(stage2);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        } 
    }
}