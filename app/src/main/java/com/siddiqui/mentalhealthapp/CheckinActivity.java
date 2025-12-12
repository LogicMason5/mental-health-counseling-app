package com.siddiqui.mentalhealthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CheckinActivity extends AppCompatActivity {

    private String[] questions = {
            "How have you been feeling lately",
            "Describe your appetite over the past few days",
            "What has been the cause of your discomfort"
    };

    private String[] titleColors = {"#3352BA", "#FF9885", "#8FE1F5"};
    private String[] noteColors = {"#708BD7", "#FFBE22", "#FFBE22"};

    private String[] noteText = {
            "We ask this because we care",
            "Don't worry, rough patches are temporary",
            "Happiness can be found even in the darkest times"
    };

    private int counter = 0;

    private TextView question, note;

    // Q1
    private Button q1_btn1, q1_btn2, q1_btn3, q1_btn4;
    private TextView q1_text1, q1_text2, q1_text3, q1_text4;

    // Q2
    private Button q2_btn1, q2_btn2, q2_btn3;
    private ImageView q2_img;

    // Q3
    private Button q3_btn1, q3_btn2, q3_btn3, q3_btn4;
    private ImageView q3_picture;

    // Final
    private TextView finalTextView;
    private ImageView finalImageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);

        initViews();
        showQuestion1();
    }

    private void initViews() {
        question = findViewById(R.id.question);
        note = findViewById(R.id.note);

        // Q1
        q1_btn1 = findViewById(R.id.q1_choice1);
        q1_btn2 = findViewById(R.id.q1_choice2);
        q1_btn3 = findViewById(R.id.q1_choice3);
        q1_btn4 = findViewById(R.id.q1_choice4);
        q1_text1 = findViewById(R.id.q1_text1);
        q1_text2 = findViewById(R.id.q1_text2);
        q1_text3 = findViewById(R.id.q1_text3);
        q1_text4 = findViewById(R.id.q1_text4);

        // Q2
        q2_btn1 = findViewById(R.id.q2_option1);
        q2_btn2 = findViewById(R.id.q2_option2);
        q2_btn3 = findViewById(R.id.q2_option3);
        q2_img = findViewById(R.id.q2_img);

        // Q3
        q3_btn1 = findViewById(R.id.q3_option1);
        q3_btn2 = findViewById(R.id.q3_option2);
        q3_btn3 = findViewById(R.id.q3_option3);
        q3_btn4 = findViewById(R.id.q3_option4);
        q3_picture = findViewById(R.id.q3_picture);

        // Final
        finalTextView = findViewById(R.id.finalTextView);
        finalImageview = findViewById(R.id.imageView7);
    }

    private void updateHeader(int index) {
        question.setText(questions[index]);
        question.setBackgroundColor(Color.parseColor(titleColors[index]));
        note.setText(noteText[index]);
        note.setBackgroundColor(Color.parseColor(noteColors[index]));
    }

    // ------------ Q1 ------------
    private void showQuestion1() {
        updateHeader(0);
        showQ1(true);

        q1_btn4.setOnClickListener(v -> {
            counter = 1;
            showQ1(false);
            showQuestion2();
        });
    }

    private void showQ1(boolean visible) {
        int v = visible ? View.VISIBLE : View.GONE;

        q1_btn1.setVisibility(v);
        q1_btn2.setVisibility(v);
        q1_btn3.setVisibility(v);
        q1_btn4.setVisibility(v);

        q1_text1.setVisibility(v);
        q1_text2.setVisibility(v);
        q1_text3.setVisibility(v);
        q1_text4.setVisibility(v);
    }

    // ------------ Q2 ------------
    private void showQuestion2() {
        updateHeader(1);
        showQ2(true);

        q2_btn2.setOnClickListener(v -> {
            counter = 2;
            showQ2(false);
            showQuestion3();
        });
    }

    private void showQ2(boolean visible) {
        int v = visible ? View.VISIBLE : View.GONE;

        q2_img.setVisibility(v);
        q2_btn1.setVisibility(v);
        q2_btn2.setVisibility(v);
        q2_btn3.setVisibility(v);
    }

    // ------------ Q3 ------------
    private void showQuestion3() {
        updateHeader(2);
        showQ3(true);

        q3_btn1.setOnClickListener(v -> showFinalScreen());
