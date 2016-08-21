# Ranger

Ranger is a companion application for the Charlotte Trail of History application. It allows admin users to update information for points of interest along the trail. The application uses Firebase for authentication and administration.

## Setting up Ranger

In order to develop for Ranger (and the other Trail of History applications) you will need a [Firebase account](https://firebase.google.com/).

After creating your account you need to do the following:

* Create a new Project in Firebase (the name doesn't matter but we use Trail of History)
* Enable Auth - Click Auth in the left column, and then click "Set up Sign-in Method"
* Enable Email/Password under (Auth -> Sign-In Method) Note: You can’t create a user until you have a valid way for them to log in)
* Create a new test user (Auth -> Add User from within the Firebase web console). The Ranger application does not currently allow users to be created from within the application itself.
* Click on Database in the left hand column to Enable Remote Database   
* Click on [Your Database Name] at the top of the left hand column and then click the green circle that says “Add Firebase to your Android app”. When asked for a package name, enter “org.charmeck.trailofhistory.ranger” and click “Add App”.
* Download the google-services.json file that is provided and put it in the root of the Ranger module directory (i.e., trail-of-history-android\ranger).


Google Maps API
* You will need to put your own Google Maps API key in the keys.xml file in order to use the mapping functionality.
[Getting an API Key](https://developers.google.com/maps/documentation/android-api/signup)
