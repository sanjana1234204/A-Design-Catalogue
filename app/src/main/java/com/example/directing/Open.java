package com.example.directing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Open extends AppCompatActivity
{
    SharedPreferences pref;
    Intent intent1;

    EditText ed,ed1;
    Button b1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        pref = getSharedPreferences("admin_details",MODE_PRIVATE);

        intent1 = new Intent(Open.this,MainActivity2.class);

        if(pref.contains("username") && pref.contains("password"))
        {
            startActivity(intent1);
        }

        ed = (EditText) findViewById(R.id.email);
        ed1 = (EditText) findViewById(R.id.pass);

       b1 = (Button) findViewById(R.id.login);

       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String s1 = ed.getText().toString().trim();
               String s2 = ed1.getText().toString().trim();

               if(s1.length()==0)
               {
                   ed.setError("please enter email address");
               }

               if(s2.length()==0)
               {
                   ed1.setError("please enter password");
               }

               if(s1.compareToIgnoreCase("admin1234@gmail.com")==0 && s2.compareToIgnoreCase("admin")==0)
               {
                   SharedPreferences .Editor editor = pref.edit();

                   editor.putString("username",s1);
                   editor.putString("password",s2);

                   editor.commit();

                   Intent intent = new Intent(Open.this, MainActivity2.class);
                   intent.putExtra("EMAIL", s1);
                   startActivity(intent);
               }

               else
               {
                   Toast.makeText(getApplicationContext(),"Invaild email id or password", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
}
