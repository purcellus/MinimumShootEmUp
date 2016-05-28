package minshmup.command.movement;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.DummyCollection.DummyIterator;
import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.gameobjectcollection.gameobject.ship.PlayerShip;

public class Accelerate extends AbstractAction
{//equivalent to moving up.

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Accelerate theacc;
	private PlayerShip pship;
	
	private Accelerate(PlayerShip pship)
	{
		this.pship = pship;
	}
	
	private Accelerate(GameWorld gw)
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
	
	
	public static Accelerate getAccelerate(PlayerShip pship)
	{
		if (theacc == null)
		{
			theacc = new Accelerate(pship);
		}
		return theacc;
	}
	
	public static Accelerate getAccelerate(GameWorld gw) 
	{
		// TODO Auto-generated method stub
		if (theacc == null)
		{
			theacc = new Accelerate(gw);
		}
		return theacc;
	}


	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		//System.out.println("Accelerating player ship");
		
		pship.setSpeedY(pship.getSpeedY() + 1);
	}


	
	
	
	
}
