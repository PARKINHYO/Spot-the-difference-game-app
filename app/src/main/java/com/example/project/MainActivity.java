package com.example.project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected class MyView extends View {
        int x = 100, y = 100;
        public MyView(Context context) {
            super(context);
            setup();
        }
        private Paint paint = new Paint();

        private void setup(){
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.RED);
        }
        @Override
        protected void onDraw(Canvas canvas) {

            int width = canvas.getWidth();
            int height = canvas.getHeight();

            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.back);
            Bitmap resize_bitmap = Bitmap.createScaledBitmap(b, width, height, true);

            canvas.drawBitmap(resize_bitmap, 0, 0, paint);

            canvas.drawCircle(x, y, 30, paint);
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x = (int) event.getX();
                    y = (int) event.getY();
                    //x 330~400
                    //y 150~230
                    if ((330 < x && x < 400) && (150 < y && y < 230)) {
                        Toast.makeText(MainActivity.this, "정답입니다!", Toast.LENGTH_SHORT).show();
                        invalidate();

                    } else {
                        Toast.makeText(MainActivity.this, "틀렸습니다!", Toast.LENGTH_SHORT).show();
                    }
//                    String msg = "터치를 입력받음 : " +x+" / " +y;
//                    Toast. makeText(MainActivity. this, msg, Toast.LENGTH_SHORT ).show();
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:

            }

            return true;
        }
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            MyView w = new MyView(this);
            setContentView(w);
        }

}
