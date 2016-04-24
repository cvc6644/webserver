/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.snowy;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author snowyowl
 */
@SpringView(name = Login.VIEW_NAME)
public class Login extends VerticalLayout implements View{
    @Autowired
    JdbcTemplate template;
    public static final String VIEW_NAME = "login";
    
    
    @PostConstruct
    void init(){
        setMargin(true);
        setSpacing(true);
        setSizeFull();
        Security ss = new Security(template);
        //GridLayout gl = new GridLayout();
        //gl.setColumns(2);
        //gl.setRows(3);
        //HorizontalLayout h1 = new HorizontalLayout();
        FormLayout fl = new FormLayout();
        //HorizontalLayout h2 = new HorizontalLayout();
        
        TextField tf = new TextField("Username:");
        tf.setRequired(true);
        tf.setRequiredError("A Username is Required");
        tf.setIcon(FontAwesome.USER);
        PasswordField pf = new PasswordField("Password:");
        pf.setRequiredError("A Password Is Required");
        pf.setRequired(true);
        pf.setIcon(FontAwesome.LOCK);
        //h1.addComponents(new Label("Username:"),tf);
        //h2.addComponents(new Label("Password:"),pf);
        Button b = new Button("Login");
        b.addClickListener((e)->{
            
            
            if(!ss.login(tf.getValue(), pf.getValue()))
            {
                if(fl.getComponent(0).getClass() != Label.class){
                    if(tf.isEmpty() || pf.isEmpty()){
                        Label fail = new Label("<label style=\"color:rgb(255,0,0)\">Please Enter a Username and Password</label>",Label.CONTENT_RAW);
                        fl.addComponentAsFirst(fail);
                    }else{
                        Label fail = new Label("<label style=\"color:rgb(255,0,0)\">Invalid Username or Password</label>",Label.CONTENT_RAW);
                        fl.addComponentAsFirst(fail);
                    }
                    
                }else{
                    if(tf.isEmpty() || pf.isEmpty()){
                        Label fail = new Label("<label style=\"color:rgb(255,0,0)\">Please Enter a Username and Password</label>",Label.CONTENT_RAW);
                        fl.replaceComponent(fl.getComponent(0), fail);
                    }else{
                        Label fail = new Label("<label style=\"color:rgb(255,0,0)\">Invalid Username or Password</label>",Label.CONTENT_RAW);
                        fl.replaceComponent(fl.getComponent(0), fail);
                    }
                }
                
                //"Invalid Username or Password"
                //fail.setIcon(FontAwesome.CROSSHAIRS);
                
            }else{
                tf.clear();
                pf.clear();
                if(!MainUI.previousView.equals("login")){
                    this.getUI().getNavigator().navigateTo(MainUI.previousView);
                }else{
                    this.getUI().getNavigator().navigateTo("");
                }
                
                
                
            }
            
        });
        //gl.setSpacing(true);
        Button nu = new Button("New User");
        nu.addClickListener(e->{
            
            
            
            ss.addUser("test5", "password", "Chase", "Caynoski");
                
            
           
            
        });
       
       fl.addComponents(tf,pf,b,nu);
        //gl.addComponents(new Label("Username:"),tf,new Label("Password:"),pf);
        //gl.addComponent(b, 1, 2);
        //gl.setComponentAlignment(gl.getComponent(0, 0), Alignment.MIDDLE_RIGHT);
        //gl.setComponentAlignment(gl.getComponent(0, 1), Alignment.MIDDLE_RIGHT);
        //addComponents(h1,h2,b);
        //addComponent(gl);
       addComponent(fl);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
}
