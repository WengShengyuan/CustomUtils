package main.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultInfo {
	

	private int stateId; // 状态
	private String errorMsg = ""; // 错误信息
	private List<Map<String, Object>> arrList = new ArrayList<Map<String, Object>>();
	
	

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
	

}
