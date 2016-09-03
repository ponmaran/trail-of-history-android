package org.charmeck.trailofhistory.ranger;

/**
 * Created by Trey Robinson on 7/7/16.
 *
 * Sign in Screen for Trail of History
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.IntentCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EmailPasswordActivity extends BaseActivity {

  private static final String TAG = "EmailPassword";

  @BindView(R.id.field_email) EditText emailField;
  @BindView(R.id.field_password) EditText passwordField;
  @BindView(R.id.bool_remember_id) CheckBox rememberId;

  private FirebaseAuth auth;
  private boolean rememberIdValue;
  private PreferenceSaver preferenceSaver;

  public static Intent newInstance(Context context) {
    Intent intent = new Intent(context, EmailPasswordActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
        | Intent.FLAG_ACTIVITY_CLEAR_TOP
        | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);

    return intent;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    preferenceSaver = new SharedPreferencesSaver(getApplicationContext());
    rememberIdValue = preferenceSaver.isRememberId();
    setContentView(R.layout.activity_emailpassword);
    ButterKnife.bind(this);

    if (rememberIdValue) {
      Log.d(TAG, "testing setting text and checkbox");
      rememberId.setChecked(true);
      emailField.setText(preferenceSaver.getSavedUsername());
      passwordField.requestFocus();
    }

    auth = FirebaseAuth.getInstance();
    if (auth.getCurrentUser() != null) {
      Log.d(TAG, "user is already logged in: " + auth.getCurrentUser().getEmail());
      startActivity(MainActivity.newInstance(EmailPasswordActivity.this));
      finish();
    }
  }

  private void signIn(String email, String password) {
    Log.d(TAG, "signIn:" + email);
    if (!validateForm()) {
      return;
    }

    showProgressDialog();

    // [START sign_in_with_email]
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
          @Override public void onComplete(@NonNull Task<AuthResult> task) {
            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

            // If sign in fails, display a message to the user. If sign in succeeds
            // the auth state listener will be notified and logic to handle the
            // signed in user can be handled in the listener.
            if (!task.isSuccessful()) {
              Log.w(TAG, "signInWithEmail", task.getException());
              Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                  Toast.LENGTH_SHORT).show();
            } else {
              preferenceSaver.storeCredentials(task.getResult().getUser().getEmail(),
                  rememberId.isChecked());
              startActivity(MainActivity.newInstance(EmailPasswordActivity.this));
              finish();
            }

            hideProgressDialog();
          }
        });
  }

  private boolean validateForm() {
    boolean valid = true;

    String email = emailField.getText().toString();
    if (TextUtils.isEmpty(email)) {
      emailField.setError("Required.");
      valid = false;
    } else {
      emailField.setError(null);
    }

    String password = passwordField.getText().toString();
    if (TextUtils.isEmpty(password)) {
      passwordField.setError(getString(R.string.error_required));
      valid = false;
    } else {
      passwordField.setError(null);
    }

    return valid;
  }

  @OnClick(R.id.email_sign_in_button) public void onClick(View v) {
    switch (v.getId()) {
      case R.id.email_sign_in_button:
        signIn(emailField.getText().toString(), passwordField.getText().toString());
        break;
      default:
        break;
    }
  }

  //This will remove the stored email instantly when the checkbox is changed.
  @OnClick(R.id.bool_remember_id) public void onCheckedChanged(View v) {
    if (!((CheckBox) v).isChecked()) {
      preferenceSaver.storeCredentials("", rememberId.isChecked());
    }
  }
}
