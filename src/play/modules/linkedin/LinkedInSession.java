package play.modules.linkedin;

import java.net.URLEncoder;

import play.Play;
import play.exceptions.UnexpectedException;
import play.libs.WS;
import play.mvc.Http;
import play.mvc.Router;

public class LinkedInSession {
	
	private String apiKey;
	private String secret;
	private String model;
	private String landUrl;

	public String getApiKey() {
		return apiKey;
	}

	public String getSecret() {
		return secret;
	}

	public String getModel() {
		return model;
	}

	public String getLandUrl() {
		return landUrl;
	}

	public String getLoginUrl() {
		return getLoginUrl(null);
	}

	public String getLoginUrl(String authCode) {
		return Router.getFullUrl("LinkedInController.login");
	}

	public void init() {
		if (!Play.configuration.containsKey("linkedin.apiKey")) {
			throw new UnexpectedException(
					"Module linkedin requires that you specify linkedin.apiKey in your application.conf");
		}
		if (!Play.configuration.containsKey("linkedin.secret")) {
			throw new UnexpectedException(
					"Module linkedin requires that you specify linkedin.secret in your application.conf");
		}
		if (!Play.configuration.containsKey("linkedin.model")) {
			throw new UnexpectedException(
					"Module linkedin requires that you specify linkedin.model in your application.conf");
		}
		if (!Play.configuration.containsKey("linkedin.landUrl")) {
			Play.configuration.setProperty("linkedin.landUrl", "/");
		}
		apiKey = Play.configuration.getProperty("linkedin.apiKey");
		secret = Play.configuration.getProperty("linkedin.secret");
		model = Play.configuration.getProperty("linkedin.model");
		landUrl = Play.configuration.getProperty("linkedin.landUrl");
	}

}
