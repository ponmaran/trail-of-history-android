package org.charmeck.trailofhistory.ranger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by alexandernohe on 8/14/16.
 */
public class SharedPreferencesSaver implements PreferenceSaver {

  private SharedPreferences sharedPreferences;

  private static final String TAG = "PREFERENCESAVER";

  //Preferences
  private static final String CLTPREFERENCES = "CLTPREFERENCES";
  private static final String REMEMBER_ID = "remember_id";
  private static final String STORED_USERNAME = "stored_username";
  //End Preferences

  public SharedPreferencesSaver(Context applicationContext) {
    sharedPreferences =
        applicationContext.getSharedPreferences(CLTPREFERENCES, Context.MODE_PRIVATE);
  }

  @Override public void saveInstanceState(Bundle instanceState) {
    //No implementation yet.  Want to discuss with someone or someone else can implement this.
  }

  @Override public String getSavedUsername() {
    return sharedPreferences.getString(STORED_USERNAME, "");
  }

  @Override public boolean isRememberId() {
    return sharedPreferences.getBoolean(REMEMBER_ID, false);
  }

  @Override public void storeCredentials(String email, boolean rememberId) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    if (rememberId) {
      Log.d(TAG, "storing username");
      editor.putString(STORED_USERNAME, email).apply();
      editor.putBoolean(REMEMBER_ID, true).apply();
    } else {
      Log.d(TAG, "removing username");
      editor.remove(STORED_USERNAME).apply();
      editor.remove(REMEMBER_ID).apply();
    }
  }
}
