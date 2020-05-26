package com.example.directing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private int layout;
    private ArrayList<DataList> recordList;
    private Context context;

    public CustomAdapter(Context context, int layout, ArrayList<DataList> recordList) {
        this.context = context;
        this.recordList = recordList;
        this.layout=layout;
    }

    public int getCount() {
        return recordList.size();
    }

    public Object getItem(int position) {
        return recordList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{

        ImageView petImageView;
        TextView petNameTextView;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder = new ViewHolder();
        if (v == null) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(layout, null);
            holder.petImageView =  v.findViewById(R.id.petImageView);
            holder.petNameTextView = v.findViewById(R.id.petNameTextView);
            v.setTag(holder);
        }else{
            holder = (ViewHolder)v.getTag();
        }



        DataList datalist = recordList.get(position);
        holder.petNameTextView.setText(datalist.getName());

        byte[] recordImage = datalist.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);
        holder.petImageView.setImageBitmap(bitmap);


        return v;
    }
}


