/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Back_End;

import java.util.Date;

/**
 *
 * @author Winter Fox
 */
public class Developer extends User{
    public Developer(String zID, String name, String password, Date DOB, String gender) {
        super(zID, name, password, DOB, gender);
    }
    
    public String getType() {
        return "Developer";
    }
}
