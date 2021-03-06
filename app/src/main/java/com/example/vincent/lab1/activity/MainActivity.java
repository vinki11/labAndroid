package com.example.vincent.lab1.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vincent.lab1.R;
import com.example.vincent.lab1.fragment.GMapFragment;
import com.example.vincent.lab1.fragment.ListeFragment;

public class MainActivity extends AppCompatActivity  {

    FragmentManager fragmentManager;
    GMapFragment gMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Est-ce une manière convennable de gérer ca ? (si pas ca load fragment en double kan changement orientation)
        if (savedInstanceState == null)
        {
            fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            ListeFragment listeFragment = new ListeFragment();
            gMapFragment = new GMapFragment();
            //gMapFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.liste_fragment_container, listeFragment);
            fragmentTransaction.add(R.id.map_fragment_container, gMapFragment);
            fragmentTransaction.commit();
        }


    }

    public void setCoords(double pLat, double pLon)
    {
        gMapFragment.setPOICoords(pLat,pLon);
    }


}
