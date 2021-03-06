package com.example.bs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bs.data.SQLiteDatabaseHandler;
import com.example.bs.data.User;

public class SignupActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etEmail, etPassword, etRepeatPassword;
    final int MIN_PASSWORD_LENGTH = 6;
    SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db= new SQLiteDatabaseHandler(this);
        viewInitializations();
    }

    void viewInitializations() {
        etFirstName = findViewById(R.id.sign_up_activity_firstNameEditText);
        etLastName = findViewById(R.id.sign_up_activity_lastNameEditText);
        etEmail = findViewById(R.id.sign_up_activity_emailEditText);
        etPassword = findViewById(R.id.sign_up_activity_passwordEditText);
        etRepeatPassword = findViewById(R.id.sign_up_activity_rePasswordEditText);

        // To show back button in actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Checking if the input in form is valid
    boolean validateInput() {
        if (etFirstName.getText().toString().equals("")) {
            etFirstName.setError("Please Enter First Name");
            return false;
        }
        if (etLastName.getText().toString().equals("")) {
            etLastName.setError("Please Enter Last Name");
            return false;
        }
        if (etEmail.getText().toString().equals("")) {
            etEmail.setError("Please Enter Email");
            return false;
        }
        if (etPassword.getText().toString().equals("")) {
            etPassword.setError("Please Enter Password");
            return false;
        }
        if (etRepeatPassword.getText().toString().equals("")) {
            etRepeatPassword.setError("Please Enter Repeat Password");
            return false;
        }

        // checking the proper email format
        if (!isEmailValid(etEmail.getText().toString())) {
            etEmail.setError("Please Enter Valid Email");
            return false;
        }

        // checking minimum password Length
        if (etPassword.getText().length() < MIN_PASSWORD_LENGTH) {
            etPassword.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters");
            return false;
        }

        // Checking if repeat password is same
        if (!etPassword.getText().toString().equals(etRepeatPassword.getText().toString())) {
            etRepeatPassword.setError("Password does not match");
            return false;
        }
        return true;
    }

    boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Hook Click Event

    public void performSignUp (View v) {
//        if (validateInput()) {
        if (true) {

            // Input is valid, here send data to your server

            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String repeatPassword = etRepeatPassword.getText().toString();

            User newUser = new User(firstName, lastName, email, password, false, true);
            db.addUser(newUser);
            // Here you can call you API
            Toast.makeText(this,"User added Success",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(i);
        }
    }

}