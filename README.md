# Play Framework - Module play-linkedin

Easily integrate LinkedIn authentication into any Play application.


## Prerequisites

Register your application with LinkedIn (https://www.linkedin.com/secure/developer). 
LinkedIn's API documentation can be found at http://developer.linkedin.com/community/apis.


### Install the module

Install the linkedin module from the modules repository:

`
play install linkedin
`

### Enable the module

After installing the module, add the following to your application.conf to enable it:

`
module.linkedin=${play.path}/modules/linkedin
`

### Configure the module

And finally, you need to configure the module by setting these properties in your @application.conf@:

`
linkedin.apiKey=YOUR_API_KEY_HERE
linkedin.secret=YOUR_APP_SECRET_HERE
linkedin.model=models.User
linkedin.landUrl=/
`

All of the properties are required.


## Usage

### Use the linkedin.button tag in your view

The linkedin.button tag outputs a link that will prompt your users to authenticate with LinkedIn when it is clicked.

#{linkedin.button label:'Login using your LinkedIn account.' /}

* label which defaults to *Sign in with LinkedIn*
* cssClass which defaults to *play-linkedin-button*


### Define your OAuth callback

Your linkedin.model class needs to implement a static method called @linkedinOAuthCallback@. After a user has authenticated using LinkedIn, the module will call this method with a token (String). This is your opportunity to add the user to your database, add the user to your session, or do anything else you want.

public static void linkedinOAuthCallback(play.modules.linkedin.LinkedInProfile profile) {
	Logger.info("Handle LinkedIn OAuth Callback: " + token);
	User user = findByLinkedInToken(profile.getAccessToken());
	if(user == null || user.linkedInToken == null) {
		user = new User();
		user.firstName = profile.getFirstName();
		user.lastName = profile.getLastName();
		user.linkedInId = profile.getId();
		user.industry = profile.getIndustry();
		user.headline = profile.getHeadline();
		user.pictureUrl = profile.getPictureUrl();
		user.linkedInToken = token;
		user = user.save();
	} else {
		Logger.info("Found User: " + user);
	}
	if ( user == null ) {
		throw new RuntimeException("Could not store or lookup user with LinkedIn token " + token);
	}
	Session.current().put("username", token);
}

