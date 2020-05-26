package com.example.directing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private final AppCompatActivity activity = LoginActivity.this;

    SQLiteDatabase db;

    private NestedScrollView nestedScrollView;

    SharedPreferences pref;
    Intent intent1;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private AppCompatTextView textViewLinkNext;

    private InputValidation inputValidation;
   // private DatabaseHelper1 databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = openOrCreateDatabase("LoginnDB", Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS EMPLOYE(EmpName VARCHAR, EmpMail VARCHAR, EmpPass VARCHAR);");

        pref = getSharedPreferences("user_details", MODE_PRIVATE);

        intent1 = new Intent(LoginActivity.this, MainActivity.class);

        if(pref.contains("username") && pref.contains("password"))
        {
            startActivity(intent1);
        }
          getSupportActionBar();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
        textViewLinkNext = (AppCompatTextView) findViewById(R.id.textViewLinkNext);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
        textViewLinkNext.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
     //   databaseHelper = new DatabaseHelper1(activity);
        inputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.textViewLinkNext:
                Intent intent1 = new Intent(getApplicationContext(), Next.class);
                startActivity(intent1);
                break;

        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        /* if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {

            String user_name = textInputEditTextEmail.getText().toString().trim();
            String password = textInputEditTextPassword.getText().toString().trim();

            SharedPreferences .Editor editor = pref.edit();

            editor.putString("username",user_name);
            editor.putString("password",password);

            editor.commit();

            Intent accountsIntent = new Intent(activity, MainActivity.class);
            accountsIntent.putExtra("USER_NAME", user_name);
            startActivity(accountsIntent);

           // accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            //startActivity(accountsIntent);



        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }*/

        Cursor c = db.rawQuery("SELECT * FROM EMPLOYE WHERE EmpMail='"+textInputEditTextEmail.getText()+"'",null);

        String s1 = " ";
        String s2 = " ";

        if(c.moveToFirst())
        {
            s1 = c.getString(1);
            s2 = c.getString(2);
        }

        //c.close();

        if(textInputEditTextEmail.getText().toString().trim().equalsIgnoreCase(s1) && textInputEditTextPassword.getText().toString().trim().equalsIgnoreCase(s2))
        {
            String user_name = textInputEditTextEmail.getText().toString().trim();
            String password = textInputEditTextPassword.getText().toString().trim();

            SharedPreferences .Editor editor = pref.edit();

            editor.putString("username",user_name);
            editor.putString("password",password);

            editor.commit();

            Intent accountsIntent = new Intent(activity, MainActivity.class);
            accountsIntent.putExtra("USER_NAME", user_name);
            startActivity(accountsIntent);

            Toast.makeText(getApplicationContext(),"login sucessfully done", Toast.LENGTH_SHORT).show();
        }

        else
        {
            Toast.makeText(getApplicationContext(),"wrong email id or password", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}



