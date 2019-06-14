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
import android.graphics.Paint;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class thirdImage extends AppCompatActivity {
    protected class MyView extends View {
        int j = 0;
        int[] checkCnt = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] x2 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] y2 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        public int cntCrt = 0, cntWrg = 0;
        public int remain=30;

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

            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.back3);
            Bitmap resize_bitmap = Bitmap.createScaledBitmap(b, width, height, true);

            canvas.drawBitmap(resize_bitmap, 0, 0, paint);

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setColor(Color.RED);

            for (int i = 0; i < j; i++) {
                if (x2[i] != 0 && y2[i] != 0) {
                    canvas.drawCircle(x2[i], y2[i], 30, paint);
                }
            }

            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            paint.setStrokeWidth(1);

            canvas.drawText("맞은개수: " + cntCrt, 100, 50, paint);
            canvas.drawText("틀린개수: " + cntWrg, 450, 50, paint);
            canvas.drawText("남은기회: "+remain, 800, 50, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    //Toast.makeText(getApplicationContext(),x+"."+y,Toast.LENGTH_LONG).show();

                    if ((960 < x && x < 980) && (580 < y && y < 660) && checkCnt[0] == 0) {
                        x2[j] = x;
                        y2[j] = y;
                        cntCrt++;
                        checkCnt[0] = 1;
                        invalidate();
                    } else if ((960 < x && x < 980) && (580 < y && y < 660) && checkCnt[0] == 1) {
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }

                    //2
                    else if ((910 < x && x < 950) && (595 < y && y < 635) && checkCnt[1] == 0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[1] = 1;
                        invalidate();
                    } else if ((910 < x && x < 950) && (595 < y && y < 635) && checkCnt[1] == 1) {
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //3
                    else if ((660 < x && x < 700) && (680 < y && y < 720) && checkCnt[2] == 0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[2] = 1;
                        invalidate();
                    } else if ((660 < x && x < 700) && (680 < y && y < 720) && checkCnt[2] == 1) {
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //4
                    else if ((35 < x && x < 75) && (690 < y && y < 730) && checkCnt[3] == 0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[3] = 1;
                        invalidate();
                    } else if ((35 < x && x < 75) && (690 < y && y < 730) && checkCnt[3] == 1) {
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //5
                    else if ((305 < x && x < 345) && (340 < y && y < 380) && checkCnt[4] == 0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[4] = 1;
                        invalidate();
                    } else if ((305 < x && x < 345) && (340 < y && y < 380) && checkCnt[4] == 1) {
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }

                    //6
                    else if ((560 < x && x < 600) && (300 < y && y < 345) && checkCnt[5] == 0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[5] = 1;
                        invalidate();
                    } else if ((560 < x && x < 600) && (300 < y && y < 345) && checkCnt[5] == 1) {
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }

                    //7
                    else if ((600 < x && x < 640) && (525 < y && y < 565) && checkCnt[6] == 0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[6] = 1;
                        invalidate();
                    } else if ((600 < x && x < 640) && (525 < y && y < 565) && checkCnt[6] == 1) {
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }

                    //8
                    else if ((760 < x && x < 800) && (495 < y && y < 530) && checkCnt[7] == 0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[7] = 1;
                        invalidate();
                    } else if ((760 < x && x < 800) && (495 < y && y < 530) && checkCnt[7] == 1) {
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //9
                    else if ((415 < x && x < 455) && (80 < y && y < 125) && checkCnt[8] == 0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[8] = 1;
                        invalidate();
                    } else if ((415 < x && x < 455) && (80 < y && y < 125) && checkCnt[8] == 1) {
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //10
                    else if ((590 < x && x < 670) && (700 < y && y < 740) && checkCnt[9] == 0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[9] = 1;
                        invalidate();
                    } else if ((590 < x && x < 670) && (700 < y && y < 740) && checkCnt[9] == 1) {
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //11
                    else if ((400 < x && x < 460) && (600 < y && y < 660) && checkCnt[10] == 0) {
                        x2[j] = x;
                        y2[j] = y;
                        Toast.makeText(getApplicationContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                        cntCrt++;
                        checkCnt[10] = 1;
                        invalidate();
                    } else if ((400 < x && x < 460) && (600 < y && y < 660) && checkCnt[10] == 1) {
                        Toast.makeText(getApplicationContext(), "중복입니다 다시 체크해주세요", Toast.LENGTH_SHORT).show();
                    }
                    //밑 그림 터치시
                    else if ((1 < x && x < 1072) && (823 < y && y < 1580)) {
                        Toast.makeText(getApplicationContext(), "위에 그림을 터치해주세요!", Toast.LENGTH_SHORT).show();
                        ++remain;
                    } else {
                        Toast.makeText(getApplicationContext(), "오답입니다", Toast.LENGTH_SHORT).show();
                        invalidate();
                        cntWrg++;
                    }
                    j++;
                    if(cntCrt==11){
                    sendNotification();
                    finish();
                }
                    if(cntCrt+cntWrg>=30){
                        Toast.makeText(getApplicationContext(), "횟수 초과! 다시 시작.", Toast.LENGTH_LONG).show();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                    remain--;
                    /*
                     //좌표확인 코드
                    String msg = "터치를 입력받음 : " +x+" / " +y;
                    Toast. makeText(MainActivity. this, msg, Toast.LENGTH_SHORT ).show();
                    */
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
            case R.id.goHome:
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}