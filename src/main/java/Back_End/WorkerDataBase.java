/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back_End;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.vaadin.ui.Notification;
import java.net.URI;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Winter Fox
 */
public class WorkerDataBase {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/business";
    //static Statement statement = null;
    
    //static ResultSet resultSet=null;
    
    
    public static Connection getConnection() throws Exception{
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return conn;
    }
    
    
    
    public static void addEntity(String SQLQuery) throws ClassNotFoundException, SQLException{
        Statement statement = null;
        Connection connection=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            statement = connection.createStatement();
            statement.executeUpdate(SQLQuery);
        } catch (Exception ex) {
            Logger.getLogger(WorkerDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    static ArrayList<User> allUserList = new ArrayList<User>();
    public static ArrayList<User> getAllEntity(){
        String sqlQuery="SELECT * FROM user";
        ResultSet resultSet=null;
        Connection con = null;
        Statement statement = null;
        try {
            con = getConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        } catch (Exception ex) {
            Logger.getLogger(WorkerDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            while(resultSet.next()) {
                User user = new User();
                user.setzID(resultSet.getString("user_id"));
                user.setName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("user_password"));
                user.setDOB(resultSet.getString("user_DOB"));
                user.setGender(resultSet.getString("gender"));
                user.setType(resultSet.getString("type"));
                allUserList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkerDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUserList;
    }
    
    
    
    
    
    public static Boolean isAcceptUser(String name, String password) throws ClassNotFoundException, SQLException{
        String sqlQuery="SELECT * FROM business.user WHERE user_name='"+name+"' and user_password='"+password+"'";
        ResultSet resultSet=null;
        Connection con = null;
        Statement statement = null;
        try {
            con = getConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        } catch (Exception ex) {
            Logger.getLogger(WorkerDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        int i=0;
        while(resultSet.next()){
            i++;
        }
        if(i==1) {
            return true;
        }
        else return false;
            
    }
    
    
}
