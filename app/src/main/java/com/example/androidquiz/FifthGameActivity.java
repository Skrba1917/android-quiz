package com.example.androidquiz;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class FifthGameActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private List<Button> buttonList;
    private int currentButtonIndex;
    private KorakPoKorak selectedObject;

    private CountDownTimer timer;
    private TextView timerTextView;

    private TextView pointsTextView;
    private final long TIMER_DURATION = 11000; // 10 seconds

    private int totalPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_game);

        FirebaseApp.initializeApp(this);

        // Get a reference to the Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Initialize the Button list
        buttonList = new ArrayList<>();
        buttonList.add(findViewById(R.id.outlinedButton));
        buttonList.add(findViewById(R.id.outlinedButton1));
        buttonList.add(findViewById(R.id.outlinedButton2));
        buttonList.add(findViewById(R.id.outlinedButton3));
        buttonList.add(findViewById(R.id.outlinedButton4));
        buttonList.add(findViewById(R.id.outlinedButton5));
        buttonList.add(findViewById(R.id.outlinedButton6));

        timerTextView = findViewById(R.id.timerTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        pointsTextView.setText("Bodovi: " + String.valueOf(getTotalPoints()));


        // Read the data from the database
        readDataFromFirebase();
    }

    private void readDataFromFirebase() {
        // Assuming your data is stored under the "objects" node in your Firebase database
        DatabaseReference objectsRef = databaseReference.child("KorakPoKorak");

        // Attach a listener to retrieve the data once
        objectsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    List<KorakPoKorak> objectList = new ArrayList<>();

                    // Iterate through the children of the "objects" node
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Get the data for each child and convert it to ObjectModel
                        KorakPoKorak object = snapshot.getValue(KorakPoKorak.class);
                        if (object != null) {
                            objectList.add(object);
                        }
                    }

                    // Choose a random object from the list
                    Random random = new Random();
                    int randomIndex = random.nextInt(objectList.size());
                    selectedObject = objectList.get(randomIndex);

                    // Start updating the buttons
                    startButtonUpdates();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occur
                Log.e("Firebase", "Error retrieving data from Firebase: " + databaseError.getMessage());
            }
        });
    }

    private void startButtonUpdates() {
        currentButtonIndex = 0;
        updateButton();
    }

    private void updateButton() {
        if (currentButtonIndex < buttonList.size()) {
            Button currentButton = buttonList.get(currentButtonIndex);

            String propertyName = "step" + (currentButtonIndex + 1);
            String propertyValue = getProperty(selectedObject, propertyName);

            currentButton.setText(propertyValue);

            currentButtonIndex++;

            EditText solutionEditText = findViewById(R.id.solutionEditText);


            // Add an OnEditorActionListener to the solution TextInputEditText
            solutionEditText.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String userInput = v.getText().toString();
                    if (userInput.equalsIgnoreCase(selectedObject.getSolution())) {
                        // User entered the correct solution
                        stopButtonUpdates();
                        int currentButtonIndex = getCurrentButtonIndex();
                        int points = 22 - currentButtonIndex * 2;
                        showPointsDialog(points);
                    }else {
                        showWrongSolutionAlert();
                        renderNextButton();
                    }
                    return true;
                }
                return false;
            });

            // Check if all buttons have been updated
            if (currentButtonIndex >= buttonList.size()) {
                // Set the text of the solution TextInputEditText to the "solution" property of selectedObject
                solutionEditText.setText(selectedObject.getSolution());
            } else {
                // Start the timer for each button change
                startTimer();
            }
        }
    }

    private void showWrongSolutionAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pogresno resenje");
        builder.setMessage("Pogresno resenje.");
        builder.setPositiveButton("OK", (dialog, which) -> {
            // Handle the OK button click if needed
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void renderNextButton() {
        if (currentButtonIndex < buttonList.size()) {
            Button currentButton = buttonList.get(currentButtonIndex);

            String propertyName = "step" + (currentButtonIndex + 1);
            String propertyValue = getProperty(selectedObject, propertyName);

            currentButton.setText(propertyValue);

            currentButtonIndex++;

            // Start the timer for the next button change
            startTimer();
        }
    }
    private void stopButtonUpdates() {
        // Cancel the timer
        if (timer != null) {
            timer.cancel();
        }
    }

    private int getCurrentButtonIndex() {
        return currentButtonIndex; // Subtract 1 since currentButtonIndex has already been incremented
    }

    private void startTimer() {
        if (timer != null) {
            timer.cancel();
        }

        timer = new CountDownTimer(TIMER_DURATION, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                timerTextView.setText(String.valueOf(secondsRemaining));
            }

            @Override
            public void onFinish() {
                updateButton();
            }
        };

        timer.start();
    }

    // Helper method to get the property value of an object using reflection
    private String getProperty(Object object, String propertyName) {
        try {
            Field field = object.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            Object value = field.get(object);
            if (value != null) {
                return value.toString();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel the timer when the activity is destroyed
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void showPointsDialog(int points) {
        savePoints(points);
        int totalPoints = getTotalPoints();
        endGame();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Points");
        builder.setMessage("Osvojili ste " + points + " bodova!");
        builder.setPositiveButton("OK", (dialog, which) -> {
            // Handle the OK button click if needed
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void savePoints(int points) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int totalPoints = preferences.getInt("totalPoints", 0);
        totalPoints += points;
        pointsTextView.setText("Bodovi: " + String.valueOf(totalPoints)); // Update pointsTextView with the total points
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("totalPoints", totalPoints);
        editor.apply();

    }


    private int getTotalPoints() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return preferences.getInt("totalPoints", 0);
    }

    private void resetPoints() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("totalPoints", 0);
        editor.apply();
    }

    private void endGame() {
       String points = String.valueOf(getTotalPoints());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Points");
        builder.setMessage("Kraj igre, ukupno imate " + points + " bodova!");
        builder.setPositiveButton("OK", (dialog, which) -> {
            // Handle the OK button click if needed
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}