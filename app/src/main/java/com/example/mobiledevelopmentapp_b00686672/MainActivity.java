package com.example.mobiledevelopmentapp_b00686672;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
    LoginDatabaseAdapter loginDatabaseAdapter;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void SignIn(View view)
    {
        try
        {
            String username = ((EditText) findViewById(R.id.username)).getText().toString();
            String password = ((EditText) findViewById(R.id.password)).getText().toString();
            if (username.equals("") || password.equals(""))
            {
                Toast.makeText(MainActivity.this, "Please ensure all fields are filled", Toast.LENGTH_LONG).show();
            }
            if (!username.equals(""))
            {
                String storedPassword = loginDatabaseAdapter.getSinlgeEntry(username);
                if (password.equals(storedPassword))
                {
                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                    intent.putExtra("Name", username);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "No one is registered with that username or password. Please reset or sign up", Toast.LENGTH_LONG).show();
                }
            }
        }
        catch (Exception exception)
        {
            Log.e("Error", "error when logging in");
        }
    }

    public void Register(View view)
    {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    public void Reset_Password(View view)
    {
        Intent intent = new Intent(MainActivity.this, ResetPassword.class);
        startActivity(intent);
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        loginDatabaseAdapter.close();
    }
}
