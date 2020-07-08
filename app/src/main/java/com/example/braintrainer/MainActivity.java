package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button resetButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timerTextView;
    TextView scoreTextView;
    TextView questionTextView;
    TextView ansTextView;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void reset(View view) {
        resetButton.setVisibility((View.INVISIBLE));
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        score = 0;
        numberOfQuestions = 0;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        ansTextView.setText("");
        startBrainTrainer();
    }

    public void startBrainTrainer() {
        questionTextView.setVisibility(View.VISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        ansTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000)+"s");
            }

            @Override
            public void onFinish() {
                ansTextView.setText("Done");
                resetButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
        nextQuestion();
    }

    public void go(View view) {
        goButton.setVisibility(View.INVISIBLE);
        startBrainTrainer();
    }

    public void chooseAnswer(View view) {
        if(Integer.toString(locationOfAnswer).equals(view.getTag().toString())) {
            ansTextView.setText("Correct!");
            score++;
        }
        else
            ansTextView.setText("Wrong :(");
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        nextQuestion();
    }

    public void nextQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(30);
        int b = rand.nextInt(30);
        locationOfAnswer = rand.nextInt(4);

        questionTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        answers.clear();
        for(int i=0; i<4; i++){
            if(i == locationOfAnswer)
                answers.add(a+b);
            else {
                int wrongAnswer = rand.nextInt(61);
                while(wrongAnswer == a+b)
                    wrongAnswer = rand.nextInt(61);
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        questionTextView = findViewById(R.id.questionTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        ansTextView = findViewById(R.id.ansTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        resetButton = findViewById(R.id.resetButton);

        resetButton.setVisibility(View.INVISIBLE);
        questionTextView.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        ansTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
        timerTextView.setVisibility(View.INVISIBLE);



    }
}