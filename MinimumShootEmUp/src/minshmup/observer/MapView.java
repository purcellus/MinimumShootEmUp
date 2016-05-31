package minshmup.observer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import minshmup.command.ShowMap;
import minshmup.gameinterface.observer.IObservable;
//import minrts.gameobjectcollection.gameobject.GameObject;
import minshmup.gameinterface.observer.IObserver;
import minshmup.gamelevel.GameLevel1;
import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.DummyCollection.DummyIterator;
import minshmup.gameobjectcollection.gameobject.GameObject;

public class MapView extends JPanel implements IObserver
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//shows the map, such as player, enemies, maybe HUD, etc.

	private int windowleft = 0;
	private int windowright = 400;
	private int windowbot = 0;
	private int windowtop = 600;
	private AffineTransform worldtond, ndtoscreen, viewingtransformatinmatrix;
	
	private GameWorld gw;
	
	private ShowMap showmapcmd;
	
	public MapView(GameWorld gw)
	{
		this.gw = gw;
		//mapgraphics = this.getGraphics();//get the graphics of this panel.
		this.setVisible(true);
		
		showmapcmd = ShowMap.getShowMap(this);
		
		//draw(mapgraphics);

		//this.repaint();
	}
	
	public GameWorld getGameWorld()
	{
		return gw;
	}
	
	
	public void paintComponent(Graphics g)
	{//should be paintComponent, not draw
		//System.out.println("Drawing MapView");
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform saveAT = g2d.getTransform();
		int winwidth = windowright - windowleft;
		int winheight = windowtop - windowbot;
		
		worldtond = worldToNormalizedDevice( winwidth, winheight, windowleft, windowbot);
		//turn the world into the normalized device, 0,0 to 1,1
		ndtoscreen = normalizedDeviceToScreen(this.getWidth(),this.getHeight());
		//turn the normalized device into the screen, screen widht and screen height (for this panel).
		viewingtransformatinmatrix = (AffineTransform) ndtoscreen.clone();//clone the ndtoscreen
		viewingtransformatinmatrix.concatenate(worldtond);//now we apply the transformations to the vtm 
		
		g2d.transform(viewingtransformatinmatrix);//apply the vtm to the graphics2d
		
		
		showmapcmd.actionPerformed(null);//do the map command
		//TODO figure out why it flickers when doing the command rather than drawing here.
		
		DummyCollection dummycol = gw.getDummyCollection();
		DummyIterator diter = dummycol.getDummyIterator();
		while (diter.hasNext())
		{
			//System.out.println("Drawing map view objects");
			GameObject gobj = diter.getNext();
			gobj.draw(g2d);
		}
		
		

		this.setBackground(Color.BLACK);
		
		g2d.setTransform(saveAT);		
		this.repaint();//doing this makes the thing appear more frequently, though there are still flickers
		//need this, otherwise it stays as one image, unless automatically repainted.
	}
	
	public AffineTransform worldToNormalizedDevice( double winwidth,double winheight,double winleft,double winbot)
	{//win = window
		AffineTransform normalized = new AffineTransform();
		//starts at 0,0.
		normalized.scale(1.0 / winwidth, 1.0 / winheight);//scale so that the normalized device
		//is a size of 1,1.  use doubles so int calculations don't cause errors.
		normalized.translate(-winleft, -winbot);//translate so that the normalized device
		//translate first so it scales to 0,0 , don't forget to use negatives
		
		return normalized;
	}
	
	public AffineTransform normalizedDeviceToScreen(double screenwidth, double screenheight)
	{
		AffineTransform screen = new AffineTransform();
		screen.translate(0, screenheight);//translate it back UP to the screen, since 
		//when scaling by -screenheight, it is below the screen by that much
		screen.scale(screenwidth, -screenheight);//scale the Normalized device to the screen size.
		//make sure to flip everything in height, since screens draw top down instead of bottom up.
		
		return screen;
	}
	
	public AffineTransform getVTM()
	{
		return viewingtransformatinmatrix;
	}

	@Override
	public void update(IObservable obs, Object arg0) 
	{
		//System.out.println("Updating mapview");
		gw = (GameLevel1) obs;//because this Observable is the gameworld, I can type cast it here.
		
		
		//this.repaint();
	}
	
	
	
	
	
}
