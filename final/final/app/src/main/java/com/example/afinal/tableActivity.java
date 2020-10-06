package com.example.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tableActivity extends AppCompatActivity {
    Button bt1;
    Button bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        bt1 = (Button) findViewById(R.id.button4);
        bt1.setOnClickListener(listener1);
        bt2 = (Button) findViewById(R.id.button3);
        bt2.setOnClickListener(listener2);
    }
    Button.OnClickListener listener1 = new Button.OnClickListener() {

        public void onClick(View v) {
            Intent intent = new Intent(tableActivity.this,pic1Activity.class);
            startActivity(intent);
        }
    };
    Button.OnClickListener listener2 = new Button.OnClickListener() {

        public void onClick(View v) {
            Intent intent = new Intent(tableActivity.this,pic2Activity.class);
            startActivity(intent);
        }

    };
}
