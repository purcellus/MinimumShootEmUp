package minshmup.command;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;

import javax.swing.AbstractAction;

import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.DummyCollection.DummyIterator;
import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.observer.MapView;

public class ShowMap extends AbstractAction
{//shows the visual stuff

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static ShowMap themap;
	private DummyCollection dummycol;
	private MapView mapv;
	
	
	private ShowMap(MapView mapv)
	{
		this.mapv = mapv;
		GameWorld gw = mapv.getGameWorld();
		dummycol = gw.getDummyCollection();
	}
	
	public static ShowMap getShowMap(MapView mapv)
	{
		if (themap == null)
		{
			themap = new ShowMap(mapv);
		} 
		return themap;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{

		//System.out.println("Show map command activated");
		GameObject drawobj;
		DummyIterator diter = dummycol.getDummyIterator();
		while (diter.hasNext())
		{//draw all the objects
			drawobj = diter.getNext();
			AffineTransform vtm = mapv.getVTM();
			Graphics2D g2d = (Graphics2D) mapv.getGraphics();
			g2d.transform(vtm);
			
			drawobj.draw(g2d);//gets the graphics of the mapview
		}
		//mapv.repaint();
		
	}
	
	
	
}
