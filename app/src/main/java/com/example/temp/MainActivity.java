package com.example.temp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");

        TextView tv = findViewById(R.id.textView2);
        tv.setText(userName);

        // Get the button by its ID
        final Button myButton = (Button) findViewById(R.id.follow);

        // Set the OnClickListener
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myButton.getText().equals("FOLLOW")) {
                    myButton.setText("UNFOLLOW");
                    showToast("Followed");
                } else {
                    myButton.setText("FOLLOW");
                    showToast("Unfollowed");
                }
            }
        });

        Button messageButton = findViewById(R.id.message);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform the action when the button is clicked
                Intent intent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(intent);
            }
        });
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}




