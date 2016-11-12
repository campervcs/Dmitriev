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
    

    protected String zID;
    protected String name;
    protected String password;
    protected Date DOB;
    protected String gender;

    public User(String zID, String name, String password, Date DOB, String gender) {
        this.zID = zID;
        this.name = name;
        this.password = password;
        this.DOB = DOB;
        this.gender = gender;
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

    public Date getDOB() {
        return this.DOB;
    }

    public String getGender() {
        return this.gender;
    }

    public String getType() {
        return "";
    }

    @Override
    public String toString() {
        return this.zID + ": " + this.name;
    }
}
