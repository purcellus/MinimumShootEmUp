package minshmup.command.create;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.gameobject.ship.EnemyWeakShip;

public class CreateEnemyWeakShip extends AbstractAction
{//creates a EnemyWeakShip location, used for collection
	/**This will create one at an NPC's location that can spawn these enemy weak ships.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DummyCollection dummycol;
	private static CreateEnemyWeakShip cbexp;
	private EnemyWeakShip ewship;
	
	private CreateEnemyWeakShip(GameWorld gw)
	{
		dummycol = gw.getDummyCollection();
		
	}
	
	
	public static CreateEnemyWeakShip getCreateEnemyWeakShip(GameWorld gw)
	{
		if (cbexp == null)
		{
			cbexp = new CreateEnemyWeakShip(gw);
		}
		return cbexp;
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		dummycol.add(ewship);
		
	}
	
	public EnemyWeakShip factoryEnemyWeakShip(int translatex, int translatey)
	{
		ewship = new EnemyWeakShip(translatex, translatey);
		
		return ewship;
	}
	
	
	
	
	
}
