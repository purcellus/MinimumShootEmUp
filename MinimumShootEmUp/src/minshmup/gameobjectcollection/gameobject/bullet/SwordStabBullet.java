package minshmup.gameobjectcollection.gameobject.bullet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import minshmup.gameinterface.IDrawable;
import minshmup.gameobjectcollection.gameobject.ship.PlayerShip;


public class SwordStabBullet extends Bullet 
{

	private int maxlife = 32;//the maximum amount of time the bullet exists for.
	private int life = 32;//how long the bullet exists for.
	//movement related
	//private int heading;//the direction of the bullet.
	private PlayerShip pship;//this requires a playership, so it constantly trails on it.
	
	private int updatescale = 0;//counter that updates scale
	
	boolean removeme = false;//sees if the object needs to be removed
	
	public SwordStabBullet(int strength, int size)
	{
		//heading = 0;
		width = 8*(size/2);
		height = 32*(size/2);
		mycolor = Color.ORANGE;
		
		translate(0,0);
	}
	
	public SwordStabBullet(int strength, int size, PlayerShip pship) 
	{
		// TODO Auto-generated constructor stub
		width = 8*(size/2);
		height = 32*(size/2);
		mycolor = Color.ORANGE;

		translate(0,0);
		this.pship = pship;
	}

	public void setHeading(int heading)
	{
		//this.heading = heading;
	}



	@Override
	public void draw(Graphics g)
	{
		//System.out.println("Drawing gunBullet");
		translate(-getX(), -getY());//reset the location
		
		translate(pship.getX(), pship.getY() + getHeight()/2);//trail on the player.
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform saveAT = g2d.getTransform();
		
		//I want the stab to "animate", elongating the longer it is in the field.
		//I also want the stab to thin out as well.
		updatescale++;//increment counter
		if (updatescale % (maxlife/4) == 0)
		{//if it is time to update.
			//System.out.println("Updating sword stab bullet");
			myscale.scale(myscale.getScaleX() - 0.005, myscale.getScaleY() + 0.001);
			updatescale = 0;//reset updatescale.
		}
		
		
		g2d.transform(mytranslate);
		g2d.transform(myscale);
		g2d.transform(myrotate);

		g.setColor(mycolor);
		g.fillRect(-width/2, -height/2, width, height);
		
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
		//translate(SPEED*Math.sin(Math.toRadians(heading)), SPEED*Math.cos(Math.toRadians(heading)));//fires in a straight line.
		
	}

	
	
	@Override
	public void setRemoveFlag(boolean removeme) 
	{//checks to see if the object needs to be removed.
		//System.out.println("Checking remove GunBullet object " + life);
		if (life <= 0)
		{//if the sword is still alive, based on time.
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
		
		
		super.handleCollision(otherobj);;//the swordbullet also does what the super does.
		//System.out.println(otherobj.getClass());
		
	}
	
	
}
