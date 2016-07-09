package org.trailofhistory.charmeck.ranger;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.trailofhistory.charmeck.ranger.model.PointOfInterest;

public class DetailPointOfInterestActivity extends AppCompatActivity {

    private static String KEY_POI = "pointOfInterest";

    private TextView nameField;

    public static Intent newInstance(Context context, PointOfInterest pointOfInterest){
        Intent intent =  new Intent(context, DetailPointOfInterestActivity.class);
        intent.putExtra(KEY_POI, pointOfInterest);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_point_of_interest);

        nameField =  (TextView) findViewById(R.id.nameField);

        PointOfInterest poi = getIntent().getParcelableExtra(KEY_POI);
        if(poi != null){
            setPointOfInterest(poi);
        }

    }

    private void setPointOfInterest(PointOfInterest pointOfInterest){

        nameField.setText(pointOfInterest.getName());

    }
}
