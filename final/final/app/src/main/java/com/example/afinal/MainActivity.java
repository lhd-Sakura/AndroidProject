package com.example.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = (Button) findViewById(R.id.button2);
        bt1.setOnClickListener(listener);
    }
    Button.OnClickListener listener = new Button.OnClickListener() {

        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this, loginActivity.class);
            startActivity(intent);
        }

    };
}
