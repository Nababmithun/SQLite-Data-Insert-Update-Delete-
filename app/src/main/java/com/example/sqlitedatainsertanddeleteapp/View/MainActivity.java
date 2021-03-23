package com.example.sqlitedatainsertanddeleteapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitedatainsertanddeleteapp.DataSource.DatabaseHelper;
import com.example.sqlitedatainsertanddeleteapp.R;

public class MainActivity extends AppCompatActivity {
    private EditText nameET,ageET;
    private Button insertBtn;
    private String name, age;
    private DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        insertData();


    }

    private void insertData() {
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name =nameET.getText().toString();
                age = ageET.getText().toString();

                long id = helper.insertData(name,age);
                Toast.makeText(MainActivity.this, "ID is "+id, Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void init() {
        nameET = findViewById(R.id.nameET);
        ageET = findViewById(R.id.ageET);
        insertBtn = findViewById(R.id.insertBtn);
        helper = new DatabaseHelper(this);

    }

    public void showUser(View view) {

        startActivity(new Intent(MainActivity.this,ShowDataActivity.class));
    }
}