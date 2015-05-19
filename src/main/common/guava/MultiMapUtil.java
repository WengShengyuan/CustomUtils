package main.common.guava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class MultiMapUtil<K,V> {
	
	Multimap<K, V> multiMap = ArrayListMultimap.create();
	
	public MultiMapUtil(){}
	
	public MultiMapUtil(Map<K,V> map){
		for(K key : map.keySet()){
			multiMap.put(key, map.get(key));
		}
	}
	
	public Multimap<K,V> setMap(Map<K,V> map) {
		for(K key : map.keySet()){
			multiMap.put(key, map.get(key));
		}
		return multiMap;
	}
	
	public boolean put(K key, V value){
		return multiMap.put(key, value);
	}
	
	public boolean putAll(K key, List<V> values){
		return multiMap.putAll(key, values);
	}
	
	public Collection<V> get(K key){
		return multiMap.get(key);
	}
	
	public static void main(String[] args){
		MultiMapUtil<String, String> util = new MultiMapUtil<String, String>();
		util.put("key", "value1");
		util.put("key", "value2");
		List<String> values = new ArrayList<String>();
		values.add("value3");
		values.add("value4");
		util.putAll("key", values);
		
		System.out.println(util.get("key"));
	}

}
