package minshmup.gameobjectcollection.gameobject.powerup;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import minshmup.gameobjectcollection.gameobject.weapon.StabWeapon;
import minshmup.gameobjectcollection.gameobject.weapon.WeaponTypeStrategy;

public class SwordStabWeaponPowerUp extends PowerUp
{//increases the weaponstrength by one.
	
	private int width = 20;
	private int height = 20;
	private boolean removeme;
	
	public SwordStabWeaponPowerUp(int translatex, int translatey)
	{
		translate(translatex, translatey);
		mycolor = Color.ORANGE;
	}

	
	@Override
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform saveAT = g2d.getTransform();
		g2d.transform(mytranslate);
		g2d.transform(myrotate);
		g2d.transform(myscale);
		
		g2d.setColor(mycolor);
		g2d.fillOval(-width/2, -height/2, width, height);
		
		
		g2d.setTransform(saveAT);
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
	public WeaponTypeStrategy getWeapon() 
	{
		// TODO Auto-generated method stub
		return new StabWeapon();//makes a new weapon
	}

}
