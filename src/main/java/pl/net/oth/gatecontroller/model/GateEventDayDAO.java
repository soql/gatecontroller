package pl.net.oth.gatecontroller.model;

import java.util.List;

public class GateEventDayDAO {
	private String date;
	private List<GateEventDAO> gateEventDAOs;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<GateEventDAO> getGateEventDAOs() {
		return gateEventDAOs;
	}
	public void setGateEventDAOs(List<GateEventDAO> gateEventDAOs) {
		this.gateEventDAOs = gateEventDAOs;
	}
	
}
