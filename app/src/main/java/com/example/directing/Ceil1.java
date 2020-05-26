package com.example.directing;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class Ceil1 extends Fragment
{
    MyDataBase21 myDataBase;

    SQLiteDatabase database;

    Cursor c;

    ImageView imageView;

    byte[] img,img1;

    Bitmap b;

    TextView tx,tx1;

    FloatingActionButton f1,f2;

    Fragment fragment;

    @SuppressLint("WrongThread")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ceil_one, container, false);

        ImageView imageView=(ImageView) view.findViewById(R.id.ice);

        tx = (TextView) view.findViewById(R.id.tce);
        tx1 = (TextView) view.findViewById(R.id.tcee);

        f1 = (FloatingActionButton) view.findViewById(R.id.ce1);
        f2 = (FloatingActionButton) view.findViewById(R.id.ce2);

        myDataBase=new MyDataBase21(getActivity(),"ceilinfo",null,1);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ceil1);

        ByteArrayOutputStream bos=new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        byte[] img=bos.toByteArray();

        database=myDataBase.getWritableDatabase();

        String s1 = "Product Specification\n" +
                "\n" +
                "Type of False Ceiling:\ton\n" +
                "Minimum Order Quantity:\t300 Unit\n" +
                "Product Description\n\n" +
                "We are instrumental in manufacturing, wholesaling, exporting, retailing and supplying our clients a comprehensive range of False Ceiling. The offered false ceiling is demanded in commercial areas & various institutions. Our false ceiling is well designed by our experts using top notch quality material and leading techniques keeping in pace with market standard. Our esteemed customers can easily avail this false ceiling it at competitive prices.\n" +
                "\n" +
                "Features:\n\n" +
                "Attractive look\n" +
                "Alluring design\n" +
                "Highly durable\n";

        String s2 = " ";

        String id = "1";
        String id1 = " ";


// content values is used to create empty set of values using the default initial size

        ContentValues cv=new ContentValues();

        cv.put("id",id);
        cv.put("image", img);
        cv.put("description",s1);

        database.insert("ceilimage", null, cv);

        String selectQuery = "SELECT * FROM ceilimage";

        c=database.rawQuery(selectQuery,null);

        if(c!=null){

            c.moveToFirst();

            do{
                id1 = c.getString(c.getColumnIndex("id"));
                s2 = c.getString(c.getColumnIndex("description"));
                img1=c.getBlob(c.getColumnIndex("image"));

            }while(c.moveToNext());

        }

        Bitmap b1=BitmapFactory.decodeByteArray(img1, 0, img1.length);

        imageView.setImageBitmap(b1);
        tx.setText(id1);
        tx1.setText(s2);


        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new Ceil2();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });

        return view;
    }
}
