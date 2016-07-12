import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.lang.NullPointerException;

public class newUserController
{
    private static Stage stage;
    private loginController parent;
    private PasswordEncryptionService pes = new PasswordEncryptionService();
    private Login login2;
    
    @FXML private Pane backgroundPane;
    @FXML private TextField fnID;
    @FXML private TextField snID;
    @FXML private TextField usernameID;
    @FXML private PasswordField passID;
    @FXML private PasswordField passConfID;
    @FXML private Button submitID;
    @FXML private Button cancelID;
    
    public newUserController()
    {
        System.out.println("New user page loaded");
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
            assert fnID != null : "Can't find username box.";
            assert snID != null : "Can't find password box.";
            assert usernameID != null : "Can't find login button.";
            assert passID != null : "Can't find cancel button.";
            assert passConfID != null : "Can't find cancel button.";
            assert submitID != null : "Can't find submit button.";
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
    
    @FXML   void submitClicked() throws NoSuchAlgorithmException, InvalidKeySpecException
    {
            byte[] salt = pes.generateSalt();
            byte[] password = pes.getEncryptedPassword(passID.getText(), salt);
            String username = usernameID.getText();
            System.out.println("Username = " + username + ", Password = " + password + ", salt = " + salt);
            
            login2.userID = username;
            login2.password = password.toString();
            login2.salt = salt.toString();
            login2.addLogin();
            
    }
    
    @FXML   void cancelClicked()
    {
        System.out.println("New user option cancelled, closing application");
        stage.close();
        this.stage=null;
    }
    
    @FXML   void newUserClicked()
    {
        
    }
    
    public void setParent(loginController parent)
    {
        this.parent = parent;
    }
}