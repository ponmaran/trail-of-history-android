package org.charmeck.trailofhistory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import org.charmeck.trailofhistory.core.model.PointOfInterest;

/**
 * Fragment manages showing a card view of a POI
 *
 * Created by Chris on 10/24/16.
 */
public class PoiFragment extends Fragment {
  private static final String POI_KEY = "POI_KEY";

  @BindView(R.id.statue_name) TextView infoText;
  @BindView(R.id.distance) TextView distance;

  public static PoiFragment create(PointOfInterest poi) {
    PoiFragment fragment = new PoiFragment();
    Bundle args = new Bundle();
    args.putParcelable(POI_KEY, poi);
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_poi_card, container, false);
    ButterKnife.bind(this, rootView);
    Bundle args = getArguments();
    PointOfInterest poi = args.getParcelable(POI_KEY);
    infoText.setText(poi != null ? poi.getName() : "");
    distance.setText("300yds"); // FIXME add the distance calculations and set the value here
    return rootView;
  }
}
