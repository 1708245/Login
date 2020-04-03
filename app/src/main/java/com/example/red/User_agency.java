package com.example.red;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class User_agency extends AppCompatActivity {
    private Button mdonor, morganisation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agency);

        mdonor = (Button) findViewById(R.id.donor1);
        morganisation = (Button) findViewById(R.id.organisation1);

        mdonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_agency.this, UserLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        morganisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_agency.this, OrganisationLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });



    }
}
