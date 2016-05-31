package minshmup.gameobjectcollection.gameobject.powerup;

import java.awt.Graphics;

import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.gameobjectcollection.gameobject.weapon.WeaponTypeStrategy;

public abstract class PowerUp extends GameObject
{
	
	public abstract WeaponTypeStrategy getWeapon();
	//this is to set the weapon of the player ship that picks it up
	
	
	
	
	
	

	


	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCollIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLocalIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLocalIndex(int localindex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}//powerups are things the player can collect to
	//change weapons, increase health, etc.

}
