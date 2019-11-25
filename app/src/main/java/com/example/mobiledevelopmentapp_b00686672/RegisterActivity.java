package com.example.mobiledevelopmentapp_b00686672;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity
{
        LoginDatabaseAdapter loginDatabaseAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            loginDatabaseAdapter = new LoginDatabaseAdapter(this);
            loginDatabaseAdapter = loginDatabaseAdapter.open();
        }
        public static final Pattern VALID_EMAIL_ADDRESS_CHECK = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        public static final Pattern VALID_PASSWORD = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", Pattern.CASE_INSENSITIVE);

        public static boolean validateEmail(String email)
        {
            Matcher matcher = VALID_EMAIL_ADDRESS_CHECK.matcher(email);
            return matcher.find();
        }
        public static boolean validatePassword(String password)
        {
            Matcher matcher = VALID_PASSWORD.matcher(password);
            return matcher.find();
        }
        public void SignUP_OK(View view)
        {
            String userName = ((EditText) findViewById(R.id.username)).getText().toString();
            String password = ((EditText) findViewById(R.id.password)).getText().toString();
            String confirmPassword = ((EditText) findViewById(R.id.cnf_password)).getText().toString();
            String name = ((EditText) findViewById(R.id.name)).getText().toString();
            String email = ((EditText) findViewById(R.id.email)).getText().toString();
            String gender = ((EditText) findViewById(R.id.gender)).getText().toString();


            if (userName.equals("") || password.equals("") || confirmPassword.equals("") || name.equals("") || email.equals("") || gender.equals(""))
            {
                Toast.makeText(RegisterActivity.this, "Fill All Fields", Toast.LENGTH_LONG).show();
                return;
            }
            // check if both password matches
            if (!password.equals(confirmPassword))
            {
                Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                return;
            } else
            {

                if (validatePassword(password) == false)
                {
                    Toast.makeText(RegisterActivity.this, "Please enter a strong password Using Uppercase, Lowercase & Numbers", Toast.LENGTH_LONG).show();

                }
                else
                {
                    if (validateEmail(email) == false)
                    {
                        Toast.makeText(RegisterActivity.this, "Please enter a valid email address", Toast.LENGTH_LONG).show();
                    } else
                    {
                        // Save the Data in Database
                        loginDatabaseAdapter.insertEntry(userName, password, name, email, gender);
                        Toast.makeText(getApplicationContext(),
                                "Your Account is Successfully Created. You can Sign In now.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }


            }
        }

        public void Login_Screen(View view)
        {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            loginDatabaseAdapter.close();
        }
}
