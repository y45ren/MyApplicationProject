package com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;
import com.myapplication.component.MultiStrokes;
import com.myapplication.component.MyPaint;


public class CanvasView extends View{
	
	/**
	 * components
	 */
	public MultiStrokes largeStrokes;
    public MultiStrokes largeStrokesUp;
    public MultiStrokes largeStrokesDown;
	public Point touchPoint;

	/**
	 * paints
	 */
	private Paint chunkPaint;
	protected Paint notesPaint;
	private Paint framePaint;
	private Paint debugPaint;



    enum CanvasPosition {UP, NORMAL, DOWN};
    CanvasPosition canvasPosition = CanvasPosition.NORMAL;
	
	
	public CanvasView(Context context) {
		super(context);
		
		//components
		
		largeStrokes = new MultiStrokes();
        largeStrokesUp = new MultiStrokes();
        largeStrokesDown = new MultiStrokes();
        touchPoint = new Point(200,200);
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
        switch (canvasPosition){
            case NORMAL:
                largeStrokes.draw(c, notesPaint);
                //c.drawCircle(touchPoint.x,touchPoint.y,3,debugPaint);
                break;
            case UP:
                largeStrokesUp.draw(c, notesPaint);

                //c.drawCircle(touchPoint.x,touchPoint.y-100,3,debugPaint);
                break;
            case DOWN:
                largeStrokesDown.draw(c, notesPaint);
               // c.drawCircle(touchPoint.x,touchPoint.y+100,3,debugPaint);
                break;
        }
		//largeStrokes.draw(c, notesPaint);
        //c.drawCircle(touchPoint.x,touchPoint.y,3,debugPaint);
		//c.drawCircle(330,1000,100,framePaint);
	}
	
	public void undo(){
		if (!this.largeStrokes.isEmpty()){
			largeStrokes.chunk.removeLast();			
			this.invalidate();
		}
		
		
	}



	public void addStroke(Point eventPoint) {
		this.largeStrokes.addStroke(eventPoint);
        this.largeStrokesUp.addStroke(new Point(eventPoint.x-100,eventPoint.y-100));
        this.largeStrokesDown.addStroke(new Point(eventPoint.x,eventPoint.y+100));
	}



	public void addPoint(Point eventPoint) {
		
		this.largeStrokes.addPoint(eventPoint);
        this.largeStrokesUp.addPoint(new Point(eventPoint.x-100, eventPoint.y - 100));
        this.largeStrokesDown.addPoint(new Point(eventPoint.x, eventPoint.y + 100));
	}


    public void greyOut(boolean b) {
        if (b){
            this.notesPaint.setColor(0xff3f3f3f);
        }else{
            this.notesPaint.setColor(Color.BLACK);
        }
    }

    public void addTouchPoint() {
        this.addPoint(touchPoint);
    }
    public void addTouchStroke() {

        this.addStroke(touchPoint);
    }

    public void shiftUp() {
        canvasPosition = CanvasPosition.UP;
        System.out.println("UP");
    }

    public void shiftBack() {
        canvasPosition = CanvasPosition.NORMAL;
        System.out.println("B");
    }

    public void shiftDown() {
        canvasPosition = CanvasPosition.DOWN;
        System.out.println("DOWN");
    }
}
