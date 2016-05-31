package minshmup;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JFrame;

import minshmup.command.TickTime;
import minshmup.command.create.CreateBulletExplosion;
import minshmup.command.create.CreateEnemyHeliCarrierShip;
import minshmup.command.create.CreateEnemyWeakShip;
import minshmup.command.fireweapon.FireBeamBullet;
import minshmup.command.fireweapon.FireGunBullet;
import minshmup.command.fireweapon.FirePierceBullet;
import minshmup.command.fireweapon.FireStab;
import minshmup.command.fireweapon.FireSwipe;
import minshmup.command.movement.Accelerate;
import minshmup.command.movement.Brake;
import minshmup.command.movement.SteerLeft;
import minshmup.command.movement.SteerRight;
import minshmup.gamelevel.GameLevel1;
import minshmup.gamelevel.GameWorld;
import minshmup.gameobjectcollection.DummyCollection;
import minshmup.gameobjectcollection.DummyCollection.DummyIterator;
import minshmup.gameobjectcollection.gameobject.GameObject;
import minshmup.gameobjectcollection.gameobject.ship.PlayerShip;
import minshmup.gameobjectcollection.gameobject.weapon.GunWeapon;
import minshmup.observer.MapView;
//TODO game needs syncing before running, to have all weapons ready.

public class Game extends JFrame implements ActionListener, KeyListener
{//holds mostly the visual stuff
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	//views, observables
	
	//size of the map screen
	private final int MAPWIDTH = 400;
	private final int MAPHEIGHT = 600;
	
	private GameWorld gw;
	private MapView mapv;
	
	//timer
	private Timer thetimer;
	private int millispeed = 20;
	private TickTime ticktimecmd;
	
	//player ship
	private PlayerShip pship;
	
	//player controls for moving
	private Accelerate accelcmd;
	private Brake brakecmd;
	private SteerLeft steerleftcmd;
	private SteerRight steerrightcmd;
	
	//player commands
	@SuppressWarnings("unused")
	private FireGunBullet firegunbulletcmd;
	@SuppressWarnings("unused")
	private FirePierceBullet firepiercecmd;
	@SuppressWarnings("unused")
	private FireBeamBullet firebeamcmd;
	@SuppressWarnings("unused")
	private FireStab firestabcmd;
	@SuppressWarnings("unused")
	private FireSwipe fireswipecmd;
	
	//create enemy stuff, such as bullets and ships.
	@SuppressWarnings("unused")
	private CreateBulletExplosion createbulexpcmd;
	@SuppressWarnings("unused")
	private CreateEnemyWeakShip createweakshipcmd;
	private CreateEnemyHeliCarrierShip createhelishipcmd;
	
	
	
	public Game()
	{//constructor for game.
		
		//System.out.println("Making Game...");
		gw = new GameLevel1();//only have one game world
		this.setTitle("Minimum Shoot em Up");//title of the window
		this.setVisible(true);//make sure the screen is visible.
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//close when hitting 'x'
		this.setSize(MAPWIDTH, MAPHEIGHT);//set size of window.
		this.setLocation(300,50);//location relative to computer screen.
		this.setLayout(new BorderLayout());//have top,left, etc. kind of deal
	
		
		//views, observers
		mapv = new MapView(gw);
		this.add(mapv,BorderLayout.CENTER);//put this in the center
		gw.addObserver(mapv);//make mapv listen to this
		
		this.addKeyListener(this);//listener's an observer design pattern, 
		//add a this to listen to key inputs
		
		
		
		//has to be done before the game world is initialized
		//commands for creating enemy stuff, such as bullets and ships.
		createbulexpcmd = CreateBulletExplosion.getCreateBulletExplosion(gw);
		createweakshipcmd = CreateEnemyWeakShip.getCreateEnemyWeakShip(gw);
		createhelishipcmd = CreateEnemyHeliCarrierShip.getCreateEnemyHeliCarrierShip(gw);
		
		
		gw.initLayout();//initialize layout here.  This does a lot of building the world.
	
		//I need to do this after the player is created
		//player controls
		accelcmd = Accelerate.getAccelerate(gw);
		brakecmd = Brake.getBrake(gw);
		steerleftcmd = SteerLeft.getSteerLeft(gw);
		steerrightcmd = SteerRight.getSteerRight(gw);
		firegunbulletcmd = FireGunBullet.getFireWeapon(gw);
		firepiercecmd = FirePierceBullet.getFirePierceWeapon(gw);
		firebeamcmd = FireBeamBullet.getFireBeamWeapon(gw);
		firestabcmd = FireStab.getFireStabWeapon(gw);
		fireswipecmd = FireSwipe.getFireSwipeWeapon(gw);
		
		//find ship
		DummyCollection dummycol = gw.getDummyCollection();
		DummyIterator diter = dummycol.getDummyIterator();
		GameObject plsbeship;
		while (diter.hasNext())
		{//look in list for ship
			plsbeship = diter.getNext();
			if (plsbeship instanceof PlayerShip)
			{
				pship = (PlayerShip) plsbeship;
				break;
			}
		}
		pship.setWeaponType(new GunWeapon());//set the player's weapon to this at the start.
		pship.getWeaponType().translate(0, pship.getHeight()/2 + 4);//get the weapon.
		
		//timer start
		thetimer = new Timer(millispeed, this);
		ticktimecmd = TickTime.getTickTime(gw);
		thetimer.setActionCommand("clockincrement");
		thetimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{

		
		if (e.getActionCommand() == "clockincrement")
		{//increment ticker.
			//System.out.println("Tick...");
			ticktimecmd.actionPerformed(null);
			gw.notifyObservers();
			//this.repaint();
			
		}
		
		
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent thekey)
	{//does things based on user input of PRESSING a key
		if (thekey.getKeyCode() == KeyEvent.VK_UP)
		{//if the player presses the up arrow key
			//System.out.println("Up arrow Key Pressed");
			accelcmd.actionPerformed(null);
		} else if (thekey.getKeyCode() == KeyEvent.VK_DOWN)
		{//if the player presses the down arrow key
			//System.out.println("Down arrow Key Pressed");
			brakecmd.actionPerformed(null);
		} else if (thekey.getKeyCode() == KeyEvent.VK_LEFT)
		{
			//System.out.println("Left arrow Key Pressed");
			steerleftcmd.actionPerformed(null);
		} else if (thekey.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			//System.out.println("Right arrow Key Pressed");
			steerrightcmd.actionPerformed(null);
		} else if (thekey.getKeyCode() == KeyEvent.VK_Z)
		{//player fires bullet, based on their weapon
			
			//TODO See if this is strategy and command design pattern
			
			pship.getWeaponType().fireWeapon();
			
		} else if (thekey.getKeyCode() == KeyEvent.VK_0)
		{//DEBUG spawns helicarrier
			
			createhelishipcmd.factoryEnemyHeliCarrierShip(pship.getX(), pship.getY());
			createhelishipcmd.actionPerformed(null);
		}
	}

	@Override
	public void keyReleased(KeyEvent thekey) 
	{
		
	}

	@Override
	public void keyTyped(KeyEvent thekey) 
	{
		
	}
	
	
	
	
	
}
