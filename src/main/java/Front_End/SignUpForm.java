/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Front_End;

import Back_End.MainSystem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Winter Fox
 */
public class SignUpForm extends  VerticalLayout implements View{

    MainSystem main = new MainSystem();
    
    
    
    Label lblTitle;
    Label lblHeader;
    TextField tfID;
    TextField tfName;
    PasswordField tfPassword;
    DateField dfDOB;
    ComboBox cbType;
    Button btnConfirm;
    Button btnCancel;
    OptionGroup opGender;
    
    public SignUpForm(){
        
        setSpacing(true);
        setMargin(true);
        
        lblTitle = new Label("Регистрация");
        lblTitle.addStyleName("h1");
        addComponent(lblTitle);
        
        
        FormLayout formLayout = new FormLayout();
        formLayout.setMargin(false);
        formLayout.setWidth("700");
        formLayout.addStyleName("light");
        addComponent(formLayout);
        
        lblHeader = new Label("Информация о пользователе");
        lblHeader.addStyleName("h2");
        lblHeader.addStyleName("colored");
        formLayout.addComponent(lblHeader);
        
//        tfID = new TextField("zID");
//        tfID.setRequired(true);
//        formLayout.addComponent(tfID);
        
        tfName = new TextField("Никнейм");
        tfName.setRequired(true);
        formLayout.addComponent(tfName);
        
        tfPassword = new PasswordField("Пароль");
        tfPassword.setRequired(true);
        formLayout.addComponent(tfPassword);
        
        dfDOB = new DateField("День рождения");
        dfDOB.setDateFormat("dd-MM-yyyy");
        dfDOB.setValue(new java.util.Date());
        formLayout.addComponent(dfDOB);
        
        opGender = new OptionGroup("Пол");
        opGender.addItem("Мужчина");
        opGender.addItem("Женщина");
        opGender.addStyleName("horizontal");
        formLayout.addComponent(opGender);
        
        ArrayList<String> listType = new ArrayList<>();
        listType.add("Пользователь");
        listType.add("Разработчик");
        
        cbType = new ComboBox("Тип пользователя", listType);
        cbType.setRequired(true);
        cbType.setTextInputAllowed(false);
        formLayout.addComponent(cbType);
        
        btnConfirm = new Button("Далее");
        btnConfirm.addStyleName("primary");
        
        
        btnConfirm.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    main.signUp(tfName.getValue(), tfPassword.getValue(),cbType.getValue().toString(), dfDOB.getValue(), opGender.getCaption());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(SignUpForm.class.getName()).log(Level.SEVERE, null, ex);
                }
               Notification.show("Регистрация прошла успешно!");
               tfName.setValue("");
               tfPassword.setValue("");
               getUI().getNavigator().navigateTo("Login");
            }
        });
        
        
        btnCancel = new Button("Отмена");
        btnCancel.addStyleName("danger");
        
        btnCancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
               
               getUI().getNavigator().navigateTo("Login");
            }
        });
        
        HorizontalLayout  footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        formLayout.addComponent(footer);
        footer.addComponent(btnConfirm);
        footer.addComponent(btnCancel);
        
        
        
        
        
        
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
