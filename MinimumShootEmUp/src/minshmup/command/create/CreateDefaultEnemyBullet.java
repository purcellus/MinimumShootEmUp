package minshmup.command.create;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.gameobject.bullet.DefaultEnemyBullet;

public class CreateDefaultEnemyBullet extends AbstractAction
{//creates a EnemyWeakShip location, used for collection
	/**This will create one at an NPC's location that can spawn these enemy weak ships.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DummyCollection dummycol;
	private static CreateDefaultEnemyBullet cbexp;
	private DefaultEnemyBullet ebullet;
	
	private CreateDefaultEnemyBullet(GameWorld gw)
	{
		dummycol = gw.getDummyCollection();
		
	}
	
	
	public static CreateDefaultEnemyBullet getCreateEnemyBullet(GameWorld gw)
	{
		if (cbexp == null)
		{
			cbexp = new CreateDefaultEnemyBullet(gw);
		}
		return cbexp;
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		dummycol.add(ebullet);
		
	}
	
	public DefaultEnemyBullet factoryDefaultEnemyBullet(int translatex, int translatey)
	{
		ebullet = new DefaultEnemyBullet(translatex, translatey);
		
		return ebullet;
	}
	
	
	
	
	
}
