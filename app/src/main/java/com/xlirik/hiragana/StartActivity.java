package com.xlirik.hiragana;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class StartActivity extends ActionBarActivity implements View.OnClickListener {

    private Intent intent;

    private Button button10;
    private Button button20;
    private Button button30;
    private Button buttonAll;
    private Button buttonResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        button10 = (Button) findViewById(R.id.button_10);
        button20 = (Button) findViewById(R.id.button_20);
        button30 = (Button) findViewById(R.id.button_30);
        buttonAll = (Button) findViewById(R.id.button_all);

        buttonResults = (Button) findViewById(R.id.button_results);

        button10.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        button20.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        button30.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        buttonAll.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        buttonResults.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

        button10.setOnClickListener(this);
        button20.setOnClickListener(this);
        button30.setOnClickListener(this);
        buttonAll.setOnClickListener(this);
        buttonResults.setOnClickListener(this);
    }

    public void onClick(View v) {
        intent = new Intent(this, MainActivity.class);
        switch (v.getId()) {
            case R.id.button_10:
                intent.putExtra("allQ", 10);
                startActivity(intent);
                break;
            case R.id.button_20:
                intent.putExtra("allQ", 20);
                startActivity(intent);
                break;
            case R.id.button_30:
                intent.putExtra("allQ", 30);
                startActivity(intent);
                break;
            case R.id.button_all:
                intent.putExtra("allQ", 46);
                startActivity(intent);
                break;
            case R.id.button_results:
                startActivity(new Intent(this, ResultsActivity.class));
                break;
        }
    }
}
