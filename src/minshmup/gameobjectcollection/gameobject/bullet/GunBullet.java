package minshmup.gameobjectcollection.gameobject.bullet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import minshmup.gameinterface.IDrawable;
import minshmup.gameobjectcollection.gameobject.ship.EnemyShip;


public class GunBullet extends Bullet 
{

	
	private int life = 64;//how long the bullet exists for.
	//movement related
	private final int SPEED = 8;
	private int heading;//the direction of the bullet.
	
	boolean removeme = false;//sees if the object needs to be removed
	
	public GunBullet(double xspawn, double yspawn)
	{
		heading = 0;
		width = 4;
		height = 4;
		mycolor = Color.GRAY;
		
		translate(xspawn,yspawn);
	}
	
	public void setHeading(int heading)
	{
		this.heading = heading;
	}



	@Override
	public void draw(Graphics g)
	{
		//System.out.println("Drawing gunBullet");
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform saveAT = g2d.getTransform();
		
		g2d.transform(mytranslate);
		g2d.transform(myscale);
		g2d.transform(myrotate);

		g.setColor(mycolor);
		g.fillOval(-width/2, -height/2, width, height);
		
		g2d.setTransform(saveAT);
	}

	@Override
	public int getCollIndex() 
	{
		
		return 0;
	}

	@Override
	public int getLocalIndex() 
	{

		return 0;
	}

	@Override
	public void setLocalIndex(int localindex)
	{

		
	}

	@Override
	public void move() 
	{
		life--;//every tick, the bullet loses a life
		translate(SPEED*Math.sin(Math.toRadians(heading)), SPEED*Math.cos(Math.toRadians(heading)));//fires in a straight line.
		
	}

	
	
	@Override
	public void setRemoveFlag(boolean removeme) 
	{//checks to see if the object needs to be removed.
		//System.out.println("Checking remove GunBullet object " + life);
		if (life <= 0)
		{
			//System.out.println("Remove flag for GunBullet is true");
			this.removeme = true;
		} else
		{
			//System.out.println("Check autoremove in GunBullet " + removeme);
			this.removeme = removeme;//automatically remove it the paramater is true.
		}
	}

	@Override
	public boolean getRemoveFlag() 
	{
		return removeme;
	}
	
	@Override
	public void handleCollision(IDrawable otherobj)
	{//the bullet sets the remove flag if it hits something.
		
		
		super.handleCollision(otherobj);;//the gunbullet also does what the super does.
		//System.out.println(otherobj.getClass());
		if (otherobj instanceof EnemyShip)
		{
			removeme = true;//the gun bullet will be removed, regardless of the other conditions.
			//System.out.println("Remove GunBullet, collided with EnemyShip");
		}
	}
	
}
