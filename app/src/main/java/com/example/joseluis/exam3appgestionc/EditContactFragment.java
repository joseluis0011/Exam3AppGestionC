package com.example.joseluis.exam3appgestionc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class EditContactFragment extends Fragment {
    private static final String TAG="EditContactFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_editcontact,container,false);
        Log.d(TAG,"onCreate: started");
        return view;
    }
}
