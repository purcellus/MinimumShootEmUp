package minshmup.command.movement;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import minshmup.gamelevel.GameLevel1;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.DummyCollection.DummyIterator;
import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.gameobjectcollection.gameobject.ship.PlayerShip;

public class StopShip extends AbstractAction
{//this command sets the speed to zero if any key is released
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static StopShip stopit;
	private PlayerShip pship;
	
	private StopShip(GameLevel1 gw)
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
	
	public static StopShip getStopShip(GameLevel1 gw)
	{
		if (stopit == null)
		{
			stopit = new StopShip(gw);
		}
		return stopit;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		
		pship.setSpeedX(0);//stop the ship.
		pship.setSpeedY(0);
		
	}
	
}
