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

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public Login(String userID, String password)
    {
        this.userID = userID;
        this.password = password;
    }

    @Override public String toString()
    {
        
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
                    login = new Login(results.getString("userID"), results.getString("password"));
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }

        return books;
    }
    
    public void addLogin()
    {
        try
        {
            PreparedStatement statement;
            
            statement = Application.database.newStatement("SELECT userID FROM books ORDER BY ISBN DESC");
            ResultSet results = Application.database.runQuery(statement);
            statement = Application.database.newStatement("INSERT INTO books (ISBN, title, authorfn, authorsn, year, genre, category, language, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");             
                        
            
            statement.setString(1, ISBN);
            statement.setString(2, title);
            statement.setString(3, authorfn);
            statement.setString(4, authorsn);
            statement.setInt(5, year);
            statement.setString(6, genre);
            statement.setString(7, category);
            statement.setString(8, language);
            statement.setFloat(9, rating);
            
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
        
            statement = Application.database.newStatement("SELECT ISBN FROM books ORDER BY ISBN DESC");
            ResultSet results = Application.database.runQuery(statement); 
            statement = Application.database.newStatement("UPDATE books SET isbn = ?, title = ? authorfn = ? authorsn = ? year = ? genre = ? category = ? language = ? rating = ? WHERE id = ?");             
            statement.setString(1, ISBN);
            statement.setString(2, title);
            statement.setString(3, authorfn);
            statement.setString(4, authorsn);
            statement.setInt(5, year);
            statement.setString(6, genre);
            statement.setString(7, category);
            statement.setString(8, language);
            statement.setFloat(9, rating);
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
