package com.drinkingbuddies.drinkingbuddies.controller.Util;

public class Event {
	private String start_time; //"YYYY-MM-DD hh:mm:ss"
	private String event_name;
	
	public Event(String start_time, String event_name) {
		this.start_time = start_time;
		this.event_name = event_name;
	}

	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	
}
