package minshmup.command.fireweapon;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.DummyCollection.DummyIterator;
import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.gameobjectcollection.gameobject.bullet.Bullet;
import minshmup.gameobjectcollection.gameobject.bullet.GunBullet;
import minshmup.gameobjectcollection.gameobject.ship.PlayerShip;

public class FireGunBullet extends AbstractAction
{//fires the primary weapon
	/**This is a command to fire weapons.
	 * There are many like these, but this is considered the default
	 */
	private static final long serialVersionUID = 1L;
	
	private static FireGunBullet fireweap;
	private PlayerShip pship;
	private GameWorld gw;
	
	private FireGunBullet(PlayerShip pship)
	{
		this.pship = pship;
	}
	
	private FireGunBullet(GameWorld gw)
	{
		this.gw = gw;
		DummyCollection dummycol = gw.getDummyCollection();
		DummyIterator diter = dummycol.getDummyIterator();
		GameObject plsbeship;
		while (diter.hasNext())
		{//look in list for ship
			plsbeship = diter.getNext();
			if (plsbeship instanceof PlayerShip)
			{
				pship = (PlayerShip) plsbeship;
				break;
			}
		}
		
	}
	
	

	public static FireGunBullet getFireWeapon(GameWorld gw) 
	{
		// TODO Auto-generated method stub
		if (fireweap == null)
		{
			fireweap = new FireGunBullet(gw);
		}
		return fireweap;
	}


	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		//System.out.println("Making new bullet");
		DummyCollection dummycol = gw.getDummyCollection();
		
		int bulletpower = 10;//for demonstration
		//int bulletpower = theweap.getWeaponStrength();//get the weapon strength to determine what bullets to fire.
		int powerc = 0;//the strength of the bullets.
		
		
		
		
		while (powerc < bulletpower)
		{
			Bullet firebullet = new GunBullet(0,0);//this makes a bullet
		
			firebullet.translate(pship.getX(), pship.getY());//since the bullet is made, translate it to the player's location.
			//add  + powerc*16 - bulletpower*8 to the x coord to change the spawning point fo the bullet
			
			
			firebullet.rotate(powerc*8 - (bulletpower-1)*4);
			firebullet.setHeading(powerc*8 - (bulletpower-1)*4);
			//offset the bullets by a set amount, using transformations.
			
			dummycol.add(firebullet);//add new bullet to list
			powerc++;//increment counter
		}
		
		
	}

}
