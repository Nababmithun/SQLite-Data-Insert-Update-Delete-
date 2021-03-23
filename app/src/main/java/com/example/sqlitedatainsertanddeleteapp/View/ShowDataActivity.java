package com.example.sqlitedatainsertanddeleteapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.sqlitedatainsertanddeleteapp.Adapter.UserAdapter;
import com.example.sqlitedatainsertanddeleteapp.DataSource.DatabaseHelper;
import com.example.sqlitedatainsertanddeleteapp.Model.User;
import com.example.sqlitedatainsertanddeleteapp.R;

import java.util.ArrayList;
import java.util.List;

public class ShowDataActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<User> users;
    private UserAdapter adapter;
    private DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);


        init();
        getData();
    }

    private void getData() {

        Cursor cursor=  helper.showData();
        while (cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
            String name= cursor.getString(cursor.getColumnIndex(helper.COL_NAME));
            String age = cursor.getString(cursor.getColumnIndex(helper.COL_AGE));

            users.add(new User(id,name,age));
            adapter.notifyDataSetChanged();
        }

    }

    private void init() {
        recyclerView = findViewById(R.id.userRecyclearview);
        users = new ArrayList<>();
        helper = new DatabaseHelper(this);
        adapter = new UserAdapter(this,users,helper);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}