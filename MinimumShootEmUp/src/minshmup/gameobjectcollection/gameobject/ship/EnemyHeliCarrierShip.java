package minshmup.gameobjectcollection.gameobject.ship;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;

import minshmup.command.create.CreateEnemyWeakShip;
import minshmup.gameinterface.IDrawable;
import minshmup.gameobjectcollection.gameobject.bullet.Bullet;


public class EnemyHeliCarrierShip extends EnemyShip
{//the bomber is slow, but has some health
	private boolean removeme = false;
		
	
	private HelicopterRotors rotor1, rotor2;
	private PlayerWings pwing1, pwing2;
	private CreateEnemyWeakShip cewship;
	
	private final int WEAKSHIPVAL = 95;//the value to check for when to spawn ships.
	
	
	public EnemyHeliCarrierShip(double translatex, double translatey)
	{
		width = 50;
		height = 50;
		
		//System.out.println("Spawning enemy weak ship");
		translate(translatex,translatey);
		
		rotor1 = new HelicopterRotors(mycolor, width-40, height-10);
		rotor1.translate( -width/2,0);
		pwing1 = new PlayerWings(mycolor,width-35, height-35);
		pwing1.translate(-width/2, 0);
		
		rotor2 = new HelicopterRotors(mycolor, width-40, height-10);
		rotor2.translate( width/2, 0);
		pwing2 = new PlayerWings(mycolor,width-35, height-35);
		pwing2.translate( width/2, 0);
		mycolor = Color.GREEN;
		
		life = 1000;
		maxlife = 1000;
		timelife = 1000;
		maxtimelife = 1000;
		
		cewship = CreateEnemyWeakShip.getCreateEnemyWeakShip(null);
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
		
		
		
		//draw the decorations.
		rotor1.draw(g2d);
		pwing1.draw(g2d);
		rotor2.draw(g2d);
		pwing2.draw(g2d);
		
		
		//spawn weak ships
		Random rand = new Random();
		int checkfact = rand.nextInt(100);
		if (checkfact > WEAKSHIPVAL)
		{
			cewship.factoryEnemyWeakShip(getX(), getY());//spawn at the helicarrier's location
			cewship.actionPerformed(null);
		}
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
