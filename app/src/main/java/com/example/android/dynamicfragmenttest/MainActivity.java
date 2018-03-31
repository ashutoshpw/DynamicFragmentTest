package com.example.android.dynamicfragmenttest;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener, DetailsFragment.OnFragmentInteractionListener {

    private ListFragment lf;
    private DetailsFragment df;
    private boolean visible_details = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //if(findViewById(R.id.details))
        lf = new ListFragment().newInstance(visible_details);
        ft.add(R.id.list, lf);
        //ft.add(R.id.list, lf);
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        String element = uri.toString();
        if(visible_details) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (df == null) {
                df = new DetailsFragment().newInstance(element, "");
                ft.add(R.id.details, df);
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();
            } else {
                df = new DetailsFragment().newInstance(element, "");
                ft.replace(R.id.details, df);
                ft.addToBackStack(null);
                ft.commitAllowingStateLoss();

            }
        }else{
            Intent i = new Intent(MainActivity.this,DetailsActivity.class);
            i.putExtra("element",element);
            startActivity(i);
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
