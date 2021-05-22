package com.example.bs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.bs.data.CustomUserList;
import com.example.bs.data.SQLiteDatabaseHandler;
import com.example.bs.data.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CustomUserListActivity extends AppCompatActivity {
    SQLiteDatabaseHandler db;
    List<User> userList;
    CustomUserList customUserList;
    ListView listView;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_user_list);
        activity=this;
        db= new SQLiteDatabaseHandler(this);
        listView = findViewById(android.R.id.list);

        Intent previousIntent = getIntent();
        String userType = previousIntent.getStringExtra("userType");
        userList = db.getAllUsers(userType);
        customUserList = new CustomUserList(this, userList, db);
        listView.setAdapter(customUserList);
    }
}