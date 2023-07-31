package com.example.androidquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.collection.LLRBNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FourthGameActivity extends AppCompatActivity {
    private Button btnNextGame;
    List<String> correctCombination = new ArrayList<>();

    List<String> currentRow = new ArrayList<>();

    int nextImageViewIndex = 0;

    int buttonsIndex = 0;

    private TextView pointsTextView;

    private TextView timerTextView;

    private CountDownTimer timer;
    private final long TIMER_DURATION = 31000; // 60 seconds
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;
    ImageView imageView17;
    ImageView imageView18;
    ImageView imageView19;
    ImageView imageView20;
    ImageView imageView21;
    ImageView imageView22;
    ImageView imageView23;
    ImageView imageView24;

    ImageView imageViewSolution1;
    ImageView imageViewSolution2;
    ImageView imageViewSolution3;
    ImageView imageViewSolution4;

    Button btnSolution1;
    Button btnSolution2;
    Button btnSolution3;
    Button btnSolution4;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn10;
    Button btn11;
    Button btn12;
    Button btn13;
    Button btn14;
    Button btn15;
    Button btn16;
    Button btn17;
    Button btn18;
    Button btn19;
    Button btn20;
    Button btn21;
    Button btn22;
    Button btn23;
    Button btn24;

    ImageView pik;

    ImageView karo;

    ImageView zvezda;

    ImageView herc;

    ImageView tref;

    ImageView skocko;

    List<ImageView> imageViewList = new ArrayList<>();

    List<Button> buttons = new ArrayList<>();

    int[] imageResources = {R.drawable.pik_icon, R.drawable.karo_icon, R.drawable.zvezda_icon, R.drawable.herc_icon, R.drawable.tref_icon, R.drawable.skocko3_icon};
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
        generateCorrectCombination();

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        imageView15 = findViewById(R.id.imageView15);
        imageView16 = findViewById(R.id.imageView16);
        imageView17 = findViewById(R.id.imageView17);
        imageView18 = findViewById(R.id.imageView18);
        imageView19 = findViewById(R.id.imageView19);
        imageView20 = findViewById(R.id.imageView20);
        imageView21 = findViewById(R.id.imageView21);
        imageView22 = findViewById(R.id.imageView22);
        imageView23 = findViewById(R.id.imageView23);
        imageView24 = findViewById(R.id.imageView24);

        imageViewSolution1 = findViewById(R.id.imageViewSolution1);
        imageViewSolution2 = findViewById(R.id.imageViewSolution2);
        imageViewSolution3 = findViewById(R.id.imageViewSolution3);
        imageViewSolution4 = findViewById(R.id.imageViewSolution4);

        btnSolution1 = findViewById(R.id.btnSolution1);
        btnSolution2 = findViewById(R.id.btnSolution2);
        btnSolution3 = findViewById(R.id.btnSolution3);
        btnSolution4 = findViewById(R.id.btnSolution4);

        imageViewList.add(imageView1);
        imageViewList.add(imageView2);
        imageViewList.add(imageView3);
        imageViewList.add(imageView4);
        imageViewList.add(imageView5);
        imageViewList.add(imageView6);
        imageViewList.add(imageView7);
        imageViewList.add(imageView8);
        imageViewList.add(imageView9);
        imageViewList.add(imageView10);
        imageViewList.add(imageView11);
        imageViewList.add(imageView12);
        imageViewList.add(imageView13);
        imageViewList.add(imageView14);
        imageViewList.add(imageView15);
        imageViewList.add(imageView16);
        imageViewList.add(imageView17);
        imageViewList.add(imageView18);
        imageViewList.add(imageView19);
        imageViewList.add(imageView20);
        imageViewList.add(imageView21);
        imageViewList.add(imageView22);
        imageViewList.add(imageView23);
        imageViewList.add(imageView24);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);
        btn11 = findViewById(R.id.btn11);
        btn12 = findViewById(R.id.btn12);
        btn13 = findViewById(R.id.btn13);
        btn14 = findViewById(R.id.btn14);
        btn15 = findViewById(R.id.btn15);
        btn16 = findViewById(R.id.btn16);
        btn17 = findViewById(R.id.btn17);
        btn18 = findViewById(R.id.btn18);
        btn19 = findViewById(R.id.btn19);
        btn20 = findViewById(R.id.btn20);
        btn21 = findViewById(R.id.btn21);
        btn22 = findViewById(R.id.btn22);
        btn23 = findViewById(R.id.btn23);
        btn24 = findViewById(R.id.btn24);

        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btn7);
        buttons.add(btn8);
        buttons.add(btn9);
        buttons.add(btn10);
        buttons.add(btn11);
        buttons.add(btn12);
        buttons.add(btn13);
        buttons.add(btn14);
        buttons.add(btn15);
        buttons.add(btn16);
        buttons.add(btn17);
        buttons.add(btn18);
        buttons.add(btn19);
        buttons.add(btn20);
        buttons.add(btn21);
        buttons.add(btn22);
        buttons.add(btn23);
        buttons.add(btn24);

        pik = findViewById(R.id.pik);
        karo = findViewById(R.id.karo);
        zvezda = findViewById(R.id.zvezda);
        herc = findViewById(R.id.herc);
        tref = findViewById(R.id.tref);
        skocko = findViewById(R.id.skocko);

        timerTextView = findViewById(R.id.timerTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        pointsTextView.setText("Bodovi: " + String.valueOf(getTotalPoints()));

        startTimer();
        pik.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                imageViewList.get(nextImageViewIndex).setImageResource(R.drawable.pik_icon);
                currentRow.add("pik");
                if (currentRow.size() == 4 && currentRow.equals(correctCombination)) {
                    showPointsDialog(buttonsIndex);
                    return;
                }
                if (currentRow.size() == 4 && !currentRow.equals(correctCombination)) {
                    int currentIndex = buttonsIndex;
                    int count = 0;
                    for (int i = 0; i < currentRow.size() && i < correctCombination.size(); i++) {
                        String currentElement = currentRow.get(i);
                        String correctElement = correctCombination.get(i);
                        if (currentElement.equals(correctElement)) {
                            count++;
                        }
                    }

                    int count1 = 0;
                    Set<String> uniqueElements = new HashSet<>();

                    for (int i = 0; i < currentRow.size(); i++) {
                        String currentElement = currentRow.get(i);
                        if (correctCombination.contains(currentElement) && currentRow.indexOf(currentElement) != correctCombination.indexOf(currentElement)) {
                            if (!uniqueElements.contains(currentElement)) {
                                count1++;
                                uniqueElements.add(currentElement);
                            }
                        }
                    }




                    currentRow.clear();


                    for (int i = 0; i < count; i++) {
                        int redColor = ContextCompat.getColor(v.getContext(), R.color.red);
                        buttons.get(currentIndex).setBackgroundTintList(ColorStateList.valueOf(redColor));
                        currentIndex += 1;
                    }
                    for (int i = 0; i < count1; i++) {
                        int yellowColor = ContextCompat.getColor(v.getContext(), R.color.yellow);
                        buttons.get(currentIndex).setBackgroundTintList(ColorStateList.valueOf(yellowColor));
                        currentIndex += 1;
                    }

                    buttonsIndex += 4;
                    System.out.println("buttonsIndex" + buttonsIndex);
                }
                nextImageViewIndex += 1;

            }
        });

        karo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewList.get(nextImageViewIndex).setImageResource(R.drawable.karo_icon);
                currentRow.add("karo");
                if(currentRow.size() == 4 && currentRow.equals(correctCombination)){
                    showPointsDialog(buttonsIndex);
                    return;
                }
                if (currentRow.size() == 4 && !currentRow.equals(correctCombination)) {
                    int currentIndex = buttonsIndex;
                    int count = 0;
                    for (int i = 0; i < currentRow.size() && i < correctCombination.size(); i++) {
                        String currentElement = currentRow.get(i);
                        String correctElement = correctCombination.get(i);
                        if (currentElement.equals(correctElement)) {
                            count++;
                        }
                    }

                    int count1 = 0;
                    Set<String> uniqueElements = new HashSet<>();

                    for (int i = 0; i < currentRow.size(); i++) {
                        String currentElement = currentRow.get(i);
                        if (correctCombination.contains(currentElement) && currentRow.indexOf(currentElement) != correctCombination.indexOf(currentElement)) {
                            if (!uniqueElements.contains(currentElement)) {
                                count1++;
                                uniqueElements.add(currentElement);
                            }
                        }
                    }




                    currentRow.clear();


                    for (int i = 0; i < count; i++) {
                        int redColor = ContextCompat.getColor(v.getContext(), R.color.red);
                        buttons.get(currentIndex).setBackgroundTintList(ColorStateList.valueOf(redColor));
                        currentIndex += 1;
                    }
                    for (int i = 0; i < count1; i++) {
                        int yellowColor = ContextCompat.getColor(v.getContext(), R.color.yellow);
                        buttons.get(currentIndex).setBackgroundTintList(ColorStateList.valueOf(yellowColor));
                        currentIndex += 1;
                    }

                    buttonsIndex += 4;
                    System.out.println("buttonsIndex" + buttonsIndex);
                }
                nextImageViewIndex += 1;

            }
        });

        zvezda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewList.get(nextImageViewIndex).setImageResource(R.drawable.zvezda_icon);
                currentRow.add("zvezda");
                if(currentRow.size() == 4 && currentRow.equals(correctCombination)){
                    showPointsDialog(buttonsIndex);
                    return;
                }
                if (currentRow.size() == 4 && !currentRow.equals(correctCombination)) {
                    int currentIndex = buttonsIndex;
                    int count = 0;
                    for (int i = 0; i < currentRow.size() && i < correctCombination.size(); i++) {
                        String currentElement = currentRow.get(i);
                        String correctElement = correctCombination.get(i);
                        if (currentElement.equals(correctElement)) {
                            count++;
                        }
                    }

                    int count1 = 0;
                    Set<String> uniqueElements = new HashSet<>();

                    for (int i = 0; i < currentRow.size(); i++) {
                        String currentElement = currentRow.get(i);
                        if (correctCombination.contains(currentElement) && currentRow.indexOf(currentElement) != correctCombination.indexOf(currentElement)) {
                            if (!uniqueElements.contains(currentElement)) {
                                count1++;
                                uniqueElements.add(currentElement);
                            }
                        }
                    }




                    currentRow.clear();


                    for (int i = 0; i < count; i++) {
                        int redColor = ContextCompat.getColor(v.getContext(), R.color.red);
                        buttons.get(currentIndex).setBackgroundTintList(ColorStateList.valueOf(redColor));
                        currentIndex += 1;
                    }
                    for (int i = 0; i < count1; i++) {
                        int yellowColor = ContextCompat.getColor(v.getContext(), R.color.yellow);
                        buttons.get(currentIndex).setBackgroundTintList(ColorStateList.valueOf(yellowColor));
                        currentIndex += 1;
                    }

                    buttonsIndex += 4;
                    System.out.println("buttonsIndex" + buttonsIndex);
                }
                nextImageViewIndex += 1;

            }
        });

        herc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewList.get(nextImageViewIndex).setImageResource(R.drawable.herc_icon);
                currentRow.add("herc");
                if(currentRow.size() == 4 && currentRow.equals(correctCombination)){
                    showPointsDialog(buttonsIndex);
                    return;
                }
                if (currentRow.size() == 4 && !currentRow.equals(correctCombination)) {
                    int currentIndex = buttonsIndex;
                    int count = 0;
                    for (int i = 0; i < currentRow.size() && i < correctCombination.size(); i++) {
                        String currentElement = currentRow.get(i);
                        String correctElement = correctCombination.get(i);
                        if (currentElement.equals(correctElement)) {
                            count++;
                        }
                    }

                    int count1 = 0;
                    Set<String> uniqueElements = new HashSet<>();

                    for (int i = 0; i < currentRow.size(); i++) {
                        String currentElement = currentRow.get(i);
                        if (correctCombination.contains(currentElement) && currentRow.indexOf(currentElement) != correctCombination.indexOf(currentElement)) {
                            if (!uniqueElements.contains(currentElement)) {
                                count1++;
                                uniqueElements.add(currentElement);
                            }
                        }
                    }




                    currentRow.clear();


                    for (int i = 0; i < count; i++) {
                        int redColor = ContextCompat.getColor(v.getContext(), R.color.red);
                        buttons.get(currentIndex).setBackgroundTintList(ColorStateList.valueOf(redColor));
                        currentIndex += 1;
                    }
                    for (int i = 0; i < count1; i++) {
                        int yellowColor = ContextCompat.getColor(v.getContext(), R.color.yellow);
                        buttons.get(currentIndex).setBackgroundTintList(ColorStateList.valueOf(yellowColor));
                        currentIndex += 1;
                    }

                    buttonsIndex += 4;
                    System.out.println("buttonsIndex" + buttonsIndex);
                }
                nextImageViewIndex += 1;

            }
        });

        tref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewList.get(nextImageViewIndex).setImageResource(R.drawable.tref_icon);
                currentRow.add("tref");
                if(currentRow.size() == 4 && currentRow.equals(correctCombination)){
                    showPointsDialog(buttonsIndex);
                    return;
                }
                if (currentRow.size() == 4 && !currentRow.equals(correctCombination)) {
                    int currentIndex = buttonsIndex;
                    int count = 0;
                    for (int i = 0; i < currentRow.size() && i < correctCombination.size(); i++) {
                        String currentElement = currentRow.get(i);
                        String correctElement = correctCombination.get(i);
                        if (currentElement.equals(correctElement)) {
                            count++;
                        }
                    }

                    int count1 = 0;
                    Set<String> uniqueElements = new HashSet<>();

                    for (int i = 0; i < currentRow.size(); i++) {
                        String currentElement = currentRow.get(i);
                        if (correctCombination.contains(currentElement) && currentRow.indexOf(currentElement) != correctCombination.indexOf(currentElement)) {
                            if (!uniqueElements.contains(currentElement)) {
                                count1++;
                                uniqueElements.add(currentElement);
                            }
                        }
                    }




                    currentRow.clear();


                    for (int i = 0; i < count; i++) {
                        int redColor = ContextCompat.getColor(v.getContext(), R.color.red);
                        buttons.get(currentIndex).setBackgroundTintList(ColorStateList.valueOf(redColor));
                        currentIndex += 1;
                    }
                    for (int i = 0; i < count1; i++) {
                        int yellowColor = ContextCompat.getColor(v.getContext(), R.color.yellow);
                        buttons.get(currentIndex).setBackgroundTintList(ColorStateList.valueOf(yellowColor));
                        currentIndex += 1;
                    }

                    buttonsIndex += 4;
                    System.out.println("buttonsIndex" + buttonsIndex);
                }
                nextImageViewIndex += 1;

            }
        });

        skocko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewList.get(nextImageViewIndex).setImageResource(R.drawable.skocko3_icon);
                currentRow.add("skocko3");
                if(currentRow.size() == 4 && currentRow.equals(correctCombination)){
                    showPointsDialog(buttonsIndex);
                    return;
                }
                if (currentRow.size() == 4 && !currentRow.equals(correctCombination)) {
                    int currentIndex = buttonsIndex;
                    int count = 0;
                    for (int i = 0; i < currentRow.size() && i < correctCombination.size(); i++) {
                        String currentElement = currentRow.get(i);
                        String correctElement = correctCombination.get(i);
                        if (currentElement.equals(correctElement)) {
                            count++;
                        }
                    }

                    int count1 = 0;
                    Set<String> uniqueElements = new HashSet<>();

                    for (int i = 0; i < currentRow.size(); i++) {
                        String currentElement = currentRow.get(i);
                        if (correctCombination.contains(currentElement) && currentRow.indexOf(currentElement) != correctCombination.indexOf(currentElement)) {
                            if (!uniqueElements.contains(currentElement)) {
                                count1++;
                                uniqueElements.add(currentElement);
                            }
                        }
                    }




                    currentRow.clear();


                    for (int i = 0; i < count; i++) {
                        int redColor = ContextCompat.getColor(v.getContext(), R.color.red);
                        buttons.get(currentIndex).setBackgroundTintList(ColorStateList.valueOf(redColor));
                        currentIndex += 1;
                    }
                    for (int i = 0; i < count1; i++) {
                        int yellowColor = ContextCompat.getColor(v.getContext(), R.color.yellow);
                        buttons.get(currentIndex).setBackgroundTintList(ColorStateList.valueOf(yellowColor));
                        currentIndex += 1;
                    }

                    buttonsIndex += 4;
                    System.out.println("buttonsIndex" + buttonsIndex);
                }
                nextImageViewIndex += 1;

            }
        });


    }

    private void generateCorrectCombination() {
        Random random = new Random();
        String[] options = {"pik", "karo", "zvezda", "herc", "skocko3", "tref"};
        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(options.length);
            correctCombination.add(options[randomIndex]);

        }
        System.out.println(correctCombination);
    }

    private void showPointsDialog(int index) {
        timer.cancel();
        int points = 0;
        if(index < 8){
            points = 20;
        }
        if(index >= 8 && index <16){
            points = 15;
        }
        if(index >=16){
            points = 10;
        }
        savePoints(points);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Points");
        builder.setMessage("Osvojili ste " +points + " bodova!");
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

    public void openFifthGameActivity() {
        Intent intent = new Intent(this, FifthGameActivity.class);
        startActivity(intent);
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
                showSolution();
            }
        };

        timer.start();
    }

    private void showSolution(){
        int resourceId = getResources().getIdentifier(correctCombination.get(0) + "_icon", "drawable", getPackageName());
        imageViewSolution1.setImageResource(resourceId);
        int resourceId1 = getResources().getIdentifier(correctCombination.get(1) + "_icon", "drawable", getPackageName());
        imageViewSolution2.setImageResource(resourceId1);
        int resourceId2 = getResources().getIdentifier(correctCombination.get(2) + "_icon", "drawable", getPackageName());
        imageViewSolution3.setImageResource(resourceId2);
        int resourceId3 = getResources().getIdentifier(correctCombination.get(3) + "_icon", "drawable", getPackageName());
        imageViewSolution4.setImageResource(resourceId3);

        int redColor = ContextCompat.getColor(FourthGameActivity.this, R.color.red);
        btnSolution1.setBackgroundTintList(ColorStateList.valueOf(redColor));
        btnSolution2.setBackgroundTintList(ColorStateList.valueOf(redColor));
        btnSolution3.setBackgroundTintList(ColorStateList.valueOf(redColor));
        btnSolution4.setBackgroundTintList(ColorStateList.valueOf(redColor));
        showWrongSolutionAlert();
    }

    private void showWrongSolutionAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vreme je isteklo");
        builder.setMessage("Vreme je isteklo.");
        builder.setPositiveButton("OK", (dialog, which) -> {
            // Handle the OK button click if needed
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
