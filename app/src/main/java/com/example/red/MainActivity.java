package com.example.red;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;



import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private Beam_fragment beam_fragment;
    private Booking_fragment booking_fragment;
    private Chat_fragment chat_fragment;
    private Feed_fragment feed_fragment;
    private Profile_fragment profile_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFrame = findViewById(R.id.main_Frame);
        mMainNav = findViewById(R.id.main_nav);

        // Initialising Fragments
        beam_fragment = new Beam_fragment();
        booking_fragment = new Booking_fragment();
        chat_fragment = new Chat_fragment();
        feed_fragment = new Feed_fragment();
        profile_fragment = new Profile_fragment();

        setFragment(booking_fragment);

        /*
        Listening the clicks on Navigation Bottom Bar
         */
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.nav_beam: {
                        setFragment(beam_fragment);
                        return true;
                    }
                    case R.id.nav_book: {
                        setFragment(booking_fragment);
                        return true;
                    }
                    case R.id.nav_chat: {
                        setFragment(chat_fragment);
                        return true;
                    }
                    case R.id.nav_feed: {
                        setFragment(feed_fragment);
                        return true;
                    }
                    case R.id.nav_profile: {
                        setFragment(profile_fragment);
                        return true;
                    }
                    default:
                        return false;

                }
            }
        });

    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_Frame, fragment);
        fragmentTransaction.commit();

    }


}
