package minshmup.gameinterface.observer;


public interface IObservable 
{//observer design pattern.

	public void addObserver(IObserver obs);
	public void notifyObservers();//updates observers
	
	
}
