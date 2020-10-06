package com.example.movetext;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public class DragView extends ImageView {
            float moveX;
        float moveY;

        public DragView(Context context) {
            super(context);
        }

        public DragView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    moveX = event.getX();
                    moveY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    setTranslationX(getX() + (event.getX() - moveX));
                    setTranslationY(getY() + (event.getY() - moveY));
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
            }

            return true;
        }
    }


}
