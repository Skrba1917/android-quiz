package com.example.androidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondGameActivity extends AppCompatActivity {

    private Button btnNextGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_game);

        btnNextGame = (Button) findViewById(R.id.btn_nextGame);
        btnNextGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThirdGameActivity();
            }
        });

    }

    public void openThirdGameActivity() {
        Intent intent = new Intent(this, ThirdGameActivity.class);
        startActivity(intent);
    }

}