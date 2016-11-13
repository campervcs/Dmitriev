/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back_End;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Winter Fox
 */
public class User {
    

    protected String zID = "Не объявлено";
    protected String name = "Не объявлено";
    protected String password = "Не объявлено";
    protected String DOB;
    protected String gender = "Не объявлено";
    protected String type = "Не объявлено";
    
    public User(){}

    public User(String zID, String name, String password, String type, String DOB, String gender) {
        this.zID = zID;
        this.name = name;
        this.password = password;
        this.DOB = DOB;
        this.gender = gender;
        this.type=type;
    }

    public String getID() {
        return this.zID;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getDOB() {
        return this.DOB;
    }

    public String getGender() {
        return this.gender;
    }
    
    public String getType() {
        return this.type;
    }

    public void setzID(String zID) {
        this.zID = zID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    

    @Override
    public String toString() {
        return this.zID + ": " + this.name;
    }
}
