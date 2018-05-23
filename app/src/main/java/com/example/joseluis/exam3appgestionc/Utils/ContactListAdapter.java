package com.example.joseluis.exam3appgestionc.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.joseluis.exam3appgestionc.R;
import com.example.joseluis.exam3appgestionc.models.Contactos;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactListAdapter extends ArrayAdapter<Contactos> {

    private LayoutInflater ninflater;
    private List<Contactos> nContactos= null;
    private ArrayList<Contactos>arrayList;
    private int layoutResource;
    private Context nContext;
    private String mAppend;

    public ContactListAdapter(@NonNull Context context, int resource, List<Contactos>contactos, String append) {
        super(context, resource, contactos);
        ninflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutResource=resource;
        this.nContext=context;
        mAppend=append;
        this.nContactos= contactos;
        arrayList=new ArrayList<>();
        this.arrayList.addAll(nContactos);
    }
    private static class ViewHolder{
        TextView nombre;
        CircleImageView contactoimagen;
        ProgressBar nProgressBar;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null){
            convertView = ninflater.inflate(layoutResource,parent,false);
            holder=new ViewHolder();

            holder.nombre=(TextView)convertView.findViewById(R.id.contactName);
            holder.contactoimagen=(CircleImageView)convertView.findViewById(R.id.contactimage);

            holder.nProgressBar=(ProgressBar)convertView.findViewById(R.id.contactProgressBar);

            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        String nombre_=getItem(position).getNombre();
        String imagePath = getItem(position).getPerfil();
        holder.nombre.setText(nombre_);
        ImageLoader imageLoader=ImageLoader.getInstance();
        imageLoader.displayImage(mAppend + imagePath, holder.contactoimagen, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                holder.nProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                holder.nProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.nProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                holder.nProgressBar.setVisibility(View.GONE);
            }
        });
        return convertView;
    }
}
