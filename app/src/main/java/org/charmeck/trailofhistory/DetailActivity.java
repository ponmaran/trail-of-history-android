package org.charmeck.trailofhistory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import org.charmeck.trailofhistory.core.model.PointOfInterest;

/**
 * Created by JFF on 10/18/2016.
 *
 */

public class DetailActivity extends AppCompatActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
  @BindView(R.id.detail_textView) TextView detail_textView;

  private static final String KEY_POI = "pointOfInterest";


  public static Intent newInstance(Context context, PointOfInterest pointOfInterest) {
    Intent intent = new Intent(context, DetailActivity.class);
    intent.putExtra(KEY_POI, pointOfInterest);
    return intent;
  }


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    ButterKnife.bind(this);

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    // note this can be set here or in the xml layout
    //collapsingToolbar.setContentScrimColor(
    //                       ContextCompat.getColor(this, R.color.colorPrimaryDark));

    detail_textView.setText(dummyData());

    //PointOfInterest mPOI = (PointOfInterest) PointOfInterest.CREATOR.createFromParcel(poi);

      }


  private String dummyData() {

    String lorem =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. "
            + "Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. "
            + "Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. "
            + "Mauris massa. Vestibulum lacinia arcu eget nulla. Class aptent taciti sociosqu ad "
            + "litora torquent per conubia nostra, per inceptos himenaeos. "
            + "Curabitur sodales ligula in libero. \n"
            + "\n"
            + "Sed dignissim lacinia nunc. Curabitur tortor. Pellentesque nibh. Aenean quam. "
            + "In scelerisque sem at dolor. Maecenas mattis. Sed convallis tristique sem. "
            + "Proin ut ligula vel nunc egestas porttitor. Morbi lectus risus, iaculis vel, "
            + "suscipit quis, luctus non, massa. Fusce ac turpis quis ligula lacinia aliquet. "
            + "Mauris ipsum. Nulla metus metus, ullamcorper vel, tincidunt sed, euismod in, nibh. "
            + "Quisque volutpat condimentum velit. Class aptent taciti sociosqu ad litora torquent "
            + "per conubia nostra, per inceptos himenaeos. \n"
            + "\n"
            + "Nam nec ante. Sed lacinia, urna non tincidunt mattis, tortor neque adipiscing diam, "
            + "a cursus ipsum ante quis turpis. Nulla facilisi. Ut fringilla. Suspendisse potenti. "
            + "Nunc feugiat mi a tellus consequat imperdiet. Vestibulum sapien. Proin quam. Etiam "
            + "ultrices. Suspendisse in justo eu magna luctus suscipit. Sed lectus. Integer euismod "
            + "lacus luctus magna. Quisque cursus, metus vitae pharetra auctor, sem massa mattis "
            + "sem, at interdum magna augue eget diam. \n"
            + "\n"
            + "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia "
            + "Curae; Morbi lacinia molestie dui. Praesent blandit dolor. Sed non quam. In vel mi "
            + "sit amet augue congue elementum. Morbi in ipsum sit amet pede facilisis laoreet. "
            + "Donec lacus nunc, viverra nec, blandit vel, egestas et, augue. Vestibulum tincidunt "
            + "malesuada tellus. Ut ultrices ultrices enim. Curabitur sit amet mauris. Morbi in dui "
            + "quis est pulvinar ullamcorper. Nulla facilisi. Integer lacinia sollicitudin massa. \n"
            + "\n"
            + "\n";

    return lorem;
  }


  /*     PointOfInterest poi = getIntent().getParcelableExtra(KEY_POI);
    if (poi != null) {
      setPointOfInterest(poi);
    }*/

}