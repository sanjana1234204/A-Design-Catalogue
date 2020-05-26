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

public class Office3 extends Fragment
{
    MyDataBase3 myDataBase;

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

        View view = inflater.inflate(R.layout.office_three, container, false);

        ImageView imageView=(ImageView) view.findViewById(R.id.io2);

        tx = (TextView) view.findViewById(R.id.to2);
        tx1 = (TextView) view.findViewById(R.id.to22);

        f1 = (FloatingActionButton) view.findViewById(R.id.o7);
        f2 = (FloatingActionButton) view.findViewById(R.id.o8);

        myDataBase=new MyDataBase3(getActivity(),"officeinfo1",null,1);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.office3);

        ByteArrayOutputStream bos=new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        byte[] img=bos.toByteArray();

        database=myDataBase.getWritableDatabase();

        String s1 = "\tProduct Specification\n\n" +
                "Brand:\tSONIKA\n" +
                "Material:\tWood\n" +
                "Usage/Application:\tNEW\n" +
                "Color:\tcustomized\n" +
                "Shape:\tL Shape\n" +
                "Type:\tModular\n" +
                "Style:\tModern\n" +
                "Design Type:\tCustomized\n" +
                "Product Type:\tFurniture\n" +
                "Wood Style:\tPLYWOOD\n" +
                "Model Number:\tMODERN\n" +
                "Warranty:\t1 YEAR\n" +
                "Primary Material:\tWOOD\n" +
                "Number of Seats:\tcustomized\n" +
                "Packaging:\tCOTTON BOX\n" +
                "Condition:\tNew\n" +
                "Minimum Order Quantity:\t2 Piece\n";
        String s2 = " ";

        String id = "1";
        String id1 = " ";


// content values is used to create empty set of values using the default initial size

        ContentValues cv=new ContentValues();

        cv.put("id",id);
        cv.put("image", img);
        cv.put("description",s1);

        database.insert("officeimage1", null, cv);

        String selectQuery = "SELECT * FROM officeimage1";

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


        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new Office2();

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
