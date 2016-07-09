package org.trailofhistory.charmeck.ranger.manager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.trailofhistory.charmeck.ranger.model.PointOfInterest;

/**
 * Created by Trey Robinson on 7/9/16.
 * Copyright 2016 MindMine LLC.
 */
public class PointOfInterestManager {

    private static PointOfInterestManager mInstance;

    private static String KEY_POI = "pointOfInterest";

    private DatabaseReference mDatabase;


    public static PointOfInterestManager getInstance(){

        if(mInstance == null){
            mInstance = new PointOfInterestManager();
        }

        return mInstance;
    }

    private PointOfInterestManager(){

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public PointOfInterest savePOI(PointOfInterest pointOfInterest, DatabaseReference.CompletionListener completionListener) {

        if(pointOfInterest.getUid() == null){
            pointOfInterest.setUid(mDatabase.child(KEY_POI).push().getKey());
        }

        mDatabase.child(KEY_POI).child(pointOfInterest.getUid()).setValue(pointOfInterest, completionListener);

        return pointOfInterest;
    }
}
