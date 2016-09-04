package org.charmeck.trailofhistory;

import android.app.Application;
import timber.log.Timber;

public class TrailOfHistoryApp extends Application {

  @Override public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
