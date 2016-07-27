package org.charmeck.trailofhistory.ranger;

/**
 * Created by Trey Robinson on 7/7/16.
 *
 * Sign in Screen for Trail of History
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.IntentCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class EmailPasswordActivity extends BaseActivity {

    private static final String TAG = "EmailPassword";

    //Preferences
    private static final String CLTPREFERENCES = "CLTPREFERENCES";
    private static final String REMEMBER_ID = "remember_id";
    private static final String STORED_USERNAME = "stored_username";
    //End Preferences

    @BindView(R.id.field_email) EditText mEmailField;
    @BindView(R.id.field_password) EditText mPasswordField;
    @BindView(R.id.bool_remember_id) CheckBox mRememberId;

    private FirebaseAuth mAuth;
    private SharedPreferences mSharedPreferences;
    private boolean mRememberIdValue;

    public static Intent newInstance(Context context){
        Intent intent =  new Intent(context, EmailPasswordActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP
                | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);

        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences(CLTPREFERENCES, MODE_PRIVATE);
        mRememberIdValue = mSharedPreferences.getBoolean(REMEMBER_ID, false);
        setContentView(R.layout.activity_emailpassword);
        ButterKnife.bind(this);

        if (mRememberIdValue) {
            Log.d(TAG, "testing setting text and checkbox");
            mRememberId.setChecked(true);
            mEmailField.setText(mSharedPreferences.getString(STORED_USERNAME, ""));
            mPasswordField.requestFocus();
        }

        mAuth = FirebaseAuth.getInstance();
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        storeCredentials(mSharedPreferences, email, mRememberId.isChecked());

        showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(MainActivity.newInstance(EmailPasswordActivity.this));
                            finish();
                        }

                        hideProgressDialog();
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError(getString(R.string.error_required));
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    /**
     * This is used to store the credentials for the user.  This only stores the username and only
     * if the rememeber Id option is valid.  Otherwise, it removes it from the shared preferences so
     * in the event someone does turn on the switch, they do not get a previous stored value.
     *
     * @param sharedPreferences - This is the shared preferences for the application.  Intentionally
     *                          passing in as a parameter for di in the future.
     * @param email - The email of the user to be stored.
     * @param rememberId - The boolean value of the checkbox so it can be stored for future use and
     *                   to retrieve the email in the onCreate method.
     */
    private void storeCredentials(SharedPreferences sharedPreferences, String email, boolean rememberId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(rememberId) {
            Log.d(TAG, "storing username");
            editor.putString(STORED_USERNAME, email).apply();
            editor.putBoolean(REMEMBER_ID, true).apply();
        }
        else {
            Log.d(TAG, "removing username");
            editor.remove(STORED_USERNAME).apply();
            editor.remove(REMEMBER_ID).apply();
        }
    }

    @OnClick(R.id.email_sign_in_button)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
                break;
            default:
                break;
        }
    }
}
