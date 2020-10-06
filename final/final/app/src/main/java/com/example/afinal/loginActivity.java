package com.example.afinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class loginActivity extends AppCompatActivity {
    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bt1 = (Button) findViewById(R.id.button);
        bt1.setOnClickListener(listener);
    }
    Button.OnClickListener listener = new Button.OnClickListener() {

        public void onClick(View v) {

            Intent intent = new Intent(loginActivity.this,tableActivity.class);
            startActivity(intent);
        }

    };
}
