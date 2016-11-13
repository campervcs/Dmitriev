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
public class SimpleUser extends User{
    public SimpleUser(String zID, String name, String password, String type, String DOB, String gender) {
        super(zID, name, password, type, DOB, gender);
    }
    
    public String getType() {
        return "Пользователь";
    }
}
