package minshmup.gameobjectcollection.gameobject;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import minshmup.command.create.CreateBulletExplosion;
import minshmup.gameinterface.IDrawable;

public abstract class GameObject implements  IDrawable
{//all game objects can translate, rotate, or scale (at least one of those
	
	//any game object can and will use these attributes
	//Might not be the best idea, but I should be careful if I do this.
	protected AffineTransform mytranslate = new AffineTransform();
	protected AffineTransform myrotate = new AffineTransform();
	protected AffineTransform myscale = new AffineTransform();
	protected CreateBulletExplosion cbexp = CreateBulletExplosion.getCreateBulletExplosion(null);
	
	protected boolean alreadycollided;//states that the object is finished with checking collisions.
	
	
	
	protected int width;
	protected int height;
	protected Color mycolor;
	
	//for collision detection.
	public int getX()
	{//returns the object's center location
		return (int)mytranslate.getTranslateX();
	}
	
	public int getY()
	{//returns the object's center location
		return (int)mytranslate.getTranslateY();
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public Color getColor()
	{
		return mycolor;
	}
	@Override
	public boolean hasCollided(IDrawable theobj)
	{//use square collisions to find out what happened
		//TODO figure out a better method to check collisions, as things rotate.
		
		/*       ___ty+th   
		 *    tx|___|tx+tw    ____oy+oh 
		 *           ty	   ox|____|ox+ow
		 *                         oy
		 *                         
		 *  ex.  tx = 5, tw = 10                       
		 *       ty = 10, th = 10,                  
		 *       ox = 8, ow = 4                  
		 *       oy = 4, oh = 4                 
		 */
		
		GameObject otherobj = (GameObject) theobj;//typecast it, so it can getx, gety, etc.
		
		
		
		
		if (this.getX() - this.getWidth()/2  > otherobj.getX() + otherobj.getWidth() || this.getX() - this.getWidth()/2 + this.getWidth() < otherobj.getX() )
		{//if this left x is less than the other's right x, or this right x is greater than the other's left x
			//System.out.println("No collision detected in x");
			return false;
		} else if (this.getY() - this.getHeight()/2  > otherobj.getY() - otherobj.getHeight()/2 + otherobj.getHeight() || this.getY()  - this.getHeight()/2 + this.getHeight() < otherobj.getY()  - otherobj.getHeight()/2)
		{//if this top y is greater than the other's bottom y, or this bottom y is less than the other's top y
			//System.out.println("No collision detected in y");
			return false;
		}
		
		
		
		//System.out.println("Collision Detected");
		return true;
	}
	
	
	public void handleCollision(IDrawable otherobj)
	{
		
		
		
			
		
	}
	
	public boolean getAlreadyCollided()
	{
		return alreadycollided;
	}
	
	public void setAlreadyCollided(boolean theval)
	{
		alreadycollided = theval;
	}
	
	
	//all game objects will also extend this, in some manner.
	public abstract int getCollIndex();//gets the index of the gameobject.
	//the index of the game object indicates what type of object the game object is.
	public abstract int getLocalIndex();//gets the index of the gameobject in their respective location.
	//for example, the ship will be the first collection index, but could have a local index of 2
	//(two player game).
	public abstract void setLocalIndex(int localindex);
	
	public abstract void move();//will attempt to move an object
	
	
	
	
	//Transformation
	
	
	public void translate(double tx, double ty)
	{
		mytranslate.translate(tx, ty);
	}
	
	public void rotate(double theta)
	{
		//System.out.println("rotating");
		myrotate.rotate(Math.toRadians(theta));
		//myrotate.rotate(theta);
	}
	
	public void scale(double sx, double sy)
	{
		myscale.scale(sx, sy);
	}
	
	public void resetTransform()
	{
		mytranslate.setToIdentity();
		myrotate.setToIdentity();
		myscale.setToIdentity();

	}
	
}
