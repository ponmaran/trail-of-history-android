package org.charmeck.trailofhistory;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;
import org.charmeck.trailofhistory.core.model.PointOfInterest;

/**
 * FragmentStatePagerAdapter for the map view cards.
 *
 * Created by Chris on 10/24/16.
 */
public class PoiPagerAdapter extends FragmentStatePagerAdapter {
  private List<PointOfInterest> pois;
  private static final float PAGE_WIDTH_PERCENT = 0.90f;

  public PoiPagerAdapter(FragmentManager fm, List<PointOfInterest> pois) {
    super(fm);
    this.pois = pois;
  }

  @Override public Fragment getItem(int position) {
    PointOfInterest poi = pois.get(position);
    return PoiFragment.create(poi);
  }

  @Override public int getCount() {
    return pois.size();
  }

  @Override public float getPageWidth(int position) {
    return PAGE_WIDTH_PERCENT;
  }
}
