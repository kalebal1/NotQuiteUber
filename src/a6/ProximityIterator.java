package a6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ProximityIterator implements Iterator<Driver>{
	
	private Iterator<Driver> iter;
	private List<Driver> driverPool;
	private Driver nextClose;
	private int proximity_limit;
	private Position client_position;
	private int curIndex;
	
	

	public ProximityIterator(Iterable<Driver> driver_pool, Position client_position, int proximity_limit) {
		consArgTest(driver_pool, client_position, proximity_limit);
		nextClose = null;
		this.iter = driver_pool.iterator();
		driverPool = (List<Driver>) iterableToCollection(driver_pool);
		this.proximity_limit = proximity_limit;
		this.client_position = client_position;
		curIndex = 0;
		
		}
		
	

	@Override
	public boolean hasNext() {
	
		if(curIndex >= driverPool.size()) {
			return false;
		}
		
		if(nextClose != null) {
			return true;
		}
		
		while(curIndex < driverPool.size()) {
				
				if(driverPool.get(curIndex).getVehicle().getPosition().getManhattanDistanceTo(client_position) <= proximity_limit) {
				nextClose = driverPool.get(curIndex);
				return true;
			}
			curIndex += 1;
		}
		return false;
	}

	@Override
	public Driver next() {
		
		if (!hasNext()) {
			throw new NoSuchElementException("No next driver");
		} 
		
		Driver d = nextClose;
		nextClose = null;
		curIndex += 1;
		
		return d;
	}
	
	// Transforms iterable argument to collection to determine size. 
	// assumes static iterable
	
	public static <T> Collection<T> iterableToCollection(Iterable<T> iterable) {
		  Collection<T> collection = new ArrayList<>();
		  iterable.forEach(collection::add);
		  return collection;
		}
	
	private void consArgTest(Iterable<Driver> driver_pool, Position client_position, int proximity_limit) {
		if(client_position == null) {
			throw new IllegalArgumentException("client position null");
		}
		if(proximity_limit <= 0) {
			throw new IllegalArgumentException("illegal proximity limit");
		}
		if(driver_pool == null) {
			throw new IllegalArgumentException("driver_pool null");
		}
	}

}
