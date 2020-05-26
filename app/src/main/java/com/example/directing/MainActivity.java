package com.example.directing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    GridView myPetList;
    ArrayList<DataList> petList = new ArrayList<DataList>();
    CustomAdapter customAdapter;
    ImageView petImageView;
    DatabaseHelper mDatabaseHelper;

    String name;
    byte[] image;
    int id;

    TextView txt;
    View header;

    Fragment fragment = null;

    SharedPreferences pref;

    Intent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // session management

        pref = getSharedPreferences("user_details", MODE_PRIVATE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.floatingaction);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();

                fragment = new FragmentThird();

                if(fragment != null)
                {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment);
                    ft.commit();
                }
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // to show email id in navigation header view

        header = navigationView.getHeaderView(0);
        txt = (TextView) header.findViewById(R.id.textView);

        Intent intent = getIntent();
        String user_name = intent.getStringExtra("USER_NAME");

        txt.setText(user_name);

        // session management coding

        String result = pref.getString("username", null);

        txt.setText(result);

        fragment = new Changing();

        if(fragment != null)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

       /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new FragmentThird();

                if(fragment != null)
                {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment);
                    ft.commit();
                }

            }
        }); */


        /* myPetList = findViewById(R.id.petsListView);
        petList = new ArrayList<>();
        customAdapter = new CustomAdapter(this, R.layout.custom_list_layout, petList);
        myPetList.setAdapter(customAdapter);

        mDatabaseHelper = new DatabaseHelper(this);
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

        //fragment addition in navigation drawer


        myPetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i == 0)
                {
                    Intent intent = new Intent(MainActivity.this, FragmentFirst.class);
                    startActivity(intent);
                }

                if (i == 1)
                {
                    Intent intent = new Intent(MainActivity.this, FragmentSecond.class);
                    startActivity(intent);
                }

                if(i == 2)
                {
                    // fragment = new FragmentShow();
                }
            }
        });

        // replacing the fragment
*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            Toast.makeText(getApplicationContext(), "Back Press disabled! click on logout button", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home)
        {

            fragment = new Changing();

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        else if(id == R.id.nav_bath)
        {
            fragment = new Bath1();
        }

        else if(id == R.id.nav_bed)
        {
            fragment = new Livone();
        }

        else if(id == R.id.nav_curtain)
        {
            fragment = new Cur1();
        }
        else if(id == R.id.nav_door)
        {
            fragment = new Door1();
        }
        else if(id == R.id.nav_floor)
        {
            fragment = new Floor1();
        }
        else if(id == R.id.nav_front)
        {
            fragment = new Front1();
        }
        else if(id == R.id.nav_gate)
        {
            fragment = new Gate1();
        }
        else if(id == R.id.nav_kitchen)
        {
            fragment = new FragmentOne();
        }
        else if(id == R.id.nav_wall)
        {
            fragment = new Wall1();
        }
        else if(id == R.id.nav_ward)
        {
            fragment = new Ward1();
        }

        else if(id == R.id.nav_ceil)
        {
            fragment = new Ceil1();
        }

        else if(id == R.id.nav_out)
        {
            intent1 = new Intent(MainActivity.this, LoginActivity.class);

            SharedPreferences.Editor editor = pref.edit();

            editor.clear();
            editor.commit();

            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent1);
            finish();

        }

        if(fragment != null)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


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

    } */
}
