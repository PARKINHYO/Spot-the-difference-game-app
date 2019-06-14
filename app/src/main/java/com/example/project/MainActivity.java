package com.example.project;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.Image;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick1(View button) { //팝업메뉴
        PopupMenu popup = new PopupMenu(this, button);
        popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

        popup.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.G1:
                                Intent intent = new Intent(getApplicationContext(), firstImage.class);
                                startActivity(intent);
                                return true;
                            case R.id.G2:
                                Intent intent2 = new Intent(getApplicationContext(), secondImage.class);
                                startActivity(intent2);
                                return true;
                            case R.id.G3:
                                Intent intent3 = new Intent(getApplicationContext(), thirdImage.class);
                                startActivity(intent3);
                                return true;
                            default:
                                return true;
                        }

                    }
                });
        popup.show();
    }
    public void onClick2(View button){
        PopupMenu popup = new PopupMenu(this, button);
        popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

        popup.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.G1:
                                Intent intent = new Intent(getApplicationContext(), Answer1.class);
                                startActivity(intent);
                                return true;
                            case R.id.G2:
                                Intent intent2 = new Intent(getApplicationContext(), Answer2.class);
                                startActivity(intent2);
                                return true;
                            case R.id.G3:
                                Intent intent3 = new Intent(getApplicationContext(), Answer3.class);
                                startActivity(intent3);
                                return true;
                            default:
                                return true;
                        }
                    }
                });
        popup.show();
    }
    public void onClick3(View view){
        ActivityCompat.finishAffinity(this);
        System.runFinalizersOnExit(true);
        System.exit(0);
    }
}
