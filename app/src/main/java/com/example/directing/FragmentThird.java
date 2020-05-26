package com.example.directing;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentThird extends Fragment
{
    /* GridView myPetList;
    ArrayList<DataList> petList = new ArrayList<DataList>();
    CustomAdapter customAdapter;
    ImageView petImageView;
    DatabaseHelper mDatabaseHelper;

    String name;
    byte[] image;
    int id; */

    private DatabaseHandler db;
    dataAdapter data;
    GridView gridView;
    private Contact dataModel;

    Fragment fragment = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_other, container, false);

        db = new DatabaseHandler(getActivity());

        gridView = (GridView) view.findViewById(R.id.list1);

        final ArrayList<Contact> contacts = new ArrayList<>(db.getAllContacts());
        data = new dataAdapter(getActivity(), contacts);

        gridView.setAdapter(data);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel = contacts.get(position);

                Toast.makeText(getActivity(), String.valueOf(dataModel.getID()), Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

        /* myPetList =(GridView) view.findViewById(R.id.petsListView);
        petList = new ArrayList<>();
        customAdapter = new CustomAdapter(getActivity(), R.layout.custom_list_layout, petList);
        myPetList.setAdapter(customAdapter);

        mDatabaseHelper = new DatabaseHelper(getActivity());
        addSomeData(); //<<<<<<<<<< FOR DEMO

        Cursor data = mDatabaseHelper.getData("SELECT * FROM pet_table");
        petList.clear();
        while(data.moveToNext())
        {

            id = data.getInt(0);
            name = data.getString(1);
            image = data.getBlob(2);

            petList.add(new DataList(id, name, image));
            Log.i("image",String.valueOf(image));
        }
        data.close(); //<<<<<<<<<< SHOULD ALWAYS CLOSE CURSOR WHEN DONE WITH IT
        customAdapter.notifyDataSetChanged();

        myPetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0)
                {

                    fragment = new FragmentOne();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 1)
                {

                    fragment = new Livone();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 2)
                {

                    fragment = new Office1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 3)
                {

                    fragment = new Swim1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 4)
                {

                    fragment = new Wall1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 5)
                {

                    fragment = new Ward1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 6)
                {

                    fragment = new Wed1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 7)
                {

                    fragment = new Gard1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 8)
                {

                    fragment = new Gate1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 9)
                {

                    fragment = new Home1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 10)
                {

                    fragment = new Plan1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 11)
                {

                    fragment = new Cab1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 12)
                {

                    fragment = new Door1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 13)
                {

                    fragment = new Floor1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 14)
                {

                    fragment = new Front1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 15)
                {

                    fragment = new Bath1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 16)
                {

                    fragment = new Van1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }

                if (i == 17)
                {

                    fragment = new Cur1();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, fragment);
                    fragmentTransaction.commit();
                }
            }
        });

        return view;
    } */

    /* private void addSomeData()
    {
        mDatabaseHelper.getWritableDatabase().delete(DatabaseHelper.TABLE_PET,null,null); //<<<<<<<<<< Delete all pets
        mDatabaseHelper.addPetWithImageFromAssets("kitchen design","kitchen.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("living room design","livingroom.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("office furniture design","office.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("swimming pool design","swimming.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("wall decoration","wall.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("wardrobe design","wardrobe.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("wedding decorations","wedding.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("garden design","garden.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("gate design","gate.png");
        mDatabaseHelper.addPetWithImageFromAssets("house furniture design","homefurniture2.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("house plan 3d","houseplan2.png");
        mDatabaseHelper.addPetWithImageFromAssets("kitchen cabinet design","kitchencabinet.jpg"); //<<<<<<< ooops!!!!!
        mDatabaseHelper.addPetWithImageFromAssets("door design", "door.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("floor design", "floor.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("front elevation design", "front.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("bathroom design", "bathroom.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("bathroom vanity design", "vanity.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("curtain design", "curtain.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("DIY garden ideas" , "gardenidea.jpg");
        mDatabaseHelper.addPetWithImageFromAssets("DIY pallet projects", "pallet.jpg");

    }*/
}
