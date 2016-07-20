package test.launcher.mummu.androidscreenrecordsecond.camerautils;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import test.launcher.mummu.androidscreenrecordsecond.R;
import test.launcher.mummu.androidscreenrecordsecond.utils.RecordPreference;

/**
 * Created by muhammed on 7/20/2016.
 */
public class WaterMarkHeadService extends Service {
    private WindowManager windowManager;
    WindowManager.LayoutParams params;
    private View view;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.watermark_textview, null);
        TextView textView = (TextView) view;

        String text = RecordPreference.getStringData(getApplicationContext(), RecordPreference.WATER_MARK_TEXT);
        String textcolor = RecordPreference.getStringData(getApplicationContext(), RecordPreference.WATER_MARK_TEXT_COLOR);
        String textsize = RecordPreference.getStringData(getApplicationContext(), RecordPreference.WATER_MARK_TEXT_SIZE);

        textView.setText(text);
        textView.setTextColor(Color.parseColor(textcolor));
        textView.setTextSize(Float.parseFloat(textsize));

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;
//this code is for dragging the chat head
        view.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX
                                + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY
                                + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(view, params);
                        return true;
                }
                return false;
            }
        });
        windowManager.addView(view, params);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        if (view != null)
            windowManager.removeView(view);
        super.onDestroy();
    }
}
