/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.snowy;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;

/**
 *
 * @author snowyowl
 */
@SpringView(name = Welcome.VIEW_NAME)
public class Welcome extends VerticalLayout implements View{
    public static final String VIEW_NAME = "welcome";
    @PostConstruct
    void init(){
        addComponent(new Label("welcome"));
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    
}
