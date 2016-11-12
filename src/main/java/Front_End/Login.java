/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Front_End;

import Back_End.MainSystem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Winter Fox
 */
public class Login extends  VerticalLayout implements View{
    
    MainSystem main = new MainSystem();

    TextField tfIDLogin;
    PasswordField tfPassword;
    Button btnSignUp;
    Button btnLogin;
    
    public Login(){
        tfIDLogin = new TextField("Ваш логин/Id");
        tfPassword = new PasswordField("Пароль");
        
        HorizontalLayout HLayout = new HorizontalLayout();
        
        btnLogin = new Button("Вход");
        btnLogin.addStyleName("friendly");
        
        btnLogin.addClickListener(new Button.ClickListener(){
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    if (main.Login(tfIDLogin.getValue(), tfPassword.getValue())) {
                        //logged in successfully
                        //getUI().getNavigator().navigateTo("dashboard");
                        Notification.show("Успех");
                        //reset UI Components
                        tfIDLogin.setValue("");
                        tfPassword.setValue("");
                    }else {
                        //incorrect login notification
                        Notification.show("Incorrect Login Details!", Notification.Type.ERROR_MESSAGE);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        btnSignUp = new Button("Регистрация");
        btnSignUp.addStyleName("primary");
        
        btnSignUp.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().navigateTo("SignUpForm");
                
            }
        });
        
        
        
        HLayout.addComponent(btnLogin);
        HLayout.addComponent(btnSignUp);
        HLayout.setSpacing(true);
        
        FormLayout formLayout = new FormLayout(tfIDLogin, tfPassword, HLayout);
        formLayout.setMargin(true);
        
        Panel loginPanel = new Panel ("Добро пожаловть! Войдите в систему", formLayout);
        loginPanel.setWidth("450");
        loginPanel.setHeight("250");
        
        addComponent(loginPanel);
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        setHeight("100%");
        
        
        
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
