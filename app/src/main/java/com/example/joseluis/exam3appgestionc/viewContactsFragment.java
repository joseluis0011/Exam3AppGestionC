package com.example.joseluis.exam3appgestionc;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.joseluis.exam3appgestionc.Utils.ContactListAdapter;
import com.example.joseluis.exam3appgestionc.models.Contactos;

import java.util.ArrayList;
import java.util.Locale;


public class viewContactsFragment extends Fragment {
    private static  final String TAG="viewContactsFragment";
    private String testImageURL="icons.iconarchive.com/icons/paomedia/small-n-flat/512/user-male-icon.png";

    public interface  OnContactSelecedListerner{
        public void OnContactSelected(Contactos con);
    }
    OnContactSelecedListerner mContactListener;

    // variables
    private static final int STANDARD_APPBAR=0;
    private static final int SEARCH_APPBAR=1;
    private int mAppBarState;
    private AppBarLayout viewContactsBar, searchBar;

    private ContactListAdapter adapter;
    private ListView contactslist;
    private EditText nSearchContacs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_viewcontacts,container,false);
        viewContactsBar=(AppBarLayout)view.findViewById(R.id.viewContacsToolbar);
        searchBar=(AppBarLayout)view.findViewById(R.id.searchToolbar);
        contactslist=(ListView)view.findViewById(R.id.contactslist);
        nSearchContacs=(EditText)view.findViewById(R.id.etSearchContacts);
        Log.d(TAG,"onCreate:started.");
        setAppBarState(STANDARD_APPBAR);
        setupContactsList();

        FloatingActionButton fab=(FloatingActionButton)view.findViewById(R.id.fabAddContact);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked fab");
            }
        });
        ImageView ivSearchContact=(ImageView)view.findViewById(R.id.ivSeachIcon);
        ivSearchContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked seach icon");
                toggleToolBarState();
            }
        });
        ImageView ivbackArrow=(ImageView)view.findViewById(R.id.ivBackArrow);
        ivbackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked back arrow");
                toggleToolBarState();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mContactListener=(OnContactSelecedListerner)getActivity();
        }catch (ClassCastException e){
            Log.e(TAG,"onAttach:ClassCastException "+e.getMessage());
        }
    }

    private void setupContactsList(){
        final ArrayList<Contactos>contactos=new ArrayList<>();
        contactos.add(new Contactos("Paul","981766260","josecardenasupeu",testImageURL));
        contactos.add(new Contactos("jose","981766260","josecardenasupeu",testImageURL));
        contactos.add(new Contactos("ruth","981766260","josecardenasupeu",testImageURL));
        contactos.add(new Contactos("pablo","981766260","josecardenasupeu",testImageURL));
        contactos.add(new Contactos("Paul","981766260","josecardenasupeu",testImageURL));
        contactos.add(new Contactos("Paul","981766260","josecardenasupeu",testImageURL));

        adapter=new ContactListAdapter(getActivity(),R.layout.layout_contactslistitem,contactos,"https://");

        nSearchContacs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String text=nSearchContacs.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        contactslist.setAdapter(adapter);

        contactslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"onClick: navegating "+getString(R.string.contacts));
                mContactListener.OnContactSelected(contactos.get(position));
            }
        });

    }
    private void toggleToolBarState() {
        Log.d(TAG,"toggleToolBarState: toggling AppBarState");
        if (mAppBarState==STANDARD_APPBAR){
            setAppBarState(SEARCH_APPBAR);
        }else{
            setAppBarState(STANDARD_APPBAR);
        }
    }
    /*
    *
    * @param state
     */
    private void setAppBarState(int state) {
        Log.d(TAG,"setAppState:changing app bar state to"+state);
        mAppBarState=state;
        if (mAppBarState==STANDARD_APPBAR){
            searchBar.setVisibility(View.GONE);
            viewContactsBar.setVisibility(View.VISIBLE);
            View view =getView();
            InputMethodManager imm=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            try {
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);
            }catch (NullPointerException e){
                Log.d(TAG,"setAppState:NullPointerException:"+e.getMessage());
            }
        }else if (mAppBarState==SEARCH_APPBAR){
            viewContactsBar.setVisibility(View.GONE);
            searchBar.setVisibility(View.VISIBLE);
            InputMethodManager imm=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setAppBarState(SEARCH_APPBAR);
    }
}
