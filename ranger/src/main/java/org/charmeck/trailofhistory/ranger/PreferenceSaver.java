package org.charmeck.trailofhistory.ranger;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by alexandernohe on 8/14/16.
 */
public interface PreferenceSaver {


    public void saveInstanceState(Bundle InstanceState);

    public String getSavedUsername();

    public boolean isRememberId();

    public void storeCredentials(String email, boolean rememberId);
}
