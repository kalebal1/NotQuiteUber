package a6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ExpandingProximityIterator implements Iterator<Driver> {
	
	private Iterator<Driver> iter;
	private Iterable<Driver> iterable;
	private List<Driver> driverPool;
	private Driver nextClose;
	private int expand;
	private Position client_position;
	// counters
	private int nextCount = 0;
	private int curIndex = 0;
	private int expandInd = 0;
	// ring diameters
	private int innerRing;
	private int outerRing;
	
	public ExpandingProximityIterator(Iterable<Driver> driver_pool, Position client_position, int expansion_step) {
		consArgTest(driver_pool, client_position, expansion_step);
		expand = expansion_step;
		innerRing = 0;
		outerRing = 1;
		nextClose = null;
		this.client_position = client_position;
		this.iterable = driver_pool;
		this.iter = driver_pool.iterator();
		driverPool = (List<Driver>) iterableToCollection(driver_pool);
	
	
	}

	@Override
	public boolean hasNext() {
		//if nextCount is equal to size, all drivers have been found
		if(nextCount >= driverPool.size()) return false;
		
		if(nextClose != null) return true;
		
		// first ring
		
		if(expandInd == 0) {
			while(curIndex < driverPool.size()) {
				if(driverPool.get(curIndex).getVehicle().getPosition().getManhattanDistanceTo(client_position) 
						<= 1) {
					nextClose = driverPool.get(curIndex);
					curIndex ++;
					return true;
				}
				curIndex ++;
			}
		}
		
		// all other rings
		
		while(curIndex < driverPool.size() && expandInd >= 1) {
			if(driverPool.get(curIndex).getVehicle().getPosition().getManhattanDistanceTo(client_position) <= outerRing 
					&& driverPool.get(curIndex).getVehicle().getPosition().getManhattanDistanceTo(client_position) > innerRing) {
				
				nextClose = driverPool.get(curIndex);
				curIndex ++;
				return true;
			}
			curIndex ++;
		}
		
		//if no drivers match, resets iterator and index, jumps to next ring
		expandRing();
		return hasNext();
	}

	@Override
	public Driver next() {
		
		if (!iter.hasNext()) {
			throw new NoSuchElementException("No next driver");
		} 
		
		Driver d = nextClose;
		nextClose = null;
		nextCount ++;
		
		return d;
	}
	
	// makes iterable into collection to determine size
	public static <T> Collection<T> iterableToCollection(Iterable<T> iterable) {
		  Collection<T> collection = new ArrayList<>();
		  iterable.forEach(collection::add);
		  return collection;
		}
	
	private void expandRing() {
		//resets iterator for next ring
		iter = iterable.iterator();
		curIndex = 0;
		// changes ring diameters
		expandInd ++;
		innerRing = outerRing;
		outerRing = 1 + expandInd * expand;
		
	}
	
	private void consArgTest(Iterable<Driver> driver_pool, Position client_position, int expansion_step) {
		if(client_position == null) {
			throw new IllegalArgumentException("client position null");
		}
		if(expansion_step <= 0) {
			throw new IllegalArgumentException("illegal expansion step (<= 0)");
		}
		if(driver_pool == null) {
			throw new IllegalArgumentException("driver_pool null");
		}
	}
}