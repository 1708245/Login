package com.example.red;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OrganisationLoginActivity extends AppCompatActivity {

    private EditText mEmail1, mPassword1;
    private Button mLogin1, mRegistration1;
    TextView forgotTextLink12;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_login);

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(OrganisationLoginActivity.this, Organisationloggedin.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        mEmail1 = (EditText) findViewById(R.id.Email2);
        mPassword1 = (EditText) findViewById(R.id.Password22);

        mLogin1 = (Button) findViewById(R.id.Loginbutton2);
        mRegistration1 = (Button) findViewById(R.id.button22);
        forgotTextLink12 = findViewById(R.id.forgotPassword2);

        mRegistration1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(getApplicationContext(), Register_page.class));
                onclickmethod123();
            }
        });


        mLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail1.getText().toString();
                final String password = mPassword1.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    mEmail1.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword1.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6) {
                    mPassword1.setError("Password Must be >= 6 Characters");
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(OrganisationLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(OrganisationLoginActivity.this, "sign in error", Toast.LENGTH_SHORT).show();
                        }
                    }

                })
                ;
            }
        });
        forgotTextLink12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText Resetmail12 = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialog1 = new AlertDialog.Builder(view.getContext());
                passwordResetDialog1.setTitle("Reset Password?");
                passwordResetDialog1.setMessage("Enter You Email To Receive Reset Link.");
                passwordResetDialog1.setView(Resetmail12);

                passwordResetDialog1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail = Resetmail12.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(OrganisationLoginActivity.this, "Reset Link Sent To Your EMail.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(OrganisationLoginActivity.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });


                    }
                });


                passwordResetDialog1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                passwordResetDialog1.create().show();

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }

        private void onclickmethod123() {
            Intent intent = new Intent(this, Register_organization_page.class);
            startActivity(intent);
        }

    @Override
    public void onBackPressed() {

    }

    }

