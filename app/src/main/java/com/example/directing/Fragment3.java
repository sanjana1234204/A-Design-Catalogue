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

public class Fragment3 extends Fragment
{
    MyDataBase1 myDataBase;

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

        View view = inflater.inflate(R.layout.fragment_three, container, false);

        ImageView imageView=(ImageView) view.findViewById(R.id.imageView2);

        tx = (TextView) view.findViewById(R.id.textView3);
        tx1 = (TextView) view.findViewById(R.id.tx3);

        f1 = (FloatingActionButton) view.findViewById(R.id.fab6);
        f2 = (FloatingActionButton) view.findViewById(R.id.fab7);

        myDataBase=new MyDataBase1(getActivity(),"kitcheninfo",null,1);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kitchen3);

        ByteArrayOutputStream bos=new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        byte[] img=bos.toByteArray();

        database=myDataBase.getWritableDatabase();

        String s1 = "Contemporary Kitchen Design Inspiration \n\n" +
                "In the contemporary kitchen with the clean bold look it is important to neatly store away as many items as possible and what is left on display should contribute to the modern feel, not lessen it. It basically requires ridding the area of clutter. The kitchen should have bold, bright colors like blues, greens and reds to make it different and to create a modern finish. It is necessary that every choice be carefully made giving the kitchen just the right touch, like putting icing on a cake. Checkout 25 Contemporary Kitchen Design Inspiration. Enjoy!\n" +
                "\n";

        String s2 = " ";

        String id = "3";
        String id1 = " ";
// content values is used to create empty set of values using the default initial size

        ContentValues cv=new ContentValues();

        cv.put("id",id);
        cv.put("image", img);
        cv.put("description",s1);

        database.insert("kitchenimage", null, cv);

        String selectQuery = "SELECT * FROM kitchenimage";

        c=database.rawQuery(selectQuery,null);

        if(c!=null){

            c.moveToFirst();

            do{
                id1=c.getString(c.getColumnIndex("id"));
                s2=c.getString(c.getColumnIndex("description"));
                img1=c.getBlob(c.getColumnIndex("image"));

            }while(c.moveToNext());

        }

        Bitmap b1=BitmapFactory.decodeByteArray(img1, 0, img1.length);

        imageView.setImageBitmap(b1);
        tx.setText(id1);
        tx1.setText(s2);


        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new Fragment1();

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
