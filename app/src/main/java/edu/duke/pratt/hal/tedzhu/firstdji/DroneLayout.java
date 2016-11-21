package edu.duke.pratt.hal.tedzhu.firstdji;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ted Zhu on 10/19/2016.
 */

public class DroneLayout extends View {

    Bitmap bgr;
    TextView droneMode;
    Drawable dronePNG, targetPNG;
    String mode;
//    Canvas canvas;
    int targetX, targetY;
    int droneX, droneY;

    public DroneLayout(Context context) {
        super(context);
        Log.w("dbug","droneMode set as TextView!");
        dronePNG = getResources().getDrawable(R.drawable.drone);
        targetPNG = getResources().getDrawable(R.drawable.target);
        droneX = 100;
        droneY =100;
        mode = "";
    }

    public void onDraw(Canvas canvas) {
        canvas.drawColor(0xffffff);
        drawTarget(canvas);
        drawDrone(canvas);
//        Log.w("dbug", "Drawing drone layout!");
        invalidate();
    }

    public void drawDrone(Canvas canvas) {
        if (mode.equals("TAKEOFF")) {
            droneX += (targetX - droneX)/30;
            droneY += (targetY - droneY)/30;
        }
        dronePNG.setBounds(droneX, droneY, droneX+100, droneY+100);
        dronePNG.draw(canvas);
    }

    public void drawTarget(Canvas canvas) {

        targetPNG.setBounds(targetX, targetY, targetX+100, targetY+100);
        targetPNG.draw(canvas);
    }

    public boolean onTouchEvent( MotionEvent event) {
        super.onTouchEvent(event);
        Log.d ("motionEvent", event.toString());
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            Log.w("dbug", "Click: " + event.getX() + ", " + event.getY());
            targetX = (int)event.getX();
            targetY = (int)event.getY();
        }
        return true;
    }


    public void takeOff() {
        mode = "TAKEOFF";
        setMode("Taking off");
    }

    public void land() {
        mode = "LANDING";
        setMode("Landing");
    }

    public void setMode(String m) {
        droneMode.setText(m);
    }

    public void linkModeView(TextView modeView) {
        droneMode = modeView;
    }



}

// Activate window for inspection task.
// Nudge Control
// Virtual sticks
// Tape Indicator for Altitude.
// Takeoff, transit, inspection, done with inpsection, go home.
// 3 ppl
