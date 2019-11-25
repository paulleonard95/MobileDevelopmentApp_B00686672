package com.example.mobiledevelopmentapp_b00686672;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResetPassword extends AppCompatActivity
{

    LoginDatabaseAdapter loginDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

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

    public void Reset_Password_OK(View view)
    {
        Intent intent = new Intent(ResetPassword.this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDatabaseAdapter.close();
    }
}
