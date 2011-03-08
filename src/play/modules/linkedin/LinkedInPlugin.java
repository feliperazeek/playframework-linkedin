package play.modules.linkedin;

import javax.naming.ConfigurationException;

import play.Play;
import play.PlayPlugin;
import play.exceptions.UnexpectedException;
import play.mvc.Router;

/**
 * The Class LinkedInPlugin.
 * 
 * @author Felipe Oliveira (http://geeks.aretotally.in)
 * @copyright Felipe Oliveira (http://geeks.aretotally.in)
 */
public class LinkedInPlugin extends PlayPlugin {
    
    /**
     * Start
     * 
     * @see play.PlayPlugin#onApplicationStart()
     */
    @Override
    public void onApplicationStart() {
    	// Do Nothing
    }
    
    /**
     * Routes
     * 
     * @see play.PlayPlugin#onRoutesLoaded()
     */
    @Override
    public void onRoutesLoaded() {
        Router.addRoute("GET", "/linkedin/login", "LinkedInController.login");
        Router.addRoute("GET", "/linkedin/logout", "LinkedInController.logout");
        Router.addRoute("GET", "/linkedin/oauth/callback", "LinkedInController.callback");
    }
    
}
