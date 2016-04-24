/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.snowy;

import com.vaadin.server.VaadinService;
import com.vaadin.ui.Notification;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 *
 * @author snowyowl
 */
public class Security {
    JdbcTemplate template;
    private SqlRowSet srs;
    public Security(JdbcTemplate _template){
        template = _template;
    }
    public boolean login(String username, String password){
       
        if(userExists(username)){
            
            try {
                //try {
                srs = template.queryForRowSet("Select password from user where username = ?",username);
                srs.first();
                //back to previous page
                if(PasswordHash.verifyPassword(password, srs.getString("password"))){
                    srs = template.queryForRowSet("Select * from user where username = ?",username);
                    srs.first();
                    VaadinService.getCurrentRequest().getWrappedSession().setAttribute("user", new User(Integer.toUnsignedLong(srs.getInt("id")),srs.getString("fname"),srs.getString("lname"),srs.getString("username")));
                    return true;
                }else{
                    Notification.show("invalid password");
                    return false;
                }
                
                // Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
                // Notification.show("exception");
                //return false;
                
                //
                
                // 
            } catch (PasswordHash.CannotPerformOperationException | PasswordHash.InvalidHashException ex) {
                Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
                Notification.show("exception");
                return false;
            }
            
        }else{
            Notification.show("user doesnt exist");
           return false; 
        }
        
        
    }
    public boolean addUser(String username,String password,String fname, String lname){
        
        
        try {
            String ss = PasswordHash.createHash(password);
            
            template.update("insert into user (fname,lname,username,password) values (?,?,?,?)", fname,lname,username,ss);
            return true;
        } catch (PasswordHash.CannotPerformOperationException ex) {
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
            
            
            
        
    }
    public boolean userExists(String username){
        srs=  template.queryForRowSet("Select username from user where username = ?",username);
       srs.first();
        return srs.getString("username").equals(username);
           
    }
    
    
}
