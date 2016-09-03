package org.charmeck.trailofhistory.ranger.manager;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Trey Robinson on 7/9/16.
 */
public class AuthenticationManager {

  private static AuthenticationManager instance;

  private FirebaseAuth auth;

  public static AuthenticationManager getInstance() {

    if (instance == null) {
      instance = new AuthenticationManager();
    }

    return instance;
  }

  private AuthenticationManager() {
    auth = FirebaseAuth.getInstance();
  }

  public void addAuthStateListener(FirebaseAuth.AuthStateListener listener) {
    auth.addAuthStateListener(listener);
  }

  public void removeAuthStateListener(FirebaseAuth.AuthStateListener listener) {
    if (auth != null) {
      auth.removeAuthStateListener(listener);
    }
  }
}
