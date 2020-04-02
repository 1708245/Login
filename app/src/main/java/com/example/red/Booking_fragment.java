package com.example.red;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class Booking_fragment extends Fragment {

    //private FirebaseAuth firebaseAuth;
   // private Button logout;

    public Booking_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // firebaseAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking_fragment, container, false);


    }
    //{
        //logout = getActivity().findViewById(R.id.logout);

        //logout.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
          //      Logout();
        //    }
      //  });}


   // private void Logout(){
     //   firebaseAuth.signOut();
       // getActivity().finish();
        //startActivity( new Intent(Booking_fragment.this.getActivity(),MainActivity.class));

    //}


}



