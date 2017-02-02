package com.example.cbluser22.drawerapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cbluser22.drawerapp.R;
import com.example.cbluser22.drawerapp.model.DataModel;

/**
 * Created by cbluser22 on 2/2/17.
 */

public class DrawerAdapter extends ArrayAdapter<DataModel> {

    Context mContext;
    int layoutResourceId;
    DataModel data[] = null;
    public DrawerAdapter(Context mContext, int layoutResourceId,DataModel[] data) {
        super(mContext,layoutResourceId,data);
        this.mContext=mContext;
        this.layoutResourceId=layoutResourceId;
        this.data=data;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View listItem=convertView;
        LayoutInflater inflater=((Activity)mContext).getLayoutInflater();
        listItem=inflater.inflate(layoutResourceId,parent,false);

        ImageView ivImage;
        TextView tvName;
        ivImage=(ImageView)listItem.findViewById(R.id.iv_image);
        tvName=(TextView)listItem.findViewById(R.id.tv_name);

        DataModel dataModel=data[position];

        tvName.setText(dataModel.name);
        ivImage.setImageResource(dataModel.image);


        return listItem;
    }
}
