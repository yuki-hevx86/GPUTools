package com.example.gvb_e.gputools;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;


public class LayerService extends Service {

    Process _process = null;
    DataOutputStream _outputStream = null;
    DataInputStream _inputStream = null;

    final int INTERVAL_PERIOD = 200;
    Timer timer = new Timer();

    View view;
    View view1;
    View view2;
    WindowManager wm;

    @Override
    public void onCreate()
    {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Settings.System.putInt(getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);

        // startForegroundService() -----
        Context context = getApplicationContext();
        String channelId = "default";
        String title = context.getString(R.string.app_name);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Notification　Channel 設定
        NotificationChannel channel = new NotificationChannel(
                channelId, title , NotificationManager.IMPORTANCE_DEFAULT);

        if(notificationManager != null){
            notificationManager.createNotificationChannel(channel);

            Notification notification = new Notification.Builder(context, channelId)
                    .setContentTitle(title)
                    // android標準アイコンから
                    .setSmallIcon(android.R.drawable.btn_star)
                    .setContentText("Machine Information")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .build();

            // startForeground
            startForeground(1, notification);

        }
        // ----- startForegroundService()

        super.onStartCommand(intent, flags, startId);
        try {
            _process = Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
        }



        LayoutInflater layoutInflater = LayoutInflater.from(this);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP;

        wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

        view = layoutInflater.inflate(R.layout.usage, null);
        view1 = layoutInflater.inflate(R.layout.usage, null);
        view2 = layoutInflater.inflate(R.layout.usage, null);

        wm.addView(view, params);
        wm.addView(view1, params);
        wm.addView(view2, params);

        timer.scheduleAtFixedRate(new TimerTask() {
            final Handler handler = new Handler();

            @Override
            public void run() {

                String[] comgpu = new String[]{"cp /sys/kernel/debug/dri/0/*_pm_info /data/data/com.example.gvb_e.gputools/files/amd_pm_pool_tmp && tail -n 15 /data/data/com.example.gvb_e.gputools/files/amd_pm_pool_tmp > /data/data/com.example.gvb_e.gputools/files/amd_pm_pool && chmod 444 /data/data/com.example.gvb_e.gputools/files/amd_pm_pool"};
                _outputStream = new DataOutputStream(_process.getOutputStream());
                for (String command : comgpu) {
                    try {
                        _outputStream.writeBytes(command + "\n");
                        _outputStream.flush();
                        //_outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    String[] gpumem = new String[]{"cp /sys/kernel/debug/dri/0/*_vram_mm /data/data/com.example.gvb_e.gputools/files/vram_tmp && tail -n 1 /data/data/com.example.gvb_e.gputools/files/vram_tmp > /data/data/com.example.gvb_e.gputools/files/vram_mm_pool && chmod 444 /data/data/com.example.gvb_e.gputools/files/vram_mm_pool"};
                    _outputStream = new DataOutputStream(_process.getOutputStream());
                    for (String command2 : gpumem) {
                        try {
                            _outputStream.writeBytes(command2 + "\n");
                            _outputStream.flush();
                            //_outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    //String[] cpufreq = new String[]{"cp /proc/cpuinfo /data/data/com.example.gvb_e.gputools/files/cpu_tmp && grep -e 'cpu MHz' -e 'model name' /data/data/com.example.gvb_e.gputools/files/cpu_tmp > /data/data/com.example.gvb_e.gputools/files/cpu_pool && chmod 444 /data/data/com.example.gvb_e.gputools/files/cpu_pool"};
//                    String[] cpufreq = new String[]{"cat /proc/cpuinfo | grep -e 'cpu MHz' -e 'model name'"};
//                    _outputStream = new DataOutputStream(_process.getOutputStream());
//                    for (String command3 : cpufreq) {
//                        try {
//                            _outputStream.writeBytes(command3 + "\n");
//                            _outputStream.flush();
//                            //_outputStream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }


                }


                handler.post(new Runnable() {
                    @Override
                    public void run() {


                        try {
                            FileInputStream fis = openFileInput("vram_mm_pool");
                            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                            StringBuffer sb = new StringBuffer();
                            String str;
                            TextView textView1 = view.findViewById(R.id.info_pool);
                            while ((str = br.readLine()) != null) {
                                sb.append(str + "\n");
                            }
                            textView1.setText(sb);
                            wm.updateViewLayout(view, params);
                            //wm.removeView(view);
                            //wm.addView(view, params);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            FileInputStream fis = openFileInput("amd_pm_pool");
                            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                            StringBuffer sb = new StringBuffer();
                            String str;
                            TextView textView2 = view1.findViewById(R.id.info_pool1);
                            while ((str = br.readLine()) != null) {
                                sb.append(str + "\n");
                            }
                            textView2.setText(sb);
                            wm.updateViewLayout(view1, params);
                            //wm.removeView(view1);
                            //wm.addView(view1, params);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            String[] cpufreq = new String[]{"cat /proc/cpuinfo | grep -e 'cpu MHz' -e 'model name'"};
                            _outputStream = new DataOutputStream(_process.getOutputStream());
                            for (String command3 : cpufreq) {
                            _outputStream.writeBytes(command3 + "\n");
                            _outputStream.flush();
                            android.util.Log.e("yuki", "yuki key=" + command3);
                            //_outputStream.close();
                            }
                            //FileInputStream fis = openFileInput("cpu_pool");
                            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
                            BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
                            StringBuffer sb = new StringBuffer();
                            String str;
                            TextView textView3 = view2.findViewById(R.id.info_cpu);

                               while ((str = br.readLine()) != null) {
                                sb.append(str + "\n");
                            }

                            textView3.setText(str);
                            wm.updateViewLayout(view2, params);
                            //wm.removeView(view2);
                            //wm.addView(view2, params);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        }


                });

                }

        }, 0, INTERVAL_PERIOD);

        return START_STICKY;
    }

     @Override
    public void onDestroy() {
        super.onDestroy();
        wm.removeView(view);
        wm.removeView(view1);
         wm.removeView(view2);
         if(timer != null){
             timer.cancel();
         }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}
