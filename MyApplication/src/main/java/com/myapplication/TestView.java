package com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

import com.myapplication.component.MultiStrokes;
import com.myapplication.component.MyPaint;

/**
 * Created by y45ren on 11/27/13.
 */
public class TestView extends View {
    /**
     * components
     */

    public Point touchPoint;

    /**
     * paints
     */
    private Paint chunkPaint;
    protected Paint notesPaint;
    private Paint framePaint;
    private Paint debugPaint;

    public void setPoint(Point eventPoint) {
        touchPoint.x = eventPoint.x;
        touchPoint.y = eventPoint.y;
    }


    enum CanvasPosition {UP, NORMAL, DOWN};
    CanvasPosition canvasPosition = CanvasPosition.NORMAL;


    public TestView(Context context) {
        super(context);

        //components

        touchPoint = new Point(0,0);
        //paints
        chunkPaint = MyPaint.createPaint(Color.BLACK, 8);
        notesPaint = MyPaint.createPaint(Color.BLACK, 6);
        framePaint = MyPaint.createPaint(Color.GREEN, 2);
        debugPaint = MyPaint.createPaint(Color.RED, 2);
    }



    @Override
    public void onDraw(Canvas c){
        super.onDraw(c);
        //draw large strokes

        //largeStrokes.draw(c, notesPaint);
        //c.drawCircle(touchPoint.x,touchPoint.y,3,debugPaint);
        c.drawLine(touchPoint.x+30,touchPoint.y-30,touchPoint.x-100,touchPoint.y-100,debugPaint);
        c.drawLine(touchPoint.x-50,touchPoint.y+20,touchPoint.x-100,touchPoint.y-100,debugPaint);
    }
}
