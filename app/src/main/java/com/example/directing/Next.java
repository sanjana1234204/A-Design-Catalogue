package com.example.directing;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Next extends AppCompatActivity
{
    SQLiteDatabase db;
    EditText ed;
    Button b1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        db = openOrCreateDatabase("LoginnDB", Context.MODE_PRIVATE, null);

        ed = (EditText) findViewById(R.id.editText);

        b1 = (Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor c = db.rawQuery("SELECT * FROM EMPLOYE WHERE EmpMail='"+ed.getText()+"'",null);

                String s1 = " ";
                String s2 = " ";

                if(c.moveToFirst())
                {
                    s1 = c.getString(1);
                    s2 = c.getString(2);
                }

                if(ed.getText().toString().trim().equalsIgnoreCase(s1))
                {
                    Toast.makeText(getApplicationContext(),"password is:-"+s2, Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Toast.makeText(getApplicationContext(),"wrong or invalid email id", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

