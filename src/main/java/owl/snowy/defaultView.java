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
@SpringView(name = defaultView.VIEW_NAME)
public class defaultView extends VerticalLayout implements View{
    public static final String VIEW_NAME = "";
    @PostConstruct
    void init(){
        setMargin(true);
        setSpacing(true);
        addComponent(new Label("Herllo"));
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
    }
    
}
