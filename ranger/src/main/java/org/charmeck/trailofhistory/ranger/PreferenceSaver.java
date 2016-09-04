package org.charmeck.trailofhistory.ranger;

import android.os.Bundle;

/**
 * Created by alexandernohe on 8/14/16.
 */
public interface PreferenceSaver {

  void saveInstanceState(Bundle instanceState);

  String getSavedUsername();

  boolean isRememberId();

  void storeCredentials(String email, boolean rememberId);
}
