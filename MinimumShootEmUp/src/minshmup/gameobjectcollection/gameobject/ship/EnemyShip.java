package minshmup.gameobjectcollection.gameobject.ship;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import minshmup.gameinterface.IDrawable;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.DummyCollection.DummyIterator;
import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.gameobjectcollection.gameobject.bullet.BeamBullet;
import minshmup.gameobjectcollection.gameobject.bullet.Bullet;
import minshmup.gameobjectcollection.gameobject.bullet.GunBullet;

public abstract class EnemyShip extends GameObject
{
	protected PlayerShip pship;//the coordinates of the player.
	//use getTranslateX and getTranslateY
	protected int life;//how much hits it can take in terms of damage.
	protected int maxlife;
	protected int timelife;//how long it exists on the battle.
	protected int maxtimelife;
	
	
	public void draw(Graphics g)
	{//ALL ENEMY SHIPS WILL SHOW THEIR HEALTH AND TIME LEFT ON THIS MORTAL PLANE
		
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform saveAT = g2d.getTransform();
		
		g2d.transform(mytranslate);
		g2d.transform(myrotate);
		g2d.transform(myscale);
		
		g2d.setColor(mycolor);
		
		
		//shows the life bars of the enemy ships.
		g2d.fillRect(-width/2, height - 5, (int)(((double)life/maxlife) * width), 5);
		g2d.fillRect(-width/2, height - 15, (int)(((double)timelife/maxtimelife) * width), 5);
		
	
		g2d.setTransform(saveAT);
	}
	
	public void getPlayer(DummyCollection dummycol)
	{
		DummyIterator diter = dummycol.getDummyIterator();
		while (diter.hasNext())
		{
			GameObject pobj = diter.getNext();
			if (pobj instanceof PlayerShip)
			{//if we found a player ship
				pship = (PlayerShip) pobj;
				break;
			}
		}
		
	}
	
	public PlayerShip getPlayerShip()
	{
		return pship;
	}
	
	@Override
	public void handleCollision(IDrawable otherobj)
	{//enemy ships will always take damage, and show that they've taken damage through bullet explosions.
		
		
		//System.out.println("Handling Collision for Enemy Ship");
		if (otherobj instanceof Bullet)
		{//if the thing hitting the ship is a bullet, spawn explosions.
			cbexp.factoryBulletExplosion(otherobj.getX(), otherobj.getY(), otherobj.getColor());
			cbexp.actionPerformed(null);
			if (otherobj instanceof GunBullet)
			{
				//System.out.println("setremoveflag on gunbullet from Enemy SHip");
				otherobj.setRemoveFlag(true);
			} else if (otherobj instanceof BeamBullet)
			{
				otherobj.setRemoveFlag(true);
			}
			
			
			
			
			
		}
		
	}
	
	
}
