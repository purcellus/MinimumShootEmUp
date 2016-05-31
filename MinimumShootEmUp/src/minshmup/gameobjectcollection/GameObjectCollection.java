package minshmup.gameobjectcollection;

import java.util.Vector;

import minshmup.gameobjectcollection.gameobject.GameObject;

public class GameObjectCollection 
{//the game object collection will hold all game objects
	private int[][] openlocalindex;//this holds the spaces between and the last space open.
	//for example, when initializing, it will go from 0 and go up.
	//however, if an object is removed, the localindex will change to that object's index
	//therefore, having quick addition and quick subtraction.
	//This will be an array, and the last element is the list size, so to speak.
	//for example, there are 10 elements.  elements 2, 6, 11 are open.
	//therefore, this array will hold those elements, and add any elements that are open.
	//the first [] indicates the game object type index.
	//the second [] indicates the true local index
	private final int NUM_OF_GAMEOBJECTS = 128;//the number of game objects that can exist at any time.
	private final int NUM_OF_LOCALOBJECTS = 1024;//the number of objects in the local collection.
	
	
	private static Vector<Vector<GameObject>> thecol;
	//a two dimensional array consisting of:
	//	The master collection, organized based on object type.

	//	The local collection, where all of one type exists, ex. all of player ships are in
	//	master index 1, but varying local indexes.
	
	
	//private static Vector<GameObject> thecol;//a dummy holder, for simplicity sake
	//we will use the two d version in the future
	
	private static GameObjectCollection thiscollect;//this is for singleton
	
	//there will be only one game object collection
	
	private GameObjectCollection()
	{
		thecol = new Vector<Vector<GameObject>>();
		//thecol = new Vector<GameObject>();
		//players = new Vector<GameObject>();
		//thecol.add(players);
		openlocalindex = new int[NUM_OF_GAMEOBJECTS][NUM_OF_LOCALOBJECTS];
		//TODO figure out how to change this
		
		int spawnc = 0;//counter to initialize arrays.
		while (spawnc < NUM_OF_GAMEOBJECTS)
		{//initialize array 
			openlocalindex[spawnc][0] = 0;//first element becomes zero
			spawnc++;//increment counter
		}
		
	}
	
	public void addIndex(int newindex)
	{//adds the indexes
		
	}
	
	public static GameObjectCollection getGameObjectCollection()
	{//uses singleton method to get the game object collection
		if (thiscollect == null)
		{
			thiscollect = new GameObjectCollection();
		}
		return thiscollect;
	}
	
	//TODO when adding or removing, have  a quick way to make a new index
	public void add(GameObject gameobj)
	{//puts a game object into the Vector, based on its index
		Vector<GameObject> localcol;//localcollection
		if (thecol.get(gameobj.getCollIndex()) == null)
		{//if that list isn't instantiated yet
			//System.out.println("No Vector<> at that index.  making a new one");
			thecol.add(new Vector<GameObject>());//make a new list of those objects
		} 
		
		localcol = thecol.get(gameobj.getCollIndex());
		localcol.add(gameobj);//add that object to the localcolleciton.
		
	}
	
	public void remove(GameObject gameobj)
	{//remove an object from the Vector, based on its index AND object type
		Vector<GameObject> localcol;//localcollection
		if (thecol.get(gameobj.getCollIndex()) == null)
		{//if that list isn't instantiated yet
			//System.out.println("No Vector<> at that index.  making a new one");
			thecol.add(new Vector<GameObject>());//make a new list of those objects
		} 
		localcol = thecol.get(gameobj.getCollIndex());
		if (localcol.get(gameobj.getLocalIndex()) == null)
		{
			System.out.println("Object doesn't exist");
			//don't do anything, the object doesn't exist
		} else
		{
			localcol.remove(gameobj);
		}
	
	}
	
	public GameObject getObjectByIndex(GameObject gameobj)
	{//This should be a very efficient method to grab something
		GameObject theobj;
		Vector<GameObject> localcol;//localcollection
		if (thecol.get(gameobj.getCollIndex()) == null)
		{//if that list isn't instantiated yet
			//System.out.println("No Vector<> at that index.  making a new one");
			thecol.add(new Vector<GameObject>());//make a new list of those objects
		} 
		localcol = thecol.get(gameobj.getCollIndex());
		//get the object from the local collection.
		if (localcol.get(gameobj.getLocalIndex()) == null)
		{
			System.out.println("Object doesn't exist");
			theobj = null;//TODO figure out a better solution.
			//things to do:  Maybe add that object automatically.
			//query the user if they want to make that object.
			//check if theobj is null, and if so, state that an issue occurred.
		} else
		{
			theobj = localcol.get(gameobj.getLocalIndex());
		}

		return theobj;
	}
	
	
	

}
