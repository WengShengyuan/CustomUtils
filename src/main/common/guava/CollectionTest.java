package main.common.guava;


import java.util.HashSet;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.primitives.Ints;

/**
 * Collection 工具是guava最常用的部分之一
 * @author shengyuan
 *
 */
public class CollectionTest {
	
	public static void main(String[] args) {
		
		/***********************************  ImmutableList  *************************************************/
		ImmutableList<String> of = ImmutableList.of("a", "b", "c", "d");  
		ImmutableMap<String,String> map = ImmutableMap.of("key1", "value1", "key2", "value2");  
		for(String o : of){
			System.out.println(o);
		}
		System.out.println(map.get("key1"));
		
		
		/***********************************  Joiner  *************************************************/
		int[] numbers = { 1, 2, 3, 4, 5 };  
		System.out.println(Joiner.on(";").join(Ints.asList(numbers)));  
		
		
		
		/***********************************  Set  *************************************************/
		HashSet setA = new HashSet();
		setA.add(1); setA.add(2); setA.add(3); setA.add(4);
		HashSet setB = new HashSet();  
		setB.add(3); setB.add(4); setB.add(5); setB.add(6);
		   
		SetView union = Sets.union(setA, setB);  
		System.out.println("union:");  
		for (Object integer : union)  
		    System.out.println(integer);         
		   
		SetView difference = Sets.difference(setA, setB);  
		System.out.println("difference:");  
		for (Object integer : difference)  
		    System.out.println(integer);        
		   
		SetView intersection = Sets.intersection(setA, setB);  
		System.out.println("intersection:");  
		for (Object integer : intersection)  
		    System.out.println(integer);  
		
		
		
		/***********************************  Multimap  *************************************************/
		
		
	}
	
	

}
