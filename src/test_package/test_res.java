package test_package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import a6.*;

public interface test_res {
	
	
	//Values used for running the test code
	public Position[] pos = { new PositionImpl(0, 0), new PositionImpl(1, 0), new PositionImpl(2, 2),
			new PositionImpl(1, 1), new PositionImpl(2, 1) };
	public Vehicle[] veh = { new VehicleImpl("Acura", "CLS", "FHC8151", pos[0]),
			new VehicleImpl("Acura", "CLS", "FHC8151", pos[1]), new VehicleImpl("Acura", "CLS", "FHC8151", pos[2]),
			new VehicleImpl("Acura", "CLS", "FHC8151", pos[3]), new VehicleImpl("Acura", "CLS", "FHC8151", pos[4]) };
	public Driver[] drivers = { new DriverImpl("Eddie", "Ennion", 0, veh[0]),
			new DriverImpl("Liliana", "Pagano", 1, veh[1]), new DriverImpl("maha", "Alhomoud", 2, veh[2]),
			new DriverImpl("demoniaque", "something", 3, veh[3]), new DriverImpl("Yazid", "Alamry", 4, veh[4]) };
	List<Driver> driver_pool1 = new ArrayList<Driver>(Arrays.asList(drivers));
	Driver[] list2 = { drivers[1], drivers[2], drivers[3] };
	List<Driver> driver_pool2 = new ArrayList<Driver>(Arrays.asList(list2));
	Driver[] list3 = { drivers[1], drivers[2], drivers[3], drivers[0], drivers[2], drivers[1] };
	List<Driver> driver_pool3 = new ArrayList<Driver>(Arrays.asList(list3));
	List<Driver> driver_pool_empty = new ArrayList<Driver>();


	List<Iterable<Driver>> driver_pool = new ArrayList<Iterable<Driver>>();

	
	
	/*
	 * Proximity results:
	 * 
	 * Eddie Ennion
	 * 
	 * Liliana Pagano
	 * 
	 * demoniaque something
	 */
	/*
	 * Expansion results:
	 * 
	 * Eddie Ennion
	 * 
	 * Liliana Pagano
	 * 
	 * demoniaque something
	 * 
	 * Yazid Alamry
	 * 
	 * maha Alhomoud
	 */

	/*
	 * Snake results:
	 * 
	 * Eddie Ennion
	 * 
	 * Liliana Pagano
	 * 
	 * Liliana Pagano
	 * 
	 * Liliana Pagano
	 * 
	 * maha Alhomoud
	 * 
	 * maha Alhomoud
	 * 
	 * maha Alhomoud
	 * 
	 * Liliana Pagano
	 * 
	 * maha Alhomoud
	 * 
	 * demoniaque something
	 * 
	 * demoniaque something
	 * 
	 * demoniaque something
	 * 
	 * Eddie Ennion
	 * 
	 * demoniaque something
	 * 
	 * Yazid Alamry
	 * 
	 * maha Alhomoud
	 * 
	 * Liliana Pagano
	 * 
	 * 
	 */

}
