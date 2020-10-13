package a6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver> {
	
	private ArrayList<Iterator<Driver>> driverIterators;
	private int iterIndex;
	private int numberOfPools;
	private boolean goingUp;
	boolean end = false;
	boolean first = true;

	public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driver_pools) {
		iterIndex = 0;
		goingUp = true;
		consArgCheck(driver_pools);
		driverIterators = new ArrayList<Iterator<Driver>>();
		for(int i = 0; i < driver_pools.size(); i++) {
			Iterator<Driver> thisIter = driver_pools.get(i).iterator();
			driverIterators.add(thisIter);
			}
		numberOfPools = driverIterators.size();
		
		
	}

	@Override
	public boolean hasNext() {
		
		if(numberOfPools == 1) {
			return driverIterators.get(0).hasNext();
		}
		
		if(driverIterators.isEmpty()) {
			return false;
		}
		
		if(iterIndex >= driverIterators.size() && driverIterators.size() > 1 && !driverIterators.get(iterIndex).hasNext()) {
			return false;
		}
		if(iterIndex < 0) {
			return false;
		}
		
		if(driverIterators.get(iterIndex).hasNext()) {
			return true;
		} else {
			driverIterators.remove(iterIndex);
			iterIndex --;
			return hasNext();
		}
	}

	@Override
	public Driver next() {
		
		if(numberOfPools == 1) {
			return driverIterators.get(0).next();
		}
		
		Driver d = driverIterators.get(iterIndex).next();
		
		if(driverIterators.size() == 1) {
			return d; 
		}
		if(first) {
			iterIndex++;
			first = false;
			return d;
		}
		
		if(iterIndex == driverIterators.size() - 1 && !end) {
			goingUp = false;
			end = true;
		} else if (iterIndex == driverIterators.size() - 1 && end) {
			end = false;
			iterIndex --;
		} else if (iterIndex == 0 && !end) {
			goingUp = true;
			end = true;
		} else if (iterIndex == 0 && end) {
			end = false;
			iterIndex ++;
		} else if(goingUp) {
			iterIndex ++;
		} else {
			iterIndex --;
		}
		return d;
	}
	
	private void consArgCheck(List<Iterable<Driver>> list) {
		for(Iterable<Driver> d: list) {
			if(d.equals(null)) {
				throw new IllegalArgumentException("driverPool contains null Driver");
			}
		}
	}
	

}
