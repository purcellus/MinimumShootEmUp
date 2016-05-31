package minshmup.gamelevel;

import minshmup.gameinterface.observer.IObserver;
import minshmup.gameobjectcollection.DummyCollection;

public abstract class GameWorld 
{//abstract, other levels extend this.
	
	public abstract DummyCollection getDummyCollection();
	public abstract void initLayout();
	
	public abstract void addObserver(IObserver obs);
	public abstract void notifyObservers();
	
}
