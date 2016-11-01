# Trail of History

This is the main module for the Trail of History App. Remember to seup Firebase and 
Google Maps to get the application up and running.

## Setup

### Firebase
In order to develop you will need a [Firebase account](https://firebase.google.com/).

After creating your account you need to do the following:

1. Create a new Project in Firebase (the name doesn't matter but we use Trail of History)
* Enable Auth - Click Auth in the left column, and then click "Set up Sign-in Method"
* Enable Email/Password under (Auth -> Sign-In Method) Note: You can’t create a user until you have a valid way for them to log in)
* Create a new test user (Auth -> Add User from within the Firebase web console). The application does not currently allow users to be created from within the application itself.
* Click on Database in the left hand column to Enable Remote Database   
* Click on [Your Database Name] at the top of the left hand column and then click the green circle that says “Add Firebase to your Android app”. When asked for a package name, enter “org.charmeck.trailofhistory” and click “Add App”.
* Download the google-services.json file that is provided and put it in the root of the App module directory (i.e., trail-of-history-android\app).

### Google Maps API
You will need to add your own Google Maps API key in order for the application to work.

* The maps api key should be put in a string resources file named keys.xml 
* The .gitignore file excludes keys.xml so your private key doesn't get uploaded to the git repo. Remember, it's supposed to be a private key :)
* The Manifest is specifically looking for the string named "google_maps_api_key." Here is a drop in format example of keys.xml:
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
  <string name="google_maps_api_key">[YOUR_KEY_HERE]</string>
</resources>
```
* For more instructions see here: [Getting an API Key](https://developers.google.com/maps/documentation/android-api/signup)