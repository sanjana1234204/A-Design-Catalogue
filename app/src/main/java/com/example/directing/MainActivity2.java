package com.example.directing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

        GridView myPetList;
        ArrayList<DataList> petList = new ArrayList<DataList>();
        CustomAdapter customAdapter;
        ImageView petImageView;
        DatabaseHelper mDatabaseHelper;

        String name;
        byte[] image;
        int id;

        TextView txt1;
        View header;

        Fragment fragment = null;

        SharedPreferences pref;

        Intent intent1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // session management

            pref = getSharedPreferences("admin_details", MODE_PRIVATE);

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

            txt1 = (TextView) header.findViewById(R.id.textView);

            Intent intent = getIntent();
            String user_name = intent.getStringExtra("EMAIL");

            txt1.setText(user_name);

            // session management coding

            String result = pref.getString("username", null);

            txt1.setText(result);

            fragment = new FragmentThird();

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
            getMenuInflater().inflate(R.menu.menu2, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings)
            {

                fragment = new Insert();

                if(fragment != null)
                {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment);
                    ft.commit();
                }

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

                fragment = new FragmentThird();

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
                intent1 = new Intent(MainActivity2.this, Open.class);

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

    }


