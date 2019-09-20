package pl.net.oth.gatecontroller.model;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class GateEventDAO {
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	private String id;
	private String direction;
	private boolean revert;
	private boolean fast;
	private String dateAsString;
	private Double speed;
	private int time;
	public GateEventDAO(GateEvent gateEvent) {
		GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Europe/Warsaw"));
		calendar.setTimeInMillis(Long.parseLong(gateEvent.getId()));
		this.setDirection(gateEvent.getDirection().toLowerCase());
		this.setRevert(gateEvent.getRevert());
		this.setFast(gateEvent.getFast());
		this.setDateAsString(sdf.format(calendar.getTime()));
		this.setSpeed(gateEvent.getSpeed());
		this.setTime(gateEvent.getTime());
		this.setId(gateEvent.getId());
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getDateAsString() {
		return dateAsString;
	}
	public void setDateAsString(String dateAsString) {
		this.dateAsString = dateAsString;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isRevert() {
		return revert;
	}
	public void setRevert(boolean revert) {
		this.revert = revert;
	}
	public boolean isFast() {
		return fast;
	}
	public void setFast(boolean fast) {
		this.fast = fast;
	}
	
	
	
	
}
