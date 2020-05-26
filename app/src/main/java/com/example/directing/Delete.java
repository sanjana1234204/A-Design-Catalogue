package com.example.directing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends AppCompatActivity
{
    EditText ed;
    Button b1;

    private DatabaseHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        db=new DatabaseHandler(this);

        ed = (EditText) findViewById(R.id.edit2);

        b1 = (Button) findViewById(R.id.delete);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = ed.getText().toString();

                int value = db.deleteContact(Integer.parseInt(str));

                if (value != 0)
                {
                    Toast.makeText(getApplicationContext(),"data deleted successfully",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Toast.makeText(getApplicationContext(),"data not found",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
