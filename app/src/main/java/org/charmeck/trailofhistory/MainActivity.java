package org.charmeck.trailofhistory;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import org.charmeck.trailofhistory.core.model.PointOfInterest;
import org.charmeck.trailofhistory.core.ui.poi.list.PointOfInterestAdapter;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.recyclerView) RecyclerView recyclerView;
  PointOfInterestAdapter poiAdapter;
  private DatabaseReference databaseReference;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    databaseReference = FirebaseDatabase.getInstance().getReference();

    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    poiAdapter = new PointOfInterestAdapter(new ArrayList<>());
    recyclerView.setAdapter(poiAdapter);

    fetchStatues();
  }

  public void fetchStatues() {
    final ProgressDialog progressDialog =
        ProgressDialog.show(this, "Fetching Statues", "Loading...");
    databaseReference.child("pointOfInterest")
        .addListenerForSingleValueEvent(new ValueEventListener() {
          @Override public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot poiSnapshot : dataSnapshot.getChildren()) {
              PointOfInterest poi = poiSnapshot.getValue(PointOfInterest.class);
              poiAdapter.addPointOfIntrest(poi);
            }
            poiAdapter.notifyDataSetChanged();
            progressDialog.dismiss();
          }

          @Override public void onCancelled(DatabaseError databaseError) {
            progressDialog.dismiss();
          }
        });
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }
}
