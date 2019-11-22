package com.example.mobiledevelopmentapp_b00686672;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity
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

}
