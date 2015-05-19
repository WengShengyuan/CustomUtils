package main.common.guava;


import java.util.HashSet;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;
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
		/**
		 * using one key to get multiple value
		 */
		Multimap<String, String> multiMap = ArrayListMultimap.create();
		multiMap.put("key", "value1");
		multiMap.put("key", "value2");
		List<String> values = (List<String>) multiMap.get("key");
		for(String o : values){
			System.out.println(o);
		}
		
		
		/***********************************  Bimap  *************************************************/
		/**
		 * allow to get value by key or get key by value
		 * 
		 * 在使用BiMap时，会要求Value的唯一性。如果value重复了则会抛出错误：java.lang.IllegalArgumentException。
		 */
		BiMap<Integer,String> logfileMap = HashBiMap.create(); 
		logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log"); 
        System.out.println("logfileMap:"+logfileMap); 
        /**
         * inverse方法会返回一个反转的BiMap，但是注意这个反转的map不是新的map对象，它实现了一种视图关联，这样你对于反转后的map的所有操作都会影响原先的map对象。
         */
        BiMap<String,Integer> filelogMap = logfileMap.inverse();
        System.out.println("filelogMap:"+filelogMap);
	}
	
	

}
