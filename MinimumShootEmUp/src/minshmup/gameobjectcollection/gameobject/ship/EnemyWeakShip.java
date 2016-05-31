package minshmup.gameobjectcollection.gameobject.ship;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import minshmup.gameinterface.IDrawable;
import minshmup.gameobjectcollection.gameobject.bullet.Bullet;


public class EnemyWeakShip extends EnemyShip
{//the enemy weak ship is a fast ship, but only has one health.

	private boolean removeme = false;
	
		
	private PlayerWings pwing1;
	private PlayerWings pwing2;
	
	private int speed;
	//private int incspeed;
	//private final int MAXINCSPEED = 50;//the max value incspeed reaches before incrementing speed
	
	public EnemyWeakShip(double translatex, double translatey)
	{
		//System.out.println("Spawning enemy weak ship");
		width = 20;
		height = 20;
		
		translate(translatex,translatey);
		pwing1 = new PlayerWings(mycolor);
		pwing1.translate(-width/2, 0);
		pwing1.rotate(-45);
		pwing2 = new PlayerWings(mycolor);
		pwing2.translate(width/2, 0);
		pwing2.rotate(45);
		
		mycolor = Color.MAGENTA;
		speed = 2;
		//incspeed = 0;
		life = 1;
		maxlife = 1;
		timelife = 500;
		maxtimelife = 500;
	}

	@Override
	public void draw(Graphics g) 
	{
		super.draw(g);
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform saveAT = g2d.getTransform();
		
		g2d.transform(mytranslate);
		g2d.transform(myrotate);
		g2d.transform(myscale);
		
		g2d.setColor(mycolor);
		g2d.drawRect(-width/2, -height/2, width, height);
		
		pwing1.draw(g2d);
		pwing2.draw(g2d);
		
		
		g2d.setTransform(saveAT);
	}

	
	@Override
	public void handleCollision(IDrawable otherobj)
	{
		super.handleCollision(otherobj);
		
		if (otherobj instanceof Bullet)
		{
			life--;
		}
	}
	

	@Override
	public void setRemoveFlag(boolean removeme) 
	{
		if (timelife <= 0 || life <= 0)
		{
			//System.out.println("remove enemy weak ship");
			this.removeme = true;
		} else
		{
			this.removeme = removeme;
		}
		
	}

	@Override
	public boolean getRemoveFlag() 
	{
		return removeme;
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
		timelife--;
		translate(0,-speed);
		//incspeed++;
		//if (incspeed > MAXINCSPEED)
		//{
			//speed++;
			//incspeed = 0;
		//}
	}
	
	
	
}
