package com.example.androidquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class SixthGameActivity extends AppCompatActivity {

    private Button stopTargetButton;
    private Button generateNumbersButton;
    private Button submitButton;
    private TextView targetNumberTextView;
    private Button[] generatedNumberButtons;
    private Button[] operandButtons;
    private TextView mathInputField;
    private TextView timerTextView;
    private Set<Integer> usedNumbers = new HashSet<>();
    private boolean generateNumbersButtonClicked = false;
    private CountDownTimer timer;
    private boolean gameIsOver = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth_game);

        stopTargetButton = findViewById(R.id.stopTargetButton);
        generateNumbersButton = findViewById(R.id.stopGenerateButton);
        submitButton = findViewById(R.id.submitButton);
        targetNumberTextView = findViewById(R.id.targetNumberTextView);
        generatedNumberButtons = new Button[]{
                findViewById(R.id.generatedNumber1Button),
                findViewById(R.id.generatedNumber2Button),
                findViewById(R.id.generatedNumber3Button),
                findViewById(R.id.generatedNumber4Button),
                findViewById(R.id.generatedNumber5Button),
                findViewById(R.id.generatedNumber6Button)
        };
        operandButtons = new Button[]{
                findViewById(R.id.additionButton),
                findViewById(R.id.subtractionButton),
                findViewById(R.id.multiplicationButton),
                findViewById(R.id.divisionButton),
                findViewById(R.id.leftParenthesisButton),
                findViewById(R.id.rightParenthesisButton)
        };
        mathInputField = findViewById(R.id.mathInputField);
        timerTextView = findViewById(R.id.timerTextView);

        initializeTimer(600000);

        stopTargetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int targetNumber = generateRandomNumber(1, 1000);
                targetNumberTextView.setText("Target: " + targetNumber);
                stopTargetButton.setEnabled(false);
            }
        });

        generateNumbersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!generateNumbersButtonClicked) {
                    Set<Integer> uniqueNumbers = generateUniqueNumbers(1, 9, 4);
                    int fifthNumber = generateRandomNumberFromSet(new int[]{10, 15, 20});
                    int sixthNumber = generateRandomNumberFromSet(new int[]{25, 50, 75, 100});

                    generatedNumberButtons[0].setText(String.valueOf(uniqueNumbers.toArray()[0]));
                    generatedNumberButtons[1].setText(String.valueOf(uniqueNumbers.toArray()[1]));
                    generatedNumberButtons[2].setText(String.valueOf(uniqueNumbers.toArray()[2]));
                    generatedNumberButtons[3].setText(String.valueOf(uniqueNumbers.toArray()[3]));
                    generatedNumberButtons[4].setText(String.valueOf(fifthNumber));
                    generatedNumberButtons[5].setText(String.valueOf(sixthNumber));

                    usedNumbers.clear();
                    usedNumbers.addAll(uniqueNumbers);

                    generateNumbersButton.setEnabled(false);
                    submitButton.setEnabled(true);
                    generateNumbersButtonClicked = true;

                    startTimer();
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (gameIsOver) {
                    return;
                }

                String userExpression = mathInputField.getText().toString();
                int result = evaluateExpression(userExpression);
                String targetText = targetNumberTextView.getText().toString();
                int targetNumber = Integer.parseInt(targetText.substring(targetText.lastIndexOf(" ") + 1));

                if (result == targetNumber) {
                    showToast("Correct! You matched the target number.");
                } else {
                    showToast("Incorrect! Game over.");
                }

                submitButton.setEnabled(false);

                // Mark the game as over
                gameIsOver = true;

                cancelTimer();
            }
        });

        for (int i = 0; i < generatedNumberButtons.length; i++) {
            final int index = i;
            generatedNumberButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = Integer.parseInt(generatedNumberButtons[index].getText().toString());

                    if (!usedNumbers.contains(number) || !mathInputField.getText().toString().contains(String.valueOf(number))) {
                        String currentMathInput = mathInputField.getText().toString();
                        mathInputField.setText(currentMathInput + number);
                        usedNumbers.add(number);
                    }
                }
            });
        }

        for (int i = 0; i < operandButtons.length; i++) {
            final int index = i;
            operandButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String currentMathInput = mathInputField.getText().toString();
                    String selectedOperand = operandButtons[index].getText().toString();
                    mathInputField.setText(currentMathInput + selectedOperand);
                }
            });
        }
    }

    private int evaluateExpression(String expression) {
        try {
            expression = expression.replaceAll("\\s+", "");
            Stack<Integer> numbers = new Stack<>();
            Stack<Character> operators = new Stack<>();

            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                if (Character.isDigit(c)) {
                    StringBuilder numBuilder = new StringBuilder();
                    numBuilder.append(c);
                    while (i + 1 < expression.length() && Character.isDigit(expression.charAt(i + 1))) {
                        i++;
                        numBuilder.append(expression.charAt(i));
                    }
                    int num = Integer.parseInt(numBuilder.toString());
                    numbers.push(num);
                } else if (c == '(') {
                    operators.push(c);
                } else if (c == ')') {
                    while (!operators.isEmpty() && operators.peek() != '(') {
                        int b = numbers.pop();
                        int a = numbers.pop();
                        char op = operators.pop();
                        numbers.push(applyOperator(a, b, op));
                    }
                    operators.pop();
                } else if (isOperator(c)) {
                    while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                        int b = numbers.pop();
                        int a = numbers.pop();
                        char op = operators.pop();
                        numbers.push(applyOperator(a, b, op));
                    }
                    operators.push(c);
                }
            }

            while (!operators.isEmpty()) {
                int b = numbers.pop();
                int a = numbers.pop();
                char op = operators.pop();
                numbers.push(applyOperator(a, b, op));
            }

            if (!numbers.isEmpty()) {
                return numbers.peek();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private int applyOperator(int a, int b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b != 0) {
                    return a / b;
                } else {
                    throw new ArithmeticException("Division by zero");
                }
            default:
                return 0;
        }
    }

    private int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private int generateRandomNumberFromSet(int[] values) {
        Random random = new Random();
        int randomIndex = random.nextInt(values.length);
        return values[randomIndex];
    }

    private Set<Integer> generateUniqueNumbers(int min, int max, int count) {
        Set<Integer> uniqueNumbers = new HashSet<>();
        Random random = new Random();

        while (uniqueNumbers.size() < count) {
            int number = random.nextInt(max - min + 1) + min;
            uniqueNumbers.add(number);
        }

        return uniqueNumbers;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void initializeTimer(long millisInFuture) {
        timer = new CountDownTimer(millisInFuture, 1000) {
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                String timerText = String.format("Timer: %02d:%02d", seconds / 60, seconds % 60);
                timerTextView.setText(timerText);
            }

            public void onFinish() {
                showToast("Time is out!");
            }
        };
    }

    private void startTimer() {
        if (timer != null) {
            timer.start();
        }
    }

    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }
}







