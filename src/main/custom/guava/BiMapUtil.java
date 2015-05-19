package main.custom.guava;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class BiMapUtil<K,V> {
	
	BiMap<K,V> oriMap = HashBiMap.create();
	BiMap<V,K> inverseMap;
	
	public BiMapUtil(){}
	
	public BiMapUtil(Map<K,V> map){
		for(K key : map.keySet()){
			oriMap.put(key, map.get(key));
		}
	}
	
	public BiMap<K,V> setOriMap(Map<K,V> map){
		for(K key : map.keySet()){
			oriMap.put(key, map.get(key));
		}
		return oriMap;
	}
	
	public BiMap<K,V> getOriMap(){
		return oriMap;
	}
	
	public BiMap<V,K> getInverseMap(){
		if(inverseMap == null){
			inverseMap = oriMap.inverse();
		}
		return inverseMap;
	}
	
	public Object getByKey(V obj){
		return oriMap.get(obj);
	}
	
	public Object getByValue(K obj){
		if(inverseMap == null){
			inverseMap = oriMap.inverse();
		}
		return inverseMap.get(obj);
	}
	
	public V putOriMap(K key, V value){
		V r = oriMap.get(key);
		oriMap.put(key, value);
		return r;
	}
	
	public K putInverseMap(V value, K key){
		if(inverseMap == null){
			inverseMap = oriMap.inverse();
		}
		K r = inverseMap.get(value);
		inverseMap.put(value, key);
		return r;
	}
	
	public static void main(String[] args){
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "v1");
		map.put("2", "v2");
		
		BiMapUtil<String,String> util = new BiMapUtil(map);
		
		System.out.println(util.getOriMap());
		System.out.println(util.getInverseMap());
		
	}

}
