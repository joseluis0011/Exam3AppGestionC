package com.example.joseluis.exam3appgestionc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joseluis.exam3appgestionc.models.Contactos;


public class EditContactFragment extends Fragment {
    private static final String TAG="EditContactFragment";

    public EditContactFragment(){
        super();
        setArguments(new Bundle());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_editcontact,container,false);
        Log.d(TAG,"onCreate: started");
        getContactFromBundle();
        return view;
    }

    private Contactos getContactFromBundle(){
        Log.d(TAG,"getContactFromBundle: argukment"+ getArguments());
        Bundle bundle=this.getArguments();
        if (bundle!=null){
            return bundle.getParcelable(getString(R.string.contacts));
        }else{
            return null;
        }
    }
}
