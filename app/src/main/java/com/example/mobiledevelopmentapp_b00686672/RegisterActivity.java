package com.example.mobiledevelopmentapp_b00686672;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public static final Pattern VALID_EMAIL_ADDRESS_CHECK = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_CHECK.matcher(email);
        return matcher.find();
    }

    public static boolean validatePassword(String password) {
        Matcher matcher = VALID_PASSWORD.matcher(password);
        return matcher.find();
    }

    public void SignUP_OK(View view) {
        String userName = ((EditText) findViewById(R.id.username)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        String confirmPassword = ((EditText) findViewById(R.id.cnf_password)).getText().toString();
        String name = ((EditText) findViewById(R.id.name)).getText().toString();
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String gender = ((EditText) findViewById(R.id.gender)).getText().toString();

        if (userName.equals("") || password.equals("") || confirmPassword.equals("") || name.equals("") || email.equals("") || gender.equals("")) {
            Toast.makeText(RegisterActivity.this, "Fill All Fields", Toast.LENGTH_LONG).show();
            return;
        }
        // check if both password matches
        if (!password.equals(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
            return;
        } else {
            if (validatePassword(password) == false) {
                Toast.makeText(RegisterActivity.this, "Please enter a strong password Using Uppercase, Lowercase & Numbers", Toast.LENGTH_LONG).show();

            } else {
                if (validateEmail(email) == false) {
                    Toast.makeText(RegisterActivity.this, "Please enter a valid email address", Toast.LENGTH_LONG).show();
                }
            }


            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Successfully Registered",
                                        Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                finish();
                            } else {
                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                Toast.makeText(RegisterActivity.this, "Failed Registration: "
                                        + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });



        }

    }
}
