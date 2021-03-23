package com.example.sqlitedatainsertanddeleteapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedatainsertanddeleteapp.DataSource.DatabaseHelper;
import com.example.sqlitedatainsertanddeleteapp.Model.User;
import com.example.sqlitedatainsertanddeleteapp.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private List<User> users;
    private DatabaseHelper helper;

    public UserAdapter(Context context, List<User> users, DatabaseHelper helper) {
        this.context = context;
        this.users = users;
        this.helper = helper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final User user = users.get(position);
        holder.name.setText(user.getName());
        holder.age.setText(user.getAge());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                helper = new DatabaseHelper(context);
                helper.deleteData(user.getId());
                users.remove(position);
                notifyDataSetChanged();


                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name,age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name= itemView.findViewById(R.id.nameTV);
            age = itemView.findViewById(R.id.ageTV);
        }
    }
}