package com.example.project;

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

    protected class MyView extends View {
        int j=0;
        int[] checkCnt=new int[] {0,0,0,0,0,0,0,0,0};
        int[] x2=new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] y2=new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        public int cntCrt=0, cntWrg=0;
        public MyView(Context context) {
            super(context);
        }
        private Paint paint = new Paint();

        public void sendNotification() {
            String channelId = "channel";
            String channelName = "Channel Name";

            NotificationManager notifManager = (NotificationManager) getSystemService  (Context.NOTIFICATION_SERVICE);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(
                        channelId, channelName, importance);
                notifManager.createNotificationChannel(mChannel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId);



            Intent notificationIntent = new Intent(getApplicationContext(), Main2Activity.class);

            notificationIntent.putExtra("cntCrt",cntCrt);
            notificationIntent.putExtra("cntWrg",cntWrg);

            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            int requestID = (int) System.currentTimeMillis();
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), requestID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentTitle("축하합니다!") // required
                    .setContentText("전부 맞추셨습니다! *터치시 결과창으로 이동")  // required
                    .setDefaults(Notification.DEFAULT_ALL) // 알림, 사운드 진동 설정
                    .setAutoCancel(true) // 알림 터치시 반응 후 삭제
                    .setSound(RingtoneManager
                            .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setSmallIcon(android.R.drawable.btn_star)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.android))
                    .setBadgeIconType(R.drawable.android)
                    .setContentIntent(pendingIntent);
            notifManager.notify(0, builder.build());
        }

        @Override
        protected void onDraw(Canvas canvas) {

            int width = canvas.getWidth();
            int height = canvas.getHeight();

            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.back);
            Bitmap resize_bitmap = Bitmap.createScaledBitmap(b, width, height, true);

            canvas.drawBitmap(resize_bitmap, 0, 0, paint);

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.RED);

            for(int i=0;i<j;i++){
                if(x2[i]!=0&&y2[i]!=0) {
                    canvas.drawCircle(x2[i], y2[i], 30, paint);
                }
            }

            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            paint.setStrokeWidth(1);

            canvas.drawText("맞은개수: "+cntCrt, 100, 50, paint);
            canvas.drawText("틀린개수: "+cntWrg, 450, 50, paint);
//            canvas2=canvas;
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    //x 330~400 ,480~520
                    //y 150~230 ,210~250
                    if ((310 < x && x < 410) && (140 < y&& y < 240)&&checkCnt[0]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        cntCrt++;
                        checkCnt[0] = 1;
                        invalidate();
                    }
                    else if ((310 < x && x < 410) && (140 < y&& y < 240)&&checkCnt[0]==1){
                        Toast.makeText(MainActivity.this, "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }

                    //2
                    else if ((440 < x && x < 530) && (210 < y&& y < 270) &&checkCnt[1]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(MainActivity.this, "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[1] = 1;
                        invalidate();
                    }
                    else if ((440 < x && x < 530) && (210 < y&& y < 270)&&checkCnt[1]==1){
                        Toast.makeText(MainActivity.this, "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //3
                    else if ((900 < x && x < 960) && (690 < y&& y < 750)&&checkCnt[2]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(MainActivity.this, "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[2] = 1;
                        invalidate();
                    }
                    else if ((900 < x && x < 960) && (690 < y&& y < 750)&&checkCnt[2]==1){
                        Toast.makeText(MainActivity.this, "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //4
                    else if ((480 < x && x < 540) && (430 < y&& y < 500)&&checkCnt[3]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(MainActivity.this, "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[3] = 1;
                        invalidate();
                    }
                    else if ((480 < x && x < 540) && (430 < y&& y < 500)&&checkCnt[3]==1){
                        Toast.makeText(MainActivity.this, "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //5
                    else  if ((110 < x && x < 450) && (690 < y&& y < 750) &&checkCnt[4]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(MainActivity.this, "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[4] = 1;
                        invalidate();
                    }
                    else if ((110 < x && x < 450) && (690 < y&& y < 750)&&checkCnt[4]==1){
                        Toast.makeText(MainActivity.this, "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }

                    //6
                    else if ((310 < x && x < 370) && (390 < y&& y < 460) &&checkCnt[5]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(MainActivity.this, "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[5] = 1;
                        invalidate();
                    }
                    else if ((310 < x && x < 370) && (390 < y&& y < 460)&&checkCnt[5]==1){
                        Toast.makeText(MainActivity.this, "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }

                    //7
                    else if ((930 < x && x < 990) && (200 < y&& y < 260) &&checkCnt[6]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(MainActivity.this, "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[6] = 1;
                        invalidate();
                    }
                    else if ((930 < x && x < 990) && (200 < y&& y < 260)&&checkCnt[6]==1){
                        Toast.makeText(MainActivity.this, "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }

                    //8
                    else if ((290 < x && x < 360) && (660 < y&& y < 710) &&checkCnt[7]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(MainActivity.this, "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[7] = 1;
                        invalidate();
                    }
                    else if ((290 < x && x < 360) && (660 < y&& y < 710)&&checkCnt[7]==1){
                        Toast.makeText(MainActivity.this, "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }

                    //9
                    else if ((960 < x && x < 1030) && (510 < y&& y < 680) &&checkCnt[8]==0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(MainActivity.this, "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[8] = 1;
                        invalidate();
                    }
                    else if ((960 < x && x < 1030) && (510 < y&& y < 680)&&checkCnt[8]==1){
                        Toast.makeText(MainActivity.this, "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "오답입니다", Toast.LENGTH_SHORT).show();
                        invalidate();
                        cntWrg++;
                    }
                    j++;
                    if(cntCrt==9){
                        sendNotification();
//
//                        Intent intent= new Intent(getApplicationContext(), Main2Activity.class);
//                        startActivity(intent);
//                        finish();

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




    //옵션메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
                case R.id.regame:
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

/*
//컨텍스트메뉴
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("ReGame");
        menu.add(0, 1, 0, "다시시작");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    //버튼 클릭시 컨텍스트메뉴
    public void onClick(View button) {
        PopupMenu popup = new PopupMenu(this, button);
        popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

        popup.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getApplicationContext(), "클릭된 팝업 메뉴: "
                                        + item.getTitle(),
                                Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

        popup.show();
    }
*/

}
