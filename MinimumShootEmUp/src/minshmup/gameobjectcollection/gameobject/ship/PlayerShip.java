package minshmup.gameobjectcollection.gameobject.ship;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import minshmup.gameinterface.IDrawable;
import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.gameobjectcollection.gameobject.powerup.BeamWeaponPowerUp;
import minshmup.gameobjectcollection.gameobject.powerup.GunWeaponPowerUp;
import minshmup.gameobjectcollection.gameobject.powerup.PierceWeaponPowerUp;
import minshmup.gameobjectcollection.gameobject.powerup.PowerUp;
import minshmup.gameobjectcollection.gameobject.powerup.SwordStabWeaponPowerUp;
import minshmup.gameobjectcollection.gameobject.powerup.SwordSwipeWeaponPowerUp;
import minshmup.gameobjectcollection.gameobject.weapon.BeamWeapon;
import minshmup.gameobjectcollection.gameobject.weapon.GunWeapon;
import minshmup.gameobjectcollection.gameobject.weapon.PierceWeapon;
import minshmup.gameobjectcollection.gameobject.weapon.StabWeapon;
import minshmup.gameobjectcollection.gameobject.weapon.SwipeWeapon;
import minshmup.gameobjectcollection.gameobject.weapon.WeaponTypeStrategy;

public class PlayerShip extends GameObject implements IDrawable
{//the ship the players control.

	//only want one ship
	private static PlayerShip theship;
	
	//collection related
	private final int COLLECTION_INDEX = 0;//this is a constant, to determine where to be put in
	//the master collection.  Others can change it, but CANNOT MATCH any other.
	private int localindex;
	
	//Health related (hp, damage, etc.)
	//private int health;//how much health the ship has
	//private int maxhp = 10;//the maximum health, health can't be above this.
	private WeaponTypeStrategy gun1;//the primary weapon type the ship is using
	
	//movement related (speed, transforms, etc.)
	private int speedx;//the current speed
	private int speedy;//these are speeds to use for translating
	private int maxspeed;//the maximum speed the ship can go, speed can't be above this.
	
	private PlayerWings pwing1;
	private PlayerWings pwing2;
	
	
	//graphics related
	
	
	
	private boolean removeme;
	
	
	private PlayerShip(double translatex, double translatey)
	{
		//hp related
		//health = maxhp;
		
		
		//movement related
		speedx = 0;
		speedy = 0;
		maxspeed = 2;
		
		
		
		
		translate(translatex, translatey);
		
		//graphics related
		mycolor = Color.BLUE;
		width = 16;
		height = 16;
		
		
		pwing1 = new PlayerWings(mycolor);
		pwing1.translate(-width/2, 0);
		pwing1.rotate(45);
		pwing2 = new PlayerWings(mycolor);
		pwing2.translate(width/2, 0);
		pwing2.rotate(-45);
		
		//gun1 = new GunWeapon();//default weapon
		//gun1.translate(0, height/2 + 4);
		
		
	}
	
	//singleton related
	public static PlayerShip getPlayerShip(int tx, int ty)
	{
		if (theship == null)
		{//if it hasn't existed yet, make a new one.
			System.out.println("currently no ship.  making new one");
			theship = new PlayerShip(tx, ty);
		}
		return theship;
	}
	
	
	
	
	
	
	//collection related

	@Override
	public int getCollIndex() 
	{
		return COLLECTION_INDEX;
	}



	@Override
	public int getLocalIndex() 
	{
		return localindex;
	}

	@Override
	public void setLocalIndex(int localindex) 
	{
		this.localindex = localindex;
	}
	
	@Override
	public void handleCollision(IDrawable otherobj)
	{
		if (otherobj instanceof PowerUp)
		{
			if (otherobj instanceof GunWeaponPowerUp)
			{
				//System.out.println("Player has collided with gun weapon powerup");
				setWeaponType(new GunWeapon(0, height/2 + 4));
			} else if (otherobj instanceof PierceWeaponPowerUp)
			{
				//System.out.println("Player has collided with pierce weapon powerup");
				setWeaponType(new PierceWeapon(0, height/2 + 4));
				//get the pierce weapon
			} else if (otherobj instanceof BeamWeaponPowerUp)
			{
				setWeaponType(new BeamWeapon(0, height/2 + 4));
			} else if (otherobj instanceof SwordStabWeaponPowerUp)
			{
				setWeaponType(new StabWeapon(0, height/2 + 4));
			} else if (otherobj instanceof SwordSwipeWeaponPowerUp)
			{//to differentiate from stab, I set the weapon in a differnt location.
				setWeaponType(new SwipeWeapon(0, height/2 - 4));
			}
		}
	}
	
	//health related
	public WeaponTypeStrategy getWeaponType()
	{
		return gun1;
	}
	
	public void setWeaponType(WeaponTypeStrategy newweap)
	{
		gun1 = newweap;
	}
	
	
	
	//movement related
	public int getSpeedX()
	{
		//System.out.println("Getting player ship speed");
		return speedx;
	}
	
	public void setSpeedX(int speedx)
	{
		//System.out.println("Setting player ship speed");
		if (speedx > maxspeed)
		{//can't go faster than max speed
			this.speedx = maxspeed;
		} else if (speedx < -maxspeed)
		{//the ship can reverse as fast as it can go forward
			this.speedx = -maxspeed;
		} else
		{//set the speed, positive or negative.
			this.speedx = speedx;
		}
	}
	
	public int getSpeedY()
	{
		//System.out.println("Getting player ship speed");
		return speedy;
	}
	
	public void setSpeedY(int speedy)
	{
		//System.out.println("Setting player ship speed");
		if (speedy > maxspeed)
		{//can't go faster than max speed
			this.speedy = maxspeed;
		} else if (speedy < -maxspeed)
		{//the ship can reverse as fast as it can go forward
			this.speedy = -maxspeed;
		} else
		{//set the speed, positive or negative.
			this.speedy = speedy;
		}
	}
	

	
	
	
	//location related
	public int getX()
	{
		return (int) mytranslate.getTranslateX();
	}
	
	public int getY()
	{
		return (int) mytranslate.getTranslateY();
	}
	
	public void move()
	{
		//System.out.println("Moving Player Ship");
		translate(speedx, speedy);
		
	}
	
	


	
	//graphics related
	
	@Override
	public void draw(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform saveAT = g2d.getTransform();
		g2d.transform(mytranslate);
		g2d.transform(myscale);
		g2d.transform(myrotate);//rotate first, Affine Transforms does these things from bottom to top
		//for vertical shoot em ups, you don't actually rotate the object.  rather, it just translates.
		
		
		//System.out.println("Drawing player ship");
		g.setColor(mycolor);
		//g.drawString("Player Ship", 0, 0);//for debugging
		g.fillRect(-width/2, -height/2, width, height);
		
		//draw the decorations on the ship
		pwing1.draw(g2d);
		pwing2.draw(g2d);
		
		if (gun1 == null)
		{//to prevent null pointer exception.
			
		} else 
		{
			gun1.draw(g2d);
		}
		
		g2d.setTransform(saveAT);
	}

	@Override
	public void setRemoveFlag(boolean removeme) 
	{
		
		this.removeme = removeme;
	}

	@Override
	public boolean getRemoveFlag() 
	{
		return removeme;
	}





	

}
