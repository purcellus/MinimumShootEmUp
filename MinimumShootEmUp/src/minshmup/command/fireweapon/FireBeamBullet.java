package minshmup.command.fireweapon;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.DummyCollection.DummyIterator;
import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.gameobjectcollection.gameobject.bullet.BeamBullet;
import minshmup.gameobjectcollection.gameobject.bullet.Bullet;
import minshmup.gameobjectcollection.gameobject.ship.PlayerShip;

public class FireBeamBullet extends AbstractAction
{//fires the primary weapon
	/**This is a command to fire weapons.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static FireBeamBullet fireweap;
	private PlayerShip pship;
	private GameWorld gw;
	
	private FireBeamBullet(PlayerShip pship)
	{
		this.pship = pship;
	}
	
	private FireBeamBullet(GameWorld gw)
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
	
	

	public static FireBeamBullet getFireBeamWeapon(GameWorld gw) 
	{
		// TODO Auto-generated method stub
		if (fireweap == null)
		{
			fireweap = new FireBeamBullet(gw);
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
			Bullet firebullet = new BeamBullet(0,0);//this makes a bullet
		
			firebullet.translate(pship.getX() + powerc*4 - bulletpower*2 , pship.getY());//since the bullet is made, translate it to the player's location.
			//add  + powerc*16 - bulletpower*8 to the x coord to change the spawning point fo the bullet
			
			
			
			dummycol.add(firebullet);//add new bullet to list
			powerc++;//increment counter
		}
		
		
	}

}
