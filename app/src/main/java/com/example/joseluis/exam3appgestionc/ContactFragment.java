package com.example.joseluis.exam3appgestionc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.joseluis.exam3appgestionc.Utils.ContactPropertyListAdapter;
import com.example.joseluis.exam3appgestionc.Utils.UniversalImageLoader;
import com.example.joseluis.exam3appgestionc.models.Contactos;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class ContactFragment extends Fragment {
    private static final String TAG="ContactFragment";
    public ContactFragment(){
        super();
        setArguments(new Bundle());
    }

    private Toolbar toolbar;
    private Contactos nContactos;
    private TextView mContactName;
    private CircleImageView mContactImage;
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_contact,container,false);
        toolbar=(Toolbar)view.findViewById(R.id.contactToolbar);
        mContactName=(TextView)view.findViewById(R.id.contactName);
        mContactImage=(CircleImageView)view.findViewById(R.id.contactimage);
        mListView=(ListView)view.findViewById(R.id.ivContactProperties);
        Log.d(TAG,"onCreate: started");
        nContactos=getContactFromBundle();

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
         setHasOptionsMenu(true);

         init();

        ImageView ivbackArrow=(ImageView)view.findViewById(R.id.ivBackArrow);
        ivbackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked back arrow");
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });
        ImageView ivEdit =(ImageView)view.findViewById(R.id.ivEdit);
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: click the edit icon");

            }
        });
        return view;
    }
    private void init(){
        mContactName.setText(nContactos.getNombre());
        UniversalImageLoader.setImage(nContactos.getPerfil(),mContactImage,null,"https://");

        ArrayList<String>properties =new ArrayList<>();
        properties.add(nContactos.getNumero());
        properties.add(nContactos.getEmail());
        ContactPropertyListAdapter adapter=new ContactPropertyListAdapter(getActivity(),R.layout.layout_cardview,properties);
        mListView.setAdapter(adapter);
        mListView.setDivider(null);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.contact_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuitem_delete:
                Log.d(TAG,"onOptionsItemSelected: deliting contact");
        }
        return super.onOptionsItemSelected(item);
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