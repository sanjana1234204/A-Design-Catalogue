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

public class Wall2 extends Fragment
{
    MyDataBase5 myDataBase;

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

        View view = inflater.inflate(R.layout.wall_two, container, false);

        ImageView imageView=(ImageView) view.findViewById(R.id.iw1);

        tx = (TextView) view.findViewById(R.id.tw1);
        tx1 = (TextView) view.findViewById(R.id.tw11);

        f1 = (FloatingActionButton) view.findViewById(R.id.w4);
        f2 = (FloatingActionButton) view.findViewById(R.id.w5);

        myDataBase=new MyDataBase5(getActivity(),"wallinfo1",null,1);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wall2);

        ByteArrayOutputStream bos=new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        byte[] img=bos.toByteArray();

        database=myDataBase.getWritableDatabase();

        String s1 = "Spectrum Art PVC Cutting Wall Decorative Design\n\n" +
                "•\tWall Decor\n" +
                "•\t3 mm Acrylic\n" +
                "•\tShiny Finish\n" +
                "•\tEasily Transferable to Other Wall\n" +
                "•\tLarge Size\n" +
                "•\t3D Wall Art\n";
        String s2 = " ";

        String id = "2";
        String id1 = " ";


// content values is used to create empty set of values using the default initial size

        ContentValues cv=new ContentValues();

        cv.put("id",id);
        cv.put("image", img);
        cv.put("description",s1);

        database.insert("wallimage1", null, cv);

        String selectQuery = "SELECT * FROM wallimage1";

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

                fragment = new Wall3();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });

        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new Wall1();

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
