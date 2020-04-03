package com.example.red;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register_organization_page extends AppCompatActivity {

    public static final String TAG = "TAG";

    EditText mFullName,mEmail,mPassword,mdateofbirth,maddress,maddress2,mcounty,mpost,mblood,mweight,mtrans,mcancer;
    Button mRegisterBtn;
    TextView mLogin1Btn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fstore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_organization_page); mFullName = findViewById(R.id.FullNAme1);
        mEmail = findViewById(R.id.Email1);
        mPassword = findViewById(R.id.Password1);
        mRegisterBtn = findViewById(R.id.Register1);
        mLogin1Btn = findViewById(R.id.Login1);
        maddress = findViewById(R.id.Address1);
        maddress2 = findViewById(R.id.address21);
        mcounty = findViewById(R.id.County1);
        mpost = findViewById(R.id.Postcode1);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Organisationloggedin.class));
            finish();
        }
        mLogin1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OrganisationLoginActivity.class));
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
               final  String password = mPassword.getText().toString().trim();
                final String FullName1 = mFullName.getText().toString();
                final String address = maddress.getText().toString();
                final String address2 = maddress2.getText().toString();
                final String Countyy = mcounty.getText().toString();
                final String Postcode = mpost.getText().toString();


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

                if (TextUtils.isEmpty(address)) {
                    maddress.setError("address is Required.");
                    return;
                }

                if (TextUtils.isEmpty(Countyy)) {
                    mcounty.setError("County is Required.");
                    return;
                }

                if (TextUtils.isEmpty(Postcode)) {
                    mpost.setError("Postcode is Required.");
                    return;
                }


                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register_organization_page.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentreference = fstore.collection("organisation").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("fName", FullName1);
                            user.put("email", email);
                            user.put("address", address);
                            user.put("address2", address2);
                            user.put("County", Countyy);
                            user.put("Postcode", Postcode);
                            user.put("Password",password);
                            documentreference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), Organisationloggedin.class));

                        } else {
                            Toast.makeText(Register_organization_page.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                        mLogin1Btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getApplicationContext(), OrganisationLoginActivity.class));
                            }
                        });
                    }
                })
                ;
            }
        });}

    @Override
    public void onBackPressed(){

    }
}
