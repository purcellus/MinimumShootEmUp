package minshmup.gamelevel;



import java.util.Random;
import java.util.Vector;

import minshmup.gameinterface.observer.IObservable;
import minshmup.gameinterface.observer.IObserver;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.gameobject.powerup.BeamWeaponPowerUp;
import minshmup.gameobjectcollection.gameobject.powerup.GunWeaponPowerUp;
import minshmup.gameobjectcollection.gameobject.powerup.PierceWeaponPowerUp;
import minshmup.gameobjectcollection.gameobject.powerup.SwordStabWeaponPowerUp;
import minshmup.gameobjectcollection.gameobject.powerup.SwordSwipeWeaponPowerUp;
import minshmup.gameobjectcollection.gameobject.ship.EnemyBomberShip;
import minshmup.gameobjectcollection.gameobject.ship.EnemyHeliCarrierShip;
import minshmup.gameobjectcollection.gameobject.ship.EnemyWeakShip;
//import minrts.gameobjectcollection.GameObjectCollection;
import minshmup.gameobjectcollection.gameobject.ship.PlayerShip;

public class GameLevel1 extends GameWorld implements IObservable
{
	
	
	
	//private GameObjectCollection gobjcol;
	private DummyCollection dummycol;
	private Vector<IObserver> observers;
	
	private final int NUMOFWEAKSHIP = 8;//number of weak ships to spawn.
	private final int NUMOFBOMBERSHIP = 2;
	private final int NUMOFPIERCEPOWERUP = 1;
	private final int NUMOFGUNPOWERUP = 1;
	private final int NUMOFHELISHIP = 2;
	private final int NUMOFBEAMPOWERUP = 1;
	private final int NUMOFSWORDPOWERUP = 1;
	private final int NUMOFSWIPEPOWERUP = 1;
	
	public GameLevel1()
	{
		//gobjcol = GameObjectCollection.getGameObjectCollection();//only have one game object collection
		dummycol = new DummyCollection();
		observers = new Vector<IObserver>();
		
	}
	
	public void initLayout()
	{//initializes the game
		System.out.println("Initializing Layout");
		dummycol.add(factoryPlayerShip());
		int numc = 0;//counter for spawning ships
		
		while (numc < NUMOFPIERCEPOWERUP)
		{
			dummycol.add(factoryPierceWeaponPowerUp());
			numc++;
		}
		
		
		numc = 0;
		while (numc < NUMOFWEAKSHIP)
		{
			dummycol.add(factoryEnemyWeakShip());
			numc++;
		}
		
		numc = 0;
		while (numc < NUMOFBOMBERSHIP)
		{
			dummycol.add(factoryEnemyBomberShip());
			numc++;
		}
		
		numc = 0;
		while (numc < NUMOFGUNPOWERUP)
		{
			dummycol.add(factoryGunWeaponPowerUp());
			numc++;
		}
		
		numc = 0;
		while (numc < NUMOFHELISHIP)
		{
			dummycol.add(factoryEnemyHeliCarrierShip());
			numc++;
		}
		
		numc = 0;
		while (numc < NUMOFBEAMPOWERUP)
		{
			dummycol.add(factoryBeamWeaponPowerUp());
			numc++;
		}
		
		numc = 0;
		while (numc < NUMOFSWORDPOWERUP)
		{
			dummycol.add(factorySwordStabWeaponPowerUp());
			numc++;
		}
		
		numc = 0;
		while (numc < NUMOFSWIPEPOWERUP)
		{
			dummycol.add(factorySwordSwipeWeaponPowerUp());
			numc++;
		}
	}
	
	public DummyCollection getDummyCollection()
	{
		return dummycol;
	}
	
	public GunWeaponPowerUp factoryGunWeaponPowerUp()
	{
		Random rand = new Random();
		GunWeaponPowerUp thegun = new GunWeaponPowerUp(rand.nextInt(200), rand.nextInt(400));
		return thegun;
	}
	
	public PierceWeaponPowerUp factoryPierceWeaponPowerUp()
	{
		Random rand = new Random();
		
		PierceWeaponPowerUp thepower = new PierceWeaponPowerUp(rand.nextInt(200),rand.nextInt(400));
		return thepower;
	}
	
	public BeamWeaponPowerUp factoryBeamWeaponPowerUp()
	{
		Random rand = new Random();
		
		BeamWeaponPowerUp beampower = new BeamWeaponPowerUp(rand.nextInt(200),rand.nextInt(400));
		return beampower;
	}
	
	public SwordStabWeaponPowerUp factorySwordStabWeaponPowerUp()
	{
		Random rand = new Random();
		
		SwordStabWeaponPowerUp sword = new SwordStabWeaponPowerUp(rand.nextInt(200), rand.nextInt(400));
		return sword;
	}
	
	public SwordSwipeWeaponPowerUp factorySwordSwipeWeaponPowerUp()
	{
Random rand = new Random();
		
		SwordSwipeWeaponPowerUp sword = new SwordSwipeWeaponPowerUp(rand.nextInt(200), rand.nextInt(400));
		return sword;
	}
	
	public PlayerShip factoryPlayerShip()
	{
		//System.out.println("spawning player ship");
		PlayerShip trueship = PlayerShip.getPlayerShip(200,100);//player spawns here
		return trueship;
	}
	
	public EnemyWeakShip factoryEnemyWeakShip()
	{
		//System.out.println("spawning enemy weak ship");
		Random rand = new Random();
		EnemyWeakShip ewship = new EnemyWeakShip(rand.nextInt(300),rand.nextInt(500));
		return ewship;
	}

	public EnemyBomberShip factoryEnemyBomberShip()
	{
		Random rand = new Random();
		EnemyBomberShip ebship = new EnemyBomberShip(rand.nextInt(300), rand.nextInt(500));
		return ebship;
	}
	
	public EnemyHeliCarrierShip factoryEnemyHeliCarrierShip()
	{
		Random rand = new Random();
		EnemyHeliCarrierShip ehcship = new EnemyHeliCarrierShip(rand.nextInt(300), rand.nextInt(500));
		return ehcship;
	}
	
	@Override
	public void addObserver(IObserver obs) 
	{
		observers.addElement(obs);//add this observer to the list
	}

	@Override
	public void notifyObservers() 
	{
		//System.out.println("Notifying Observers");
		for (IObserver obs: observers)
		{//for there still having observers to update in the list
			obs.update(this, null);
		}
	}
	
	
}
