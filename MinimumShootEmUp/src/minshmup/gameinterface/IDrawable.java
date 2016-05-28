package minshmup.gameinterface;

import java.awt.Color;
import java.awt.Graphics;


public interface IDrawable 
{//game objects that are drawable
	public void draw(Graphics g);//draw something onto the screen
	
	//IDrawable stuff are also ITransformable
	public void translate(double tx, double ty);
	public void rotate(double theta);
	public void scale(double sx, double sy);
	public void resetTransform();

	//IDrawable stuff are also IRemovable
	public void setRemoveFlag(boolean autoremove);//sets if an object needs to be removed, based on its own condition
	//the autoremove is a condition where the object will remove itself, regardless of other conditions.
	public boolean getRemoveFlag();//sees if an object needs to be removed
	
	//IDrawable stuff are also ICollidable
	public int getX();//to check for collision stuff
	public int getY();
	
	public boolean hasCollided(IDrawable otherobj);
	public void handleCollision(IDrawable otherobj);
	public boolean getAlreadyCollided();//checks to see if the object already collided
	public void setAlreadyCollided(boolean theval);//resets

	public Color getColor();
	
}
