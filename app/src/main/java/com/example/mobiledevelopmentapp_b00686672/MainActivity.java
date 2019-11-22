package com.example.mobiledevelopmentapp_b00686672;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    LoginDatabaseAdapter loginDatabaseAdapter;

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
        loginDatabaseAdapter = new LoginDatabaseAdapter(this);
        loginDatabaseAdapter = loginDatabaseAdapter.open();
    }

    public void SignIn(View view)
    {
        try
        {
            String username = ((EditText) findViewById(R.id.userName)).getText().toString();
            String password = ((EditText) findViewById(R.id.loginPassword)).getText().toString();
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
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        loginDatabaseAdapter.close();
    }
}
