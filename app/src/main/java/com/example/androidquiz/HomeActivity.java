package com.example.androidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private Button playButton;

    private String loggedInUsername = ""; // Track logged-in user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        playButton = findViewById(R.id.btn_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActivity();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            loggedInUsername = extras.getString("loggedInUsername", "");
        }
    }

    public void openGameSixActivity() {
        Intent intent = new Intent(this, SixthGameActivity.class);
        startActivity(intent);
    }

    public void openGameActivity() {
        Intent intent = new Intent(this, ThirdGameActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem myProfileItem = menu.findItem(R.id.item1);
        myProfileItem.setEnabled(!loggedInUsername.isEmpty()); // Enable if logged in

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item1) {
            if (!loggedInUsername.isEmpty()) {
                openProfileActivity();
            } else {
                Toast.makeText(this, "Please log in to view profile", Toast.LENGTH_SHORT).show();
            }
        } else if (itemId == R.id.item2) {
            Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.item3) {
            Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.subitem1) {
            Toast.makeText(this, "Sub Item 1 selected", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.subitem2) {
            Toast.makeText(this, "Sub Item 2 selected", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void openProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("loggedInUsername", loggedInUsername); // Pass the username
        startActivity(intent);
    }
}



