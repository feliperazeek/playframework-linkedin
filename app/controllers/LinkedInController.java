package controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import play.Logger;
import play.Play;
import play.cache.Cache;
import play.exceptions.UnexpectedException;
import play.mvc.Controller;
import play.mvc.Router;

/**
 * The Class LinkedInController.
 */
public class LinkedInController extends Controller {

	public static String getApiKey() {
		return Play.configuration.getProperty("linkedin.apiKey");
	}

	public static String getSecret() {
		return Play.configuration.getProperty("linkedin.secret");
	}

	public static String getModel() {
		return Play.configuration.getProperty("linkedin.model");
	}

	public static String getLandUrl() {
		return Play.configuration.getProperty("linkedin.landUrl");
	}

	public static String getLoginUrl() {
		OAuthService service = getService();
		Token token = service.getRequestToken();
		Logger.info("Request Token - " + token.getToken() + " with secret "
				+ token.getSecret());
		Cache.add(token.getToken(), token.getSecret());
		return service.getAuthorizationUrl(token);
	}

	public static void login() {
		String url = getLoginUrl();
		Logger.info("Redirect: " + url);
		redirect(url);
	}

	private static OAuthService getService() {
		return new ServiceBuilder().provider(LinkedInApi.class)
				.apiKey(getApiKey()).apiSecret(getSecret())
				.callback(Router.getFullUrl("LinkedInController.callback"))
				.build();
	}

	private static OAuthService getServiceNoCallback() {
		return new ServiceBuilder().provider(LinkedInApi.class)
				.apiKey(getApiKey()).apiSecret(getSecret()).build();
	}

	public static void callback() {
		OAuthService service = getServiceNoCallback();
		Logger.info("LinkedIn Callback - Params: " + params);
		String oauthToken = params.get("oauth_token");
		String oauthVerifier = params.get("oauth_verifier");
		Verifier verifier = new Verifier(oauthVerifier);
		Logger.info("Token: " + oauthToken);
		Logger.info("Verifier: " + oauthVerifier);
		Token accessToken = service.getAccessToken(
				new Token(oauthToken, Cache.get(oauthToken, String.class)),
				verifier);
		Logger.info("LinkedIn Access Token: " + accessToken);

		if (accessToken != null && accessToken.getToken() != null) {
			try {
				Class model = Class.forName(getModel());
				Method method = model.getMethod("linkedinOAuthCallback",
						new Class[] { String.class });
				if (Modifier.isStatic(method.getModifiers())) {
					method.invoke(null, accessToken.getToken());

				} else {
					throw new UnexpectedException(
							"Method linkedinOAuthCallback method to be static");
				}
			} catch (ClassNotFoundException e) {
				throw new UnexpectedException("Cannot find your model class "
						+ getModel());

			} catch (NoSuchMethodException e) {
				throw new UnexpectedException(
						"Model class "
								+ getModel()
								+ " must provide a method with this signature: [public static void linkedinOAuthCallback(String token)]");

			} catch (IllegalAccessException e) {
				throw new UnexpectedException(
						"Module fbconnect does not have access to call your model's findForFacebookOAuth");

			} catch (InvocationTargetException e) {
				throw new UnexpectedException(
						"Module fbconnect encountered an error while calling your model's findForFacebookOAuth: "
								+ e.getMessage());
			}
		} else {
			throw new UnexpectedException("Access Token Unavailable");
		}

		redirect(getLandUrl());
	}
}
