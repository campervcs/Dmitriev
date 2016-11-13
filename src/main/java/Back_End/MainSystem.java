/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back_End;

import com.vaadin.ui.Notification;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Winter Fox
 */
public class MainSystem {
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/business";
    
    private static ArrayList<User> userList = new ArrayList<>();
    private static User currentUser;
    
    public ArrayList<User> getUserList() {
        return this.userList;
    }
    
    public User getCurrentUser() {
        return this.currentUser;
    }
    
    public ArrayList<Developer> getDevelopers() { //from user list
        ArrayList<Developer> DeveloperList = new ArrayList<>();
        for (User Developer : userList) {
            if (Developer instanceof Developer) {
                DeveloperList.add((Developer) Developer);
            }
        }
        return DeveloperList;
    }

    public ArrayList<SimpleUser> getSimpleUser() { //from user list
        ArrayList<SimpleUser> studentList = new ArrayList<>();
        for (User student : userList) {
            if (student instanceof SimpleUser) {
                studentList.add((SimpleUser) student);
            }
        }
        return studentList;
    }
    
    public Boolean Login(String name, String password) throws ClassNotFoundException, SQLException  {
        
        Boolean result = WorkerDataBase.isAcceptUser(name, password);
        return result;
        
        
//        for (User user : userList) {
//            if (user.zID.equals(zID) && user.password.equals(password)) {
//                this.currentUser = user;
//                return true;    //move to next screen
//            }
//        }
        //return false;   //show notification
    }
    
    public void Logout() {
        this.currentUser = null;
    }
    
    public void signUp(String name, String password, String type, Date DOB, String gender) throws ClassNotFoundException, SQLException {
        
        
        String insertTableSQL = "INSERT INTO user (user_name, user_password, user_DOB, gender, type)" 
            + " VALUES ( '"+name+"' , '"+password+"', '"+DOB+"' , '"+gender+"' , '"+type+"'  )";
        
        //Notification.show(insertTableSQL);
        
        //WorkerDataBase.addEntity("insert into bisiness.user (user_name, user_password, user_DOB, gender, type) values (\"name\", \"password\", \"DOB\", \"gender\", \"type\")");
        WorkerDataBase.addEntity(insertTableSQL);
        
        
//        if (type.equals("SimpleUser")) {
//            SimpleUser newSimpleUser = new SimpleUser(zID, name, password, DOB, gender);
//            userList.add(newSimpleUser);
//        } else { //know its a teacher
//            Developer newDeveloper = new Developer(zID, name, password, DOB, gender);
//            userList.add(newDeveloper);
//        }
    }
    
    
    
    
}
