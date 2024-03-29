package com.myapplication.component;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.util.LinkedList;


public class Stroke {
	public LinkedList<Point> stroke;
	
	public Stroke(){
		stroke = new LinkedList<Point>();
	}

	public Stroke(Stroke stroke2) {
		
		stroke = new LinkedList<Point>();
		for (Point point:stroke2.stroke){
			stroke.add(new Point(point.x,point.y));
		}
		//stroke = (ArrayList<Point>) stroke2.stroke.clone();
	}

	public int size() {
		
		return stroke.size();
	}

	public Point get(int i) {
		
		return stroke.get(i);
	}

	public void addPoint(Point point) {
		
		stroke.add(point);
	}

	public void draw(Canvas c, Paint paint) {
//		GoogleMap map = new GoogleMap();
//		map.addPolyline(new PolylineOptions()
//	     .add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0))
//	     .width(5)
//	     .color(Color.RED));
//		
//		Polyline lines;
//		
//		// Instantiates a new Polyline object and adds points to define a rectangle
//		PolylineOptions rectOptions = new PolylineOptions()
//		        .add(new LatLng(37.35, -122.0))
//		        .add(new LatLng(37.45, -122.0))  // North of the previous point, but at the same longitude
//		        .add(new LatLng(37.45, -122.2))  // Same latitude, and 30km to the west
//		        .add(new LatLng(37.35, -122.2))  // Same longitude, and 16km to the south
//		        .add(new LatLng(37.35, -122.0)); // Closes the polyline.
//
//		// Set the rectangle's color to red
//		rectOptions.color(Color.RED);
//
//		GoogleMap myMap = null;
//		// Get back the mutable Polyline
//		//Polyline polyline = myMap.addPolyline(rectOptions);
		
		
//		if (stroke.size() > 0) {
//			Point p0 = stroke.get(0);
//			for (int i = 1; i < stroke.size(); i++) {
//				Point p1 = stroke.get(i);
//				
//				c.drawLine(p0.x, p0.y, p1.x, p1.y, paint);
////				paint.setStrokeWidth(paint.getStrokeWidth()-1);
////				c.drawPoint(p0.x, p0.y, paint);
////				
//				p0 = p1;
//			}
//		}
		Path path = new Path();
	    boolean first = true;
	    for(int i = 0; i < stroke.size(); i += 2){
	        Point point = stroke.get(i);
	        if(first){
	            first = false;
	            path.moveTo(point.x, point.y);
	        }

	        else if(i < stroke.size() - 1){
	            Point next = stroke.get(i + 1);
	            path.quadTo(point.x, point.y, next.x, next.y);
	        }
	        else{
	            path.lineTo(point.x, point.y);
	        }
	    }

	    c.drawPath(path, paint);
	}
	
	public void drawOld(Canvas c, Paint paint) {
	
		if (stroke.size() > 0) {
			Point p0 = stroke.get(0);
			for (int i = 1; i < stroke.size(); i++) {
				Point p1 = stroke.get(i);
				
				c.drawLine(p0.x, p0.y, p1.x, p1.y, paint);
//				paint.setStrokeWidth(paint.getStrokeWidth()-1);
//				c.drawPoint(p0.x, p0.y, paint);
//				
				p0 = p1;
			}
		}
		
	}
	
	public void drawInLarge(Canvas c, Paint paint){
		Path path = new Path();
	    boolean first = true;
	    for(int i = 0; i < stroke.size(); i += 2){
	        Point point = stroke.get(i);
	        if(first){
	            first = false;
	            path.moveTo(point.x, point.y);
	        }

	        else if(i < stroke.size() - 1){
	            Point next = stroke.get(i + 1);
	            path.quadTo(point.x, point.y, next.x, next.y);
	        }
	        else{
	            path.lineTo(point.x, point.y);
	        }
	    }

	    c.drawPath(path, paint);
	}
	
	public void print() {
		
		if (stroke.size()==0){
			System.out.println("Stroke NULL");
		}else{
			System.out.print(stroke.get(stroke.size()-1).x+"  ");
			System.out.println(stroke.get(stroke.size()-1).y);
		}
	}
	
	
}
