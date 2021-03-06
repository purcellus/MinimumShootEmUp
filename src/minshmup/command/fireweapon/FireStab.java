package minshmup.command.fireweapon;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.DummyCollection.DummyIterator;
import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.gameobjectcollection.gameobject.bullet.Bullet;
import minshmup.gameobjectcollection.gameobject.bullet.SwordStabBullet;
import minshmup.gameobjectcollection.gameobject.ship.PlayerShip;

public class FireStab extends AbstractAction
{//fires the primary weapon
	/**This is a command to fire weapons.
	 * TODO this sword will always be on the player, so to speak, moving with the player.
	 */
	private static final long serialVersionUID = 1L;
	
	private static FireStab fireweap;
	private PlayerShip pship;
	private GameWorld gw;
	
	private FireStab(PlayerShip pship)
	{
		this.pship = pship;
	}
	
	private FireStab(GameWorld gw)
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
	
	

	public static FireStab getFireStabWeapon(GameWorld gw) 
	{
		// TODO Auto-generated method stub
		if (fireweap == null)
		{
			fireweap = new FireStab(gw);
		}
		return fireweap;
	}


	@Override
	public void actionPerformed(ActionEvent arg0)
	{//firing this is similar to using a stabbing sword.
		
		//System.out.println("Making new bullet:  FireStab");
		DummyCollection dummycol = gw.getDummyCollection();
		
		int bulletpower = 10;//for demonstration
		//int bulletpower = theweap.getWeaponStrength();//get the weapon strength to determine what bullets to fire.
		
		
		
		
			//there's only one bullet here:  the sword
			Bullet firebullet = new SwordStabBullet(bulletpower,bulletpower, pship);//this makes the bullet
		
			firebullet.translate(pship.getX(), pship.getY() + pship.getHeight()*2);//since the bullet is made, translate it to the player's location.
			//add  + powerc*16 - bulletpower*8 to the x coord to change the spawning point fo the bullet
			
			
			
			dummycol.add(firebullet);//add new bullet to list
		
		
	}

}
