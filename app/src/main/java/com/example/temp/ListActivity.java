package com.example.temp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    private ImageView myImage;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        generateUserList();

        UserAdapter adapter = new UserAdapter(userList);
        recyclerView.setAdapter(adapter);
    }

    public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
        private List<User> userList;

        public UserAdapter(List<User> userList) {
            this.userList = userList;
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_item, parent, false);
            return new UserViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            User user = userList.get(position);
            holder.userNameTextView.setText(user.getName());
            holder.userDescriptionTextView.setText(user.getDescription());

        }

        @Override
        public int getItemCount() {
            return userList.size();
        }

        public class UserViewHolder extends RecyclerView.ViewHolder {
            public ImageView userImageView;
            public TextView userNameTextView;
            public TextView userDescriptionTextView;


            public UserViewHolder(View itemView) {
                super(itemView);
                userImageView = itemView.findViewById(R.id.userImageView);
                userNameTextView = itemView.findViewById(R.id.userNameTextView);
                userDescriptionTextView = itemView.findViewById(R.id.userDescriptionTextView);

                userImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            User user = userList.get(position);
                            showAlertDialog(user.getName());
                        }
                    }
                });
            }

            private void showAlertDialog(String name) {
                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                builder.setTitle("Profile");
                builder.setMessage(name);
                builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // Start MainActivity here
                        Intent intent = new Intent(itemView.getContext(), MainActivity.class);
                        intent.putExtra("userName", name);
                        itemView.getContext().startActivity(intent);
                    }
                });
                builder.show();
            }
        }
    }

    public class VerticalMarginItemDecoration extends RecyclerView.ItemDecoration {
        private int verticalMargin;

        public VerticalMarginItemDecoration(int verticalMargin) {
            this.verticalMargin = verticalMargin;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            if (position != RecyclerView.NO_POSITION && position != parent.getAdapter().getItemCount() - 1) {
                outRect.bottom = verticalMargin;
            }
        }
    }

    public class User {
        private String name;
        private String description;
        private boolean isFollowed;

        // Constructor
        public User(String name, String description, boolean isFollowed) {
            this.name = name;
            this.description = description;
            this.isFollowed = isFollowed;
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isFollowed() {
            return isFollowed;
        }

        public void setFollowed(boolean followed) {
            isFollowed = followed;
        }
    }

    private List<User> userList;

    private void generateUserList() {
        userList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            String name = "Name" + generateRandomNumber();
            String description = "Description " + generateRandomNumber();
            boolean isFollowed = Math.random() < 0.5; // Randomly assign followed value

            User user = new User(name, description, isFollowed);
            userList.add(user);
        }
    }

    private String generateRandomNumber() {
        long randomNumber = (long) (Math.random() * 1e10);
        return String.valueOf(randomNumber);
    }




}
