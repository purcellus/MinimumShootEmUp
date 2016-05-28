package minshmup.gameobjectcollection.gameobject.environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import minshmup.gameinterface.IDrawable;
import minshmup.gameobjectcollection.gameobject.GameObject;

public class BulletExplosion extends GameObject
{

	private int life = 8;
	private boolean removeme;
			
	public BulletExplosion(int translatex, int translatey, Color bulletcolor)
	{//spawn the explosion where the bullets hit.
		width = 1;
		height = 1;
		mytranslate.translate(translatex, translatey);
		mycolor = bulletcolor;
	}
	
	
	@Override
	public boolean hasCollided(IDrawable otherobj)
	{//do nothing, don't check for collisions in environments.
		return false;
	}
	
	@Override
	public void handleCollision(IDrawable otherobj)
	{//don't do anything when colliding with other objects, should stop constant spawning.
		
		//System.out.println("Handling BulletExplosion collision");
	}
	
	@Override
	public void draw(Graphics g) 
	{
		// TODO Auto-generated method stub
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform saveAT = g2d.getTransform();
		
		g2d.setColor(mycolor);
		g2d.transform(mytranslate);
		g2d.transform(myrotate);
		g2d.transform(myscale);
		
		
		g2d.drawOval(-width/2, -height/2, width, height);
		
		g2d.setTransform(saveAT);
	}

	@Override
	public void setRemoveFlag(boolean removeme) 
	{

		//System.out.println(life);
		if (life < 0)
		{
			//System.out.println("Remove bullet explosion, life is 0 or less");
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLocalIndex() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLocalIndex(int localindex) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() 
	{
		life--;
		width += 2;
		height += 2;
	}//a bullet explosion basically is something that happens when a bullet hits something.

}
