package com.example.androidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item1) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.item2) {
            Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.item3) {
            Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.subitem1) {
            Toast.makeText(this, "Sub Item 1 selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.subitem2) {
            Toast.makeText(this, "Sub Item 2 selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}