package test_package;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import a6.*;
public class test_class implements test_res {

	public static void main(String[] args) {
		driver_pool.add(driver_pool1);
		
		driver_pool.add(driver_pool2);
		driver_pool.add(driver_pool3);
		driver_pool.add(driver_pool2);
		
		driver_pool.add(driver_pool_empty);
		driver_pool.add(driver_pool_empty);
		driver_pool.add(driver_pool_empty);
		
		driver_pool.add(driver_pool2);
		
		//comment out all iterators except the one you are testing
		
		// ProximityIterator iter = new ProximityIterator(driver_pool1, pos[0], 2);
		//ExpandingProximityIterator iter = new ExpandingProximityIterator(driver_pool1, pos[0], 1);
		SnakeOrderAcrossPoolsIterator iter = new SnakeOrderAcrossPoolsIterator(driver_pool);
		
		
		
		
		// your output values should match the values made in test_res package
		while(iter.hasNext()) {
			System.out.println(iter.next().getFullName()+"\n");
			// System.out.println("iter index = " + iter.getIterIndex());
			
		}
		
	}

}
