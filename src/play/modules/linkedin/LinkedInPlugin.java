package play.modules.linkedin;

import javax.naming.ConfigurationException;

import play.Play;
import play.PlayPlugin;
import play.exceptions.UnexpectedException;
import play.mvc.Router;

public class LinkedInPlugin extends PlayPlugin {
    
    private static ThreadLocal<LinkedInSession> _session = new ThreadLocal<LinkedInSession>();
    
    public LinkedInSession session(){
        return _session.get();
    }
    
    @Override
    public void onApplicationStart() {
    	LinkedInSession session = new LinkedInSession();
        session.init();
        _session.set(session);
    }
    
    @Override
    public void onRoutesLoaded() {
        Router.addRoute("GET", "/linkedin/login", "LinkedInController.login");
        Router.addRoute("GET", "/linkedin/logout", "LinkedInController.logout");
        Router.addRoute("GET", "/linkedin/oauth/callback", "LinkedInController.callback");
    }
    
}
