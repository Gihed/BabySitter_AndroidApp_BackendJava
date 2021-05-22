package com.example.bs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bs.data.SQLiteDatabaseHandler;
import com.example.bs.data.User;

import static java.util.Objects.nonNull;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signUpButton;
    SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new SQLiteDatabaseHandler(this);

        usernameEditText = findViewById(R.id.activity_main_usernameEditText);
        passwordEditText = findViewById(R.id.activity_main_passwordEditText);
        loginButton = findViewById(R.id.activity_main_loginButton);
        signUpButton = findViewById(R.id.activity_main_signupButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User loggedInUser = db.getUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                if(loggedInUser!= null && usernameEditText.getText().toString().equals(loggedInUser.getEmail()) &&
                        passwordEditText.getText().toString().equals(loggedInUser.getPassword())) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, CustomUserListActivity.class);
                    if(loggedInUser.getBabySitter()) {
                        i.putExtra("userType","userIsParent");
                    }
                    if(loggedInUser.getParent()) {
                        i.putExtra("userType","userIsBs");
                    }
                    startActivity(i);
                } else {
                    String toastMessage = "Wrong Username or Password";
                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Redirecting...to sign Up",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });*/
    }

    public void redirectToSignUp(View view){
        Intent i = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(i);
    }


}