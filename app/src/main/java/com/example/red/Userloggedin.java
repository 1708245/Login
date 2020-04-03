package com.example.red;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Userloggedin extends AppCompatActivity {
private Button mproceed;
    private Button mlogout1;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userloggedin);

        mproceed = findViewById(R.id.button29);

        firebaseAuth = FirebaseAuth.getInstance();

        mlogout1 = (Button)findViewById(R.id.button30);

        mlogout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });

        mproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(getApplicationContext(), Register_page.class));
                onclickmethod23();
            }
    });


    }


        public void onclickmethod23() {
            Intent intent = new Intent(this, Home_page.class);
            startActivity(intent);
        }

    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Userloggedin.this, User_agency.class));
    }
    @Override
    public void onBackPressed() {

    }

}




