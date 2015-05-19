package main.custom.guava.test;

import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

public class RangeTest {
	public static void main(String[] args) {
		System.out.println("open:"+Range.open(1, 10));
        System.out.println("closed:"+ Range.closed(1, 10));
        System.out.println("closedOpen:"+ Range.closedOpen(1, 10));
        System.out.println("openClosed:"+ Range.openClosed(1, 10));    
        System.out.println("greaterThan:"+ Range.greaterThan(10));
        System.out.println("atLeast:"+ Range.atLeast(10));
        System.out.println("lessThan:"+ Range.lessThan(10));
        System.out.println("atMost:"+ Range.atMost(10));
        System.out.println("all:"+ Range.all());
        System.out.println("closed:"+Range.closed(10, 10));
        System.out.println("closedOpen:"+Range.closedOpen(10, 10));
	
        
        
        System.out.println(Range.closed(1, 3).contains(2)); 
        System.out.println(Range.closed(1, 3).contains(4)); 
        System.out.println(Range.lessThan(5).contains(5)); 
        System.out.println(Range.closed(1, 4).containsAll(Ints.asList(1, 2, 3))); 
        
        
        
        System.out.println(Range.closed(3, 5).isConnected(Range.open(5, 10))); 
        System.out.println(Range.closed(0, 9).isConnected(Range.closed(3, 4)));
        System.out.println(Range.closed(0, 5).isConnected(Range.closed(3, 9))); 
        System.out.println(Range.open(3, 5).isConnected(Range.open(5, 10))); 
        System.out.println(Range.closed(1, 5).isConnected(Range.closed(6, 10)));
        
	}
}
