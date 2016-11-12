/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back_End;

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
    public static final String URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false";
    
    private static ArrayList<User> userList = new ArrayList<>();
    private static User currentUser;
    
    public ArrayList<User> getUserList() {
        return this.userList;
    }
    
    public User getCurrentUser() {
        return this.currentUser;
    }
    
    public ArrayList<Developer> getDevelopers() { //from user list
        ArrayList<Developer> teacherList = new ArrayList<>();
        for (User teacher : userList) {
            if (teacher instanceof Developer) {
                teacherList.add((Developer) teacher);
            }
        }
        return teacherList;
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
        
        ResultSet result1 = WorkerDataBase.getUsersEntity();
        
        try {
            while(result1.next()){
                if(result1.getString("user_name").equals(name) && result1.getString("user_password").equals(password)){
                    this.currentUser = (User) result1;
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
//        for (User user : userList) {
//            if (user.zID.equals(zID) && user.password.equals(password)) {
//                this.currentUser = user;
//                return true;    //move to next screen
//            }
//        }
        return false;   //show notification
    }
    
    public void Logout() {
        this.currentUser = null;
    }
    
    public void signUp(String name, String password, String type, /*Date DOB,*/ String gender) throws ClassNotFoundException, SQLException {
        
        //WorkerDataBase.addEntity("insert into bisiness.user (user_name, user_password, user_DOB, gender, type) values (\"name\", \"password\", \"DOB\", \"gender\", \"type\")");
        WorkerDataBase.addEntity("insert into bisiness.user (user_name, user_password, gender, type) values (\"name\", \"password\", \"gender\", \"type\")");

        
        
//        if (type.equals("SimpleUser")) {
//            SimpleUser newSimpleUser = new SimpleUser(zID, name, password, DOB, gender);
//            userList.add(newSimpleUser);
//        } else { //know its a teacher
//            Developer newDeveloper = new Developer(zID, name, password, DOB, gender);
//            userList.add(newDeveloper);
//        }
    }
    
    
    
    
}
