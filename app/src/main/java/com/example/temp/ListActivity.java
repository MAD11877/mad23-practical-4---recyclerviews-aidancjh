package com.example.temp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ListActivity extends AppCompatActivity {

    private ImageView myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        myImage = findViewById(R.id.image);
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile");
        builder.setMessage("MADness");
        builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Random random = new Random();
                int randomnumber = random.nextInt(100000);
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                intent.putExtra("random_integer", randomnumber);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("CLOSE", null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
