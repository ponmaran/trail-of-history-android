package org.charmeck.trailofhistory;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.charmeck.trailofhistory.poi.PointOfInterestAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.recyclerView) RecyclerView recyclerView;
  PointOfInterestAdapter poiAdapter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    poiAdapter = new PointOfInterestAdapter(new ArrayList<>());
    recyclerView.setAdapter(poiAdapter);

    fetchStatues();
  }

  public void fetchStatues() {
//    final ProgressDialog progressDialog = ProgressDialog.show(this, "Fetching Statues", "Loading...");
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }
}
