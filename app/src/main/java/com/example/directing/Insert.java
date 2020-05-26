package com.example.directing;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;

public class Insert extends Fragment {

    private EditText fname,fid;
    private ImageView pic;
    private DatabaseHandler db;
    private String f_name,f_id;
    // GridView gridView;
    dataAdapter data;
    private Contact dataModel;
    private Bitmap bp;
    private byte[] photo,photo1;

    Button b1,b2;

    Button save,show;

    Fragment fragment = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_insert, container, false);

        //Instantiate database handler
        db=new DatabaseHandler(getActivity());

        // gridView = (GridView) findViewById(R.id.list1);
        pic= (ImageView) view.findViewById(R.id.pic);

        fname=(EditText) view.findViewById(R.id.txt1);

        fid = (EditText) view.findViewById(R.id.txt2);

        b1 = (Button) view.findViewById(R.id.updates);
        b2 = (Button) view.findViewById(R.id.deletes);

        /* b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Update.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Delete.class);
                startActivity(intent);
            }
        }); */

        save = (Button) view.findViewById(R.id.save);
        show = (Button) view.findViewById(R.id.display);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fname.getText().toString().trim().equals("")){
                    Toast.makeText(getActivity(),"Name edit text is empty, Enter name", Toast.LENGTH_LONG).show();
                }

                if(fid.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getActivity(),"id edit text is empty, Enter id", Toast.LENGTH_SHORT).show();
                }
                else{
                    addContact();
                }

            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                fragment = new FragmentThird();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.commit();
            }
        });

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });


        return view;
    }

    /* public void buttonClicked(View v){
        int id=v.getId();

        switch(id){

            case R.id.save:

                if(fname.getText().toString().trim().equals("")){
                    Toast.makeText(getActivity(),"Name edit text is empty, Enter name", Toast.LENGTH_LONG).show();
                }

                if(fid.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getActivity(),"id edit text is empty, Enter id", Toast.LENGTH_SHORT).show();
                }
                else{
                    addContact();
                }

                break;

            case R.id.display:
                //ShowRecords();
                //Intent intent=new Intent(MainActivity.this,Other.class);
                //startActivity(intent);
                fragment = new FragmentThird();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.commit();
                break;

            case R.id.pic:
                selectImage();
                break;

        } */

    public void selectImage()
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 2);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 2:
                if(resultCode == RESULT_OK){
                    Uri choosenImage = data.getData();

                    if(choosenImage !=null){

                        bp=decodeUri(choosenImage, 400);
                        pic.setImageBitmap(bp);
                    }
                }
        }
    }


    //COnvert and resize our image to 400dp for faster uploading our images to DB
    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {

        try {

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(selectedImage), null, o);

            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(selectedImage), null, o2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Convert bitmap to bytes
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private byte[] profileImage(Bitmap b){

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();

    }



    // function to get values from the Edittext and image
    private void getValues()
    {
        f_id = fid.getText().toString();
        f_name = fname.getText().toString();
        photo = profileImage(bp);
    }

    //Insert data to the database
    private void addContact(){
        getValues();

        int id1 = Integer.parseInt(f_id);
        db.addContacts(new Contact(id1,f_name, photo));
        Toast.makeText(getActivity(),"Saved successfully", Toast.LENGTH_LONG).show();
    }

    //Retrieve data from the database and set to the list view
   /* private void ShowRecords()
    {
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
    }*/

}

