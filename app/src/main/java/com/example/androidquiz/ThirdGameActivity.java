package com.example.androidquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdGameActivity extends AppCompatActivity {
    private Button btnNextGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_game);

        btnNextGame = (Button) findViewById(R.id.btn_nextGame);
        btnNextGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFourthGameActivity();
            }
        });
    }

    public void openFourthGameActivity() {
        Intent intent = new Intent(this, FourthGameActivity.class);
        startActivity(intent);
    }
}
