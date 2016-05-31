package minshmup.command;

import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.AbstractAction;

import minshmup.command.create.CreateEnemyWeakShip;
import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.DummyCollection.DummyIterator;
import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.gameobjectcollection.gameobject.ship.EnemyWeakShip;

public class TickTime extends AbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static TickTime ttime;
	private DummyCollection dummycol;//the collection to be worked on
	
	private int randfactship = 0;
	private Random rand;
	private final int FACTWEAKSHIPCHECK = 80;//value randfactship must exceed to spawn a ship.
	
	private CreateEnemyWeakShip cewship;

	
	private TickTime(GameWorld gw)
	{
		dummycol = gw.getDummyCollection();
		rand = new Random();
		
		cewship = CreateEnemyWeakShip.getCreateEnemyWeakShip(null);

	}
	
	public static TickTime getTickTime(GameWorld gw)
	{
		if (ttime == null)
		{
			ttime = new TickTime(gw);
		}
		return ttime;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//first move the objects
		GameObject gobj;
		DummyIterator diter = dummycol.getDummyIterator();
		while (diter.hasNext())
		{
			gobj = diter.getNext();
			//move the object, if it can
			gobj.move();//move the objects
			
		}
		
		//now check collisions
		DummyCollection dummycol2 = new DummyCollection();//copy to check for collisions.
		dummycol2.addAll(dummycol);//copy first collection into the other.
		
		DummyIterator diter2 = dummycol2.getDummyIterator();//make a second iterator.
		GameObject gobj2;
		
		//int a = 0;
		//int c = 0;
		
		diter = dummycol.getDummyIterator();//restart iterator

		
		
		while (diter.hasNext())
		{
			
			//System.out.println("first gobj " + a);
			//a++;
			
			
			
			//System.out.println("Checking for collisions...");
			gobj = diter.getNext();
			diter2 = dummycol2.getDummyIterator();//restart the iterator.
			//System.out.println(gobj.getClass());

			
			while (diter2.hasNext())
			{//check each object with this, to see if it has collided
				
				//System.out.println("second gobj " + c);
				//c++;
				
				gobj2 = diter2.getNext();//get the next element to check with.
				//System.out.println(gobj2.getClass());

				
				
				if (!(gobj.equals(gobj2)) && !gobj2.getAlreadyCollided())
				{//if this isn't the same object and hasn't been checked already.
					if (gobj.hasCollided(gobj2))
					{//if this object collided with the other object
						
							//System.out.println(gobj.getClass() + " Has collided with " + gobj2.getClass());
							gobj.handleCollision(gobj2);//handle the collision
					}
				}
				
			}
			
			//c = 0;//reset c

			gobj.setAlreadyCollided(true);//since we've finished checking it, we won't look at this anymore.
			
		}
		
		//a = 0;//reset a
		
		
		dummycol.removeOnCondition();//check objects to remove
		
		diter = dummycol.getDummyIterator();//another iterator, to set already collided to false.
		while (diter.hasNext())
		{
			gobj = diter.getNext();
			gobj.setAlreadyCollided(false);
		}
		
		randfactship = rand.nextInt(100);
		if (randfactship > FACTWEAKSHIPCHECK)
		{
			cewship.factoryEnemyWeakShip(rand.nextInt(400), rand.nextInt(100) + 600);//spawn at the top of the map
			cewship.actionPerformed(null);		}
		
	}
	
	public EnemyWeakShip factoryWeakShip(int translatex, int translatey)
	{
		EnemyWeakShip ewship = new EnemyWeakShip(translatex, translatey);
		return ewship;
	}
	
	

}
