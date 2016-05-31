package minshmup.gameobjectcollection.gameobject.ship;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import minshmup.gameobjectcollection.gameobject.GameObject;

public class HelicopterRotors extends GameObject
{//I make it extend game object, but I won't have to deal with collisions here, only drawing
	//TODO figure out why rotations don't work.
	
	
	private boolean removeme;
	private int rotatespeed;//how fast the rotors rotate.
	
	public HelicopterRotors(Color c)
	{
		
		mycolor = c;
		width = 32;
		height = 8;
		rotatespeed = 2;
		
	}

	

	public HelicopterRotors(Color mycolor2, int width, int height) 
	{
		mycolor = mycolor2;
		this.width = width ;
		this.height = height;
		rotatespeed = 2;		
		
	}



	@Override
	public void draw(Graphics g)
	{

		move();
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform saveAT = g2d.getTransform();
		g2d.transform(mytranslate);
		g2d.transform(this.myrotate);
		g2d.transform(myscale);
		
		g2d.setColor(mycolor);
		g2d.drawRect(-width/2, -height/2, width, height);
		
		
		g2d.setTransform(saveAT);
	}

	@Override
	public void setRemoveFlag(boolean removeme) 
	{//because these will be automatically removed from holding object(ships),this doesn't matter a whole lot
		this.removeme = removeme;
		
	}

	@Override
	public boolean getRemoveFlag() {
		// TODO Auto-generated method stub
		return removeme;
	}



	@Override
	public int getCollIndex() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int getLocalIndex() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void setLocalIndex(int localindex) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void move() 
	{//this won't do too much, just rotate the rotors
		//System.out.println("Moving helicopter rotors");
		rotate(rotatespeed);
		//translate(0,-1);
	}
	
	
	
}
