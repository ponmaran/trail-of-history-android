package org.trailofhistory.charmeck.ranger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import org.trailofhistory.charmeck.ranger.manager.PointOfInterestManager;
import org.trailofhistory.charmeck.ranger.model.PointOfInterest;

public class NewPointOfInterestActivity extends AppCompatActivity {

    private Button saveButton;
    private EditText nameField;

    private PointOfInterest pointOfInterest;

    public static Intent newInstance(Context context) {
        return new Intent(context, NewPointOfInterestActivity.class);
    }

    /**
     * Used for editing POI
     * @param context
     * @param pointOfInterest
     * @return
     */
    public static Intent newInstance(Context context, PointOfInterest pointOfInterest) {
        return new Intent(context, NewPointOfInterestActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_point_of_interest);

        nameField = (EditText) findViewById(R.id.poiname);

        saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAndSave();
            }
        });
    }

    private void validateAndSave(){
        if(validateForm()){
            save();
        }
    }
    private void save(){
        PointOfInterest poi = new PointOfInterest();
        poi.setName(nameField.getText().toString());
        pointOfInterest = PointOfInterestManager.getInstance().savePOI(poi, new DatabaseReference.CompletionListener() {

            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError == null){
                    //go to detail view
                    startActivity(DetailPointOfInterestActivity.newInstance(NewPointOfInterestActivity.this, pointOfInterest));
                    finish();
                }
            }
        });
    }


    private boolean validateForm(){
        boolean valid = true;

        if(TextUtils.isEmpty(nameField.getText())){
            nameField.setError(getString(R.string.error_required));
            valid = false;
        }

        return valid;
    }

}
