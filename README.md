# play-linkedin

Easily integrate LinkedIn authentication into any Play application. 

## Demo

[http://play-lists-linkedin.appspot.com/](http://play-lists-linkedin.appspot.com/) is an instance of Guillaume's lists-with-gae demo, modified to utilize this module for LinkedIn based authentication. The source code of the modified demo can be found in the `samples-and-tests` folder.

## Prerequisites

Before you can use LinkedIn authentication, you have to [register your application with LinkedIn](http://www.linkedin.com/developers/createapp.php). Be sure to note your Application ID, API Key, and Application Secret.

## Installation

### Install the module

Install the linkedin module from the modules repository:

    play install linkedin

### Enable the module

After installing the module, add the following to your `application.conf` to enable it:

    module.linkedin=${play.path}/modules/linkedin

### Configure the module

And finally, you need to configure the module by setting these properties in your `application.conf`:

    # LinkedIn Connect
    # ~~~~~
    linkedin.id=YOUR_APP_ID_HERE
    linkedin.apiKey=YOUR_API_KEY_HERE
    linkedin.secret=YOUR_APP_SECRET_HERE
    linkedin.model=models.User
    linkedin.landUrl=/home

All of the properties are required except for `linkedin.landUrl`, which will default to `/` (Note: `linkedin.landUrl` can be a URL like `/home` or a controller action like `Application.index`).

## Usage

### Use the `linkedin.button` tag in your view

The `linkedin.button` tag outputs a link that will prompt your users to authenticate with LinkedIn when it is clicked.

	#{linkedin.button label:'Login using your linkedin account.' /}

The tag takes three optional parameters.

- `label` which defaults to *Sign in with LinkedIn*
- `cssClass` which defaults to *play-linkedin-button*
- `scope` which defaults to *email* (see [the linkedin docs](http://developers.linkedin.com/docs/authentication/permissions) for possible values of scope)

### Define your OAuth callback

Your `linkedin.model` class needs to implement a static method called `linkedinOAuthCallback`. After a user has authenticated using LinkedIn, the module will call this method with a JsonObject that contains data about the user. This is your opportunity to add the user to your database, add the user to your session, or do anything else you want to do with the authentic data LinkedIn provides. [See this table](http://developers.linkedin.com/docs/reference/api/user) for all the properties that LinkedIn makes available on the JsonObject.

    public static void linkedinOAuthCallback(JsonObject data){
        String email = data.get("email").getAsString();
        User user = findByEmail(email);
        if(user == null){
            user = new User();
            user.email = email;
            user.insert();
        }
        Session.current().put("user", user.email);
    }

### Add some style

You are welcome to style the button however you like, but the linkedin module provides some default styles that you can use to get a decent looking LinkedIn Connect button.

#### Before

![before](http://play-lists-linkedin.appspot.com/assets/images/before.jpg)

#### After

![after](http://play-lists-linkedin.appspot.com/assets/images/after.jpg)

These styles are in a file called `play-linkedin.min.css` and they can be found in the module's `lib` directory. I should note that the styles will not work if you specify a custom `cssClass` when using the `linkedin.button` tag. The button graphics are courtesy of [Rogie](http://www.komodomedia.com/blog/2009/05/sign-in-with-twitter-and-linkedin-buttons/).