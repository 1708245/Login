package com.example.red;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Organisationloggedin extends AppCompatActivity {
    private Button mproceed1;
    private Button mlogout2;
    private FirebaseAuth firebaseAuth1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisationloggedin);

        mproceed1 = findViewById(R.id.button32);

        firebaseAuth1 = FirebaseAuth.getInstance();

        mlogout2 = (Button)findViewById(R.id.button31);

        mlogout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });

        mproceed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(getApplicationContext(), Register_page.class));
                onclickmethod234();
            }
        });


    }


    public void onclickmethod234() {
        Intent intent = new Intent(this, Home_page.class);
        startActivity(intent);
    }

    private void Logout(){
        firebaseAuth1.signOut();
        finish();
        startActivity(new Intent(Organisationloggedin.this, User_agency.class));
    }
    @Override
    public void onBackPressed() {

    }
}





