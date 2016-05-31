package minshmup.command.movement;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.DummyCollection.DummyIterator;
import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.gameobjectcollection.gameobject.ship.PlayerShip;

public class SteerRight extends AbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static SteerRight sright;
	private PlayerShip pship;
	
	private SteerRight(GameWorld gw)
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
	
	public static SteerRight getSteerRight(GameWorld gw)
	{
		if (sright == null)
		{
			sright = new SteerRight(gw);
		}
		return sright;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		pship.setSpeedX(pship.getSpeedX() + 1);
		
	}
	
	
	
}
