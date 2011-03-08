package tags.linkedin;

import groovy.lang.Closure;

import java.io.PrintWriter;
import java.util.Map;

import play.mvc.Router;
import play.templates.FastTags;
import play.templates.GroovyTemplate.ExecutableTemplate;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkedInTags.
 * 
 * @author Felipe Oliveira (http://geeks.aretotally.in)
 * @copyright Felipe Oliveira (http://geeks.aretotally.in)
 */
@FastTags.Namespace("linkedin")
public class LinkedInTags extends FastTags {

	/**
	 * LinkedIn Login Tag
	 * 
	 * @param args
	 *            the args
	 * @param body
	 *            the body
	 * @param out
	 *            the out
	 * @param template
	 *            the template
	 * @param fromLine
	 *            the from line
	 */
	public static void _button(Map<?, ?> args, Closure body, PrintWriter out,
			ExecutableTemplate template, int fromLine) {
		Object labelArg = args.get("label");
		Object classArg = args.get("cssClass");
		String label = labelArg != null ? labelArg.toString()
				: "<img src='http://developer.linkedin.com/servlet/JiveServlet/downloadImage/102-1182-2-1070/152-21/log-in-linkedin-small.png' border='0'>";
		String className = classArg != null ? classArg.toString()
				: "play-linkedin-button";
		out.println("<a href='" + Router.getFullUrl("LinkedInController.login")
				+ "' class='" + className + "'>" + label + "</a>");
	}

}
