/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl.snowy;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author snowyowl
 */

@SpringUI
@Theme("valo")
@PreserveOnRefresh
public class MainUI extends UI{
    public static String previousView ="";
    @Autowired
    private SpringViewProvider viewProvider;
    
    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        getPage().setTitle("Chase Caynoski");
        layout.setSizeFull();
        layout.setMargin(false);
        layout.setSpacing(true);
        setContent(layout);
        
        /*final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        Button b = new Button("test");
        b.addStyleName(ValoTheme.BUTTON_LARGE);
        navigationBar.setSizeFull();
        b.addClickListener(event -> getUI().getNavigator().navigateTo("welcome"));
        layout.addComponent(b);
        layout.addComponent(navigationBar);*/
        HorizontalLayout hl = new HorizontalLayout();
        //hl.setSizeFull();
        //VerticalLayout v2 = new VerticalLayout();
        
        
        //v2.setSizeFull();
        //v2.setMargin(false);
        //v2.setSpacing(true);
        /*
        TextField tfUser = new TextField();
        PasswordField pf = new PasswordField();
        HorizontalLayout hlUser = new HorizontalLayout();
        hlUser.setSpacing(true);
        
        hlUser.addComponents(new Label("Username:"),tfUser);
        v2.addComponent(hlUser);
        hlUser.setComponentAlignment(hlUser.getComponent(0), Alignment.MIDDLE_RIGHT);
        HorizontalLayout hlPW = new HorizontalLayout();
        hlPW.setSpacing(true);
        hlPW.addComponents(new Label("Password:"),pf);
        
        hlPW.setExpandRatio(hlPW.getComponent(0), 1);
        hlPW.setComponentAlignment(hlPW.getComponent(0),Alignment.MIDDLE_LEFT);
        
        //hlPW.setComponentAlignment(hlPW.getComponent(1),Alignment.MIDDLE_RIGHT);
        v2.addComponent(hlPW);
        */
        //DefaultHorizontalLoginForm dvlf = new DefaultHorizontalLoginForm();
        //v2.addComponent(dvlf);
        //VerticalLayout vl = new VerticalLayout();
        layout.setSizeFull();
        //layout.setExpandRatio(hl, 1);
        //hl.addComponent(vl);
        //hl.setExpandRatio(vl, 1);
        //hl.addComponent(v2);
       // hl.setExpandRatio(v2,1);
        //vl.setMargin(new MarginInfo(true,true,false,true));
        //layout.addComponent(hl);
        MenuBar menu = new MenuBar();
        MenuItem menuI1 = menu.addItem("Home", (e) -> {
            getUI().getNavigator().navigateTo("");
        });
        
        MenuItem menuI2 = menu.addItem("Welcome", (e) -> {
            getUI().getNavigator().navigateTo("welcome");
            
        });
        MenuItem menuI3 = menu.addItem("Resources", null);
        menuI3.addItem("test1", e -> {
            Notification.show("test1");
        });
        hl.addComponent(menu);
        hl.setMargin(new MarginInfo(true,true,false,true));
        HorizontalLayout h2 = new HorizontalLayout();
        h2.setSpacing(true);
        hl.setWidth("100%");
        Button login = new Button("Login");
        login.addClickListener(e->{
            getUI().getNavigator().navigateTo("login");
        });
        
        h2.addComponent(login);
        hl.addComponent(h2);
        hl.setComponentAlignment(h2, Alignment.MIDDLE_RIGHT);
        hl.setSpacing(true);
        //hl.setSizeFull();
        layout.addComponent(hl);
        //vl.addComponent(menu);
        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        layout.addComponent(viewContainer);
        layout.setExpandRatio(viewContainer, 1.0f);
        Navigator navigator = new Navigator(this,viewContainer);
        navigator.addProvider(viewProvider);
        navigator.addViewChangeListener(new ViewChangeListener(){

            @Override
            public boolean beforeViewChange(ViewChangeListener.ViewChangeEvent event) {
                previousView = navigator.getState();
                //Logger.getLogger(Security.class.getName()).log(Level.INFO,previousView);
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeListener.ViewChangeEvent event) {
                Object user = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("user");
                if(user!=null){
                    if(h2.getComponentIndex(login) != -1){
                        Label l = new Label("Welcome "+((User)user).getUserName());
                        Button b = new Button("Sign out");
                        b.addClickListener(e->{
                            VaadinService.getCurrentRequest().getWrappedSession().removeAttribute("user");
                            //Notification.show("You have been Signed out");
                            navigator.navigateTo(navigator.getState());
                            
                        });
                        h2.replaceComponent(login, l);
                        h2.setComponentAlignment(l, Alignment.MIDDLE_LEFT);
                        //h2.addComponent(new Label(" "));
                        h2.addComponent(b);
                    }
                    
                }else{
                    if(h2.getComponentIndex(login) == -1){
                        h2.removeAllComponents();
                        h2.addComponent(login);
                    }
                }
            }
            
        });
        
        
        
    }
    
}
