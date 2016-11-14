package org.charmeck.trailofhistory;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import org.charmeck.trailofhistory.core.model.PointOfInterest;
import org.charmeck.trailofhistory.core.ui.poi.list.PointOfInterestRecyclerView;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.poiRecyclerView) PointOfInterestRecyclerView recyclerView;
  private DatabaseReference databaseReference;


  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    databaseReference = FirebaseDatabase.getInstance().getReference();

    fetchStatues();

  }



  public void fetchStatues() {
    final ProgressDialog progressDialog =
        ProgressDialog.show(this, "Fetching Statues", "Loading...");
    databaseReference.child("pointOfInterest")
        .addListenerForSingleValueEvent(new ValueEventListener() {
          @Override public void onDataChange(DataSnapshot dataSnapshot) {
            List<PointOfInterest> pointsOfInterest = new ArrayList<>();
            for (DataSnapshot poiSnapshot : dataSnapshot.getChildren()) {
              PointOfInterest poi = poiSnapshot.getValue(PointOfInterest.class);
              pointsOfInterest.add(poi);
            }
            recyclerView.addPointsOfInterest(pointsOfInterest);
            progressDialog.dismiss();
          }

          @Override public void onCancelled(DatabaseError databaseError) {
            progressDialog.dismiss();
          }
        });
  }


/*  private void showDetail() {
    if (pointOfInterest != null) {
      startActivity(DetailActivity.newInstance(this, pointOfInterest));
    }
  }*/



  @Override protected void onDestroy() {
    super.onDestroy();
  }
}
