package minshmup.gameobjectcollection.gameobject.weapon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import minshmup.command.fireweapon.FireBeamBullet;
import minshmup.gameobjectcollection.gameobject.bullet.Bullet;
import minshmup.gameobjectcollection.gameobject.bullet.GunBullet;

public class BeamWeapon extends WeaponTypeStrategy
{

	private int gunstrength;//how powerful the gun is
	private int width = 4;//the size of the gun, not the bullet.
	private int height = 16;
	private Color mycolor = Color.CYAN;
	
	//should be instantiated before doing this.
	private FireBeamBullet firebeamcmd = FireBeamBullet.getFireBeamWeapon(null);
	
	
	private boolean removeme;
	
	public BeamWeapon()
	{
		gunstrength = 1;//starts at 1
	}
	
	public BeamWeapon(int translatex, int translatey)
	{
		gunstrength = 1;
		translate(translatex, translatey);
	}
	
	public Bullet getBullet()
	{
		return new GunBullet(0,0);
	}

	@Override
	public void setWeaponStrength(int newstrength)
	{
		gunstrength = newstrength;
	}
	
	@Override
	public int getWeaponStrength() 
	{
		// TODO Auto-generated method stub
		return gunstrength;
	}
	
	
	//not extened from weapontypestrategy
	


	@Override
	public void draw(Graphics g) 
	{
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform saveAT = g2d.getTransform();
		g2d.transform(mytranslate);
		g2d.transform(myrotate);
		g2d.transform(myscale);
		
		g2d.setColor(mycolor);
		g2d.drawRect(-width/2, -height/2, width, height);
		g2d.drawRect(-width/2 - 4/2, -height/2 + 4/2 , width+4, height - 4);
		
		g2d.setTransform(saveAT);
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
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setRemoveFlag(boolean removeme) 
	{
		// TODO Auto-generated method stub
		this.removeme = removeme;
	}


	@Override
	public boolean getRemoveFlag() 
	{
		// TODO Auto-generated method stub
		return removeme;
	}

	@Override
	public void fireWeapon() 
	{
		firebeamcmd.actionPerformed(null);
	}


	
	
}
