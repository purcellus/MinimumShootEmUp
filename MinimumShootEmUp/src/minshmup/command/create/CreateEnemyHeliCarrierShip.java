package minshmup.command.create;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.gameobject.ship.EnemyHeliCarrierShip;

public class CreateEnemyHeliCarrierShip extends AbstractAction
{
	/**This will create one at an NPC's location that can spawn these enemy helicarrier ships.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DummyCollection dummycol;
	private static CreateEnemyHeliCarrierShip cehcs;
	private EnemyHeliCarrierShip ehcship;
	
	private CreateEnemyHeliCarrierShip(GameWorld gw)
	{
		dummycol = gw.getDummyCollection();
		
	}
	
	
	public static CreateEnemyHeliCarrierShip getCreateEnemyHeliCarrierShip(GameWorld gw)
	{
		if (cehcs == null)
		{
			cehcs = new CreateEnemyHeliCarrierShip(gw);
		}
		return cehcs;
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		dummycol.add(ehcship);
		
	}
	
	public EnemyHeliCarrierShip factoryEnemyHeliCarrierShip(int translatex, int translatey)
	{
		ehcship = new EnemyHeliCarrierShip(translatex, translatey);
		
		return ehcship;
	}
	
	
	
}
