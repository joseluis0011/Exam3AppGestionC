package com.example.joseluis.exam3appgestionc;


import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.joseluis.exam3appgestionc.Utils.UniversalImageLoader;
import com.example.joseluis.exam3appgestionc.models.Contactos;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements viewContactsFragment.OnContactSelecedListerner{
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate:started.");
        initImageLoader();
        init();
    }
    private void init(){
        viewContactsFragment fragment =new viewContactsFragment();
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void initImageLoader(){
        UniversalImageLoader universalImageLoader=new UniversalImageLoader(MainActivity.this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

    @Override
    public void OnContactSelected(Contactos con) {
        Log.d(TAG,"OnContactSelected: contact select from"+getString(R.string.view_contacs_fragment)+" "+con.getNombre());
        ContactFragment fragment=new ContactFragment();
        Bundle args =new Bundle();
        args.putParcelable(getString(R.string.contacts),con);
        fragment.setArguments(args);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(getString(R.string.contacts));
        transaction.commit();
    }
}
