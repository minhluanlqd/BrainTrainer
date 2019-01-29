package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageButton buttonPlay;
    ArrayList<Integer>listAns = new ArrayList<Integer>();
    int locationOfCorrectAns;
    TextView textViewCorrect;
    TextView textViewScore;
    TextView sumTextView;
    TextView timerTextView;
    int score = 0;
    int numberOfQuestions = 0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button buttonPlayAgain;
    ConstraintLayout gameLayout;

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        textViewScore.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
        buttonPlayAgain.setVisibility(View.INVISIBLE);
        textViewCorrect.setText("");

        new CountDownTimer(32500,100){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                textViewCorrect.setText("DONE!!!");
                buttonPlayAgain.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }
        }.start();
    }

    public void newQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(1000);
        int b = rand.nextInt(1000);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAns = rand.nextInt(4);

        listAns.clear();

        for(int i = 0; i<4; i++){
            if (i == locationOfCorrectAns){
                listAns.add(a+b);
            }else {
                int wrongAns = rand.nextInt(1000);
                while (wrongAns == a+b){
                    wrongAns = rand.nextInt(1000);
                }
                listAns.add(wrongAns);
            }
        }
        button0.setText(Integer.toString(listAns.get(0)));
        button1.setText(Integer.toString(listAns.get(1)));
        button2.setText(Integer.toString(listAns.get(2)));
        button3.setText(Integer.toString(listAns.get(3)));

    }
    public void chooseAnswer(View view){
        if (Integer.toString(locationOfCorrectAns).equals(view.getTag().toString())){
            textViewCorrect.setText("Correct!!!");
            score++;
        }else {
            textViewCorrect.setText("Wrong!!!");
        }
        numberOfQuestions++;
        textViewScore.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        buttonPlayAgain = findViewById(R.id.playAgainButton);
        buttonPlay = findViewById(R.id.buttonPlay);
        textViewCorrect = findViewById(R.id.textViewCorrect);
        textViewScore = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timeTextView);
        gameLayout = findViewById(R.id.constraintLayout);

        buttonPlay.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }

    public void Start(View view) {
        buttonPlay.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timeTextView));
        gameLayout.setVisibility(View.VISIBLE);
    }

}
