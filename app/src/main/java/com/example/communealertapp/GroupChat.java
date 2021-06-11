package com.example.communealertapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GroupChat extends AppCompatActivity {
    private Toolbar mToolbar;
    private Button sendButton;
    private EditText userMessageInput;
    private ScrollView mScrollView;
    private TextView displayMessage;

    private String currentGroupName;

    DatabaseReference reference;
    String temp_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        currentGroupName = getIntent().getExtras().get("groupName").toString();
        Toast.makeText(GroupChat.this, currentGroupName, Toast.LENGTH_SHORT).show();

        InitializeFields();

    }

    private void InitializeFields() {
        mToolbar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(currentGroupName);

        sendButton = (Button) findViewById(R.id.send_message);
        userMessageInput = (EditText) findViewById(R.id.input_group_message);
        displayMessage = (TextView) findViewById(R.id.group_chat_text_display);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
    }

}