package minshmup.gameobjectcollection;

import java.util.Vector;

import minshmup.gameobjectcollection.gameobject.GameObject;

public class DummyCollection 
{//this is a simple collection, using a very simple iterative method to get things.
	private Vector<GameObject> thecol = new Vector<GameObject>();
	private int iteratec;//the iterator counter
	
	public DummyCollection()
	{
		thecol = new Vector<GameObject>();
		iteratec = 0;
	}
	
	public void add(GameObject gobj)
	{
		thecol.addElement(gobj);
	}
	
	public void addAll(DummyCollection dcol)
	{
		DummyIterator diter = dcol.getDummyIterator();
		while (diter.hasNext())
		{
			add(diter.getNext());
		}
	}
	
	public void remove(GameObject gobj)
	{
		thecol.remove(gobj);
	}
	
	public void removeOnCondition()
	{//iteratively checks to remove objects
		//System.out.println("Checking objects to remove in collection");
		DummyIterator diter = getDummyIterator();//gonna do it iteratively
		while (diter.hasNext())
		{
			GameObject robj = diter.getNext();
			robj.setRemoveFlag(robj.getRemoveFlag());//update the removal flag, just in case
			if (robj.getRemoveFlag())
			{//if the object needs to be removed
				//System.out.println("Remove " + robj.getClass());
				remove(robj);//remove the object
			}
		}
	}
	
	public DummyIterator getDummyIterator()
	{
		return new DummyIterator();
	}
	
	
	
	
	public class DummyIterator
	{
		public DummyIterator()
		{
			iteratec = 0;//reset the iterator if declared
		}
		
		public GameObject get(int theindex)
		{
			return thecol.get(theindex);
		}
		
		public GameObject getNext()
		{
			GameObject retobj = thecol.get(iteratec);
			if (iteratec < thecol.size())
			{
				iteratec++;
			} else
			{
				//iteratec = 0;//reset the iterator
				System.out.println("reached end of list");
			}

			
			
			return retobj;
		}
		
		public boolean hasNext()
		{
			if (iteratec < thecol.size())
			{
				return true;
			} else
			{
				return false;
			}
		}
		
		public void resetCount()
		{//reset the counter
			iteratec = 0;
		}
	}
	
	
}


