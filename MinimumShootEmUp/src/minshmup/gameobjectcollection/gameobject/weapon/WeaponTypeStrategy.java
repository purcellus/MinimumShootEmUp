package minshmup.gameobjectcollection.gameobject.weapon;

import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.gameobjectcollection.gameobject.bullet.Bullet;

public abstract class WeaponTypeStrategy extends GameObject
{//all weapon types extends this.
	//the weapon type tells the player what type of bullet it will fire.
	//the weapon type will be a strategy design pattern, so as to be able to change it on the fly.

	public abstract Bullet getBullet();//pretty much the strategy's execute
	
	public abstract void setWeaponStrength(int wstrength);
	public abstract int getWeaponStrength();//how powerful the weapon is
	//basically fires more bullets, or does more damage.
	
	public abstract void fireWeapon();//fire the weapon polymorphically,
	//decouples stuff in game
}
