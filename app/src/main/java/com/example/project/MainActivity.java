package com.example.project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView imageView=(ImageView)findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.image);
        final ImageView iv=(ImageView)findViewById(R.id.imageView3);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                        int x=(int)event.getX();
                        int y=(int)event.getY();
                        //x 550~630
                        //y 590~670
                        if((550<x&&x<630)&&(590<y&&y<670)){
                            Toast. makeText(MainActivity. this, "정답입니다!", Toast.LENGTH_SHORT ).show();
                            iv.setX(event.getX()-100);
                            iv.setY(event.getY()-100);
                        }
                        else{
                            Toast. makeText(MainActivity. this, "틀렸습니다!", Toast.LENGTH_SHORT ).show();
                        }
                        String msg = "터치를 입력받음 : " +x+" / " +y;
                        //Toast. makeText(MainActivity. this, msg, Toast.LENGTH_SHORT ).show();
                    case MotionEvent.ACTION_MOVE :
                    case MotionEvent.ACTION_UP   :
                        // 이미지 뷰의 위치를 옮기기

                }
                return true;
            }
        });
    }

}
