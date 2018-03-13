package com.ravitheja.jokedisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeTeller extends AppCompatActivity {

    public static final String RETRIEVED_TEXT="joke";

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_teller);

        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setText(getIntent().getStringExtra(RETRIEVED_TEXT));
    }
}
