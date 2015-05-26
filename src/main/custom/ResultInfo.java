package main.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultInfo {
	private int stateId; // 状态
	private String errorMsg = ""; // 错误信息
	private List<Map<String, Object>> arrList = new ArrayList<Map<String, Object>>();
	private Map<String, List<Object>> map = new HashMap<String, List<Object>>();

	public Map<String, List<Object>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<Object>> map) {
		this.map = map;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<Map<String, Object>> getArrList() {
		return arrList;
	}

	public void setArrList(List<Map<String, Object>> arrList) {
		this.arrList = arrList;
	}

	public void addObj2Map(String mapKey, Object obj) {
		if (map.containsKey(mapKey)) {
			List<Object> ori = map.get(mapKey);
			ori.add(obj);
			map.put(mapKey, ori);
		} else {
			List<Object> newList = new ArrayList<Object>();
			newList.add(obj);
			map.put(mapKey, newList);
		}
	}
	
	public void addMap2ArrList(Map map){
		arrList.add(map);
	}

}
