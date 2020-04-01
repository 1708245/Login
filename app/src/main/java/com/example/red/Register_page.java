package com.example.red;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register_page extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName,mEmail,mPassword,mdateofbirth,maddress,maddress2;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fstore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        mFullName = findViewById(R.id.FullNAme);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mdateofbirth = findViewById(R.id.DAteofbirth2);
        mRegisterBtn = findViewById(R.id.Register);
        mLoginBtn = findViewById(R.id.Login);
        maddress = findViewById(R.id.Address);
        maddress2 = findViewById(R.id.address2);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Home_page.class));
            finish();
        }
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String FullName1 = mFullName.getText().toString();
                final String Dateofbirth = mdateofbirth.getText().toString();
                final String address = maddress.getText().toString();
                final String address2 = maddress2.getText().toString();


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
                progressBar.setVisibility(View.VISIBLE);

                // Register the user in databse

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register_page.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentreference = fstore.collection("users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("fName", FullName1);
                            user.put("email", email);
                            user.put("Date of Birth", Dateofbirth);
                            user.put("address", address);
                            user.put("address2", address2);
                            documentreference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), Home_page.class));

                        } else {
                            Toast.makeText(Register_page.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });


            }
        });
    }
        @Override
        public void onBackPressed(){

        }


}
