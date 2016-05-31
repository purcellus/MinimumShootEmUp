package minshmup.command.create;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.gameobject.environment.BulletExplosion;

public class CreateBulletExplosion extends AbstractAction
{//creates a bullet location, used for collection
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DummyCollection dummycol;
	private static CreateBulletExplosion cbexp;
	private BulletExplosion be;
	
	private CreateBulletExplosion(GameWorld gw)
	{
		dummycol = gw.getDummyCollection();
		
	}
	
	
	public static CreateBulletExplosion getCreateBulletExplosion(GameWorld gw)
	{
		if (cbexp == null)
		{
			cbexp = new CreateBulletExplosion(gw);
		}
		return cbexp;
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		dummycol.add(be);
		
	}
	
	public BulletExplosion factoryBulletExplosion(int translatex, int translatey, Color bulletcolor)
	{
		be = new BulletExplosion(translatex, translatey, bulletcolor);
		
		return be;
	}
	
	
	
	
	
}
