package com.example.androidquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Handler;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FirstGameActivity extends AppCompatActivity {
    private Button btnNextGame;
    private List<Button> buttonList;
    private Handler handler = new Handler();
    private Button answer4;
    private Button question;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private DatabaseReference databaseReference;

    private List<KoZnaZna> selectedObjects;
    private TextView timerTextView;

    private int currentObjectIndex = 0;
    private TextView questionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_game);

        FirebaseApp.initializeApp(this);

        // Get a reference to the Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference();
        selectedObjects = new ArrayList<>();

        answer1 = (findViewById(R.id.answer1));
        answer2 = (findViewById(R.id.answer2));
        answer3 = (findViewById(R.id.answer3));
        answer4 = (findViewById(R.id.answer4));

        answer1.setBackgroundColor(Color.GRAY);
        answer2.setBackgroundColor(Color.GRAY);
        answer3.setBackgroundColor(Color.GRAY);
        answer4.setBackgroundColor(Color.GRAY);

        timerTextView = findViewById(R.id.timer);
        questionTextView = findViewById(R.id.question);

        btnNextGame = (Button) findViewById(R.id.btn_nextGame);

        readDataFromFirebase();
        btnNextGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSecondGameActivity();
            }
        });

    }

    private void readDataFromFirebase() {
        // Assuming your data is stored under the "objects" node in your Firebase database
        DatabaseReference objectsRef = databaseReference.child("KoZnaZna");

        // Attach a listener to retrieve the data once
        objectsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    List<KoZnaZna> objectList = new ArrayList<>();

                    // Iterate through the children of the "objects" node
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Get the data for each child and convert it to ObjectModel
                        KoZnaZna object = snapshot.getValue(KoZnaZna.class);
                        if (object != null) {
                            objectList.add(object);
                        }
                    }



                    Set<Integer> usedIndices = new HashSet<>();
                    Random random = new Random();

                    while (selectedObjects.size() < 5) {
                        int randomIndex = random.nextInt(objectList.size());

                        // Check if the index has already been used
                        if (!usedIndices.contains(randomIndex)) {
                            selectedObjects.add(objectList.get(randomIndex));
                            usedIndices.add(randomIndex);
                        }
                    }


                    UpdateButtons();
                }
            }



            private void UpdateButtons() {
                if(currentObjectIndex == 5){
                    openSecondGameActivity();
                }
                answer1.setBackgroundColor(Color.GRAY);
                answer2.setBackgroundColor(Color.GRAY);
                answer3.setBackgroundColor(Color.GRAY);
                answer4.setBackgroundColor(Color.GRAY);
                KoZnaZna x = selectedObjects.get(currentObjectIndex);

                List<String> options = new ArrayList<>();
                options.add(x.getOption1());
                options.add(x.getOption2());
                options.add(x.getOption3());
                options.add(x.getSolution());

                Collections.shuffle(options);

                answer1.setText(options.get(0));
                answer2.setText(options.get(1));
                answer3.setText(options.get(2));
                answer4.setText(options.get(3));
                questionTextView.setText(x.getQuestion());

                answer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentObjectIndex++;
                        checkCorrectAnswer(selectedObjects.get(currentObjectIndex - 1));
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                UpdateButtons();
                            }
                        }, 3000);


                    }
                });

                answer2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentObjectIndex++;
                        checkCorrectAnswer(selectedObjects.get(currentObjectIndex - 1));
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                UpdateButtons();
                            }
                        }, 3000);

                    }
                });

                answer3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentObjectIndex++;
                        checkCorrectAnswer( selectedObjects.get(currentObjectIndex - 1));
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                UpdateButtons();
                            }
                        }, 3000);

                    }
                });

                answer4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentObjectIndex++;
                        checkCorrectAnswer(selectedObjects.get(currentObjectIndex - 1));
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                UpdateButtons();
                            }
                        }, 3000);

                    }
                });

            }

            private void checkCorrectAnswer(KoZnaZna currentObject) {
                String correctSolution = currentObject.getSolution();

                if (answer1.getText().equals(correctSolution)) {
                    answer1.setBackgroundColor(Color.GREEN);
                } else {
                    answer1.setBackgroundColor(Color.RED);
                }

                if (answer2.getText().equals(correctSolution)) {
                    answer2.setBackgroundColor(Color.GREEN);
                } else {
                    answer2.setBackgroundColor(Color.RED);
                }

                if (answer3.getText().equals(correctSolution)) {
                    answer3.setBackgroundColor(Color.GREEN);
                } else {
                    answer3.setBackgroundColor(Color.RED);
                }

                if (answer4.getText().equals(correctSolution)) {
                    answer4.setBackgroundColor(Color.GREEN);
                } else {
                    answer4.setBackgroundColor(Color.RED);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occur
                Log.e("Firebase", "Error retrieving data from Firebase: " + databaseError.getMessage());
            }
        });
    }

    public void openSecondGameActivity() {
        Intent intent = new Intent(this, SecondGameActivity.class);
        startActivity(intent);
    }

}