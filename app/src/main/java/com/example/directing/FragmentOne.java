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

public class FragmentOne extends Fragment
{
    MyDataBase1 myDataBase;

    SQLiteDatabase database;

    Cursor c;

    ImageView imageView;

    byte[] img,img1;

    Bitmap b;

    TextView tx,tx1;

    FloatingActionButton f1;

    Fragment fragment;

    @SuppressLint("WrongThread")

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);

        ImageView imageView=(ImageView) view.findViewById(R.id.imageView);

        tx = (TextView) view.findViewById(R.id.textView1);
        tx1 = (TextView) view.findViewById(R.id.textView2);


        f1 = (FloatingActionButton) view.findViewById(R.id.fab);

        myDataBase=new MyDataBase1(getActivity(),"kitcheninfo",null,1);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kitchen1);

        ByteArrayOutputStream bos=new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        byte[] img=bos.toByteArray();

        database=myDataBase.getWritableDatabase();

        String s1 = "Acrylic modern kitchen furniture designs are the trending latest kitchen furniture. These are mostly of closed concept-where the furniture and utensils are kept hidden in a cupboard and is made up of acrylic material. The material is also embossed with designs to make it look classier. This design mostly concentrates on the large kitchen appliance and the finished look. The colour used for the acrylic materials is mostly bright colours. This furniture design gives a ‘toy house’ look. Though it takes effort to maintain the same glossy look, these are the most preferred kitchen designs in modern homes and apartments.\n" +
                "\n";
        String s2 = " ";

        String id = "1";
        String str = " ";

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
                str = c.getString(c.getColumnIndex("id"));
                s2 = c.getString(c.getColumnIndex("description"));
                img1=c.getBlob(c.getColumnIndex("image"));

            }while(c.moveToNext());

        }

        Bitmap b1=BitmapFactory.decodeByteArray(img1, 0, img1.length);

        imageView.setImageBitmap(b1);
        tx.setText(str);
        tx1.setText(s2);


        f1.setOnClickListener(new View.OnClickListener() {
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
