package com.example.red;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class UserLoginActivity extends AppCompatActivity {
    public static final int Google_SIgnIn_Code = 10005;
    SignInButton google;
    GoogleSignInOptions gso;
    GoogleSignInClient signInClient;
    private EditText mEmail, mPassword;
    private Button mLogin, mRegistration;
     TextView forgotTextLink1;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        google = findViewById(R.id.google);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("541692718714-a4od4uo4gi94h8ch0iuiinc1g3tk878a.apps.googleusercontent.com")
                .requestEmail()
                .requestProfile()
                .requestId()
                .build();
        signInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null) {
            startActivity(new Intent(this,Home_page.class));
            // Toast.makeText(this, "User is ALready Logged in", Toast.LENGTH_SHORT).show();
        }

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sign = signInClient.getSignInIntent();
                startActivityForResult(sign,
                        Google_SIgnIn_Code);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(UserLoginActivity.this, Userloggedin.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        mEmail = (EditText) findViewById(R.id.Email1);
        mPassword = (EditText) findViewById(R.id.Password1);

        mLogin = (Button) findViewById(R.id.Loginbutton1);
        mRegistration = (Button) findViewById(R.id.Organisation11);
        forgotTextLink1 = findViewById(R.id.Forgotpasswordlink);

        mRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(getApplicationContext(), Register_page.class));
                onclickmethod2();
            }
        });


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(UserLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(UserLoginActivity.this, "sign in error", Toast.LENGTH_SHORT).show();
                        }
                    }

                })
                ;
            }
        });

        forgotTextLink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText Resetmail1 = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialog1 = new AlertDialog.Builder(view.getContext());
                passwordResetDialog1.setTitle("Reset Password?");
                passwordResetDialog1.setMessage("Enter You Email To Receive Reset Link.");
                passwordResetDialog1.setView(Resetmail1);

                passwordResetDialog1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail = Resetmail1.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UserLoginActivity.this,"Reset Link Sent To Your EMail.",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UserLoginActivity.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();

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
    public void onclickmethod2() {
        Intent intent = new Intent(this, Register_donor_page.class);
        startActivity(intent);
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == Google_SIgnIn_Code) {
                Task<GoogleSignInAccount> signInTask = GoogleSignIn.getSignedInAccountFromIntent(data);

                try {
                    GoogleSignInAccount signInAcc = signInTask.getResult(ApiException.class);
                    AuthCredential authCredential = GoogleAuthProvider.getCredential(signInAcc.getIdToken(), null);
                    startActivity(new Intent(this, Home_page.class));
                    Toast.makeText(this, "Your Google Account is Connected to our Application", Toast.LENGTH_SHORT).show();
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }


    }
    @Override
    public void onBackPressed() {

    }
    }
