package com.example.directing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class Other extends AppCompatActivity
{
    private DatabaseHandler db;
    dataAdapter data;
    GridView gridView;
    private Contact dataModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        db=new DatabaseHandler(this);

        gridView = (GridView) findViewById(R.id.list1);

        final ArrayList<Contact> contacts = new ArrayList<>(db.getAllContacts());
        data=new dataAdapter(this, contacts);

        gridView.setAdapter(data);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel = contacts.get(position);

                Toast.makeText(getApplicationContext(),String.valueOf(dataModel.getID()), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
