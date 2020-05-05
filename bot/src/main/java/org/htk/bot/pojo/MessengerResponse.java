package org.htk.bot.pojo;

public class MessengerResponse {
	
	private String type_code;
	
	private String message_result;
	
	
	public MessengerResponse() {
		
	}
	public MessengerResponse(String type, String message) {
		this.setType_code(type);
		this.setMessage_result(message);
	}
	public String getType_code() {
		return type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getMessage_result() {
		return message_result;
	}
	public void setMessage_result(String message_result) {
		this.message_result = message_result;
	}
	
	

}
