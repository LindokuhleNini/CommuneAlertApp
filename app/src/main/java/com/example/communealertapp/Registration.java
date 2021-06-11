package com.example.communealertapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    private View mProgressView;
    private TextView tvLoginLink;
    private View mRegisterFormView;
    private FirebaseUser currentUser;
    private EditText Username, UserEmail, UserPassword, PasswordRetype;
    private Button btnRegister;

    private FirebaseAuth mAuth;
    private DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        InitializeAllFields();
        mAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();

        tvLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToLoginActivity();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });
    }

    private void CreateNewAccount() {
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();
        final String username = Username.getText().toString();

        if (Username.getText().toString().isEmpty() || UserEmail.getText().toString().isEmpty() ||
                UserPassword.getText().toString().isEmpty() || PasswordRetype.getText().toString().isEmpty())
        {
            Toast.makeText(Registration.this, "Please enter all details", Toast.LENGTH_SHORT).show();
        }
        else{
            showProgress(true);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                           if (task.isSuccessful()){

                               String currentUserID = mAuth.getCurrentUser().getUid();
                               rootRef.child("Users").child(currentUserID).setValue(username);

                               sendUserToMainActivity();
                               Toast.makeText(Registration.this, "Account created successfully...", Toast.LENGTH_SHORT).show();
                               showProgress(false);
                           }
                           else{
                               String message = task.getException().toString();
                               Toast.makeText(Registration.this, "Error : "+message, Toast.LENGTH_SHORT).show();
                               showProgress(false);
                           }

                        }
                    });
        }
    }

    private void InitializeAllFields() {
        mRegisterFormView = findViewById(R.id.registerForm);
        mProgressView = findViewById(R.id.registration_progress);
        tvLoginLink = findViewById(R.id.tvLoginLink);
        Username = findViewById(R.id.register_Username);
        UserEmail = findViewById(R.id.register_email);
        UserPassword = findViewById(R.id.register_Password);
        PasswordRetype = findViewById(R.id.register_PasswordRetype);
        btnRegister = findViewById(R.id.btnRegister);
    }


    private void sendUserToLoginActivity() {
        Intent loginIntent = new Intent(Registration.this, Login.class);
        startActivity(loginIntent);
    }

    private void sendUserToMainActivity() {

        Intent intent = new Intent(Registration.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegisterFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}