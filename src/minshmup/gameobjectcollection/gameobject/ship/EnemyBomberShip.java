package minshmup.gameobjectcollection.gameobject.ship;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import minshmup.gameinterface.IDrawable;
import minshmup.gameobjectcollection.gameobject.bullet.Bullet;


public class EnemyBomberShip extends EnemyShip
{//the bomber is slow, but has some health
	private boolean removeme = false;
		
	
	private PlayerWings wing1, wing2;
	
	public EnemyBomberShip(double translatex, double translatey)
	{
		width = 50;
		height = 20;
		
		//System.out.println("Spawning enemy weak ship");
		translate(translatex,translatey);
		
		wing1 = new PlayerWings(mycolor, width-10, height-5);
		wing1.translate(-width/2, 0);
		wing1.rotate(-30);
		wing2 = new PlayerWings(mycolor, width-10, height-5);
		wing2.translate(width/2, 0);
		wing2.rotate(30);
		mycolor = Color.YELLOW;
		
		life = 100;
		maxlife = 100;
		timelife = 800;
		maxtimelife = 800;
	}

	@Override
	public void draw(Graphics g) 
	{
		super.draw(g);//draw the health bar stuff
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform saveAT = g2d.getTransform();
		
		g2d.transform(mytranslate);
		g2d.transform(myrotate);
		g2d.transform(myscale);
		
		g2d.setColor(mycolor);
		g2d.drawRect(-width/2, -height/2, width, height);
		
		
		
		//draw the decorations.
		wing1.draw(g2d);
		wing2.draw(g2d);
		
		
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
			//System.out.println("remove enemy Bomber ship");
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
	public int getLocalIndex() {
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
		
	}
	
}
