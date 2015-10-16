package com.xlirik.hiragana;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Intent intent;

    private Resources resources;

    private TextView progress;
    private ImageView ivHira;

    private Button button1;
    private Button button2;
    private Button button3;
    private String[] romaji;
    private TypedArray hiraganaArray;

    private int question;

    private int answer_1;
    private int answer_2;
    private int answer_3;

    private int buttonPosition;

    private int allQ;
    private int currQ = 1;
    private int correctAnsw = 0;

    private String resultCorr;
    private String resultAll;

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    progress = (TextView) findViewById(R.id.progressText);
	    ivHira = (ImageView) findViewById(R.id.imageView);

        db = new DBHelper(this);
        intent = getIntent();
        resources = getResources();

	    hiraganaArray = resources.obtainTypedArray(R.array.array_hiragana);
	    romaji = new String[]{"su" ,"e" ,"yu" ,"re" ,"ho" ,"yo" ,"shi" ,"hi" ,"nu" ,"o" ,
			    "ku" ,"sa" ,"n" ,"u" ,"te" ,"no" ,"chi" ,"na" ,"ra" ,"wa" ,"he" ,"mu" ,"fu" ,
			    "ki" ,"mo" ,"ta" ,"ni" ,"i" ,"ha" ,"me" ,"ma" ,"se" ,"ko" ,"so" ,"a" ,"tsu" ,
			    "wo" ,"ri" ,"ya" ,"mi" ,"ka" ,"ke" ,"ne" ,"ru" ,"ro" ,"to"};

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
	    button1.setOnClickListener(this);
	    button2.setOnClickListener(this);
	    button3.setOnClickListener(this);

        resultCorr = getString(R.string.result_correct);
        resultAll = getString(R.string.result_all);
	    question = 0;

        allQ = intent.getIntExtra("allQ", 10);
	    if (allQ == romaji.length) {
		    AnswersAll();
        } else {
            Answers();
        }

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void Answers() {
	    ButtonColor();

        progress.setText(currQ + " / " + allQ);

        buttonPosition = (int) (Math.random() * 4);
	    ButtonPosition();

        question = (int) (Math.random() * romaji.length);
        ivHira.setImageDrawable(hiraganaArray.getDrawable(question));
        answer_1 = question;
        button1.setText(romaji[answer_1]);

        while (true) {
            answer_2 = (int) (Math.random() * romaji.length);
            if (answer_2 != answer_1) {
                button2.setText(romaji[answer_2]);
                break;
            }
        }

        while (true) {
            answer_3 = (int) (Math.random() * romaji.length);
	        if (answer_3 != answer_1 & answer_3 != answer_2) {
                button3.setText(romaji[answer_3]);
                break;
            }

        }

    }

    public void AnswersAll() {
	    ButtonColor();

        progress.setText(currQ + " / " + allQ);

        buttonPosition = (int) (Math.random() * 4);
	    ButtonPosition();
        ivHira.setImageDrawable(hiraganaArray.getDrawable(question));
        answer_1 = question;

        button1.setText(romaji[question]);

        while (true) {
            answer_2 = (int) (Math.random() * romaji.length);
            if (answer_2 != answer_1) {
                button2.setText(romaji[answer_2]);
                break;
            }
        }

        while (true) {
            answer_3 = (int) (Math.random() * romaji.length);
            if (answer_3 != answer_1 & answer_3 != answer_2) {
                button3.setText(romaji[answer_3]);
                break;
            }

        }
    }

	private void ButtonColor() {
		button1.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
		button2.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
		button3.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
		button1.setClickable(true);
		button2.setClickable(true);
		button3.setClickable(true);
	}

	private void ButtonPosition() {
		if (buttonPosition == 0) {
			float b1 = button1.getY();
			button1.setY(button2.getY());
			button2.setY(button3.getY());
			button3.setY(b1);
		} else if (buttonPosition == 1) {
			float b1 = button1.getY();
			button1.setY(button3.getY());
			button3.setY(b1);
		} else if (buttonPosition == 2) {
			float b1 = button1.getY();
			button1.setY(button2.getY());
			button2.setY(b1);
			button3.setY(button3.getY());
		}
	}

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.button1:
                if (answer_1 == question) {
                    button1.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                    correctAnsw++;
                } else {
                    button1.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                }
                break;
            case R.id.button2:
                if (answer_2 == question) {
                    button2.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                    correctAnsw++;
                } else {
                    button2.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                }
                break;
            case R.id.button3:
                if (answer_3 == question) {
                    button3.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                    correctAnsw++;
                } else {
                    button3.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                }
                break;
        }

        button1.setClickable(false);
        button2.setClickable(false);
        button3.setClickable(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                currQ++;
                if (currQ <= allQ) {
                    if (allQ == romaji.length) {
                        question++;
                        AnswersAll();
                    } else {
                        Answers();
                    }
                } else {
                   ResultOfTest();
                }

            }
        }, 800);

    }

    public void ResultOfTest() {

        db.addResult(new Results(correctAnsw + " / " + allQ, "" + DateFormat.format("dd.MM.yyyy kk:mm", new Date())));
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.result_title)
                .setMessage(resultCorr + correctAnsw + "\n" + resultAll + allQ)
                .setCancelable(true)
                .setNegativeButton(R.string.result_button,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                TestEnd();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void TestEnd() {
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
