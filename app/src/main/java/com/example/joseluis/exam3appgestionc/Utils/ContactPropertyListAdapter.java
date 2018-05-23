package com.example.joseluis.exam3appgestionc.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

public class ContactPropertyListAdapter extends ArrayAdapter<String>{

    private LayoutInflater ninflater;
    private List<String> mProperties=null;
    private int layoutResource;
    private Context nContext;

    public ContactPropertyListAdapter(@NonNull Context context, int resource, List<String>properties) {
        super(context, resource, properties);
        ninflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutResource=resource;
        this.nContext=context;
        this.mProperties=properties;
    }
    private static class ViewHolder{
        TextView property;
        ImageView rightIcon;
        ImageView leftIcon;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null){
            convertView = ninflater.inflate(layoutResource,parent,false);
            holder=new ViewHolder();

            holder.property=(TextView)convertView.findViewById(R.id.tvMiddleCardView);
            holder.rightIcon=(CircleImageView)convertView.findViewById(R.id.iconRightCardView);
            holder.leftIcon=(CircleImageView)convertView.findViewById(R.id.iconLeftCardView);


            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        final String property =getItem(position);
        holder.property.setText(property);
        //
        if (property.contains("0")){
            holder.leftIcon.setImageResource(nContext.getResources().getIdentifier("@drawable/ic_email",null,nContext.getPackageName()));

        }else if ((property.length() !=0)){
            holder.leftIcon.setImageResource(nContext.getResources().getIdentifier("@drawable/ic_phone",null,nContext.getPackageName()));
            holder.leftIcon.setImageResource(nContext.getResources().getIdentifier("@drawable/ic_message",null,nContext.getPackageName()));
        }

        return convertView;
    }

}
