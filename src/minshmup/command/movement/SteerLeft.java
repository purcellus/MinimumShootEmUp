package minshmup.command.movement;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.DummyCollection.DummyIterator;
import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.gameobjectcollection.gameobject.ship.PlayerShip;

public class SteerLeft extends AbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static SteerLeft sleft;
	private PlayerShip pship;
	
	private SteerLeft(GameWorld gw)
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
	
	public static SteerLeft getSteerLeft(GameWorld gw)
	{
		if (sleft == null)
		{
			sleft = new SteerLeft(gw);
		}
		return sleft;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		pship.setSpeedX(pship.getSpeedX() - 1);
		
	}
	
	
	
}
