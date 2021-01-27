package jspCommunity.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ResultData {
	private String resultCode;
	private String msg;
	private Object body;
	
	public ResultData(String resultCode, String msg) {
		this(resultCode,msg,null);
	}
	
	public ResultData(String resultCode, String msg, Object body) {
		this.resultCode = resultCode; 
		this.msg = msg; 
		this.body = body; 
	}
	
	public ResultData(String resultCode, String msg, String bodyParam1Key, Object bodyParam1Vlaue) {
		this(resultCode, msg, bodyParam1Key, bodyParam1Vlaue, null, null); 
	}
	
	public ResultData(String resultCode, String msg, String bodyParam1Key, Object bodyParam1Vlaue, String bodyParam2Key, Object bodyParam2Vlaue) {
		this(resultCode, msg, bodyParam1Key, bodyParam1Vlaue , bodyParam2Key, bodyParam2Vlaue , null, null); 
	}
	
	public ResultData(String resultCode, String msg, String bodyParam1Key, Object bodyParam1Vlaue, String bodyParam2Key, Object bodyParam2Vlaue, String bodyParam3Key, Object bodyParam3Vlaue) {
		this(resultCode, msg, bodyParam1Key, bodyParam1Vlaue , bodyParam2Key, bodyParam2Vlaue , bodyParam3Key, bodyParam3Vlaue, null, null); 
	}

	public ResultData(String resultCode, String msg, String bodyParam1Key, Object bodyParam1Vlaue, String bodyParam2Key, Object bodyParam2Vlaue, String bodyParam3Key, Object bodyParam3Vlaue, String bodyParam4Key, Object bodyParam4Vlaue) {
		this(resultCode, msg); 
		
		Map<String, Object> body = new LinkedHashMap<>(); 
		
		if(bodyParam1Key != null) {
			body.put(bodyParam1Key, bodyParam1Vlaue); 
		}
		
		if(bodyParam2Key != null) {
			body.put(bodyParam2Key, bodyParam2Vlaue); 
		}
		
		if(bodyParam3Key != null) {
			body.put(bodyParam3Key, bodyParam3Vlaue); 
		}
		
		if(bodyParam4Key != null) {
			body.put(bodyParam4Key, bodyParam4Vlaue); 
		}
		
		if(body.isEmpty() == false) {
			this.body = body; 
		}
	}
	
	public boolean isFail() {
		return isSuccess() == false; 
	}

	private boolean isSuccess() {
		return resultCode.startsWith("S-"); 
	}
}
