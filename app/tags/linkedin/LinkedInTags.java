package tags.linkedin;

import groovy.lang.Closure;

import java.io.PrintWriter;
import java.util.Map;

import play.mvc.Router;
import play.templates.FastTags;
import play.templates.GroovyTemplate.ExecutableTemplate;

@FastTags.Namespace("linkedin")
public class LinkedInTags extends FastTags {

	public static void _button(Map<?, ?> args, Closure body, PrintWriter out,
			ExecutableTemplate template, int fromLine) {
		Object labelArg = args.get("label");
		Object classArg = args.get("cssClass");
		Object scopeArg = args.get("scope");
		String label = labelArg != null ? labelArg.toString()
				: "Sign in with LinkedIn";
		String className = classArg != null ? classArg.toString()
				: "play-linkedin-button";
		String scope = scopeArg != null ? scopeArg.toString() : null;
		// String url =
		// Play.plugin(LinkedInPlugin.class).session().getLoginUrl(scope);
		// out.println("<a href='"+url+"' class='"+className+"'>"+label+"</a>");
		out.println("<a href='" + Router.getFullUrl("LinkedInController.login")
				+ "' class='" + className + "'>" + label + "</a>");
	}

}
