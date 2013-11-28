package com.myapplication.component;

import android.graphics.*;

import java.util.LinkedList;

public class MultiStrokes {
	
	public LinkedList<Path> chunk;
	
	public MultiStrokes() {
		

		chunk = new LinkedList<Path>();
		
	}


	public MultiStrokes(MultiStrokes chunk2) {
		

		chunk = new LinkedList<Path>();
		
		
		for (Path stroke: chunk2.chunk){
			Path temp = new Path();
			temp.addPath(stroke);
			chunk.add(temp);			
		}
	}
	
	public void copyChunk(MultiStrokes chunk2){
		for (Path stroke: chunk2.chunk){
			Path temp = new Path();
			temp.addPath(stroke);
			chunk.add(temp);			
		}
	}


	public void addStroke(Point point) {
		
		//add a copy of current path to the 2nd of the queue
		chunk.add((new Path()));
		chunk.peekLast().moveTo(point.x, point.y);
	}

	public void addStroke() {
		
		chunk.add((new Path()));
	}

	/**
	 * add a new point to the newest stroke
	 * @param point
	 */
	public void addPoint(Point point) {
		
		try{
			chunk.peekLast().lineTo(point.x, point.y);
		}catch(Exception e){
		}
	}


	public void clear() {
		
		chunk.clear();
		
	}
	
	public void draw (Canvas c, Paint paint) {
		
		for(Path stroke: chunk){
			if(!stroke.isEmpty()){
				c.drawPath(stroke, paint);
			}
		}
//		if (!chunk.isEmpty())
//			c.drawPath(chunk.peekLast(), paint);
	}


//	public void transform(int maxX, int maxY, int minX, int minY, int height) {
//		
//		for (Stroke stroke:chunk){
//			for (Point point:stroke.stroke){
//				point.set(point.x-minX, point.y=minY);
//			}
//		}
//		
//	}


	public boolean isEmpty() {
		
		return chunk.isEmpty();
	}


    public boolean isNearlyEmpty() {
        RectF rectF = new RectF();

        if (chunk.size() == 1){
            chunk.peekLast().computeBounds(rectF,false);
            if (Math.abs(rectF.height())<15 && Math.abs(rectF.width())<15){
                return true;
            }
        }
        return false;
    }
}
