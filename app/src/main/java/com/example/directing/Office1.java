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

public class Office1 extends Fragment
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

        View view = inflater.inflate(R.layout.office_one, container, false);

        ImageView imageView=(ImageView) view.findViewById(R.id.io);

        tx = (TextView) view.findViewById(R.id.to);
        tx1 = (TextView) view.findViewById(R.id.too);

        f1 = (FloatingActionButton) view.findViewById(R.id.o1);
        f2 = (FloatingActionButton) view.findViewById(R.id.o2);

        myDataBase=new MyDataBase3(getActivity(),"officeinfo1",null,1);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.office1);

        ByteArrayOutputStream bos=new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        byte[] img=bos.toByteArray();

        database=myDataBase.getWritableDatabase();

        String s1 = "\tProduct Description\n\n" +
                "Prices starts from-50000 to 500000\n\n" +
                "Office Partition System We are among the renowned names in the industry, engaged in manufacturing and supplying Designer Furniture to our esteemed customers. The offered partitions are designed as per the prevailing market trends and are regarded for their easy installation. With these partitions, it becomes extremely easy to change the outlay of the offices whenever necessary.\n" +
                "\n" +
                "Features:\n\n" +
                "•\tSturdy\n" +
                "•\tFine finish\n" +
                "•\tDurable\n\n" +
                "Other Information:\n\n" +
                "•\tMinimum Order Quantity: 1 Unit(s)\n";
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


        f1.setOnClickListener(new View.OnClickListener() {
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
