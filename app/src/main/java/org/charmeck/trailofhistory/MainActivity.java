package org.charmeck.trailofhistory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.charmeck.trailofhistory.model.PointOfInterest;
import org.charmeck.trailofhistory.poi.PointOfInterestAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    PointOfInterestAdapter poiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<PointOfInterest> pointOfInterestList = new ArrayList<>();

        for (int i = 0; i < 15 ; i++) {
            pointOfInterestList.add(new PointOfInterest("Location " + Integer.toString(i), "Description of location"));
        }

        poiAdapter = new PointOfInterestAdapter(pointOfInterestList);
        recyclerView.setAdapter(poiAdapter);

    }

}