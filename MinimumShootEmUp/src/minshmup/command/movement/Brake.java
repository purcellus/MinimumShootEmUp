package minshmup.command.movement;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.DummyCollection.DummyIterator;
import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.gameobjectcollection.gameobject.ship.PlayerShip;

public class Brake extends AbstractAction
{//equivalent to moving up.

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Brake thebrake;
	private PlayerShip pship;
	
	private Brake(PlayerShip pship)
	{
		this.pship = pship;
	}
	
	
	private Brake(GameWorld gw) 
	{
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


	public static Brake getBrake(PlayerShip pship)
	{
		if (thebrake == null)
		{
			thebrake = new Brake(pship);
		}
		return thebrake;
	}

	public static Brake getBrake(GameWorld gw) 
	{
		// TODO Auto-generated method stub
		if (thebrake == null)
		{
			thebrake = new Brake(gw);
		}
		return thebrake;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		//System.out.println("Braking player ship");
		pship.setSpeedY(pship.getSpeedY() - 1);
	}


	
	
	
	
}