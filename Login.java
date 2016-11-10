import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

/* Each table you wish to access in your database requires a model class, like this example: */
public class Login
{
    /* First, map each of the fields (columns) in your table to some public variables. */
    public String userID;
    public String password;
    public String salt;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public Login(String userID, String password, String salt)
    {
        this.userID = userID;
        this.password = password;
        this.salt = salt;
    }

    @Override public String toString()
    {
        return (userID + " " + password + " " + salt);
    }

    

    public static Login getByUserId(String userID)
    {
        Login login = null;

        PreparedStatement statement = Application.database.newStatement("SELECT userID, password FROM tblLogin WHERE userID = ?"); 

        try 
        {
            if (statement != null)
            {
                statement.setString(1, userID);
                ResultSet results = Application.database.runQuery(statement);

                if (results != null)
                {
                    login = new Login(results.getString("userID"), results.getString("password"), results.getString("salt"));
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        return login;
    }
    
    public void addLogin()
    {
        try
        {
            PreparedStatement statement;
            
            statement = Application.database.newStatement("SELECT userID FROM tblLogin ORDER BY userID DESC");
            ResultSet results = Application.database.runQuery(statement);
            System.out.println(results);
            statement = Application.database.newStatement("INSERT INTO tblLogin (userID, password, salt) VALUES (?, ?, ?)");             
                        
            statement.setString(1, userID);
            statement.setString(2, password);
            statement.setString(3, salt);
            
            if (statement != null)
            {
                Application.database.executeUpdate(statement);
            }

        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
    }
    
    public void save()
    {
        try
        {
            PreparedStatement statement;    
        
            //statement = Application.database.newStatement("SELECT userID FROM tblLogin ORDER BY userID DESC");
            //ResultSet results = Application.database.runQuery(statement); 
            statement = Application.database.newStatement("UPDATE tblLogin SET userID = ?, password = ? WHERE id = ?");             
            statement.setString(1, userID);

            if (statement != null)
            {
                Application.database.executeUpdate(statement);
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }        
    }
    
}
