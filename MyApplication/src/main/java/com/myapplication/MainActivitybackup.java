//package com.myapplication;
//
//import android.app.ActionBar;
//import android.app.Activity;
//import android.graphics.Point;
//import android.hardware.Sensor;
//import android.hardware.SensorEvent;
//import android.hardware.SensorEventListener;
//import android.hardware.SensorManager;
//import android.os.Bundle;
//import android.os.Vibrator;
//import android.view.KeyEvent;
//import android.view.MotionEvent;
//import android.view.WindowManager;
//import android.view.animation.Animation;
//import android.widget.RelativeLayout;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class MainActivitybackup extends Activity {
//    /**
//     * views
//     */
//    public CanvasView canvasView;
//    public RelativeLayout noteLayout;
//
//    /**
//     * sensors and timers
//     */
//    public SensorManager sm=null;
//    public Sensor aSensor=null;
//    private Sensor mSensor=null;
//    private Vibrator vibrator;
//
//    public Timer timer;
//    private Timer anchorTimer;
//    final private int frameRateTime = 25;
//    final private int holdAnchorTime = 420;
//    final private int vibrationTime = 100;
//
//
//    /**
//     * Listeners
//     */
//    /**
//     * status
//     */
//    enum CanvasPosition {UP, NORMAL, DOWN};
//    CanvasPosition canvasPosition;
//    /**
//     * volume keys
//     */
//    public boolean volumeKey;
//    /**
//     * components
//     */
//    private Point setAnchorPoint;
//    private int screenWidth;
//    private int screenHeight;
//    private Point pivotPoint;
//    public Point touchPoint;
//    public double velocityX;
//    public double velocityY;
//
//    /**
//     * animation
//     */
//    public Animation animation;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_main);
//        ActionBar actionBar = getActionBar();
//        actionBar.hide();
//        //sensors and timers
//        sm=(SensorManager)getSystemService(this.SENSOR_SERVICE);
//        aSensor=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        mSensor=sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//        vibrator = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
//
//        //
//        noteLayout = (RelativeLayout)findViewById(R.id.notePanel);
//        canvasView = new CanvasView(this);
//        noteLayout.addView(canvasView);
//
//        //
////        animation = AnimationUtils.loadAnimation(this, R.anim.shift_up);
////        canvasView.setAnimation(animation);
////        animation.setFillEnabled(true);
////        animation.setFillAfter(true);
//
//        //
//        pivotPoint = new Point (330,1000);
//        touchPoint = new Point(100,100);
//
//        //
//        canvasPosition = CanvasPosition.NORMAL;
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event){
//
//        Point eventPoint = new Point((int)event.getX(),(int)event.getY());
//        int action = event.getAction();
//        int actionCode = action & MotionEvent.ACTION_MASK;
//
//        switch(actionCode){
//            case MotionEvent.ACTION_DOWN:
//                if (inHandleRange(eventPoint)){
//                    setVelocity(eventPoint);
//                    this.startTimer();
//                }else{
//                    sm.registerListener(anchorSensorListener, aSensor, SensorManager.SENSOR_DELAY_GAME);
//                }
//
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                setVelocity(eventPoint);
//                break;
//            case MotionEvent.ACTION_UP:
//                if (timer!=null){
//                    timer.cancel();
//                    timer.purge();
//                }else{
//                    sm.unregisterListener(anchorSensorListener);
//                }
//
//                break;
//            default:
//                break;
//        }
//
//        switch(actionCode){
//            case MotionEvent.ACTION_DOWN:
//                if (inHandleRange(eventPoint)){
//                    setVelocity(eventPoint);
//                    this.startTimer();
//                }else{
//                    sm.registerListener(anchorSensorListener, aSensor, SensorManager.SENSOR_DELAY_GAME);
//                }
//
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                setVelocity(eventPoint);
//                break;
//            case MotionEvent.ACTION_UP:
//                if (timer!=null){
//                    timer.cancel();
//                    timer.purge();
//                }else{
//                    sm.unregisterListener(anchorSensorListener);
//                }
//
//                break;
//            default:
//                break;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        int action = event.getAction();
//        int keyCode = event.getKeyCode();
//        switch (keyCode) {
//            case KeyEvent.KEYCODE_VOLUME_UP:
//                if (action == KeyEvent.ACTION_UP) {
////                    System.out.println("UP");
////                    animation= AnimationUtils.loadAnimation(this, R.anim.shift_up);
////
////                    animation.start();
////                    canvasView.invalidate();
//                }
//                return true;
//            case KeyEvent.KEYCODE_VOLUME_DOWN:
//                if (action == KeyEvent.ACTION_DOWN) {
//
//                    if (!volumeKey){
//                        canvasView.addTouchStroke();
//                    }
//                    volumeKey = true;
//                }
//                if (action == KeyEvent.ACTION_UP) {
//
//                    volumeKey = false;
//
////                    animation= AnimationUtils.loadAnimation(this, R.anim.shift__back_up);
////                    animation.start();
////
////                    canvasView.invalidate();
//                }
//                return true;
//            default:
//                return super.dispatchKeyEvent(event);
//        }
//    }
//
//    private boolean inHandleRange(Point point){
//        if (Math.pow(point.x-pivotPoint.x,2)+Math.pow(point.y-pivotPoint.y,2)<40000){
//            return true;
//        }
//        return false;
//    }
//    private void setVelocity(Point point) {
//        if (!inHandleRange(point)){
//            return;
//        }
//        double distance = Math.sqrt(Math.pow(point.x-pivotPoint.x,2)+Math.pow(point.y-pivotPoint.y,2));
//        double scale = 0.02;
//        velocityX = Math.max(-100, Math.min(point.x-pivotPoint.x,100))*scale;
//        velocityY = Math.max(-100, Math.min(point.y-pivotPoint.y,100))*scale;
//
////        velocityX = (point.x-pivotPoint.x)/distance*2;
////        velocityY = (point.y-pivotPoint.y)/distance*2;
//    }
//
//    private void startTimer(){
//        timer = new Timer();
//
//        timer.scheduleAtFixedRate(new TimerTask() {
//            public void run() {
//                if (volumeKey){
//                    canvasView.addTouchPoint();
//                }
//                canvasView.touchPoint.set((int) (canvasView.touchPoint.x + velocityX), (int) (canvasView.touchPoint.y + velocityY));
//
//                runOnUiThread(new Runnable() {
//                    public void run() {
//
//                        canvasView.invalidate();
//
//                    }
//                });
//
//
//            }
//        }, frameRateTime, frameRateTime);
//    }
//
//    public SensorEventListener anchorSensorListener=new SensorEventListener(){
//        float[] accelerometerValues=new float[3];
//        public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//
//        }
//
//        public void onSensorChanged(SensorEvent event) {
//
//            accelerometerValues=event.values;
////            for (int i=0;i<3;i++){
////                System.out.print(accelerometerValues[i]+"  ");
////            }
////            System.out.println();
//            shift(accelerometerValues);
//            canvasView.postInvalidate();
//        }
//    };
//
//    private void shift(float[] accelerometerValues) {
////        for (int i=1;i<3;i++){
////            System.out.print(accelerometerValues[i]+"  ");
////        }
////        System.out.println();
//        switch (canvasPosition){
//            case NORMAL:
//                if (accelerometerValues[1]-accelerometerValues[2]>2){
////                    Animation animation= AnimationUtils.loadAnimation(this, R.anim.shift_up);
////                    canvasView.setAnimation(animation);
////                    animation.setFillEnabled(true);
////                    animation.setFillAfter(true);
////                    animation.start();
////                    canvasPosition = CanvasPosition.UP;
////                    System.out.println("UP");
//                    canvasView.shiftUp();
//                }
//                if (accelerometerValues[2]-accelerometerValues[1]>2){
////                    Animation animation= AnimationUtils.loadAnimation(this, R.anim.shift_down);
////                    canvasView.setAnimation(animation);
////                    animation.setFillEnabled(true);
////                    animation.setFillAfter(true);
////                    animation.start();
////                    canvasPosition = CanvasPosition.DOWN;
////                    System.out.println("DOWN");
//                    canvasView.shiftDown();
//                }
//                break;
//            case UP:
//                if (Math.abs(accelerometerValues[1]-accelerometerValues[2])<2){
////                    Animation animation= AnimationUtils.loadAnimation(this, R.anim.shift__back_up);
////                    canvasView.setAnimation(animation);
////                    animation.setFillEnabled(true);
////                    animation.setFillAfter(true);
////                    animation.start();
////                    canvasPosition = CanvasPosition.NORMAL;
////                    System.out.println("back");\
//                    canvasView.shiftBack();
//                }
//                break;
//            case DOWN:
//                if (Math.abs(accelerometerValues[1]-accelerometerValues[2])<2){
////                    Animation animation= AnimationUtils.loadAnimation(this, R.anim.shift_back_down);
////                    canvasView.setAnimation(animation);
////                    animation.setFillEnabled(true);
////                    animation.setFillAfter(true);
////                    animation.start();
////                    canvasPosition = CanvasPosition.NORMAL;
////                    System.out.println("back");
//                    canvasView.shiftBack();
//                }
//                break;
//        }
//        canvasView.postInvalidate();
//
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        sm.unregisterListener(anchorSensorListener);
//    }
//
//}
