package com.example.geekalex.shoppingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        mText=(TextView) findViewById(R.id.textView);
        mText.setText("1.To use please make sure you are a registered user."+"\n\n2.Choose your Registration name wisely because it will be displayed in all " +
                "your posts.\n\n3.Make sure you are connected to the internet every time you want to use the app.\n\n4.Your Password should exceed six characters.\n\n5.when filling the forms,all fields should be filled for it to be uploaded.\n\n");
    }
}
