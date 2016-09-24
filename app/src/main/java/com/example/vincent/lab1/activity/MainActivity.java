package com.example.vincent.lab1.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vincent.lab1.R;
import com.example.vincent.lab1.fragment.ListeFragment;
import com.example.vincent.lab1.fragment.GMapFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Est-ce une manière convennable de gérer ca ?
        if (savedInstanceState == null)
        {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            ListeFragment listeFragment = new ListeFragment();
            GMapFragment GMapFragment = new GMapFragment();
            fragmentTransaction.add(R.id.liste_fragment_container, listeFragment);
            fragmentTransaction.add(R.id.map_fragment_container, GMapFragment);
            fragmentTransaction.commit();
        }




    }


}
