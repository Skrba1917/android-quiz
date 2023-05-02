package com.example.androidquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FourthGameActivity extends AppCompatActivity {
    private Button btnNextGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_game);

        btnNextGame = (Button) findViewById(R.id.btn_nextGame);
        btnNextGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFifthGameActivity();
            }
        });
    }

    public void openFifthGameActivity() {
        Intent intent = new Intent(this, FifthGameActivity.class);
        startActivity(intent);
    }

}
