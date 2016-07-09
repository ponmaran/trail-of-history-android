package org.trailofhistory.charmeck.ranger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import org.trailofhistory.charmeck.ranger.manager.PointOfInterestManager;
import org.trailofhistory.charmeck.ranger.model.PointOfInterest;

import java.util.List;

/**
 * Lists all points of interest currently on the trail of history
 */
public class MainActivity extends AuthenticatedActivity implements PointOfInterestManager.PointOfInterestListCallback{

    private static final String TAG = "MainActivity";

    private FloatingActionButton mFab;

    public static Intent newInstance(Context context){
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPOI();
            }
        });

        PointOfInterestManager.getInstance().getPointsofInterest(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_out:
                signOut();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void signOut() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
    }

    private void createPOI(){
        startActivity(NewPointOfInterestActivity.newInstance(this));
    }

    private void viewPOI(PointOfInterest pointOfInterest){

    }

    @Override
    public void pointsOfInterestRetrieved(List<PointOfInterest> pointOfInterestList) {
        if(pointOfInterestList != null){
            pointOfInterestList.toString();
        }

    }
}
