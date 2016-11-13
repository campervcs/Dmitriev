package Front_End;



import Back_End.WorkerDataBase;
import static Back_End.WorkerDataBase.PASSWORD;
import static Back_End.WorkerDataBase.URL;
import static Back_End.WorkerDataBase.USERNAME;
import com.mysql.fabric.jdbc.FabricMySQLDriver;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
//    public static final String USERNAME = "root";
//    public static final String PASSWORD = "root";
//    public static final String URL = "jdbc:mysql://localhost:3306/business";
//    
//    static ResultSet resultSet=null;
    
    
    
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
//        Statement statement = null;
//        Connection connection=null;
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//            
//            statement = connection.createStatement();
//            statement.executeUpdate(
//                    "INSERT INTO user (user_name, user_password, type)" 
//            + " VALUES ('aaaa', 'aaaa', 'ffffff' )");
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        Navigator navigator = new Navigator(this, this);
        
        navigator.addView("Login", new Login());
        navigator.addView("SignUpForm", new SignUpForm());
        
        navigator.navigateTo("Login");
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
