package com.example.joseluis.exam3appgestionc;


import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.joseluis.exam3appgestionc.Utils.UniversalImageLoader;
import com.example.joseluis.exam3appgestionc.models.Contactos;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends AppCompatActivity implements viewContactsFragment.OnContactSelecedListerner, ContactFragment.OnEditContactListener{
    private static final String TAG="MainActivity";
   private static final int REQUEST_CODE=1;
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
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /// permisos al telefono
   public void verifyPermissions(String[] permissions){
        Log.d(TAG,"checkpermission: cheking permission");
        ActivityCompat.requestPermissions(
                MainActivity.this,
                permissions,
                REQUEST_CODE
        );
    }

   public boolean checkPermission(String[] permission){
        Log.d(TAG,"checkpermission: cheking permission"+ permission[0]);

        int permissionRequest=ActivityCompat.checkSelfPermission(
                MainActivity.this,
                permission[0]
        );
        if (permissionRequest != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "checkPermission: \n Permissions was not granted for: " + permission[0]);
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG,"checkpermission: cheking permission"+ requestCode);
        switch (requestCode){
            case REQUEST_CODE:
                for (int i=0; i < permissions.length;i++){
                    if (grantResults[i]== PackageManager.PERMISSION_GRANTED){
                        Log.d(TAG,"checkpermission: cheking permission"+ permissions[i]);
                    }else {
                        break;
                    }
                }
                break;
        }
    }

    @Override
    public void onEditcontactSlected(Contactos contact) {
        Log.d(TAG,"OnContactSelected: contact select from"+getString(R.string.view_contacs_fragment)+" "+contact.getNombre());
        EditContactFragment fragment=new EditContactFragment();
        Bundle args =new Bundle();
        args.putParcelable(getString(R.string.edit_contacts),contact);
        fragment.setArguments(args);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.addToBackStack(getString(R.string.edit_contacts));
        transaction.commit();
    }
}
