/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back_End;

import Front_End.MyUI;
import static Front_End.MyUI.PASSWORD;
import static Front_End.MyUI.URL;
import static Front_End.MyUI.USERNAME;
import com.mysql.fabric.jdbc.FabricMySQLDriver;
import java.net.URI;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Winter Fox
 */
public class WorkerDataBase {
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false";
    static Statement statement = null;
    public static void addEntity(String SQLQuery) throws ClassNotFoundException, SQLException{
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.createStatement();
        statement.executeUpdate(SQLQuery);
        
    }
    
    public static ResultSet getUsersEntity() throws ClassNotFoundException, SQLException{
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM business.user");
            
    }
    
    
}
