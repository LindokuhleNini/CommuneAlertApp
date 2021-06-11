package com.example.communealertapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CreateGroup extends AppCompatActivity {

    DatabaseReference reference;

    private EditText groupNameField, groupDescription;
    private String name;
    private EditText ee;
    private Button btnCreateGroup;
    private ImageView cancel;

    private FirebaseAuth mAuth;
    private DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        InitializeAllFields();
        mAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();

        btnCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupName = groupNameField.getText().toString();

                if (TextUtils.isEmpty(groupName)){
                    Toast.makeText(CreateGroup.this, "Please provide group name...", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        CreateNewGroup(groupName);

                }
            }
        });


    }

    private void InitializeAllFields() {

        groupNameField = (EditText)findViewById(R.id.etGroupName);
        btnCreateGroup = (Button)findViewById(R.id.createGroup);
        cancel = (ImageView)findViewById(R.id.cancelCreateGroup);
        reference = FirebaseDatabase.getInstance().getReference().getRoot();

    }

    private void CreateNewGroup(final String groupName)
    {
        rootRef.child("Groups").child(groupName).setValue("")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(CreateGroup.this, groupName + "group is created successfully...", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


}